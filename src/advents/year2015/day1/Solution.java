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
    private int part1Answer;

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

            this.part1Answer = floorUpCount - floorDownCount;

            long endTime = System.nanoTime();

            // Calculate the elapsed time in nanoseconds
            long durationInNano = endTime - startTime;
            // Calculate the elapsed time in milliseconds
            long durationInMillis = durationInNano / 1_000_000;

            logToTerminal.accept(String.format("The solution for year 2015, day 1 PART 1 is: %s", this.part1Answer));
            logToTerminal.accept(String.format("The solution for year 2015, day 1 PART 1 took %s ms to execute", durationInMillis));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}