package advents.year2015.day5;

import advents.utils.BaseSolution;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class Solution extends BaseSolution {

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

        final int[] niceStringCount = {0};

        Path filePath = Paths.get("src/advents/year2015/day5/input.txt");

        try {
            java.io.FileReader fileReader = new java.io.FileReader(filePath.toString());
            Pattern excludedCharPattern = Pattern.compile("ab|cd|pq|xy");
            Pattern containsThreeVowel = Pattern.compile("([aeiou].*){3}");
            Pattern containsSameLetterTwice = Pattern.compile("(\\w)\\1");

            Files.lines(filePath).forEach(line -> {
                boolean hasExcludedChar = excludedCharPattern.matcher(line).find();
                boolean vowelCount = containsThreeVowel.matcher(line).find();
                boolean hasSameLetterTwice = containsSameLetterTwice.matcher(line).find();

                if (!hasExcludedChar && vowelCount && hasSameLetterTwice) {
                    niceStringCount[0] += 1;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();

        // Calculate the elapsed time in nanoseconds
        long durationInNano = endTime - startTime;
        // Calculate the elapsed time in milliseconds
        long durationInMillis = durationInNano / 1_000_000;

        logToTerminal.accept(String.format("The solution for year 2015, day 5 PART 1 is: %s", niceStringCount[0]));
        logToTerminal.accept(String.format("The solution for year 2015, day 5 PART 1 took %s ms to execute", durationInMillis));
    }

    @Override
    public void solvePart2() {
        long startTime = System.nanoTime();

        final int[] niceStringCount = {0};

        Path filePath = Paths.get("src/advents/year2015/day5/input.txt");

        try {
            java.io.FileReader fileReader = new java.io.FileReader(filePath.toString());
            Pattern nonOverlappingPairRule = Pattern.compile("(.{2}).*\\1");
            Pattern repeatWithMiddleLetterRule = Pattern.compile("(.).\\1");

            Files.lines(filePath).forEach(line -> {
                boolean hasNonOverlappingPair = nonOverlappingPairRule.matcher(line).find();
                boolean hasRepeatWithMiddleLetter = repeatWithMiddleLetterRule.matcher(line).find();

                if (hasNonOverlappingPair && hasRepeatWithMiddleLetter) {
                    niceStringCount[0] += 1;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        long endTime = System.nanoTime();

        // Calculate the elapsed time in nanoseconds
        long durationInNano = endTime - startTime;
        // Calculate the elapsed time in milliseconds
        long durationInMillis = durationInNano / 1_000_000;

        logToTerminal.accept(String.format("The solution for year 2015, day 5 PART 2 is: %s", niceStringCount[0]));
        logToTerminal.accept(String.format("The solution for year 2015, day 5 PART 2 took %s ms to execute", durationInMillis));
    }
}
