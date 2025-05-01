import javax.swing.JOptionPane; // For getting player names
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Handles the core game logic, state, and rules for the Fishing Tournament.
 * Separates game mechanics from the GUI presentation.
 */
public class GameLogic {

	// --- Constants ---
	private static final int MONTHS_IN_YEAR = 12;
	private static final int INITIAL_GROWTH_MONTHS = 24; // 2 years initial growth
	private static final int TURNS_PER_PLAYER = 3;
	private static final int POND_CAPACITY = 40; // Used for random hooking spots
	private static final String[] MONTH_NAMES = { "January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November", "December" };

	// --- Game State Variables ---
	private ArrayList<FishableI_a> fishPond;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private int currentMonthIndex; // 0 = January, 11 = December
	private int attemptsLeft;
	private boolean isGameOver;
	private Random random;

	/**
	 * Constructor for GameLogic. Initializes the random number generator.
	 */
	public GameLogic() {
		random = new Random();
		fishPond = new ArrayList<>();
		isGameOver = false;
		// Other variables will be initialized in setupGame()
	}

	// --- Setup Methods ---

	/**
	 * Initializes the entire game: gets player names, creates players, fills the
	 * pond, grows fish initially, and sets the starting state.
	 */
	public void setupGame() {
		// Get Player Names using simple dialogs
		String p1Name = getPlayerName("Enter name for Player 1:");
		String p2Name = getPlayerName("Enter name for Player 2:");

		player1 = new Player(p1Name);
		player2 = new Player(p2Name);

		// Initialize game state
		fillPond();
		initialGrowFish(INITIAL_GROWTH_MONTHS); // Grow fish for 2 years

		currentPlayer = player1; // Player 1 starts
		currentMonthIndex = 0; // Start in January
		attemptsLeft = TURNS_PER_PLAYER;
		isGameOver = false;
	}

	/**
	 * Helper method to get a non-empty player name using JOptionPane.
	 * 
	 * @param prompt The message to display to the user.
	 * @return A valid (non-empty) player name.
	 */
	private String getPlayerName(String prompt) {
		String name = "";
		while (name == null || name.trim().isEmpty()) {
			name = JOptionPane.showInputDialog(null, prompt, "Player Setup",
					JOptionPane.QUESTION_MESSAGE);
			if (name == null) { // Handle cancel button
				// You might want to exit or provide a default name
				System.out.println("Player setup cancelled. Exiting.");
				System.exit(0);
			} else if (name.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Player name cannot be empty.", "Input Error",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		return name.trim();
	}

	/**
	 * Fills the fish pond with initial baby fish.
	 */
	private void fillPond() {
		fishPond.clear(); // Ensure pond is empty before filling
		// Add 10 of each baby fish type
		for (int i = 0; i < 10; i++) {
			fishPond.add(new MoiLi_i());
			fishPond.add(new Oama());
			fishPond.add(new Pua_ama());
			fishPond.add(new Ohua());
		}
		System.out.println("Initial pond size: " + fishPond.size()); // Debugging
	}

	/**
	 * Simulates fish growth for a specified number of months.
	 * 
	 * @param months The number of months to simulate growth.
	 */
	private void initialGrowFish(int months) {
		System.out.println("Simulating initial fish growth for " + months + " months...");
		for (int m = 0; m < months; m++) {
			growFishSingleMonth();
		}
		System.out.println("Initial growth complete.");
	}

	// --- Core Game Mechanics ---

	/**
	 * Simulates all fish in the pond eating and potentially growing/leveling up for
	 * a single month cycle.
	 */
	private void growFishSingleMonth() {
		// Iterate backwards to avoid issues when replacing fish after level up
		for (int i = fishPond.size() - 1; i >= 0; i--) {
			FishableI_a fish = fishPond.get(i);
			if (fish == null)
				continue; // Should not happen, but safe check

			try {
				// Determine food type based on fish type (simplified)
				String food = "plankton"; // Default food
				String engName = fish.getEnglishName();
				if (engName.contains("Mullet")) {
					food = "algae";
				} else if (engName.contains("Goatfish")) {
					food = "worms";
				} else if (engName.contains("Parrotfish")) {
					food = "algae";
				} else if (engName.contains("threadfin")) {
					food = "crustaceans";
				}
				fish.eat(food); // Fish eats and grows

			} catch (FishSizeException fse) {
				// Fish needs to level up
				try {
					FishableI_a biggerFish = fish.levelUp();
					fishPond.set(i, biggerFish); // Replace with the leveled-up fish
					// System.out.println("A fish leveled up to: " + biggerFish.getName()); // Debug
				} catch (FishSizeException finalForm) {
					// Fish is already at its maximum size/form, no change needed
					// System.out.println(fish.getName() + " is at max size."); // Debug
				}
			} catch (Exception e) {
				System.err.println("Error growing fish: " + fish.getName() + " - " + e.getMessage());
				// Handle other potential exceptions during eat/grow if necessary
			}
		}
	}

	/**
	 * Advances the game to the next player's turn. If both players have taken their
	 * turns, advances to the next month.
	 */
	public void nextTurn() {
		if (isGameOver)
			return;

		// Switch player
		if (currentPlayer == player1) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
			// Both players have had a turn, advance the month
			nextMonth();
		}

		// Reset attempts for the new turn, unless the game just ended
		if (!isGameOver) {
			attemptsLeft = TURNS_PER_PLAYER;
		}
	}

	/**
	 * Advances the game to the next month. Grows the fish and checks if the game
	 * has ended.
	 */
	private void nextMonth() {
		currentMonthIndex++;
		if (currentMonthIndex >= MONTHS_IN_YEAR) {
			isGameOver = true;
			System.out.println("12 months passed. Game Over!");
			// Winner calculation will be done later
		} else {
			System.out.println("Advancing to month: " + getCurrentMonthName());
			growFishSingleMonth(); // Grow fish at the start of the new month
		}
	}

	/**
	 * Simulates a player attempting to catch a fish. Handles random chance of
	 * hooking, catching, or getting away. Does NOT decrement attemptsLeft here;
	 * that happens after the action resolves.
	 *
	 * @return The FishableI_a caught, or null if no fish was caught or it got away.
	 */
	public FishableI_a attemptCatch() {
		if (fishPond.isEmpty()) {
			System.out.println("The pond is empty!");
			return null;
		}

		int hookSpot = random.nextInt(POND_CAPACITY); // Simulate casting to a random spot

		if (hookSpot >= fishPond.size()) {
			// Cast into an empty part of the pond
			return null;
		} else {
			// Hooked something!
			FishableI_a potentialCatch = fishPond.get(hookSpot);
			boolean actuallyCaught = random.nextBoolean(); // 50% chance to land it vs. it getting away

			if (actuallyCaught) {
				// Successfully caught, remove from pond temporarily
				// It will be permanently removed if kept legally
				fishPond.remove(hookSpot);
				return potentialCatch;
			} else {
				// Fish got away
				return null; // Represent "got away" same as "didn't catch" for simplicity here
			}
		}
	}

	/**
	 * Processes the player's decision to keep a caught fish. Checks legality (size,
	 * season, method) and updates sack/pond. Applies penalty if illegal.
	 *
	 * @param fish        The fish the player decided to keep.
	 * @param catchMethod The method used to catch the fish (e.g., "pole", "net").
	 * @return A String message indicating the outcome (kept legally, penalty
	 *         applied).
	 */
	public String keepFish(FishableI_a fish, String catchMethod) {
		if (fish == null)
			return "Error: No fish to keep."; // Should not happen

		boolean legal = checkLegality(fish, catchMethod);

		if (legal) {
			currentPlayer.addFish(fish);
			// Fish was already removed from pond in attemptCatch, so no action needed here
			return "Kept legal fish: " + fish.getName() + " ("
					+ String.format("%.2f", fish.getLength()) + " inches)";
		} else {
			// Illegal catch! Confiscate sack and return the illegal fish to the pond.
			currentPlayer.clearSack();
			fishPond.add(fish); // Put the illegal fish back
			// Construct a more specific reason (optional but helpful)
			String reason = getIllegalityReason(fish, catchMethod);
			return "ILLEGAL CATCH! " + reason + "\nAll fish confiscated!";
		}
	}

	/**
	 * Processes the player's decision to release a caught fish. Adds the fish back
	 * to the pond.
	 *
	 * @param fish The fish the player decided to release.
	 * @return A String message indicating the outcome.
	 */
	public String releaseFish(FishableI_a fish) {
		if (fish == null)
			return "Error: No fish to release.";

		fishPond.add(fish); // Put the released fish back into the pond
		return "Released fish: " + fish.getName();
	}

	/**
	 * Checks if a fish is legal to keep based on size, season, and catch method.
	 *
	 * @param fish        The fish to check.
	 * @param catchMethod The method used.
	 * @return true if legal, false otherwise.
	 */
	private boolean checkLegality(FishableI_a fish, String catchMethod) {
		if (fish == null)
			return false;

		// Check 1: Is it the right season?
		if (!fish.isInSeason(MONTH_NAMES[currentMonthIndex])) {
			return false;
		}

		// Check 2: Is it legal size? (Only applies if it's a game fish)
		// Note: Oama/Weke is always legal size according to rules.
		// The isLegalSize() method should handle the specific lengths.
		if (fish.isGamefish() && !fish.isLegalSize()) {
			// Special case: Oama/Weke has no min size, so isLegalSize might always be true for it.
			// Let's rely on the implementation within the fish classes.
			// If a fish *can* be a game fish but isn't currently legal size, it's illegal.
			// If a fish is *never* a game fish (e.g., too small Moi-li'i), isGameFish() is false.
			// If a fish *is* a game fish and *is* legal size, this check passes.
			if (!fish.getEnglishName().contains("Goatfish")) { // Oama/Weke family is always legal size
				return false;
			}
		}

		// Check 3: Was a valid catch method used for this fish?
		boolean validMethod = false;
		for (String allowedMethod : fish.getCatchMethods()) {
			if (allowedMethod.equalsIgnoreCase(catchMethod)) {
				validMethod = true;
				break;
			}
		}
		if (!validMethod) {
			return false;
		}

		// If all checks pass, it's legal
		return true;
	}

	/**
	 * Determines the specific reason why a fish catch was illegal. Assumes
	 * checkLegality(fish, catchMethod) already returned false.
	 *
	 * @param fish        The fish that was caught illegally.
	 * @param catchMethod The method used.
	 * @return A string explaining the reason.
	 */
	private String getIllegalityReason(FishableI_a fish, String catchMethod) {
		if (fish == null)
			return "Unknown reason.";

		if (!fish.isInSeason(MONTH_NAMES[currentMonthIndex])) {
			return fish.getName() + " is out of season in " + getCurrentMonthName() + ".";
		}
		if (fish.isGamefish() && !fish.isLegalSize() && !fish.getEnglishName().contains("Goatfish")) {
			return fish.getName() + " is undersized (" + String.format("%.2f", fish.getLength())
					+ " inches).";
		}
		boolean validMethod = false;
		for (String allowedMethod : fish.getCatchMethods()) {
			if (allowedMethod.equalsIgnoreCase(catchMethod)) {
				validMethod = true;
				break;
			}
		}
		if (!validMethod) {
			return "Catch method '" + catchMethod + "' is not allowed for " + fish.getName() + ".";
		}

		return "Unknown legality issue."; // Fallback
	}

	/**
	 * Allows the current player to throw back a fish from their sack.
	 *
	 * @param index The index of the fish in the player's sack to throw back.
	 * @return A message indicating success or failure.
	 */
	public String throwBackFish(int index) {
		if (currentPlayer.getSackSize() == 0) {
			return "Your sack is empty.";
		}
		if (index < 0 || index >= currentPlayer.getSackSize()) {
			return "Invalid index.";
		}

		FishableI_a fishToReturn = currentPlayer.removeFish(index);
		if (fishToReturn != null) {
			fishPond.add(fishToReturn); // Add it back to the general pond
			return "Returned " + fishToReturn.getName() + " to the pond.";
		} else {
			return "Error removing fish from sack."; // Should not happen if index is valid
		}
	}

	/**
	 * Decrements the number of attempts left for the current player. Should be
	 * called after a catch attempt action is resolved (kept, released, missed, got
	 * away).
	 */
	public void useAttempt() {
		if (attemptsLeft > 0) {
			attemptsLeft--;
		}
		// Check if turn should end automatically after using the last attempt
		// if (attemptsLeft <= 0 && !isGameOver) {
		//     nextTurn(); // Or let the GUI trigger next turn explicitly
		// }
	}

	// --- Getter Methods for GUI ---

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public String getCurrentPlayerName() {
		return currentPlayer != null ? currentPlayer.getName() : "N/A";
	}

	public String getCurrentMonthName() {
		if (currentMonthIndex >= 0 && currentMonthIndex < MONTH_NAMES.length) {
			return MONTH_NAMES[currentMonthIndex];
		}
		return "Invalid Month";
	}

	public int getAttemptsLeft() {
		return attemptsLeft;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	/**
	 * Gets a formatted string representing the current game status.
	 * 
	 * @return Status string (e.g., "Player 1's Turn - January - Attempts Left: 3").
	 */
	public String getStatusString() {
		if (isGameOver) {
			return "Game Over! Calculating results..."; // Winner calculation needed
		}
		return getCurrentPlayerName() + "'s Turn - " + getCurrentMonthName() + " - Attempts Left: "
				+ attemptsLeft;
	}

	/**
	 * Gets a formatted string listing the contents of the current player's sack.
	 * Sorted by length, longest first.
	 * 
	 * @return String representation of the sack, or "Sack is empty."
	 */
	public String getCurrentPlayerSackInfo() {
		if (currentPlayer == null)
			return "No current player.";
		return currentPlayer.getSackContentsSorted(); // Assumes Player class has this method
	}

	/**
	 * Gets the actual list of fish in the current player's sack. Needed for
	 * displaying options when throwing back fish.
	 * 
	 * @return ArrayList of FishableI_a, or an empty list if no player or empty
	 *         sack.
	 */
	public ArrayList<FishableI_a> getCurrentPlayerSackList() {
		if (currentPlayer == null) {
			return new ArrayList<>(); // Return empty list
		}
		return currentPlayer.getSack(); // Assumes Player class has getSack()
	}

	/**
	 * Generates a summary of the fishing rules.
	 * 
	 * @return A string containing the rules summary.
	 */
	public String getRulesSummary() {
		// This can be expanded significantly
		StringBuilder rules = new StringBuilder("--- Fishing Rules Summary ---\n");
		rules.append("Goal: Catch the 3 longest fish over 12 months.\n");
		rules.append("Turns: 3 attempts per player per month.\n");
		rules.append(
				"Penalty: Keeping an illegal fish (wrong size, season, or method) confiscates your entire sack!\n\n");

		// Add details for each fish family (condensed)
		rules.append("Mullet ('Anae): Legal >= 11in, Season Apr-Nov, Methods: net, pole.\n");
		rules.append(
				"Parrotfish (Uhu): Legal >= 12in, Season All Year, Methods: throw net, spear, pole.\n");
		rules.append(
				"Goatfish (Weke): No Min Size, Season All Year, Methods: net, trap, pole. (Bag limit 50 - not enforced in game yet).\n");
		rules.append("Threadfin (Moi): Legal >= 11in, Season Sep-May, Methods: net, pole.\n\n");

		rules.append("Always check size, season (current month: ").append(getCurrentMonthName())
				.append("), and allowed methods before keeping!\n");

		return rules.toString();
	}

	// --- Winner Calculation (Placeholder for Phase 4) ---

	/**
	 * Calculates the winner based on the sum of the lengths of the top 3 fish.
	 * 
	 * @return A string declaring the winner and scores.
	 */
	public String getGameResult() {
		if (!isGameOver) {
			return "Game is still in progress.";
		}

		double p1Score = calculateTop3Score(player1);
		double p2Score = calculateTop3Score(player2);

		String result = "--- Final Results ---\n";
		result += player1.getName() + "'s Top 3 Fish Length Sum: " + String.format("%.2f", p1Score)
				+ " inches\n";
		result += player2.getName() + "'s Top 3 Fish Length Sum: " + String.format("%.2f", p2Score)
				+ " inches\n\n";

		if (p1Score > p2Score) {
			result += player1.getName() + " wins!";
		} else if (p2Score > p1Score) {
			result += player2.getName() + " wins!";
		} else {
			result += "It's a tie!";
		}
		return result;
	}

	/**
	 * Helper method to calculate the score for a player (sum of top 3 fish
	 * lengths).
	 * 
	 * @param player The player whose score to calculate.
	 * @return The total length of the top 3 fish (or fewer if they have less than
	 *         3).
	 */
	private double calculateTop3Score(Player player) {
		ArrayList<FishableI_a> sack = player.getSack();
		if (sack.isEmpty()) {
			return 0.0;
		}
		// Sort sack by length descending (longest first)
		Collections.sort(sack, Collections.reverseOrder()); // Uses the compareTo in FishableI_a

		double totalLength = 0.0;
		int count = 0;
		for (FishableI_a fish : sack) {
			if (count < 3) {
				totalLength += fish.getLength();
				count++;
			} else {
				break; // Stop after the top 3
			}
		}
		return totalLength;
	}

	/**
	 * Gets the current number of fish in the pond.
	 * 
	 * @return The size of the fishPond ArrayList.
	 */
	public int getPondSize() {
		return fishPond.size();
	}

	/**
	 * Gets player 1.
	 * 
	 * @return Player object for player 1.
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * Gets player 2.
	 * 
	 * @return Player object for player 2.
	 */
	public Player getPlayer2() {
		return player2;
	}

}