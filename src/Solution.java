import advents.interfaces.Solvable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Solution implements Solvable {
    /**
     * This property will hold the solution for the given year and day
     */
    private final Solvable solvable;

    /**
     * The constructor will check which year and day we want to solve the problem for and construct the correct solutionnaire.
     * @param year - The advent of code year to solve.
     * @param day - The advent of code day to solve.
     */
    public Solution(String year, String day) {
        String className = "advents.year" + year + ".day" + day + ".Solution";

        try {
            Class<Solvable> solvableClass = (Class<Solvable>) Class.forName(className);

            Constructor<?> constructor = solvableClass.getConstructor(String.class);

            this.solvable = (Solvable) constructor.newInstance();
        } catch (
                ClassNotFoundException
                | NoSuchMethodException
                | InvocationTargetException
                | InstantiationException
                | IllegalAccessException e
        ) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method will solve the problem by using the correct solutionnaire class.
     */
    public void solve() {
        this.solvable.solve();
    }
}
