package fr.orion78.adventOfCode.year2020.day23;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2bis {
    public static void main(String[] args) throws IOException {
        long n = Utils.readFileForDay(23, Part2bis::compute, 10_000_000, 1_000_000);

        System.out.println("Result is " + n);
    }

    public static long compute(Stream<String> inputStream, int iter, int maxCup) {
        List<Integer> cupsInit = inputStream.flatMap(s -> s.chars().boxed())
                .map(c -> (char) c.intValue())
                .map(Object::toString)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        CircularIntHashList cups = new CircularIntHashList(maxCup + 1, cupsInit);

        if (maxCup > cupsInit.size()) {
            cups.putItemAfter(cupsInit.get(cupsInit.size() - 1), cupsInit.size() + 1);

            for (int i = cupsInit.size() + 1; i < maxCup; i++) {
                cups.putItemAfter(i, i + 1);
            }
        }

        int currentCup = cupsInit.get(0);

        for (int i = 0; i < iter; i++) {
            int nextCup1 = cups.removeItemAfter(currentCup);
            int nextCup2 = cups.removeItemAfter(currentCup);
            int nextCup3 = cups.removeItemAfter(currentCup);

            int destination = currentCup - 1;
            if (destination == 0) {
                destination = maxCup;
            }
            while (destination == nextCup1 || destination == nextCup2 || destination == nextCup3) {
                destination--;
                if (destination == 0) {
                    destination = maxCup;
                }
            }

            cups.putItemAfter(destination, nextCup3);
            cups.putItemAfter(destination, nextCup2);
            cups.putItemAfter(destination, nextCup1);

            currentCup = cups.getItemAfter(currentCup);
        }

        return (long) cups.removeItemAfter(1) * (long) cups.removeItemAfter(1);
    }
}
