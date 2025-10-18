package advents.year2015.day3;

import advents.utils.BaseSolution;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.function.Consumer;

public class Solution extends BaseSolution {
    record Point(int x, int y) {}

    /**
     * The solution is constructed with a log to terminal method, that logs every solution and operation done by each advent code year and day.
     *
     * @param logToTerminal - This will log into our Java Swing terminal.
     */
    public Solution(Consumer<String> logToTerminal) {
        super(logToTerminal);
    }

    @Override
    public void solvePart1() {
        long startTime = System.nanoTime();
        // This HasMap will create a list of <x, [y]>, so for example for each x, what are the y positions visited.
        HashMap<Point, Boolean> visitedPosition = new HashMap<Point, Boolean>();
        visitedPosition.put(new Point(0, 0), true);

        Path filePath = Paths.get("src/advents/year2015/day3/input.txt");

        int character = 0;
        int x = 0;
        int y = 0;

        try {
            java.io.FileReader fileReader = new java.io.FileReader(filePath.toString());
            while ((character = fileReader.read()) != -1) {
                if (character == '^') {
                    y++;
                } else if (character == 'v') {
                    y--;
                } else if (character == '<') {
                    x--;
                } else if (character == '>') {
                    x++;
                }

                visitedPosition.computeIfAbsent(new Point(x, y), k -> true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();

        // Calculate the elapsed time in nanoseconds
        long durationInNano = endTime - startTime;
        // Calculate the elapsed time in milliseconds
        long durationInMillis = durationInNano / 1_000_000;

        logToTerminal.accept(String.format("The solution for year 2015, day 3 PART 1 is: %s", visitedPosition.size()));
        logToTerminal.accept(String.format("The solution for year 2015, day 3 PART 1 took %s ms to execute", durationInMillis));
    }

    @Override
    public void solvePart2() {
        long startTime = System.nanoTime();


        // This HasMap will create a list of <x, [y]>, so for example for each x, what are the y positions visited.
        HashMap<Point, Boolean> visitedPosition = new HashMap<Point, Boolean>();
        visitedPosition.put(new Point(0, 0), true);

        Path filePath = Paths.get("src/advents/year2015/day3/input.txt");

        int character = 0;

        // The current position of the robot and the santa.
        int santaPositionX = 0,
                santaPositionY = 0;
        int robotSantaPositionX = 0,
                robotSantaPositionY = 0;

        boolean santaIsMoving = true;
        try {
            java.io.FileReader fileReader = new java.io.FileReader(filePath.toString());
            while ((character = fileReader.read()) != -1) {
                int x = santaIsMoving ? santaPositionX : robotSantaPositionX;
                int y = santaIsMoving ? santaPositionY : robotSantaPositionY;

                if (character == '^') {
                    y++;
                } else if (character == 'v') {
                    y--;
                } else if (character == '<') {
                    x--;
                } else if (character == '>') {
                    x++;
                }

                if (santaIsMoving) {
                    santaPositionX = x;
                    santaPositionY = y;
                } else {
                    robotSantaPositionX = x;
                    robotSantaPositionY = y;
                }

                visitedPosition.computeIfAbsent(new Point(x, y), k -> true);
                santaIsMoving = !santaIsMoving;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();

        // Calculate the elapsed time in nanoseconds
        long durationInNano = endTime - startTime;
        // Calculate the elapsed time in milliseconds
        long durationInMillis = durationInNano / 1_000_000;

        logToTerminal.accept(String.format("The solution for year 2015, day 3 PART 1 is: %s", visitedPosition.size()));
        logToTerminal.accept(String.format("The solution for year 2015, day 3 PART 1 took %s ms to execute", durationInMillis));
    }
}
