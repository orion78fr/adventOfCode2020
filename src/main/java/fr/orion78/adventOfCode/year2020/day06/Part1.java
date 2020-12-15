package fr.orion78.adventOfCode.year2020.day06;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.BitSet;
import java.util.Iterator;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        int count = Utils.readFileForDay(6, Part1::compute);

        System.out.println("Count : " + count);
    }

    public static int compute(Stream<String> lines) {
        Iterator<String> linesIt = lines.iterator();

        int count = 0;

        BitSet bs = new BitSet();
        while (linesIt.hasNext()) {
            String l = linesIt.next();
            if (l.isEmpty()) {
                count += bs.cardinality();
                bs.clear();
            } else {
                l.chars().forEach(bs::set);
            }
        }

        count += bs.cardinality();

        return count;
    }
}
