package fr.orion78.adventOfCode.year2020.day9;

import fr.orion78.adventOfCode.year2020.util.RingBuffer;
import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        long wrongNumber = Utils.readFileForDay(9, Part1::compute);

        // Expected : 36845998
        System.out.println("First wrong number : " + wrongNumber);
    }

    public static long compute(Stream<String> lines) {
        List<Long> values = lines.map(Long::parseLong).collect(Collectors.toList());

        RingBuffer<Long> rb = new RingBuffer<>(Long.class, 25);

        // Add the preamble
        for (int i = 0; i < 25; i++) {
            rb.put(values.get(i));
        }

        for (int i = 25; i < values.size(); i++) {
            long nextValue = values.get(i);

            Set<Long> s = rb.stream().collect(Collectors.toSet());
            boolean found = false;
            for (long possiblePair : s) {
                if (s.contains(nextValue - possiblePair)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                rb.put(nextValue);
            } else {
                return nextValue;
            }
        }

        throw new RuntimeException();
    }
}
