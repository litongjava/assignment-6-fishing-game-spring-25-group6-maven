import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The main JPanel where the game interface is displayed. Contains labels, text
 * areas, and buttons for game interaction. Interacts with the GameLogic class
 * and handles user actions.
 */
public class GamePanel extends JPanel implements ActionListener {

	// --- Game Logic Reference ---
	private GameLogic gameLogic;

	// --- GUI Components ---
	private JLabel statusLabel;
	// New label for pond size
	private JLabel pondSizeLabel;
	// Panel to hold status and pond size
   /**The top part of the panel.*/
	private JPanel topPanel;
   /**Displays the font.*/
	private JTextArea outputArea;
   /**Displays the surrounding background.*/
	private JScrollPane outputScrollPane;
   /**The button for catching the fish.*/
	private JButton catchButton;
   /**The button for the rules.*/
	private JButton rulesButton;
   /**The button to view the sack.*/
	private JButton sackButton;
   /**The button to allow throwing back.*/
	private JButton throwBackButton;
   /**Shows the options for each button.*/
	private JPanel actionButtonPanel;
   /**Shows options for a caught fish.*/
	private JPanel decisionPanel;
   /**The button to keep the fish.*/
	private JButton keepButton;
   /**The button to release the fish.*/
	private JButton releaseButton;
   /**Displays the methods of catching a fish.*/
	private JComboBox<String> methodComboBox;
   /**Displays the prompt to choose a method.*/
	private JLabel methodLabel;

	// --- State Variables ---
	private FishableI_a currentlyCaughtFish = null;
	private String selectedCatchMethod = null;

	// --- Constants ---
	private static final String[] CATCH_METHODS = { "pole", "net", "spear", "trap", "throw net" };
	// For output clarity
	private static final String SEPARATOR = "----------------------------------------";

	/**
	 * Constructor for the GamePanel. Initializes and arranges the GUI components,
	 * and adds listeners.
	 * 
	 * @param logic The initialized GameLogic instance.
	 */
	public GamePanel(GameLogic logic) {
		this.gameLogic = logic;

		setLayout(new BorderLayout(5, 5));

		// --- Top Panel (Status and Pond Size) ---
		statusLabel = new JLabel();
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setFont(new Font("Arial", Font.BOLD, 14));

		pondSizeLabel = new JLabel(); // Initialize pond size label
		pondSizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pondSizeLabel.setFont(new Font("Arial", Font.ITALIC, 12));

		// Use BorderLayout for top panel
		topPanel = new JPanel(new BorderLayout());
		topPanel.add(statusLabel, BorderLayout.CENTER);
		// Add pond size below status
		topPanel.add(pondSizeLabel, BorderLayout.SOUTH);

		// --- Center Output Area ---
		outputArea = new JTextArea(20, 60);
		outputArea.setEditable(false);
		outputArea.setLineWrap(true);
		outputArea.setWrapStyleWord(true);
		outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		outputScrollPane = new JScrollPane(outputArea);

		// --- Bottom Panel (Input and Buttons) ---
		// Catch Method Selection
		JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		methodLabel = new JLabel("Select Catch Method:");
		methodComboBox = new JComboBox<>(CATCH_METHODS);
		inputPanel.add(methodLabel);
		inputPanel.add(methodComboBox);

		// Action Buttons
		catchButton = new JButton("Try to Catch Fish");
		rulesButton = new JButton("View Rules");
		sackButton = new JButton("View Sack");
		throwBackButton = new JButton("Throw Back Fish");

		actionButtonPanel = new JPanel();
		actionButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		actionButtonPanel.add(catchButton);
		actionButtonPanel.add(rulesButton);
		actionButtonPanel.add(sackButton);
		actionButtonPanel.add(throwBackButton);

		// Keep/Release Decision Buttons
		keepButton = new JButton("Keep Fish");
		releaseButton = new JButton("Release Fish");

		decisionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		decisionPanel.add(keepButton);
		decisionPanel.add(releaseButton);
		decisionPanel.setVisible(false); // Initially hidden

		// Combine Button Panels
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(inputPanel, BorderLayout.NORTH);
		bottomPanel.add(actionButtonPanel, BorderLayout.CENTER);
		bottomPanel.add(decisionPanel, BorderLayout.SOUTH);

		// --- Add Components to the Main Panel ---
		add(topPanel, BorderLayout.NORTH); // Add the combined top panel
		add(outputScrollPane, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		// --- Add Action Listeners ---
		catchButton.addActionListener(this);
		rulesButton.addActionListener(this);
		sackButton.addActionListener(this);
		throwBackButton.addActionListener(this);
		keepButton.addActionListener(this);
		releaseButton.addActionListener(this);

		// --- Initialize Display ---
		updateStatusLabel();
		updatePondSizeLabel(); // Initialize pond size display
		outputArea.setText("Game Setup Complete.\n");
		appendOutput(gameLogic.getCurrentPlayerName() + " starts the tournament!");
		appendOutput("Select a catch method and click 'Try to Catch Fish'.");

		setPreferredSize(new Dimension(750, 580)); // Increased height slightly for pond label
	}

	/**
	 * Handles button clicks.
	 * 
	 * @param e The ActionEvent triggered by a button press.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (gameLogic.isGameOver()) {
			appendOutput("The game is over!");
			return;
		}

		if (source == catchButton) {
			handleCatchAttempt();
		} else if (source == rulesButton) {
			displayRules(); // Updated to use RuleViewer
		} else if (source == sackButton) {
			displaySack();
		} else if (source == throwBackButton) {
			handleThrowBack(); // Updated to show sack first
		} else if (source == keepButton) {
			handleKeepDecision(true);
		} else if (source == releaseButton) {
			handleKeepDecision(false);
		}
		// Update pond size after most actions that might change it
		if (source != rulesButton && source != sackButton) {
			updatePondSizeLabel();
		}
	}

	// --- Action Handling Methods ---

	private void handleCatchAttempt() {
		if (currentlyCaughtFish != null) {
			appendOutput("Please decide whether to keep or release the current fish first.");
			return;
		}
		if (gameLogic.getAttemptsLeft() <= 0) {
			appendOutput("No attempts left for this turn.");
			return;
		}

		selectedCatchMethod = (String) methodComboBox.getSelectedItem();
		if (selectedCatchMethod == null) {
			JOptionPane.showMessageDialog(this, "Please select a catch method first!", "Input Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		appendOutput("\n" + SEPARATOR); // Separator before attempt
		appendOutput(
				gameLogic.getCurrentPlayerName() + " casts out using " + selectedCatchMethod + "...");

		currentlyCaughtFish = gameLogic.attemptCatch();

		if (currentlyCaughtFish == null) {
			boolean pondMaybeEmpty = gameLogic.getPondSize() < 5; // Simple check
			if (pondMaybeEmpty && randomChance(0.2)) {
				appendOutput("...and the line comes back empty. The pond feels quiet.");
			} else if (randomChance(0.6)) {
				appendOutput("...but nothing bites.");
			} else {
				appendOutput("...a fish nibbled, but it got away!");
			}
			gameLogic.useAttempt();
			checkTurnEnd();
		} else {
			appendOutput("Success! You hooked a fish!");
			appendOutput(SEPARATOR);
			appendOutput(currentlyCaughtFish.toString());
			appendOutput(SEPARATOR);
			appendOutput("Do you want to keep this fish?");
			updateButtonStates(false);
		}
		updateStatusLabel();
	}

	private void handleKeepDecision(boolean keep) {
		if (currentlyCaughtFish == null)
			return;

		String resultMessage;
		if (keep) {
			resultMessage = gameLogic.keepFish(currentlyCaughtFish, selectedCatchMethod);
		} else {
			resultMessage = gameLogic.releaseFish(currentlyCaughtFish);
		}

		appendOutput(resultMessage);
		appendOutput(SEPARATOR); // Separator after decision

		currentlyCaughtFish = null;
		selectedCatchMethod = null;
		gameLogic.useAttempt();
		updateButtonStates(true);
		checkTurnEnd();
		updateStatusLabel();
	}

	/**
	 * Displays the fishing rules in a separate window.
	 */
	private void displayRules() {
		String rules = gameLogic.getRulesSummary();
		// Create and display the RuleViewer window
		RuleViewer ruleWindow = new RuleViewer(rules);
		// Center relative to the main game window if possible
		ruleWindow.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
		ruleWindow.display(); // Make it visible
	}

	private void displaySack() {
		setOutputText(gameLogic.getCurrentPlayerSackInfo());
		appendOutput("\n(Select another action or attempt to catch again)"); // Prompt
	}

	/**
	 * Handles the logic for the "Throw Back Fish" button. Now displays the sack
	 * first.
	 */
	private void handleThrowBack() {
		ArrayList<FishableI_a> sack = gameLogic.getCurrentPlayerSackList();
		if (sack.isEmpty()) {
			appendOutput("Your sack is empty. Nothing to throw back.");
			return;
		}

		// 1. Display the sack content clearly first
		displaySack();
		appendOutput("\n" + SEPARATOR);
		appendOutput("Prepare to select a fish to throw back from the list above.");

		// 2. Create choices for the dialog
		String[] choices = new String[sack.size()];
		for (int i = 0; i < sack.size(); i++) {
			// Format matches the displaySack output for consistency
			choices[i] = i + ": " + sack.get(i).getName() + " ("
					+ String.format("%.2f", sack.get(i).getLength()) + " in)";
		}

		// 3. Show the input dialog
		String input = (String) JOptionPane.showInputDialog(this, // Parent component
				"Enter the number of the fish to throw back:", // Message
				"Throw Back Fish", // Title
				JOptionPane.QUESTION_MESSAGE, // Message type
				null, // Icon
				choices, // Array of choices
				choices[0] // Default choice
		);

		// 4. Process the selection
		if (input != null) {
			try {
				int index = Integer.parseInt(input.substring(0, input.indexOf(':')));
				String result = gameLogic.throwBackFish(index);
				appendOutput(result);
				appendOutput(SEPARATOR);
				// Optionally, redisplay the sack after throwing back
				// displaySack();
			} catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
				System.err.println("Error parsing throw back selection: " + input);
				appendOutput("Invalid selection.");
			}
		} else {
			appendOutput("Throw back cancelled.");
		}
		// Pond size updated via actionPerformed caller
	}

	private void checkTurnEnd() {
		if (gameLogic.getAttemptsLeft() <= 0 && !gameLogic.isGameOver()) {
			appendOutput("\n--- End of " + gameLogic.getCurrentPlayerName() + "'s Turn ---");
			gameLogic.nextTurn();

			if (gameLogic.isGameOver()) {
				handleGameOver();
			} else {
				appendOutput("\n" + SEPARATOR + "\n"); // Extra separator between turns
				appendOutput("Month: " + gameLogic.getCurrentMonthName() + " - Fish are growing...");
				appendOutput("It's now " + gameLogic.getCurrentPlayerName() + "'s turn.");
				appendOutput("Select a catch method and try your luck!");
			}
		}
	}

	private void handleGameOver() {
		appendOutput("\n" + SEPARATOR);
		appendOutput("    GAME OVER!");
		appendOutput(SEPARATOR);
		// Display final sacks before results
		appendOutput("\n--- Final Sack: " + gameLogic.getCurrentPlayer().getName() + " ---");
		appendOutput(gameLogic.getCurrentPlayerSackInfo());
		// Need logic to get the *other* player's sack info too
		Player p1 = gameLogic.getCurrentPlayer();
		Player p2 = (p1 == gameLogic.getPlayer1()) ? gameLogic.getPlayer2() : gameLogic.getPlayer1(); 
		appendOutput("\n--- Final Sack: " + p2.getName() + " ---");
		// Display other player's sack
		appendOutput(p2.getSackContentsSorted());

		appendOutput("\n" + SEPARATOR);
		// Display final scores and winner
		appendOutput(gameLogic.getGameResult());
		appendOutput(SEPARATOR);

		updateButtonStates(false);
		String winnerMsg = gameLogic.getGameResult()
				.substring(gameLogic.getGameResult().lastIndexOf("inches\n\n") + 8);
		statusLabel.setText("Game Over! " + winnerMsg);
		pondSizeLabel.setText("Final Pond Size: " + gameLogic.getPondSize());
	}

	/**
	 * update Status Label
	 */
	public void updateStatusLabel() {
		if (gameLogic != null) {
			SwingUtilities.invokeLater(() -> statusLabel.setText(gameLogic.getStatusString()));
		}
	}

	/**
	 * Updates the pond size label based on the current GameLogic state.
	 */
	public void updatePondSizeLabel() {
		if (gameLogic != null && !gameLogic.isGameOver()) {
			SwingUtilities.invokeLater(
					() -> pondSizeLabel.setText("Fish in Pond: " + gameLogic.getPondSize()));
		} else if (gameLogic != null && gameLogic.isGameOver()) {
		}
	}

	public void appendOutput(String text) {
		SwingUtilities.invokeLater(() -> {
			outputArea.append(text + "\n");
			outputArea.setCaretPosition(outputArea.getDocument().getLength());
		});
	}

	public void clearOutput() {
		SwingUtilities.invokeLater(() -> outputArea.setText(""));
	}

	public void setOutputText(String text) {
		SwingUtilities.invokeLater(() -> {
			outputArea.setText(text + "\n");
			outputArea.setCaretPosition(0);
		});
	}

	private void updateButtonStates(boolean enableActions) {
		SwingUtilities.invokeLater(() -> {
			boolean canAttempt = enableActions && gameLogic.getAttemptsLeft() > 0
					&& !gameLogic.isGameOver();

			catchButton.setEnabled(canAttempt);
			rulesButton.setEnabled(enableActions && !gameLogic.isGameOver());
			sackButton.setEnabled(enableActions && !gameLogic.isGameOver());
			throwBackButton.setEnabled(enableActions && !gameLogic.isGameOver()
					&& gameLogic.getCurrentPlayer().getSackSize() > 0); // Disable if sack empty
			methodComboBox.setEnabled(canAttempt);

			decisionPanel.setVisible(!enableActions && currentlyCaughtFish != null); // Show only if fish is caught
			keepButton.setEnabled(!enableActions && currentlyCaughtFish != null);
			releaseButton.setEnabled(!enableActions && currentlyCaughtFish != null);

			if (gameLogic.isGameOver()) {
				catchButton.setEnabled(false);
				rulesButton.setEnabled(false);
				sackButton.setEnabled(false);
				throwBackButton.setEnabled(false);
				methodComboBox.setEnabled(false);
				decisionPanel.setVisible(false);
			}
		});
	}

	private boolean randomChance(double probability) {
		return Math.random() < probability;
	}
}