package advents.interfaces;

public interface Displayable {
    /**
     * We want to be able to display the Question of the problem and the answer.
     * @param detailed
     */
    void display(boolean detailed);

    /**
     * The Detailed display will print more information, like the amount of time it took to solve the problem.
     */
    void detailedDisplay();

    /**
     * The Debug display will print more information than the normal display.
     */
    void debugDisplay();
}