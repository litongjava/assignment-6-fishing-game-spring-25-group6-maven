import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Main window (JFrame) for the Fishing Game. Sets up the main frame,
 * initializes GameLogic, and adds the GamePanel.
 */
public class FishingGameGUI extends JFrame {

	/**
	 * FishingGameGUI.
	 */
	private GamePanel gamePanel;
	/**
	 * The core game logic handler.
	 */
	private GameLogic gameLogic;

	/**
	 * Constructor for the main game window.
	 */
	public FishingGameGUI() {
		// --- Initialize Game Logic ---
		gameLogic = new GameLogic();
		gameLogic.setupGame();

		// --- Window Setup ---
		setTitle("Reel It In! - Fishing Tournament");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// --- Create and Add Game Panel ---
		gamePanel = new GamePanel(gameLogic);
		add(gamePanel);

		pack();

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