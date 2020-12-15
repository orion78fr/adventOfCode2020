package fr.orion78.adventOfCode.year2020.day05;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        int max = Utils.readFileForDay(5, Part1::compute);

        System.out.println("Highest seat ID : " + max);
    }

    public static int compute(Stream<String> lines) {
        return lines.filter(s -> !s.isEmpty())
                .mapToInt(Part1::getSeatNB)
                .max().orElse(-1);
    }

    public static int getSeatNB(String seat) {
        return Integer.parseInt(seat.replace('B', '1').replace('F', '0')
                .replace('R', '1').replace('L', '0'), 2);
    }
}
