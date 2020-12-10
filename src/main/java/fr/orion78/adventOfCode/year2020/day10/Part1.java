package fr.orion78.adventOfCode.year2020.day10;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        int joltDifferences = Utils.readFileForDay(10, Part1::compute);

        // Expected : 2059
        System.out.println("Jolt 1 & 3 differences multiplied : " + joltDifferences);
    }

    public static int compute(Stream<String> lines) {
        List<Integer> adapters = lines.map(Integer::parseInt).sorted().collect(Collectors.toList());

        int jolt1 = 0;
        int jolt3 = 1; // Our device adapter have a +3 difference

        if (adapters.get(0) == 1) {
            jolt1++;
        } else if (adapters.get(0) == 3) {
            jolt3++;
        }

        for (int i = 0; i < adapters.size() - 1; i++) {
            int diff = adapters.get(i + 1) - adapters.get(i);
            if (diff == 1) {
                jolt1++;
            } else if (diff == 3) {
                jolt3++;
            }
        }

        return jolt1 * jolt3;
    }
}
