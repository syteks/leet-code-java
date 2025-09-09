package advents.interfaces;

import java.util.function.Consumer;

public interface Solvable {
    /**
     * Every class implementing this interface should expect to have a solve method.
     * Used to solve the problem.
     */
    void solve();
}