package fr.orion78.adventOfCode.year2020.day1;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        int result = Utils.readFileForDay(1, Part1::compute);

        // Expected : 319531
        System.out.format("%d x %d = %d", result, 2020 - result, (2020 - result) * result);
    }

    public static int compute(Stream<String> lines) {
        Set<Integer> expenses = lines.map(Integer::parseInt).collect(Collectors.toSet());

        for (Integer i : expenses) {
            if (expenses.contains(2020 - i)) {
                return i;
            }
        }

        throw new RuntimeException();
    }
}
