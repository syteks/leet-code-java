package advents.year2015.day1;


import advents.interfaces.Displayable;
import advents.interfaces.Solvable;
import advents.utils.FileReader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Solution implements Solvable, Displayable {

    /**
     * Solves the proble for the advent of code 2015 day 1.
     * Not Quite Lisp
     */
    @Override
    public void solve() {
        solvePart1();
        solvePart2();
    }

    private static void solvePart1()
    {
        Path filePath = Paths.get("src\\advents\\year2015\\day1\\input.txt");
        try {
            String fileReader = FileReader.readFileAsString(filePath);
            System.out.println(fileReader);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void solvePart2()
    {

    }

    @Override
    public void display(boolean detailed) {

    }

    @Override
    public void detailedDisplay() {

    }

    @Override
    public void debugDisplay() {

    }
}