package fr.orion78.adventOfCode.year2020.day6;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws IOException {
        int count = Utils.readFileForDay(6, Part2::compute);

        // Expected : 3193
        System.out.println("Count : " + count);
    }

    public static int compute(Stream<String> lines) {
        Iterator<String> linesIt = lines.iterator();

        int count = 0;

        HashMap<Integer, Integer> m = new HashMap<>();
        int nbLines = 0;

        while (linesIt.hasNext()) {
            String l = linesIt.next();
            if (l.isEmpty()) {
                final int nbLinesFinal = nbLines; // Shitty things we must do...
                count += m.values().stream().filter(c -> c == nbLinesFinal).count();
                m.clear();
                nbLines = 0;
            } else {
                l.chars().forEach(c -> m.put(c, m.getOrDefault(c, 0) + 1));
                nbLines++;
            }
        }

        final int nbLinesFinal = nbLines;
        count += m.values().stream().filter(c -> c == nbLinesFinal).count();

        return count;
    }
}
