import java.awt.Font;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A simple window (JFrame) to display the fishing rules.
 */
public class RuleViewer extends JFrame {

	/**
	 * rulesTextArea.
	 */
	private JTextArea rulesTextArea;
	/**
	 * scrollPane.
	 */
	private JScrollPane scrollPane;

	/**
	 * Constructor for the RuleViewer window.
	 * 
	 * @param rulesText The text containing the fishing rules.
	 */
	public RuleViewer(String rulesText) {
		setTitle("Fishing Rules");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		rulesTextArea = new JTextArea(rulesText);
		rulesTextArea.setEditable(false);
		rulesTextArea.setLineWrap(true);
		rulesTextArea.setWrapStyleWord(true);
		rulesTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		rulesTextArea.setMargin(new Insets(10, 10, 10, 10));

		scrollPane = new JScrollPane(rulesTextArea);

		add(scrollPane);
	}

	/**
	 * Makes the rule viewer window visible. Should be called after creation.
	 */
	public void display() {
		setVisible(true);
	}
}