package advents.year2015.day1;

import advents.interfaces.Solvable;
import advents.utils.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution implements Solvable {
    private final Consumer<String> logToTerminal;

    /**
     * The solution is constructed with a log to terminal method, that logs every solution and operation done by each advent code year and day.
     * @param logToTerminal - This will log into our Java Swing terminal.
     */
    public Solution(Consumer<String> logToTerminal) {
        this.logToTerminal = logToTerminal;
    }

    /**
     * Solves the proble for the advent of code 2015 day 1.
     * Not Quite Lisp
     */
    @Override
    public void solve() {
        this.solvePart1();
        this.solvePart2();
    }

    private void solvePart1()
    {
        long startTime = System.nanoTime();

        Path filePath = Paths.get("src\\advents\\year2015\\day1\\input.txt");
        try {
            String fileReader = FileReader.readFileAsString(filePath);
            Pattern patternFloorUp = Pattern.compile("\\(");
            Pattern patternFloorDown = Pattern.compile("\\)");

            // Checks in the file string all the characters that matches a floor up.
            Matcher matcherFloorUp = patternFloorUp.matcher(fileReader);

            // Checks in the file string all the characters that matches a floor down.
            Matcher matcherFloorDown = patternFloorDown.matcher(fileReader);

            int floorUpCount = matcherFloorUp.results()
                    .map(MatchResult::group)
                    .toList()
                    .size();

            int floorDownCount = matcherFloorDown.results()
                    .map(MatchResult::group)
                    .toList()
                    .size();

            int floorLevel = floorUpCount - floorDownCount;

            long endTime = System.nanoTime();

            // Calculate the elapsed time in nanoseconds
            long durationInNano = endTime - startTime;
            // Calculate the elapsed time in milliseconds
            long durationInMillis = durationInNano / 1_000_000;

            logToTerminal.accept(String.format("The solution for year 2015, day 1 PART 1 is: %s", floorLevel));
            logToTerminal.accept(String.format("The solution for year 2015, day 1 PART 1 took %s ms to execute", durationInMillis));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void solvePart2()
    {
        long startTime = System.nanoTime();

        Path filePath = Paths.get("src\\advents\\year2015\\day1\\input.txt");
        try {
            java.io.FileReader fileReader = new java.io.FileReader(filePath.toString());

            int floorLevel = 0;
            int characterIndex = 0;
            int character = 0;

            while ((character = fileReader.read()) != -1) {
                if (character == '(') {
                    floorLevel++;
                } else if (character == ')') {
                    floorLevel--;
                }

                characterIndex++;

                if (floorLevel == -1) {
                    break;
                }
            }

            long endTime = System.nanoTime();

            // Calculate the elapsed time in nanoseconds
            long durationInNano = endTime - startTime;
            // Calculate the elapsed time in milliseconds
            long durationInMillis = durationInNano / 1_000_000;

            logToTerminal.accept(String.format("The solution for year 2015, day 1 PART 2 is: %s", characterIndex));
            logToTerminal.accept(String.format("The solution for year 2015, day 1 PART 2 took %s ms to execute", durationInMillis));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}