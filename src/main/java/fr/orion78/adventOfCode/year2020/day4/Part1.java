package fr.orion78.adventOfCode.year2020.day4;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        int count = Utils.readFileForDay(4, Part1::compute);

        // Expected 228
        System.out.println(count + " valid passports");
    }

    public static int compute(Stream<String> lines) {
        Iterator<String> rows = lines.iterator();

        int count = 0;
        Map<String, String> m = new HashMap<>();
        while (rows.hasNext()) {
            String s = rows.next();
            if (s.isEmpty()) {
                // Empty line so it's a new entry
                if (m.size() == 7 || m.size() == 8) {
                    if (m.containsKey("byr") && m.containsKey("iyr") && m.containsKey("eyr") && m.containsKey("hgt")
                            && m.containsKey("hcl") && m.containsKey("ecl") && m.containsKey("pid")) {
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

        return count;
    }
}
