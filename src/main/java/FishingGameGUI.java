import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Main window (JFrame) for the Fishing Game. Sets up the main frame,
 * initializes GameLogic, and adds the GamePanel.
 */
public class FishingGameGUI extends JFrame {

	// The main panel containing game elements
	private GamePanel gamePanel;
	// The core game logic handler
	private GameLogic gameLogic;

	/**
	 * Constructor for the main game window.
	 */
	public FishingGameGUI() {
		// --- Initialize Game Logic ---
		gameLogic = new GameLogic();
		gameLogic.setupGame(); // Get player names, fill pond, initial growth

		// --- Window Setup ---
		setTitle("Reel It In! - Fishing Tournament");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setSize(800, 600); // Let pack() determine size initially
		setLocationRelativeTo(null);

		// --- Create and Add Game Panel ---
		// Pass the initialized gameLogic to the panel
		gamePanel = new GamePanel(gameLogic);
		add(gamePanel);

		// Pack the frame to fit the preferred sizes of its components
		// Adjust window size based on components
		pack();

		// Center the window again after packing
		setLocationRelativeTo(null);

	}

	/**
	 * The main entry point of the application. Creates and shows the GUI on the
	 * Event Dispatch Thread (EDT).
	 * 
	 * @param args Command line arguments (not used).
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new FishingGameGUI().setVisible(true);
			}
		});
	}
}