import java.util.ArrayList;
import java.util.Collections;

/**
 * Player object that has a sack to save, view, or discard fish.
 * 
 * @author Jay Jr. Cubangbang
 * @since 04/24/2025
 */
public class Player {
	/** name of player. */
	private String name;
	/** sack that holds the collected fish. */
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
		sack.add(fish);
	}

	/**
	 * Removes and returns a fish at the specified index from the sack.
	 * 
	 * @param index The index of the fish to remove.
	 * @return The removed fish.
	 */
	public FishableI_a removeFish(int index) {
		return sack.remove(index);
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