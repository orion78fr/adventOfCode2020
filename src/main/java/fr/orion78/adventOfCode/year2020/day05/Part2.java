package fr.orion78.adventOfCode.year2020.day05;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.BitSet;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws IOException {
        int freeSeat = Utils.readFileForDay(5, Part2::compute);

        System.out.println("Free seat : " + freeSeat);
    }

    public static int compute(Stream<String> lines) {
        BitSet set = lines.filter(s -> !s.isEmpty())
                .mapToInt(Part1::getSeatNB)
                .collect(BitSet::new, BitSet::set, BitSet::and);

        return set.nextClearBit(set.nextSetBit(0));
    }
}
