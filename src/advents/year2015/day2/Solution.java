package advents.year2015.day2;

import advents.interfaces.Solvable;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class Solution implements Solvable {
    private final Consumer<String> logToTerminal;

    /**
     * The solution is constructed with a log to terminal method, that logs every solution and operation done by each advent code year and day.
     * @param logToTerminal - This will log into our Java Swing terminal.
     */
    public Solution(Consumer<String> logToTerminal) {
        this.logToTerminal = logToTerminal;
    }

    @Override
    public void solve() {
        this.solvePart1();
        this.solvePart2();
    }

    private void solvePart1() {
        long startTime = System.nanoTime();

        Path filePath = Paths.get("src\\advents\\year2015\\day2\\input.txt");
        try {
            final int[] amountOfPaper = {0};

            Files.lines(filePath).forEach(line -> {
                String[] dimensions = line.split("x");

                int length = Integer.parseInt(dimensions[0]);
                int width = Integer.parseInt(dimensions[1]);
                int height = Integer.parseInt(dimensions[2]);
                int firstSide = length * width;
                int secondSide = width * height;
                int thirdSide = height * length;

                // The easiest way I found to use the lambda and increment a method local variable.
                amountOfPaper[0] += 2 * (firstSide + secondSide + thirdSide);

                if (firstSide <= secondSide && firstSide <= thirdSide ) {
                    amountOfPaper[0] += firstSide;
                } else if (secondSide <= firstSide && secondSide <= thirdSide) {
                    amountOfPaper[0] += secondSide;
                } else {
                    amountOfPaper[0] += thirdSide;
                }
            });

            long endTime = System.nanoTime();

            // Calculate the elapsed time in nanoseconds
            long durationInNano = endTime - startTime;

            // Calculate the elapsed time in milliseconds
            long durationInMillis = durationInNano / 1_000_000;

            logToTerminal.accept(String.format("The solution for year 2015, day 2 PART 1 is: %s", amountOfPaper[0]));
            logToTerminal.accept(String.format("The solution for year 2015, day 2 PART 1 took %s ms to execute", durationInMillis));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void solvePart2() {
        long startTime = System.nanoTime();

        Path filePath = Paths.get("src\\advents\\year2015\\day2\\input.txt");
        try {
            final int[] amountOfRibbon = {0};

            Files.lines(filePath).forEach(line -> {
                String[] dimensions = line.split("x");

                int length = Integer.parseInt(dimensions[0]);
                int width = Integer.parseInt(dimensions[1]);
                int height = Integer.parseInt(dimensions[2]);

                int ribbonInPlus = length * width * height;
                int firstSmallSide;
                int secondSmallSide;

                if (length <= width && length <= height ) {
                    firstSmallSide = length;
                    secondSmallSide = Math.min(width, height);
                } else if (width <= length && width <= height) {
                    firstSmallSide = width;
                    secondSmallSide = Math.min(length, height);
                } else {
                    firstSmallSide = height;
                    secondSmallSide = Math.min(width, length);
                }

                // The easiest way I found to use the lambda and increment a method local variable.
                amountOfRibbon[0] += (2 * (firstSmallSide + secondSmallSide)) + ribbonInPlus;
            });

            long endTime = System.nanoTime();

            // Calculate the elapsed time in nanoseconds
            long durationInNano = endTime - startTime;

            // Calculate the elapsed time in milliseconds
            long durationInMillis = durationInNano / 1_000_000;

            logToTerminal.accept(String.format("The solution for year 2015, day 2 PART 2 is: %s", amountOfRibbon[0]));
            logToTerminal.accept(String.format("The solution for year 2015, day 2 PART 2 took %s ms to execute", durationInMillis));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
