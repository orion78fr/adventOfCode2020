package fr.orion78.adventOfCode.year2020.day25;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {
    public static final int SUBJECT_NUMBER = 7;
    public static final int REMAINDER = 20201227;

    public static void main(String[] args) throws IOException {
        long n = Utils.readFileForDay(25, Part1::compute);

        System.out.println("Result is " + n);
    }

    public static long compute(Stream<String> inputStream) {
        List<Long> l = inputStream.map(Long::parseLong)
                .collect(Collectors.toList());

        int loopSize = 0;
        long s = 1;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            loopSize++;
            s *= SUBJECT_NUMBER;
            s %= REMAINDER;
            if (s == l.get(0)) {
                break;
            }
        }

        return BigInteger.valueOf(l.get(1)).pow(loopSize).mod(BigInteger.valueOf(REMAINDER)).longValue();
    }
}
