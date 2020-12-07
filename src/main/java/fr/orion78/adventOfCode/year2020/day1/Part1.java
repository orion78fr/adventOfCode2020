package fr.orion78.adventOfCode.year2020.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("day1.txt"))) {
            // Récupérer la liste des dépenses en tant qu'entier
            Set<Integer> expenses = r.lines().map(Integer::parseInt).collect(Collectors.toSet());

            int result = -1;
            // Pour chacun des entiers de la liste
            for (Integer i : expenses) {
                // Chercher si y'a le complément à 2020
                if (expenses.contains(2020 - i)) {
                    result = i;
                    break;
                }
            }

            // Expected : 319531
            System.out.format("%d x %d = %d", result, 2020 - result, (2020 - result) * result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
