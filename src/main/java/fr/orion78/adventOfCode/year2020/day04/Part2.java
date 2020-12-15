package fr.orion78.adventOfCode.year2020.day04;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws IOException {
        int count = Utils.readFileForDay(4, Part2::compute);

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
                    if (m.getOrDefault("byr", "").matches("19[2-9][0-9]|200[0-2]")
                            && m.getOrDefault("iyr", "").matches("201[0-9]|2020")
                            && m.getOrDefault("eyr", "").matches("202[0-9]|2030")
                            && m.getOrDefault("hgt", "").matches("((1[5-9][0-9]|18[0-3])cm|(59|6[0-9]|7[0-6])in)")
                            && m.getOrDefault("hcl", "").matches("#[0-9a-f]{6}")
                            && m.getOrDefault("ecl", "").matches("amb|blu|brn|gry|grn|hzl|oth")
                            && m.getOrDefault("pid", "").matches("[0-9]{9}")) {
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
