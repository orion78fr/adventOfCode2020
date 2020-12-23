package fr.orion78.adventOfCode.year2020.day23;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        String n = Utils.readFileForDay(23, Part1::compute, 100);

        System.out.println("Result is " + n);
    }

    public static String compute(Stream<String> inputStream, int iter) {
        List<Integer> cups = inputStream.flatMap(s -> s.chars().boxed())
                .map(c -> (char) c.intValue())
                .map(Object::toString)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int size = cups.size();

        int nextIdx = 0;

        for (int i = 0; i < iter; i++) {
            Integer currentCup = cups.get(nextIdx);

            Integer nextCup1 = cups.remove((cups.indexOf(currentCup) + 1) % cups.size());
            Integer nextCup2 = cups.remove((cups.indexOf(currentCup) + 1) % cups.size());
            Integer nextCup3 = cups.remove((cups.indexOf(currentCup) + 1) % cups.size());

            int destination = currentCup - 1;
            if (destination == 0) {
                destination = size;
            }
            while (List.of(nextCup1, nextCup2, nextCup3).contains(destination)) {
                destination = (destination - 1) % size;
                if (destination == 0) {
                    destination = size;
                }
            }

            int insertIdx = cups.indexOf(destination);

            cups.add(insertIdx + 1, nextCup3);
            cups.add(insertIdx + 1, nextCup2);
            cups.add(insertIdx + 1, nextCup1);

            nextIdx = (cups.indexOf(currentCup) + 1) % size;
        }

        StringBuilder result = new StringBuilder();
        int offset = cups.indexOf(1);

        for (int i = 0; i < size - 1; i++) {
            result.append(cups.get((offset + i + 1) % size));
        }

        return result.toString();
    }
}
