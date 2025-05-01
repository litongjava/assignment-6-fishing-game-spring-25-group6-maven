import javax.swing.*;
import java.awt.*;

/**
 * A simple window (JFrame) to display the fishing rules.
 */
public class RuleViewer extends JFrame {

    private JTextArea rulesTextArea;
    private JScrollPane scrollPane;

    /**
     * Constructor for the RuleViewer window.
     * @param rulesText The text containing the fishing rules.
     */
    public RuleViewer(String rulesText) {
        setTitle("Fishing Rules");
        setSize(500, 400); // Set a reasonable size
        setLocationRelativeTo(null); // Center relative to the main window (or screen if null)
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window, not the whole app

        rulesTextArea = new JTextArea(rulesText);
        rulesTextArea.setEditable(false);
        rulesTextArea.setLineWrap(true);
        rulesTextArea.setWrapStyleWord(true);
        rulesTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        rulesTextArea.setMargin(new Insets(10, 10, 10, 10)); // Add some padding

        scrollPane = new JScrollPane(rulesTextArea);

        add(scrollPane); // Add the scroll pane to the frame

        // Make it visible
        // setVisible(true); // Visibility should be set by the caller
    }

    /**
     * Makes the rule viewer window visible.
     * Should be called after creation.
     */
    public void display() {
        setVisible(true);
    }
}