package fr.orion78.adventOfCode.year2020.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {
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
        int idx1;
        int idx2;
        char letter;

        public Rule(int idx1, int idx2, char letter) {
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.letter = letter;
        }

        public boolean verify(String password) {
            char char1 = password.charAt(idx1-1);
            char char2 = password.charAt(idx2-1);
            return (char1 == letter || char2 == letter) && char1 != char2;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("day2.txt"))) {
            long nbValidPasswords = r.lines().filter(l -> l.length() > 0).map(l -> {
                String[] s = l.split("\\:");
                String password = s[1].trim();

                String[] rule = s[0].split(" ");

                String[] indexes = rule[0].split("\\-");

                return new PasswordEntry(new Rule(Integer.parseInt(indexes[0]), Integer.parseInt(indexes[1]), rule[1].charAt(0)),
                        password);
            }).filter(PasswordEntry::verifyPassword).count();

            // Expected : 451
            System.out.println(nbValidPasswords + " valid passwords");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
