package fr.orion78.adventOfCode.year2020.day22;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws IOException {
        long n = Utils.readFileForDay(22, Part2::compute);

        System.out.println("Result is " + n);
    }

    public static long compute(Stream<String> inputStream) {
        Iterator<String> lines = inputStream.iterator();

        Deque<Integer> deck1 = new ArrayDeque<>();
        Deque<Integer> deck2 = new ArrayDeque<>();

        String line = lines.next();
        while (lines.hasNext() && !(line = lines.next()).isEmpty()) {
            deck1.add(Integer.parseInt(line));
        }
        line = lines.next();
        while (lines.hasNext() && !(line = lines.next()).isEmpty()) {
            deck2.add(Integer.parseInt(line));
        }

        subGame(deck1, deck2);

        Deque<Integer> winnerDeck = deck1.isEmpty() ? deck2 : deck1;

        long sum = 0;
        long i = 1;

        while (!winnerDeck.isEmpty()) {
            sum += winnerDeck.removeLast() * i++;
        }

        return sum;
    }

    private static boolean subGame(Deque<Integer> deck1, Deque<Integer> deck2) {
        Set<int[]> previousArrangements = new TreeSet<>(Arrays::compare);

        while (!(deck1.isEmpty() || deck2.isEmpty())) {
            if (!previousArrangements.add(getArrangement(deck1, deck2))) {
                return true;
            }
            Integer card1 = deck1.pop();
            Integer card2 = deck2.pop();

            boolean deck1Winner;

            if (deck1.size() >= card1 && deck2.size() >= card2) {
                Deque<Integer> subDeck1 = new ArrayDeque<>();
                Deque<Integer> subDeck2 = new ArrayDeque<>();

                Iterator<Integer> iter = deck1.iterator();
                for (int i = 0; i < card1; i++) {
                    subDeck1.add(iter.next());
                }
                iter = deck2.iterator();
                for (int i = 0; i < card2; i++) {
                    subDeck2.add(iter.next());
                }

                deck1Winner = subGame(subDeck1, subDeck2);
            } else {
                deck1Winner = card1 > card2;
            }

            if (deck1Winner) {
                deck1.add(card1);
                deck1.add(card2);
            } else {
                deck2.add(card2);
                deck2.add(card1);
            }
        }

        return deck2.isEmpty();
    }

    private static int[] getArrangement(Collection<Integer> deck1, Collection<Integer> deck2) {
        int[] arrangement = new int[deck1.size() + deck2.size() + 1];
        int i = 0;

        for (Integer integer : deck1) {
            arrangement[i++] = integer;
        }
        arrangement[i++] = -1;
        for (Integer integer : deck2) {
            arrangement[i++] = integer;
        }

        return arrangement;
    }
}
