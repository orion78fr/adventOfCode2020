package fr.orion78.adventOfCode.year2020.day03;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        int count = Utils.readFileForDay(3, Part1::compute);

        System.out.println(count + " trees");
    }

    public static int compute(Stream<String> lines) {
        List<String> rows = lines.collect(Collectors.toList());

        int x = 0;
        int y = 0;

        int count = 0;
        while (y < rows.size()) {
            if (rows.get(y).charAt(x % rows.get(y).length()) == '#') {
                count++;
            }
            x += 3;
            y++;
        }

        return count;
    }
}
