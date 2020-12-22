package fr.orion78.adventOfCode.year2020.day21;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        long n = Utils.readFileForDay(21, Part1::compute);

        System.out.println("Result is " + n);
    }

    public static long compute(Stream<String> inputStream) {
        Iterator<String> iterator = inputStream.iterator();

        Map<String, Integer> occurrences = new HashMap<>();
        Map<String, Set<String>> allergenPossibleWords = new HashMap<>();

        while (iterator.hasNext()) {
            String[] line = iterator.next().split(" \\(contains ");

            String[] ingredients = line[0].split(" ");
            String[] allergens = line[1].substring(0, line[1].length() - 1) // Removes trailing parenthesis
                    .split(", ");

            for (String ingredient : ingredients) {
                occurrences.compute(ingredient, (in, oldVal) -> oldVal == null ? 1 : oldVal + 1);
            }

            Set<String> ingredientSet = new HashSet<>(Arrays.asList(ingredients));

            for (String allergen : allergens) {
                allergenPossibleWords.compute(allergen, (a, p) -> {
                    if (p == null) {
                        return new HashSet<>(ingredientSet);
                    } else {
                        p.removeIf(ingredient -> !ingredientSet.contains(ingredient));
                        return p;
                    }
                });
            }
        }

        return occurrences.entrySet().stream()
                .filter(e -> allergenPossibleWords.values().stream().noneMatch(set -> set.contains(e.getKey())))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
