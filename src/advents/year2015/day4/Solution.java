package advents.year2015.day4;

import advents.utils.BaseSolution;

import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.function.Consumer;

public class Solution extends BaseSolution {
    private final String input =  "yzbqklnj";

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

        int nonce = this.getMinimumNonceRequiredForPaddedMD5Response("00000");

        long endTime = System.nanoTime();

        // Calculate the elapsed time in nanoseconds
        long durationInNano = endTime - startTime;
        // Calculate the elapsed time in milliseconds
        long durationInMillis = durationInNano / 1_000_000;

        logToTerminal.accept(String.format("The solution for year 2015, day 4 PART 1 is: %s", nonce));
        logToTerminal.accept(String.format("The solution for year 2015, day 4 PART 1 took %s ms to execute", durationInMillis));
    }

    @Override
    public void solvePart2() {
        long startTime = System.nanoTime();

        int nonce = this.getMinimumNonceRequiredForPaddedMD5Response("000000");

        long endTime = System.nanoTime();

        // Calculate the elapsed time in nanoseconds
        long durationInNano = endTime - startTime;
        // Calculate the elapsed time in milliseconds
        long durationInMillis = durationInNano / 1_000_000;

        logToTerminal.accept(String.format("The solution for year 2015, day 4 PART 2 is: %s", nonce));
        logToTerminal.accept(String.format("The solution for year 2015, day 4 PART 2 took %s ms to execute", durationInMillis));
    }

    private int getMinimumNonceRequiredForPaddedMD5Response(String padding) {
        int nonce = 0;

        try {
            String md5Response = "";
            String paddedResponse = "";
            MessageDigest md = MessageDigest.getInstance("MD5");

            do {
                nonce++;

                String formattedInput = input + nonce;

                md.update(formattedInput.getBytes());

                byte[] digest = md.digest();

                BigInteger bigInt = new BigInteger(1, digest);

                md5Response = bigInt.toString(16);
                paddedResponse = padding + md5Response;

            } while (paddedResponse.length() != 32 );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return nonce;
    }
}
