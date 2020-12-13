package fr.orion78.adventOfCode.year2020.day13;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws IOException {
        BigInteger time = Utils.readFileForDay(13, Part2::compute);

        // Expected :
        System.out.println("Time " + time);
    }

    public static BigInteger compute(Stream<String> lines) {
        return compute(lines.skip(1).findAny().get());
    }

    public static BigInteger compute(String line) {
        String[] splits = line.split(",");

        Map<Long, Long> a = new HashMap<>();
        for (int i = 0; i < splits.length; i++) {
            if (!splits[i].equals("x")) {
                long bus = Long.parseLong(splits[i]);
                a.put(bus, bus - i % bus);
            }
        }

        BigInteger m = a.keySet().stream()
                .map(BigInteger::valueOf)
                .reduce(BigInteger.valueOf(1), BigInteger::multiply);

        Map<Long, BigInteger> q = new HashMap<>();
        for (Long l : a.keySet()) {
            BigInteger res = m
                    .divide(BigInteger.valueOf(l))
                    .pow(l.intValue() - 2)
                    .mod(BigInteger.valueOf(l));
            q.put(l, res);
        }

        BigInteger res = BigInteger.valueOf(0);
        for (Long l : a.keySet()) {
            res = res.add(BigInteger.valueOf(a.get(l))
                    .multiply(q.get(l))
                    .multiply(m)
                    .divide(BigInteger.valueOf(l)));
        }

        return res.mod(m);
    }
}
