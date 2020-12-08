package fr.orion78.adventOfCode.year2020.day2;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.stream.Stream;

public class Part2 {
    private static record PasswordEntry(Rule r, String password) {
        public boolean verifyPassword() {
            return r.verify(password);
        }
    }

    private static record Rule(int idx1, int idx2, char letter) {
        public boolean verify(String password) {
            char char1 = password.charAt(idx1 - 1);
            char char2 = password.charAt(idx2 - 1);
            return (char1 == letter || char2 == letter) && char1 != char2;
        }
    }

    public static void main(String[] args) throws IOException {
        long nbValidPasswords = Utils.readFileForDay(2, Part2::compute);

        // Expected : 451
        System.out.println(nbValidPasswords + " valid passwords");
    }

    public static long compute(Stream<String> lines) {
        return lines.filter(l -> l.length() > 0).map(l -> {
            String[] s = l.split("\\:");
            String password = s[1].trim();

            String[] rule = s[0].split(" ");

            String[] indexes = rule[0].split("\\-");

            return new PasswordEntry(
                    new Rule(Integer.parseInt(indexes[0]), Integer.parseInt(indexes[1]), rule[1].charAt(0)),
                    password
            );
        }).filter(PasswordEntry::verifyPassword).count();
    }
}
