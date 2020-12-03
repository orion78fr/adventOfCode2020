package fr.orion78.adventOfCode.year2020.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("day3.txt"))) {
            List<String> rows = r.lines().collect(Collectors.toList());

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

            // Expected : 195
            System.out.println(count + " trees");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
