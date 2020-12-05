package fr.orion78.adventOfCode.year2020.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("day5.txt"))) {
            int max = r.lines().filter(s -> !s.isEmpty())
                    .mapToInt(Part1::getSeatNB)
                    .max().orElse(-1);

            // Expected : 866
            System.out.println("Highest seat ID : " + max);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
