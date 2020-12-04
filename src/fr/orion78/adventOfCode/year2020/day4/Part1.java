package fr.orion78.adventOfCode.year2020.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Part1 {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("day4.txt"))) {
            Iterator<String> rows = r.lines().iterator();

            int count = 0;
            Map<String, String> m = new HashMap<>();
            while (rows.hasNext()) {
                String s = rows.next();
                if (s.isEmpty()) {
                    // Empty line so it's a new entry
                    if (m.size() == 7 || m.size() == 8) {
                        if (m.containsKey("byr")
                                && m.containsKey("iyr")
                                && m.containsKey("eyr")
                                && m.containsKey("hgt")
                                && m.containsKey("hcl")
                                && m.containsKey("ecl")
                                && m.containsKey("pid")) {
                            count++;
                        }
                    }

                    m.clear();
                } else {
                    for (String sub : s.split(" ")) {
                        String[] entry = sub.split("\\:");
                        m.put(entry[0], entry[1]);
                    }
                }
            }

            // Expected 228
            System.out.println(count + " valid passports");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
