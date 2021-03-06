package fr.orion78.adventOfCode.year2020.day18;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        long n = Utils.readFileForDay(18, Part1::compute);

        System.out.println("Result is " + n);
    }

    public static long compute(Stream<String> lines) {
        return lines.mapToLong(Part1::calculate)
                .sum();
    }

    private static long calculate(String s) {
        int openingParenthesis;

        // Resolve parenthesis
        while ((openingParenthesis = s.indexOf("(")) != -1) {
            int closingParenthesis = openingParenthesis;
            boolean shouldContinue = true;

            while (shouldContinue) {
                closingParenthesis = s.indexOf(")", closingParenthesis + 1);

                shouldContinue = s.substring(openingParenthesis + 1, closingParenthesis).chars()
                        .filter(c -> c == '(').count()
                        - s.substring(openingParenthesis + 1, closingParenthesis).chars()
                        .filter(c -> c == ')').count() != 0;
            }
            s = s.substring(0, openingParenthesis)
                    + calculate(s.substring(openingParenthesis + 1, closingParenthesis)) +
                    s.substring(closingParenthesis + 1);
        }

        // Solve
        String[] splits = s.split(" ");

        long ans = Long.parseLong(splits[0]);
        for (int i = 1; i < splits.length; i += 2) {
            long rOp = Long.parseLong(splits[i + 1]);

            if (splits[i].equals("+")) {
                ans += rOp;
            } else if (splits[i].equals("*")) {
                ans *= rOp;
            } else {
                throw new RuntimeException("Unknown op");
            }
        }

        return ans;
    }
}
