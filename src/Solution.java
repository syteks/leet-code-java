import advents.interfaces.Solvable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

public class Solution implements Solvable {
    /**
     * This property will hold the solution for the given year and day
     */
    private Solvable solvable;

    /**
     * The log to terminal method that will help us display the solution to the interface
     */
    private final Consumer<String> logToTerminal;

    /**
     * The constructor will check which year and day we want to solve the problem for and construct the correct solutionnaire.
     * @param year - The advent of code year to solve.
     * @param day - The advent of code day to solve.
     */
    public Solution(String year, String day, Consumer<String> logToTerminal) {
        String className = "advents.year" + year + ".day" + day + ".Solution";
        this.logToTerminal = logToTerminal;

        try {
            Class<Solvable> solvableClass = (Class<Solvable>) Class.forName(className);

            Constructor<?> constructor = solvableClass.getConstructor(Consumer.class);

            this.solvable = (Solvable) constructor.newInstance(this.logToTerminal);
        } catch (
                ClassNotFoundException
                | NoSuchMethodException
                | InvocationTargetException
                | InstantiationException
                | IllegalAccessException e
        ) {
            logToTerminal.accept("Could not find solution for year " + year + " and day " + day);
            logToTerminal.accept(e.getMessage());
        }
    }

    /**
     * This method will solve the problem by using the correct solutionnaire class.
     */
    public void solve() {
        if (this.solvable != null) {
            this.solvable.solve();
        }
    }
}
