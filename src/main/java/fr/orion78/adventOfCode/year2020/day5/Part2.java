package fr.orion78.adventOfCode.year2020.day5;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.BitSet;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws IOException {
        int freeSeat = Utils.readFileForDay(5, Part2::compute);

        // Expected : 583
        System.out.println("Free seat : " + freeSeat);
    }

    public static int compute(Stream<String> lines) {
        BitSet set = lines.filter(s -> !s.isEmpty())
                .mapToInt(Part2::getSeatNB)
                .collect(BitSet::new, BitSet::set, BitSet::and);

        return set.nextClearBit(set.nextSetBit(0));
    }

    private static int getSeatNB(String seat) {
        int row = 0;

        row += seat.charAt(0) == 'B' ? 64 : 0;
        row += seat.charAt(1) == 'B' ? 32 : 0;
        row += seat.charAt(2) == 'B' ? 16 : 0;
        row += seat.charAt(3) == 'B' ? 8 : 0;
        row += seat.charAt(4) == 'B' ? 4 : 0;
        row += seat.charAt(5) == 'B' ? 2 : 0;
        row += seat.charAt(6) == 'B' ? 1 : 0;

        int col = 0;

        col += seat.charAt(7) == 'R' ? 4 : 0;
        col += seat.charAt(8) == 'R' ? 2 : 0;
        col += seat.charAt(9) == 'R' ? 1 : 0;

        return row * 8 + col;
    }
}
