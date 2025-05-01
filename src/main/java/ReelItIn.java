import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Game file for fishing game.
 * 
 * @author Holly Kaneshiro, Tong Li, Jay Jr. Cubangbang
 * @since 04/17/2025
 */
public class ReelItIn {
	/** Maximum pond capacity for random hooking. */
	static final int POND_CAPACITY = 40;
	/** The 12 months of the year, also the duration of the game in months. */
	static final int MONTHS_IN_YEAR = 12;
	/** The amount of turns allowed per player within the month. */
	static final int TURNS = 3;
	//Gimmickier, non-mandatory constants.
	/** Number of unique messages a user could see. */
	static final int MSG_LIM = 5;
	/** Line of equal signs. Used for multiple purposes. */
	static final String EQ_SIGN_LINE = "====================";
	/** First part of wave pattern. */
	static final String WAVE_PATTERN = "^^^^^^^^^^^^^^^^^^^^";
	/** Second part of wave pattern. */
	static final String SECOND_WAVE_PATTERN = "vvvvvvvvvvvvvvvvvvvv";
	/**
	 * MONTH_NAMES.
	 */
	public static final String[] MONTH_NAMES = { "January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November", "December" };

	/**
	 * main method.
	 * 
	 * @param args not used.
	 */
	public static void main(String[] args) {
		//ArrayList to simulate a fishpond/lokoi'a
		ArrayList<FishableI_a> lokoiA = new ArrayList<>();
		boolean debug = false;

		lokoiA = ReelItIn.fillPond();
		if (debug) {
			System.out.println(lokoiA);
		}

		//Let fish in pond grow some
		//eat and grow for 24 months
		ReelItIn.growFish(lokoiA);

		//now lets open the pond for fishing!
		ReelItIn.lawaiA(lokoiA);

	} //close main

	/**
	 * Adds 40 baby I_a to ArrayList. This should match POND_CAPACITY.
	 * 
	 * @return ArrayList with small I_a in it.
	 */
	public static ArrayList<FishableI_a> fillPond() {

		ArrayList<FishableI_a> al = new ArrayList<>();
		//make 40 fish in the pond
		//10 of baby of each species

		for (int i = 0; i < 10; i++) {
			al.add(new MoiLi_i());
		}
		for (int i = 0; i < 10; i++) {
			al.add(new Oama());
		}
		for (int i = 0; i < 10; i++) {
			al.add(new Pua_ama());
		}
		for (int i = 0; i < 10; i++) {
			al.add(new Ohua());
		}

		//for testing empty locations
		//   for (int i = 0; i < 30; i++) {
		//          al.remove(i);
		//       }
		return al;

	} //fillPond method

	/**
	 * Adds patterns without exceeding Checkstyle limit.
	 * 
	 * @param text The text to be between ASCII.
	 */
	public static void wavePrint(String text) {
		System.out.print(WAVE_PATTERN + SECOND_WAVE_PATTERN + WAVE_PATTERN);
		System.out.println(SECOND_WAVE_PATTERN + WAVE_PATTERN + SECOND_WAVE_PATTERN);
		System.out.println(EQ_SIGN_LINE + EQ_SIGN_LINE + EQ_SIGN_LINE + EQ_SIGN_LINE + EQ_SIGN_LINE
				+ EQ_SIGN_LINE);
		System.out.println(text);
		System.out.print(WAVE_PATTERN + SECOND_WAVE_PATTERN + WAVE_PATTERN);
		System.out.println(SECOND_WAVE_PATTERN + WAVE_PATTERN + SECOND_WAVE_PATTERN);
		System.out.println(EQ_SIGN_LINE + EQ_SIGN_LINE + EQ_SIGN_LINE + EQ_SIGN_LINE + EQ_SIGN_LINE
				+ EQ_SIGN_LINE);

	} //end of wavePrint.

	/**
	 * Runs arraylist of I_a through 24 eating/growing cycles.
	 * 
	 * @param al the list of fish.
	 */
	public static void growFish(ArrayList<FishableI_a> al) {
		FishableI_a ia;
		boolean debug = false; //turn printing on and off

		for (int m = 0; m < 24; m++) {
			//all fish in the pond
			for (int i = 0; i < al.size(); i++) {
				//loop over array 
				if (debug) {
					System.out.println("==========================");
					System.out.println("Feeding  the fish" + i);
					System.out.println("==========================\n");
				}
				ia = al.get(i);

				if (debug) {
					System.out.println(ia);
				}

				try { //must check for need to levelUp
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
		} // close m loop

		if (debug) {
			System.out.println("Fish growth completed.\n");
		}

	} //close growFish method

	/**
	 * simulate fishing or Lawai'a over the course of 12 months. 2 players take
	 * turns to fish.
	 * 
	 * @param fishPond arrayList of fish to be caught
	 */
	public static void lawaiA(ArrayList<FishableI_a> fishPond) {
		Scanner input = new Scanner(System.in);
		Random rand = new Random();
		boolean isCaught = false;
		// Create two players
		Player p1 = createPlayer(input, "Enter name for Player 1: ");
		Player p2 = createPlayer(input, "Enter name for Player 2: ");

		// Loop through 12 months
		for (int i = 1; i <= 12; i++) {
			System.out.println("\nMonth: " + MONTH_NAMES[i - 1]);

			// Each player takes a turn
			Player[] players = { p1, p2 };
			for (int j = 0; j < players.length; j++) {
				Player currentPlayer = players[j];
				System.out.println("\n" + currentPlayer.getName() + ", it's your turn!");

				int attempts = 0;
				while (attempts < 3) {
					int choice = choiceMenu(input);

					switch (choice) {
						case 1:
							// Try to catch a fish
							int hookSpot = rand.nextInt(POND_CAPACITY);
							isCaught = rand.nextBoolean();
							if (hookSpot >= fishPond.size() || !isCaught) {
								wavePrint("You didn't catch anything.");
							} else {
								FishableI_a fish = fishPond.get(hookSpot);
								System.out.println("You have hooked a fish!");

								// Ask player to choose a catch method
								int methodChoice = -1;
								String catchMethod = "";
								while (true) {
									System.out.println("\nChoose your catch method:");
									System.out.println("1. Pole");
									System.out.println("2. Net");
									System.out.println("3. Spear");
									System.out.println("4. Trap");
									System.out.println("5. Throw Net");
									try {
										methodChoice = Integer.parseInt(input.nextLine());
									} catch (NumberFormatException e) {
										System.out.println(
												"Invalid input." + "Please choose a valid catch method using"
														+ " numbers between 1 and 5.");
										continue; // Re-ask for the catch method
									}
									switch (methodChoice) {
										case 1:
											catchMethod = "pole";
											break;
										case 2:
											catchMethod = "net";
											break;
										case 3:
											catchMethod = "spear";
											break;
										case 4:
											catchMethod = "trap";
											break;
										case 5:
											catchMethod = "throw net";
											break;
										default:
											System.out.println(
													"Invalid choice." + "Please choose a valid catch method.");
											continue; // Ask again if invalid
									}
									break; // Exit loop once a valid choice is made
								}
								if (isCaught) {
									System.out.println("You have caught a fish!");
									System.out.println(fish);

									// Ask if the player wants to keep the fish
									String keep = "";
									while (true) {
										System.out.print("\nDo you want to keep this fish? (y/n) ");
										System.out.print("\nOr see the fishing rules? (r)\n ");
										keep = input.nextLine().toLowerCase();

										switch (keep) {
											case "y":
												if (fish.isLegalSize()) { // Basic legality check
													currentPlayer.addFish(fish);
													fishPond.remove(hookSpot);
													wavePrint("You kept the fish. It was legal.");
												} else {
													wavePrint("You kept an illegal fish!"
															+ "\nAll your fish were confiscated! D:");
													currentPlayer.clearSack();
												}
												break;
											case "n":
												wavePrint("You released the fish.");
												break;
											case "r":
												printFishInfo();
												continue;
											default:
												System.out.println("Invalid input. Please enter 'y' or 'n'.");
												continue; // If invalid input, keep asking
										}
										break; // Exit loop once a valid choice is made
									}
								} else {
									wavePrint("Your fish got away...");
								}
							}
							attempts++;
							break;
						case 2:
							printFishInfo();
							break;

						case 3:
							currentPlayer.viewSack();
							break;

						case 4:
							currentPlayer.viewSackWithIndex();
							if (currentPlayer.getSackSize() > 0) {
								System.out.print("Enter the index of the fish to throw back: ");
								int index = Integer.parseInt(input.nextLine());
								FishableI_a returnedFish = currentPlayer.removeFish(index);
								fishPond.add(returnedFish);
								System.out.println("You threw the fish back into the pond.");
							} else {
								System.out.println("You have no fish to throw back.");
							}
							break;

						default:
							System.out.println("Invalid option. Please try again.");
							break;
					}
				}
			}
			// Fish grow at the end of the month
			growFish(fishPond);
			System.out.println("The fish in the pond have grown!");
		}

		System.out.println("\nGAME OVER!");
	}

	/**
	 * Creates and save player name.
	 * 
	 * @param input  what user inputs
	 * @param prompt asks to enter name
	 * @return player name
	 */
	private static Player createPlayer(Scanner input, String prompt) {
		String name = "";
		while (name.trim().isEmpty()) {
			System.out.print(prompt);
			name = input.nextLine();
			if (name.trim().isEmpty()) {
				System.out.println("Player name cannot be empty.");
			}
		} //end while loop
		return new Player(name);
	} //end createPlayer method

	/**
	 * Menu of what the player will do.
	 * 
	 * @param input what user inputs
	 * @return choice of what to do
	 */

	private static int choiceMenu(Scanner input) {
		int choice = -1;
		while (choice < 1 || choice > 4) {
			System.out.println("\n1. Try to catch a fish");
			System.out.println("2. View fishing rules");
			System.out.println("3. View sack of fish");
			System.out.println("4. Throw back a fish");
			System.out.print("Enter choice (1-4): ");
			try {
				choice = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input." + "Please select a number between 1 and 4.");
			}
		}
		return choice;
	} //end choiceMenu method

	/**
	 * Displays fishing rules and other info.
	 */
	public static void printFishInfo() {
		boolean endChoice = false;
		Scanner choiceIn = new Scanner(System.in);
		String infoChoice = "";
		String infoSection = "";
		String gameRetMsg = "";

		infoSection = "\n'Anae family:\n\tIncludes Pua'ama, Kahaha, 'Ama'ama and 'Anae.\n";
		infoSection = infoSection + "\tLegal to fish between April and November.\n";
		infoSection = infoSection + "\tNot bait.\n\tNo bag limit.\n";
		wavePrint(infoSection + "\tValid catch methods are: \"net\", \"pole\"\n");

		infoSection = "\nMoi family:\n\tIncludes Moi-li'i, Moi mana, Pala moi, and Moi.\n";
		infoSection = infoSection + "\tLegal to fish between September and May.\n";
		infoSection = infoSection + "\tLegal size is 11 inches and above.\n";
		infoSection = infoSection + "\tNot bait.\n\tNo bag limit.\n";
		wavePrint(infoSection + "\tValid catch methods are: \"net\", \"pole\".\n");

		infoSection = "\nUhu family:\n\tIncludes 'Ohua, Panuhunuhu, and Uhu.\n";
		infoSection = infoSection + "\tLegal to fish year-round.\n";
		infoSection = infoSection + "\tLegal size is 12 inches and above.\n";
		infoSection = infoSection + "\tNot bait.\n\tNo bag limit.\n";
		wavePrint(infoSection + "\tValid catch methods are: \"throw net\", \"spear\", \"pole\".\n");

		infoSection = "\n'Oama family:\n\tIncludes 'Oama, Weke'a and Weke'ula.\n";
		infoSection = infoSection + "\tLegal to fish year-round.\n";
		infoSection = infoSection + "\tNo minimum legal size.\n\tIs bait.\n";
		wavePrint(infoSection + "\tValid catch methods are: \"net\",\"trap\", \"pole\".\n");
		//following menu format from assignments 2 and 4.
		while (!endChoice) {
			System.out.println("Would you like to know a bit more? y/n");
			infoChoice = choiceIn.nextLine();
			infoChoice = infoChoice.trim();
			gameRetMsg = positiveMsg();
			if (infoChoice.equalsIgnoreCase("y")) {
				infoSection = "Legal I'a include:\tAma'ama, 'Anae, Moi, Moi mana, 'Oama, Pala moi, ";
				infoSection = infoSection + "Uhu, Weke'a and Weke'ula.\n";
				wavePrint(infoSection + "All of these fish are game fish." + gameRetMsg);
				endChoice = true;
			} else if (infoChoice.equalsIgnoreCase("n")) {
				wavePrint("Continuing with fishing game." + gameRetMsg);
				endChoice = true;
			} else {
				System.out.println("Invalid input provided.");
				System.out.println("Please put y or n as your choice.");
			}
		}
	} //end of fishInfo printing method.

	/**
	 * Shows a randomly generated message for the user, using 111 techniques.
	 * Message is intended to give player a bit of inspiration.
	 * 
	 * @return s the message the user will see before fishing.
	 */
	public static String positiveMsg() {
		Random rng = new Random();
		int msgCount = 0;
		String s = "";
		String[] msgList = new String[MSG_LIM];
		msgCount = rng.nextInt(0, MSG_LIM);
		s = "\no< o< Remember; There's plenty in this pond, ";
		switch (msgCount) {
			case 0:
				msgList[msgCount] = "the best one will come to you, keep your spirit afloat! :)";
				s = s + msgList[msgCount] + " >o >o\no< o< o< o< o< o< o<";
				break;
			case 1:
				msgList[msgCount] = "the biggest fish smiles back the brightest!! :D";
				s = s + msgList[msgCount] + " >o >o\no< o< o< o< o< o< o<";
				break;
			case 2:
				msgList[msgCount] = "so you were made to go out and get em!! *>O<*";
				s = s + msgList[msgCount] + " >o >o\no< o< o< o< o< o< o<";
				break;
			case 3:
				msgList[msgCount] = "you can find the very best, like no one ever was! \\(><)";
				s = s + msgList[msgCount] + " >o >o\no< o< o< o< o< o< o<";
				break;
			case 4:
				msgList[msgCount] = "the catch may be elusive, but you can't give up! >:O";
				s = s + msgList[msgCount] + " >o >o\no< o< o< o< o< o< o<";
				break;
			default:
				msgList[msgCount] = "good luck, sport! (^w^)";
				s = s + msgList[msgCount] + " >o >o\no< o< o< o< o< o< o<";
				break;
		}
		return s;
	}



}