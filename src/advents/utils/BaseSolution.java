package advents.utils;

import advents.interfaces.Solvable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Consumer;

abstract public class BaseSolution implements Solvable
{
    public final Consumer<String> logToTerminal;

    /**
     * The solution is constructed with a log to terminal method, that logs every solution and operation done by each advent code year and day.
     * @param logToTerminal - This will log into our Java Swing terminal.
     */
    public BaseSolution(Consumer<String> logToTerminal) {
        this.logToTerminal = logToTerminal;
    }

    /***
     * This method solves the problem by using the correct solutionnaire class.
     * Each child needs to implement the solvePart1 and solvePart2 methods.
     * The solve method will call these two methods.
     */
    @Override
    public void solve() {
        this.solvePart1();
        this.solvePart2();
    }

    /**
     * This method needs to be overriden by each solution class.
     */
    abstract public void solvePart1();

    /**
     * This method needs to be overriden by each solution class.
     */
    abstract public void solvePart2();
}
