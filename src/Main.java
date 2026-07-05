import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.graphics.Theme;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Terminal (TUI) front-end for the Advent of Code solver, built with Lanterna.
 * <p>
 * The user picks a year and a day, presses Solve, and the matching solution is loaded
 * via {@link SolutionFactory} (reflection) and executed. Output is streamed into an
 * on-screen "terminal" box. Solutions stay UI-agnostic: they only ever receive a
 * {@code Consumer<String>} sink, which here appends timestamped lines to the output box.
 */
public class Main {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    // ── Catppuccin Mocha palette — a soft, Neovim-style dark theme ──────────────
    private static final TextColor CRUST     = new TextColor.RGB(0x11, 0x11, 0x1b);
    private static final TextColor MANTLE    = new TextColor.RGB(0x18, 0x18, 0x25);
    private static final TextColor BASE       = new TextColor.RGB(0x1e, 0x1e, 0x2e);
    private static final TextColor SURFACE0   = new TextColor.RGB(0x31, 0x32, 0x44);
    private static final TextColor TEXT       = new TextColor.RGB(0xcd, 0xd6, 0xf4);
    private static final TextColor OVERLAY0   = new TextColor.RGB(0x6c, 0x70, 0x86);
    private static final TextColor BLUE       = new TextColor.RGB(0x89, 0xb4, 0xfa);

    private final WindowBasedTextGUI gui;
    private final BasicWindow window;
    private final ComboBox<String> yearBox;
    private final ComboBox<String> dayBox;
    private final TextBox output;

    /**
     * Builds the TUI window and wires up its controls.
     *
     * @param gui the Lanterna text GUI the window will be attached to.
     */
    public Main(WindowBasedTextGUI gui) {
        this.gui = gui;
        gui.setTheme(darkTheme());

        this.window = new BasicWindow("  Advent of Code — Solver  ");
        this.window.setHints(List.of(Window.Hint.EXPANDED));
        this.window.setCloseWindowWithEscape(true);

        // Year selector (2015 .. 2025).
        this.yearBox = new ComboBox<>();
        for (int year = 2015; year <= 2025; year++) {
            this.yearBox.addItem(String.valueOf(year));
        }

        // Day selector (1 .. 25).
        this.dayBox = new ComboBox<>();
        for (int day = 1; day <= 25; day++) {
            this.dayBox.addItem(String.valueOf(day));
        }

        // Read-only, scrollable output area acting as our "terminal".
        this.output = new TextBox(new TerminalSize(80, 20), TextBox.Style.MULTI_LINE);
        this.output.setReadOnly(true);

        Panel controls = new Panel(new LinearLayout(Direction.HORIZONTAL).setSpacing(2));
        controls.addComponent(new Label("Year"));
        controls.addComponent(this.yearBox);
        controls.addComponent(new Label("Day"));
        controls.addComponent(this.dayBox);
        controls.addComponent(new Button(" Solve ", this::onSolve));
        controls.addComponent(new Button(" Clear ", this::onClear));
        controls.addComponent(new Button(" Quit ", this.window::close));

        Component borderedOutput = this.output.withBorder(Borders.singleLine("Terminal"));
        borderedOutput.setLayoutData(
                LinearLayout.createLayoutData(LinearLayout.Alignment.Fill, LinearLayout.GrowPolicy.CanGrow));

        Label help = new Label("Tab: move   ←→/↑↓: change   Enter: activate   Esc: quit");
        help.setForegroundColor(OVERLAY0);

        Panel root = new Panel(new LinearLayout(Direction.VERTICAL).setSpacing(1));
        root.addComponent(controls);
        root.addComponent(borderedOutput);
        root.addComponent(help);

        this.window.setComponent(root);
    }

    /**
     * A soft dark (Catppuccin Mocha) theme so the UI reads like a modern editor
     * instead of Lanterna's default blue.
     */
    private static Theme darkTheme() {
        return SimpleTheme.makeTheme(
                true,     // active selections rendered in bold
                TEXT,     // base foreground
                BASE,     // base background
                TEXT,     // foreground for editable content (combo/textbox)
                SURFACE0, // background for editable content
                CRUST,    // foreground when focused/selected (dark text …)
                BLUE,     // background when focused/selected (… on a blue highlight)
                MANTLE);  // the desktop area behind the window
    }

    /**
     * Displays the window and blocks until the user quits it.
     */
    public void run() {
        log("Application started. Select a year and a day, then press Solve.");
        gui.addWindowAndWait(window);
    }

    /**
     * Solve handler: runs the selected puzzle on a background thread so slow puzzles
     * (e.g. 2015 day 4 MD5 mining) don't freeze the UI. Output is marshalled back onto
     * the GUI thread by {@link #log(String)}.
     */
    private void onSolve() {
        String year = yearBox.getSelectedItem();
        String day = dayBox.getSelectedItem();
        log("Solving year " + year + ", day " + day + " ...");

        Thread worker = new Thread(() -> {
            SolutionFactory solution = new SolutionFactory(year, day, this::log);
            solution.solve();
        }, "aoc-solver");
        worker.setDaemon(true);
        worker.start();
    }

    /**
     * Clear handler: empties the terminal output box.
     */
    private void onClear() {
        output.setText("");
        log("Terminal cleared. Select a year and a day, then press Solve.");
    }

    /**
     * Appends a timestamped message to the output box. Safe to call from any thread:
     * the update is scheduled on the GUI thread, and the box auto-scrolls to the bottom.
     *
     * @param message the message to display.
     */
    private void log(String message) {
        gui.getGUIThread().invokeLater(() -> {
            output.addLine("[" + LocalDateTime.now().format(TIME_FORMAT) + "] " + message);
            output.setCaretPosition(output.getLineCount() - 1, 0);
        });
    }

    public static void main(String[] args) throws IOException {
        Screen screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        try {
            WindowBasedTextGUI gui = new MultiWindowTextGUI(
                    screen, new DefaultWindowManager(), new EmptySpace(MANTLE));
            new Main(gui).run();
        } finally {
            screen.stopScreen();
        }
    }
}
