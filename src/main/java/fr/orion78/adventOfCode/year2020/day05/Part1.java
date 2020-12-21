package fr.orion78.adventOfCode.year2020.day05;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.List;
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
        return (int) Utils.fromBinary(seat, List.of('B', 'R'));
    }
}
