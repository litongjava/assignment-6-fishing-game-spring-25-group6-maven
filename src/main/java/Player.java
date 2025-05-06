import java.util.ArrayList;
import java.util.Collections;

/**
 * Player object that has a sack to save, view, or discard fish.
 */
public class Player {
	/**
	 * name.
	 */
	private String name;
	/**
	 * sack.
	 */
	private ArrayList<FishableI_a> sack;

	/**
	 * Makes a player with the given name.
	 * 
	 * @param name The name of the player
	 */
	public Player(String name) {
		this.name = name;
		this.sack = new ArrayList<>();
	}

	/**
	 * Adds a fish to the player's sack.
	 * 
	 * @param fish The fish to be added.
	 */
	public void addFish(FishableI_a fish) {
		if (fish != null) {
			sack.add(fish);
		}
	}

	/**
	 * Removes and returns a fish at the specified index from the sack. Performs
	 * index bounds checking.
	 * 
	 * @param index The index of the fish to remove.
	 * @return The removed fish, or null if the index is invalid.
	 */
	public FishableI_a removeFish(int index) {
		if (index >= 0 && index < sack.size()) {
			return sack.remove(index);
		}
		System.err.println("Error: Attempted to remove fish at invalid index " + index);
		return null; // Return null for invalid index
	}

	/** Clears all the fish from the sack. */
	public void clearSack() {
		sack.clear();
	}

	/**
	 * Returns the name of the player.
	 * 
	 * @return The player's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the number of fish in the sack.
	 * 
	 * @return The size of the sack.
	 */
	public int getSackSize() {
		return sack.size();
	}

	/**
	 * Returns the list of fish currently in the sack. Returns a copy to prevent
	 * external modification of the original list.
	 * 
	 * @return A copy of the sack ArrayList.
	 */
	public ArrayList<FishableI_a> getSack() {
		// Return a copy to prevent direct modification of the internal list
		return new ArrayList<>(sack);
	}

	/**
	 * Generates a string representation of the sack contents, sorted by length
	 * (longest first).
	 * 
	 * @return Formatted string of sack contents.
	 */
	public String getSackContentsSorted() {
		if (sack.isEmpty()) {
			return "Sack is empty.";
		}

		// Create a copy and sort it (descending order based on FishableI_a compareTo)
		ArrayList<FishableI_a> sortedSack = new ArrayList<>(sack);
		Collections.sort(sortedSack, Collections.reverseOrder()); // Longest first

		StringBuilder sb = new StringBuilder("--- Your Sack (Longest First) ---\n");
		for (int i = 0; i < sortedSack.size(); i++) {
			FishableI_a fish = sortedSack.get(i);
			// Include index for potential "throw back" reference
			sb.append(i).append(": ").append(fish.toString()).append("\n");
		}
		return sb.toString();
	}

	/**
	 * Provides a simple string representation of the Player.
	 * 
	 * @return The player's name.
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * Displays the contents of the sack, sorted from longest to shortest fish.
	 */
	public void viewSack() {
		if (sack.isEmpty()) {
			System.out.println("Your sack is empty.");
			return;
		}

		// Sort from longest to shortest
		ArrayList<FishableI_a> sorted = new ArrayList<>(sack);
		Collections.sort(sorted);
		System.out.println("Your sack (longest to shortest):");
		for (FishableI_a fish : sorted) {
			System.out.println(fish);
		}
	}

	/**
	 * Displays the contents of the sack with their corresponding indexes.
	 */
	public void viewSackWithIndex() {
		if (sack.isEmpty()) {
			System.out.println("Your sack is empty.");
			return;
		}

		for (int i = 0; i < sack.size(); i++) {
			System.out.println(i + ": " + sack.get(i));
		}
	}
}