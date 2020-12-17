package fr.orion78.adventOfCode.year2020.day15;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1And2 {
    public static void main(String[] args) throws IOException {
        int n = Utils.readFileForDay(15, Part1And2::compute, 2020);

        System.out.println("2020th number " + n);

        n = Utils.readFileForDay(15, Part1And2::compute, 30_000_000);

        System.out.println("30000000th number " + n);
    }

    public static int compute(Stream<String> lines, int maxIter) {
        return compute(lines.flatMap(l -> Arrays.stream(l.split(","))).map(Integer::parseInt).collect(Collectors.toList()),
                maxIter);
    }

    public static int compute(List<Integer> values, int maxIter) {
        /*Map<Integer, Integer> previousOccurrences = new HashMap<>();

        for (int i = 0, valuesSize = values.size() - 1; i < valuesSize; i++) {
            previousOccurrences.put(values.get(i), i);
        }

        int previousNumber = values.get(values.size() - 1);

        for (int i = values.size() - 1; i < maxIter - 1; i++) {
            int newNumber = i - previousOccurrences.getOrDefault(previousNumber, i);

            previousOccurrences.put(previousNumber, i);
            previousNumber = newNumber;
        }

        return previousNumber;*/

        // This executes way faster even though it's way dirtier...
        Object[] previousOccurrences = new Object[maxIter];

        for (int i = 0, valuesSize = values.size() - 1; i < valuesSize; i++) {
            previousOccurrences[values.get(i)] = i;
        }

        int previousNumber = values.get(values.size() - 1);

        for (int i = values.size() - 1; i < maxIter - 1; i++) {
            Object previousNumberOccurence = previousOccurrences[previousNumber];
            int newNumber = i - (previousNumberOccurence == null ?  i : (int)previousNumberOccurence);

            previousOccurrences[previousNumber] =  i;
            previousNumber = newNumber;
        }

        return previousNumber;
    }
}
