package fr.orion78.adventOfCode.year2020.day1;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {
    public static record Expenses(int expense1, int expense2, int expense3) {
    }

    public static void main(String[] args) throws IOException {
        Expenses result = Utils.readFileForDay(1, Part2::compute);

        // Expected 244300320
        System.out.format("%d x %d x %d = %d", result.expense1, result.expense2, result.expense3,
                result.expense1 * result.expense2 * result.expense3);
    }

    public static Expenses compute(Stream<String> lines) {
        List<Integer> expenses = lines.map(Integer::parseInt).collect(Collectors.toList());

        for (int i = 0; i < expenses.size(); i++) {
            int expense1 = expenses.get(i);
            for (int j = i + 1; j < expenses.size(); j++) {
                int expense2 = expenses.get(j);
                for (int k = j + 1; k < expenses.size(); k++) {
                    int expense3 = expenses.get(k);
                    if (expense1 + expense2 + expense3 == 2020) {
                        return new Expenses(expense1, expense2, expense3);
                    }
                }
            }
        }

        throw new RuntimeException();
    }
}
