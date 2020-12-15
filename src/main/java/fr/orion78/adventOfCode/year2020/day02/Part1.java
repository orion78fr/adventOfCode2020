package fr.orion78.adventOfCode.year2020.day02;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.stream.Stream;

public class Part1 {
    private static record PasswordEntry(Rule r, String password) {
        public boolean verifyPassword() {
            return r.verify(password);
        }
    }

    private static record Rule(long min, long max, char letter) {
        public boolean verify(String password) {
            long occurrence = password.chars().filter(c -> c == letter).count();
            return occurrence >= min && occurrence <= max;
        }
    }

    public static void main(String[] args) throws IOException {
        long nbValidPasswords = Utils.readFileForDay(2, Part1::compute);

        System.out.println(nbValidPasswords + " valid passwords");
    }

    public static long compute(Stream<String> lines) {
        return lines.filter(l -> l.length() > 0).map(l -> {
            String[] s = l.split("\\:");
            String password = s[1].trim();

            String[] rule = s[0].split(" ");

            String[] limits = rule[0].split("\\-");

            return new PasswordEntry(
                    new Rule(Long.parseLong(limits[0]), Long.parseLong(limits[1]), rule[1].charAt(0)),
                    password
            );
        }).filter(PasswordEntry::verifyPassword).count();
    }
}
