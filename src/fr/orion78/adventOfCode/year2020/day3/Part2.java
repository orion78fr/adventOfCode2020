package fr.orion78.adventOfCode.year2020.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("day3.txt"))) {
            List<String> rows = r.lines().collect(Collectors.toList());

            int count11 = getCount(rows, 1, 1);
            int count31 = getCount(rows, 3, 1);
            int count51 = getCount(rows, 5, 1);
            int count71 = getCount(rows, 7, 1);
            int count12 = getCount(rows, 1, 2);

            // Expected : 3772314000
            System.out.println((long)count11 * (long)count31 * (long)count51 * (long)count71 * (long)count12);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
