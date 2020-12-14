package fr.orion78.adventOfCode.year2020.day14;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws IOException {
        long sum = Utils.readFileForDay(14, Part2::compute);

        // Expected : 2900994392308
        System.out.println("Sum " + sum);
    }

    public static long compute(Stream<String> lines) {
        List<String> instructions = lines.collect(Collectors.toList());

        Map<Integer, Integer> mask = null;
        Map<Long, Long> memory = new HashMap<>();

        for (String instruction : instructions) {
            if (instruction.startsWith("mask")) {
                mask = parseMask(instruction.split("=")[1].trim());
            } else {
                String[] inst = instruction.split("=");

                long value = Long.parseLong(inst[1].trim());

                List<Long> addresses = getAddresses(mask,
                        Long.parseLong(inst[0].substring(4, inst[0].length() - 2)));
                addresses.forEach(a -> memory.put(a, value));
            }
        }

        return memory.values().stream().mapToLong(l -> l).sum();
    }

    private static List<Long> getAddresses(Map<Integer, Integer> mask, long l) {
        return getAddresses(mask, l, 0);
    }

    private static List<Long> getAddresses(Map<Integer, Integer> mask, long l, int bitShift) {
        if (bitShift == 36) {
            return Collections.singletonList(0L);
        }

        List<Long> addresses = getAddresses(mask, l / 2, bitShift + 1);

        if (mask.containsKey(bitShift)) {
            if (mask.get(bitShift) == 1) {
                // Overwritten with 1
                return addresses.stream().map(a -> a * 2 + 1).collect(Collectors.toList());
            } else {
                // Unchanged
                return addresses.stream().map(a -> a * 2 + (l % 2)).collect(Collectors.toList());
            }
        } else {
            // Floating
            return addresses.stream().flatMap(a -> Stream.of(a * 2, a * 2 + 1)).collect(Collectors.toList());
        }
    }

    private static Map<Integer, Integer> parseMask(String mask) {
        Map<Integer, Integer> m = new HashMap<>(36);

        byte[] bytes = mask.getBytes();
        for (int i = 0, bytesLength = bytes.length; i < bytesLength; i++) {
            byte b = bytes[i];

            if (b == '1') {
                m.put(bytesLength - i - 1, 1);
            } else if (b == '0') {
                m.put(bytesLength - i - 1, 0);
            }
        }
        return m;
    }
}
