package fr.orion78.adventOfCode.year2020.day20;

import fr.orion78.adventOfCode.year2020.util.Pair;
import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) throws IOException {
        long n = Utils.readFileForDay(20, Part1::compute);

        System.out.println("Result is " + n);
    }

    public static long compute(Stream<String> inputStream) {
        Iterator<String> lines = inputStream.iterator();

        Map<Integer, Tile> tiles = new HashMap<>();

        while (lines.hasNext()) {
            String tileNumLine = lines.next();

            List<String> tileParts = new ArrayList<>();
            String tilePart;

            while (lines.hasNext() && !(tilePart = lines.next()).isEmpty()) {
                tileParts.add(tilePart);
            }

            String tileNumAsString = tileNumLine.split(" ")[1];
            int tileNumAsInt = Integer.parseInt(tileNumAsString.substring(0, tileNumAsString.length() - 1));
            Tile tile = new Tile(tileNumAsInt, tileParts);

            tiles.put(tileNumAsInt, tile);
        }

        Map<Long, List<Integer>> possibleConnections = tiles.values().stream()
                .flatMap(t -> IntStream.range(0, 8).mapToObj(i -> new Pair<>(i, t)))
                .collect(Collectors.toMap(p -> p.r().getBoth()[p.l()],
                        p -> {
                            List<Integer> l = new ArrayList<>();
                            l.add(p.r().getTileNum());
                            return l;
                        },
                        (l, r) -> {
                            l.addAll(r);
                            return l;
                        }));

        Map<Integer, Integer> borders = possibleConnections.entrySet().stream()
                .filter(e -> e.getValue().size() == 1) // Borders don't connect to anything on at least one side
                .flatMap(e -> e.getValue().stream())
                .collect(Collectors.toMap(Function.identity(), x -> 1, Integer::sum));

        return borders.entrySet().stream()
                .filter(e -> e.getValue() == 4) // Corners have only 2 connections (x2 with the flipped entries)
                .mapToLong(Map.Entry::getKey)
                .reduce(1, (a, b) -> a * b);
    }
}
