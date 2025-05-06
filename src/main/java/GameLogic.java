import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Handles the core game logic, state, and rules for the Fishing Tournament.
 * Separates game mechanics from the GUI presentation.
 */
public class GameLogic {

	/**
	 * MONTHS_IN_YEAR.
	 */
	private static final int MONTHS_IN_YEAR = 12;
	/**
	 * TURNS_PER_PLAYER.
	 */
	private static final int TURNS_PER_PLAYER = 3;
	/**
	 * POND_CAPACITY.
	 */
	private static final int POND_CAPACITY = 60; 
	/**
	 * MONTH_NAMES.
	 */
	private static final String[] MONTH_NAMES = { "January", "February", "March", "April", "May",
		"June", "July", "August", "September", "October", "November", "December" };

	// --- Game State Variables ---
   /**The fishpond where players can fish.*/
	private ArrayList<FishableI_a> fishPond;
   /**Player 1 as an instance variable.*/
	private Player player1;
   /**Player 2 as an instance variable.*/
	private Player player2;
   /**The current player.*/
	private Player currentPlayer;
   /**The current month of the year.*/
	private int currentMonthIndex;
   /**The amount of attempts the player has left.*/
	private int attemptsLeft;
   /**Determines if the fish has been caught.*/
   private boolean isCaught = false;
   /**Determines if the game is over.*/
	private boolean isGameOver;
   /**Initialises the randomisation variable.*/
	private Random random;

	/**
	 * Constructor for GameLogic. Initializes the random number generator.
	 */
	public GameLogic() {
		random = new Random();
		fishPond = new ArrayList<>();
		isGameOver = false;
	}

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
		fishPond = fillPond();
		growFish(fishPond);

		currentPlayer = player1;
		currentMonthIndex = 0;
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
			if (name == null) {
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
	 * Adds 40 baby I_a to ArrayList. This should match POND_CAPACITY.
	 * 
	 * @return ArrayList with small I_a in it.
	 */
	public static ArrayList<FishableI_a> fillPond() {

		ArrayList<FishableI_a> al = new ArrayList<>();
		//make 60 fish in the pond
		//15 of baby of each species

		for (int i = 0; i < 15; i++) {
			al.add(new MoiLi_i());
		}
		for (int i = 0; i < 15; i++) {
		   al.add(new Oama());
		}
		for (int i = 0; i < 15; i++) {
		   al.add(new Pua_ama());
		}
		for (int i = 0; i < 15; i++) {
			al.add(new Ohua());
		}

		return al;

	} 

	/**
	 * Runs arraylist of I_a through 24 eating/growing cycles.
	 * 
	 * @param al the list of fish.
	 */
	public static void growFish(ArrayList<FishableI_a> al) {
		FishableI_a ia;
		boolean debug = false; 

		for (int m = 0; m < 24; m++) {
			for (int i = 0; i < al.size(); i++) {
				if (debug) {
					System.out.println("==========================");
					System.out.println("Feeding  the fish" + i);
					System.out.println("==========================\n");
				}
				ia = al.get(i);

				if (debug) {
					System.out.println(ia);
				}

				try {
					//use EnglishName because doesn't change with size
					if (ia.getEnglishName().equals("Striped Mullet")) {
						ia.eat("algae");
					} else if (ia.getEnglishName().equals("Goatfish")
							|| ia.getEnglishName().equals("Yellowfin Goatfish")
							|| ia.getEnglishName().equals("Square-spot Goatfish")) {
						ia.eat("worms");
					} else if (ia.getEnglishName().equals("Parrotfish")) {
						ia.eat("algae");

					} else if (ia.getEnglishName().equals("Six-fingered threadfin")) {
						ia.eat("crustaceans");
					}

					if (debug) {
						System.out.println(
								"****After eat and grow: " + ia.getName() + ": " + ia.getLength() + "\n");
					}
				} catch (FishSizeException fe) {
					if (debug) {
						System.out.println(fe.getMessage());
					}

					try {
						ia = ia.levelUp();
						// Replace with upgraded fish
						al.set(i, ia);

						if (debug) {
							System.out.println("Fish leveled up: " + ia.getName());
						}
					} catch (FishSizeException e) {
						// Final form (like Uhu) can't level up — just print a message
						if (debug) {
							System.out.println("This fish can't level up anymore.");
						}
						al.set(i, ia);
					}
				}
			}
		} 

		if (debug) {
			System.out.println("Fish growth completed.\n");
		}

	} 

	/**
	 * Advances the game to the next player's turn. If both players have taken their
	 * turns, advances to the next month.
	 */
	public void nextTurn() {
		if (isGameOver) {
			return;
		}

		// Switch player
		if (currentPlayer == player1) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
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
			growFish(fishPond);
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

		int hookSpot = random.nextInt(POND_CAPACITY);

		if (hookSpot >= fishPond.size()) {
			return null;
		} else {
			// Hooked something!
			FishableI_a potentialCatch = fishPond.get(hookSpot);
			isCaught = random.nextBoolean();

			if (isCaught) {
				// Successfully caught, remove from pond temporarily
				fishPond.remove(hookSpot);
				return potentialCatch;
			} else {
				return null;
			}
		}
	}

	/**
	 * Processes the player's decision to keep a caught fish. Checks legality (size,
	 * season, method) and updates sack/pond. Applies penalty if illegal.
	 *
	 * @param fish The fish the player decided to keep.
	 * @param catchMethod The method used to catch the fish (e.g., "pole", "net").
	 * @return A String message indicating the outcome (kept legally, penalty
	 *         applied).
	 */
	public String keepFish(FishableI_a fish, String catchMethod) {
		if (fish == null) {
			return "Error: No fish to keep.";
		}

		boolean legal = checkLegality(fish, catchMethod);

		if (legal) {
			currentPlayer.addFish(fish);
			// Fish was already removed from pond in attemptCatch, so no action needed here
			return "Kept legal fish: " + fish.getName() + " ("
					+ String.format("%.2f", fish.getLength()) + " inches)";
		} else {
			// Illegal catch! Confiscate sack and return the illegal fish to the pond.
			currentPlayer.clearSack();
			fishPond.add(fish);
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
		if (fish == null) {
			return "Error: No fish to release.";
		}

		fishPond.add(fish);
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
		if (fish == null) {
			return false;
		}

		// Check 1: Is it the right season
		if (!fish.isInSeason(MONTH_NAMES[currentMonthIndex])) {
			return false;
		}

		// Check 2: Is it legal size? (Only applies if it's a game fish)
		if (fish.isGamefish() && !fish.isLegalSize()) {
			if (!fish.getEnglishName().contains("Goatfish")) {
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
		if (fish == null) {
			return "Unknown reason.";
		}

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

		return "Unknown legality issue."; 
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
			fishPond.add(fishToReturn);
			return "Returned " + fishToReturn.getName() + " to the pond.";
		} else {
			return "Error removing fish from sack."; 
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
	}

	/**
	 * getCurrentPlayer.
	 * @return Player.
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * getCurrentPlayerName.
	 * @return String.
	 */
	public String getCurrentPlayerName() {
		return currentPlayer != null ? currentPlayer.getName() : "N/A";
	}

	/**
	 * getCurrentMonthName.
	 * @return String.
	 */
	public String getCurrentMonthName() {
		if (currentMonthIndex >= 0 && currentMonthIndex < MONTH_NAMES.length) {
			return MONTH_NAMES[currentMonthIndex];
		}
		return "Invalid Month";
	}

	/**
	 * getAttemptsLeft.
	 * @return int
	 */
	public int getAttemptsLeft() {
		return attemptsLeft;
	}

	/**
	 * isGameOver.
	 * @return boolean.
	 */
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
			// Winner calculation needed
			return "Game Over! Calculating results...";
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
		if (currentPlayer == null) {
			return "No current player.";
		}
		return currentPlayer.getSackContentsSorted();
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
			return new ArrayList<>(); 
		}
		return currentPlayer.getSack();
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
				"Penalty: Keeping an illegal fish (wrong size, season, or method) "
				//
				+ "confiscates your entire sack!\n\n");

		// Add details for each fish family (condensed)
		rules.append("Mullet ('Anae): Legal >= 11in, Season Apr-Nov, Methods: net, pole.\n");
		rules.append(
				"Parrotfish (Uhu): Legal >= 12in, Season All Year, Methods: throw net, spear, pole.\n");
		rules.append(
				"Goatfish (Weke): No Min Size, Season All Year, Methods: net, trap, pole. "
				+ "(Bag limit 50 - not enforced in game yet).\n");
		rules.append("Threadfin (Moi): Legal >= 11in, Season Sep-May, Methods: net, pole.\n\n");

		rules.append("Always check size, season (current month: ").append(getCurrentMonthName())
				.append("), and allowed methods before keeping!\n");

		return rules.toString();
	}

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
		Collections.sort(sack, Collections.reverseOrder());

		double totalLength = 0.0;
		int count = 0;
		for (FishableI_a fish : sack) {
			if (count < 3) {
				totalLength += fish.getLength();
				count++;
			} else {
				break;
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