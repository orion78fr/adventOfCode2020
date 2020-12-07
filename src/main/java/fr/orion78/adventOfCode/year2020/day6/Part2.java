package fr.orion78.adventOfCode.year2020.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class Part2 {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("day6.txt"))) {
            Iterator<String> lines = r.lines().iterator();

            int count = 0;

            HashMap<Integer, Integer> m = new HashMap<>();
            int nbLines = 0;

            while (lines.hasNext()) {
                String l = lines.next();
                if (l.isEmpty()) {
                    final int nbLinesFinal = nbLines; // Shitty things we must do...
                    count += m.values().stream().filter(c -> c == nbLinesFinal).count();
                    m.clear();
                    nbLines = 0;
                } else {
                    l.chars().forEach(c -> m.put(c, m.getOrDefault(c, 0) + 1));
                    nbLines++;
                }
            }

            final int nbLinesFinal = nbLines;
            count += m.values().stream().filter(c -> c == nbLinesFinal).count();

            // Expected : 3193
            System.out.println("Count : " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
