package fr.orion78.adventOfCode.year2020.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;
import java.util.Iterator;

public class Part1 {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("day6.txt"))) {
            Iterator<String> lines = r.lines().iterator();

            int count = 0;

            BitSet bs = new BitSet();
            while (lines.hasNext()) {
                String l = lines.next();
                if (l.isEmpty()) {
                    count += bs.cardinality();
                    bs.clear();
                } else {
                    l.chars().forEach(bs::set);
                }
            }

            count += bs.cardinality();

            // Expected : 6310
            System.out.println("Count : " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
