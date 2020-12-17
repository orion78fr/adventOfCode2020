package fr.orion78.adventOfCode.year2020.day16;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        int n = Utils.readFileForDay(16, Part1::compute);

        System.out.println("Sum of invalid numbers " + n);
    }

    public static int compute(Stream<String> lines) {
        List<String> list = lines.collect(Collectors.toList());

        List<Integer> emptyLines = IntStream.range(0, list.size())
                .filter(i -> list.get(i).isEmpty())
                .boxed()
                .collect(Collectors.toList());

        if (emptyLines.size() != 2) {
            throw new RuntimeException("Too many empty lines");
        }

        BitSet allowedNumbers = new BitSet();
        for (int i = 0, max = emptyLines.get(0); i < max; i++) {
            String[] ranges = list.get(i).split(":")[1].split("or"); // We don't care about the name of the field

            for (String range : ranges) {
                String[] bounds = range.trim().split("-");
                int lowerBound = Integer.parseInt(bounds[0]);
                int upperBound = Integer.parseInt(bounds[1]); // Included
                allowedNumbers.set(lowerBound, upperBound + 1);
            }
        }

        int sum = 0;

        for (int i = emptyLines.get(1) + 2, max = list.size(); i < max; i++) {
            sum += Arrays.stream(list.get(i).split(","))
                    .mapToInt(Integer::parseInt)
                    .filter(n -> !allowedNumbers.get(n))
                    .sum();
        }

        return sum;
    }
}
