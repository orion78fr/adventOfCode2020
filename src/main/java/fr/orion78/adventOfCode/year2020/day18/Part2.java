package fr.orion78.adventOfCode.year2020.day18;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws IOException {
        long n = Utils.readFileForDay(18, Part2::compute);

        System.out.println("Result is " + n);
    }

    public static long compute(Stream<String> lines) {
        return lines.mapToLong(Part2::calculate)
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
        int plusSign;
        while ((plusSign = s.indexOf("+")) != -1) {
            int previousNumberStart = s.substring(0, plusSign - 1).lastIndexOf(" ") + 1;
            int nextNumberEnd = s.indexOf(" ", plusSign + 2);
            if (nextNumberEnd == -1) {
                // No end space
                nextNumberEnd = s.length();
            }

            long val = Long.parseLong(s.substring(previousNumberStart, plusSign - 1)) + Long.parseLong(s.substring(plusSign + 2, nextNumberEnd));
            String newS = s.substring(0, previousNumberStart) + val;

            if(nextNumberEnd != s.length()) {
                newS += s.substring(nextNumberEnd);
            }
            s = newS;
        }

        String[] splits = s.split(" ");

        long ans = Long.parseLong(splits[0]);
        for (int i = 2; i < splits.length; i += 2) {
            long rOp = Long.parseLong(splits[i]);
            ans *= rOp;
        }

        return ans;
    }
}
