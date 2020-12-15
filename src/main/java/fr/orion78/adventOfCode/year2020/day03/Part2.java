package fr.orion78.adventOfCode.year2020.day03;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws IOException {
        long count = Utils.readFileForDay(3, Part2::compute);

        System.out.println(count + " trees");
    }

    public static long compute(Stream<String> lines) {
        List<String> rows = lines.collect(Collectors.toList());

        long count11 = getCount(rows, 1, 1);
        long count31 = getCount(rows, 3, 1);
        long count51 = getCount(rows, 5, 1);
        long count71 = getCount(rows, 7, 1);
        long count12 = getCount(rows, 1, 2);

        // Expected : 3772314000
        return count11 * count31 * count51 * count71 * count12;
    }

    private static int getCount(List<String> rows, int slopex, int slopey) {
        int x = 0;
        int y = 0;

        int count = 0;

        while (y < rows.size()) {
            if (rows.get(y).charAt(x % rows.get(y).length()) == '#') {
                count++;
            }
            x += slopex;
            y += slopey;
        }

        return count;
    }
}
