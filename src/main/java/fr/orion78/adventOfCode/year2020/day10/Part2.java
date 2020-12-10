package fr.orion78.adventOfCode.year2020.day10;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws IOException {
        long arrangements = Utils.readFileForDay(10, Part2::compute);

        // Expected :
        System.out.println("Number of different arrangements : " + arrangements);
    }

    public static long compute(Stream<String> lines) {
        TreeMap<Integer, Long> adapters = lines.map(Integer::parseInt).sorted().collect(Collectors.toMap(Function.identity(), o -> 0L, Long::sum, TreeMap::new));

        int charger = adapters.lastKey() + 3;
        adapters.put(charger, 0L);

        // Adapters we can directly plug
        adapters.computeIfPresent(1, (k, v) -> 1L);
        adapters.computeIfPresent(2, (k, v) -> 1L);
        adapters.computeIfPresent(3, (k, v) -> 1L);

        Integer curAdapter = adapters.firstKey();

        while (curAdapter != null) {
            long curAdapterVal = adapters.get(curAdapter);

            adapters.computeIfPresent(curAdapter + 1, (k, v) -> v + curAdapterVal);
            adapters.computeIfPresent(curAdapter + 2, (k, v) -> v + curAdapterVal);
            adapters.computeIfPresent(curAdapter + 3, (k, v) -> v + curAdapterVal);

            curAdapter = adapters.higherKey(curAdapter);
        }

        return adapters.get(charger);
    }
}
