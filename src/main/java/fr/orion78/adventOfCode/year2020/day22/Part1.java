package fr.orion78.adventOfCode.year2020.day22;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        long n = Utils.readFileForDay(22, Part1::compute);

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

        while (!(deck1.isEmpty() || deck2.isEmpty())) {
            Integer card1 = deck1.pop();
            Integer card2 = deck2.pop();

            if (card1 > card2) {
                deck1.add(card1);
                deck1.add(card2);
            } else {
                deck2.add(card2);
                deck2.add(card1);
            }
        }

        Deque<Integer> winnerDeck = deck1.isEmpty() ? deck2 : deck1;

        long sum = 0;
        long i = 1;

        while (!winnerDeck.isEmpty()) {
            sum += winnerDeck.removeLast() * i++;
        }

        return sum;
    }
}
