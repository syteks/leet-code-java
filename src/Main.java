import advents.interfaces.Solvable;
import advents.year2015.day1.Solution;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the LeetCode Terminal Interface in (Java)");

        Solvable solution = new Solution();

        solution.solve();
    }
}