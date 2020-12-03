package fr.orion78.adventOfCode.year2020.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {
    private static class PasswordEntry {
        Rule r;
        String password;

        public PasswordEntry(Rule r, String password) {
            this.r = r;
            this.password = password;
        }

        public boolean verifyPassword() {
            return r.verify(password);
        }
    }

    private static class Rule {
        long min;
        long max;
        char letter;

        public Rule(long min, long max, char letter) {
            this.min = min;
            this.max = max;
            this.letter = letter;
        }

        public boolean verify(String password) {
            long occurrence = password.chars().filter(c -> c == letter).count();
            return occurrence >= min && occurrence <= max;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("day2.txt"))) {
            long nbValidPasswords = r.lines().filter(l -> l.length() > 0).map(l -> {
                String[] s = l.split("\\:");
                String password = s[1].trim();

                String[] rule = s[0].split(" ");

                String[] limits = rule[0].split("\\-");


                return new PasswordEntry(new Rule(Long.parseLong(limits[0]), Long.parseLong(limits[1]), rule[1].charAt(0)),
                        password);
            }).filter(PasswordEntry::verifyPassword).count();

            // Expected : 422
            System.out.println(nbValidPasswords + " valid passwords");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
