package fr.orion78.adventOfCode.year2020.day09;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws IOException {
        long wrongNumber = Utils.readFileForDay(9, Part1::compute);

        long weakness = Utils.readFileForDay(9, Part2::compute, wrongNumber);

        // Expected : 4830226
        System.out.println("Weakness : " + weakness);
    }

    public static long compute(Stream<String> lines, long wrongNumber) {
        List<Long> values = lines.map(Long::parseLong).collect(Collectors.toList());

        outer:
        for (int i = 0; i < values.size(); i++) {
            long curSum = 0;
            long min = Long.MAX_VALUE;
            long max = Long.MIN_VALUE;

            for (int j = i; j < values.size(); j++) {
                long curVal = values.get(j);
                curSum += curVal;
                min = Math.min(min, curVal);
                max = Math.max(max, curVal);

                if (curSum == wrongNumber) {
                    return min + max;
                } else if (curSum > wrongNumber) {
                    // We're already past it
                    continue outer;
                }
            }
        }

        throw new RuntimeException();
    }
}
