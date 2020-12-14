package fr.orion78.adventOfCode.year2020.day14;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        long sum = Utils.readFileForDay(14, Part1::compute);

        // Expected : 3059488894985
        System.out.println("Sum " + sum);
    }

    public static long compute(Stream<String> lines) {
        List<String> instructions = lines.collect(Collectors.toList());

        Map<Integer, Integer> mask = null;
        Map<Integer, Long> memory = new HashMap<>();

        for (String instruction : instructions) {
            if (instruction.startsWith("mask")) {
                mask = parseMask(instruction.split("=")[1].trim());
            } else {
                String[] inst = instruction.split("=");

                long res = getValue(mask, Long.parseLong(inst[1].trim()));
                memory.put(Integer.parseInt(inst[0].substring(4, inst[0].length() - 2)), res);
            }
        }

        return memory.values().stream().mapToLong(l -> l).sum();
    }

    private static long getValue(Map<Integer, Integer> mask, long l) {
        long res = 0;

        for (int i = 0; i < 36; i++) {
            if (mask.containsKey(i)) {
                res += mask.get(i) * (1L << i);
            } else {
                res += (l % 2) * (1L << i);
            }

            l /= 2;
        }

        return res;
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
