import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    // A JTextArea to act as our "terminal"
    private final JTextArea terminalTextArea;

    /**
     * The default constructor to also initialize the Java Swing UI that will be used to resolve the advent of code.
     */
    public Main() {
        // Create the main window (JFrame)
        JFrame frame = new JFrame("Advent of code solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // First select box (JComboBox)
        JLabel adventOfCodeYearLabel = new JLabel("Advent of Code Year:");
        String[] systems = {"2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"};
        JComboBox<String> selectAdventOfCodeYear = new JComboBox<>(systems);

        // Second select box (JComboBox)
        JLabel adventOfCodeDayLabel = new JLabel("Advent of Code Day:");
        String[] actions = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"};
        JComboBox<String> selectAdventOfCodeDay = new JComboBox<>(actions);

        JButton solveButton = new JButton("Solve");

        // Add components to the controls panel
        controlsPanel.add(adventOfCodeYearLabel);
        controlsPanel.add(selectAdventOfCodeYear);
        controlsPanel.add(adventOfCodeDayLabel);
        controlsPanel.add(selectAdventOfCodeDay);
        controlsPanel.add(solveButton);

        // JTextArea will display our output
        terminalTextArea = new JTextArea();
        terminalTextArea.setEditable(false); // Make it read-only
        terminalTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Use a terminal-like font
        terminalTextArea.setMargin(new Insets(10, 10, 10, 10)); // Add some padding

        // Wrap the text area in a JScrollPane to get scrollbars
        JScrollPane scrollPane = new JScrollPane(terminalTextArea);

        solveButton.addActionListener(e -> {
            logToTerminal("Solving...");
        });

        frame.add(controlsPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        logToTerminal("Application started. Please select a system and an action.");
    }

    /**
     * Appends a message to the terminal text area with a timestamp.
     * This method is thread-safe for calls from other threads if needed.
     * @param message The message to log.
     */
    private void logToTerminal(String message) {
        SwingUtilities.invokeLater(() -> {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            terminalTextArea.append("[" + timestamp + "] " + message + "\n");

            // Automatically scroll to the bottom
            terminalTextArea.setCaretPosition(terminalTextArea.getDocument().getLength());
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);

//        Solvable solution = new Solution(args[0], args[2]);

//        solution.solve();
    }

}