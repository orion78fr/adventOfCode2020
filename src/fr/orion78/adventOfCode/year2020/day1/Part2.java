package fr.orion78.adventOfCode.year2020.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("day1.txt"))) {
            // Récupérer la liste des dépenses en tant qu'entier
            List<Integer> expenses = r.lines().map(Integer::parseInt).collect(Collectors.toList());

            for (int i = 0; i < expenses.size(); i++) {
                int expense1 = expenses.get(i);
                for (int j = i + 1; j < expenses.size(); j++) {
                    int expense2 = expenses.get(j);
                    for (int k = j + 1; k < expenses.size(); k++) {
                        int expense3 = expenses.get(k);
                        if (expense1 + expense2 + expense3 == 2020) {
                            // Expected 244300320
                            System.out.format("%d x %d x %d = %d", expense1, expense2, expense3, expense1 * expense2 * expense3);
                            return;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
