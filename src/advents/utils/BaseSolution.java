package advents.utils;

import advents.interfaces.Solvable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

abstract public class BaseSolution implements Solvable
{
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
