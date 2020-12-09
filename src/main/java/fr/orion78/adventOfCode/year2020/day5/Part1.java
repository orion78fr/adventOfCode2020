package fr.orion78.adventOfCode.year2020.day5;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        int max = Utils.readFileForDay(5, Part1::compute);

        // Expected : 866
        System.out.println("Highest seat ID : " + max);
    }

    public static int compute(Stream<String> lines) {
        return lines.filter(s -> !s.isEmpty())
                .mapToInt(Part1::getSeatNB)
                .max().orElse(-1);
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
