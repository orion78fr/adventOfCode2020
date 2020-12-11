package fr.orion78.adventOfCode.year2020.day11;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {
    public record Point(int x, int y) {
        public Point translate(Point p) {
            return new Point(x + p.x, y + p.y);
        }
    }

    public static void main(String[] args) throws IOException {
        long nbSeat = Utils.readFileForDay(11, Part2::compute);

        // Expected : 2089
        System.out.println("Number of occupied seat : " + nbSeat);
    }

    public static void addNeighbor(Map<Point, List<Point>> m, Point k, Point p){
        m.compute(k, (a, l) -> {
            if (l == null) {
                l = new ArrayList<>();
            }
            l.add(p);
            return l;
        });
    }

    public static long compute(Stream<String> lines) {
        Map<Point, Boolean> seats = new HashMap<>();

        List<String> seatList = lines.collect(Collectors.toList());
        for (int i = 0; i < seatList.size(); i++) {
            String seatRow = seatList.get(i);
            for (int j = 0; j < seatRow.length(); j++) {
                if (seatRow.charAt(j) == 'L') { // Free seat
                    seats.put(new Point(i, j), false);
                }
            }
        }

        // Find visible neighbour for each point
        Map<Point, List<Point>> neighbours = new HashMap<>();

        for (Point p : seats.keySet()) {
            // Right
            seats.keySet().stream()
                    .filter(k -> k.y == p.y)
                    .filter(k -> k.x > p.x)
                    .min(Comparator.comparing(Point::x))
                    .ifPresent(n -> addNeighbor(neighbours, p, n));
            // Left
            seats.keySet().stream()
                    .filter(k -> k.y == p.y)
                    .filter(k -> k.x < p.x)
                    .max(Comparator.comparing(Point::x))
                    .ifPresent(n -> addNeighbor(neighbours, p, n));
            // Down
            seats.keySet().stream()
                    .filter(k -> k.x == p.x)
                    .filter(k -> k.y > p.y)
                    .min(Comparator.comparing(Point::y))
                    .ifPresent(n -> addNeighbor(neighbours, p, n));
            // Up
            seats.keySet().stream()
                    .filter(k -> k.x == p.x)
                    .filter(k -> k.y < p.y)
                    .max(Comparator.comparing(Point::y))
                    .ifPresent(n -> addNeighbor(neighbours, p, n));

            // Bottom right
            seats.keySet().stream()
                    .filter(k -> k.x == p.x + (k.y - p.y))
                    .filter(k -> k.y > p.y)
                    .min(Comparator.comparing(Point::y))
                    .ifPresent(n -> addNeighbor(neighbours, p, n));
            // Top left
            seats.keySet().stream()
                    .filter(k -> k.x == p.x + (k.y - p.y))
                    .filter(k -> k.y < p.y)
                    .max(Comparator.comparing(Point::y))
                    .ifPresent(n -> addNeighbor(neighbours, p, n));

            // Bottom left
            seats.keySet().stream()
                    .filter(k -> k.x == p.x + (p.y - k.y))
                    .filter(k -> k.y > p.y)
                    .min(Comparator.comparing(Point::y))
                    .ifPresent(n -> addNeighbor(neighbours, p, n));
            // Top right
            seats.keySet().stream()
                    .filter(k -> k.x == p.x + (p.y - k.y))
                    .filter(k -> k.y < p.y)
                    .max(Comparator.comparing(Point::y))
                    .ifPresent(n -> addNeighbor(neighbours, p, n));
        }

        System.out.println("Neighbors found");

        boolean modified;

        do {
            modified = false;

            // To not modify the map we are iterating
            final Map<Point, Boolean> oldSeats = seats;
            Map<Point, Boolean> newSeats = new HashMap<>(seats.size());

            for (var e : seats.entrySet()) {
                Point pos = e.getKey();
                boolean currentlyOccupied = e.getValue();

                if (currentlyOccupied) {
                    if (neighbours.get(pos).stream()
                            .filter(s -> oldSeats.getOrDefault(s, false)).count() >= 5) {
                        // Too much neighbours
                        newSeats.put(pos, false);
                        modified = true;
                    } else {
                        newSeats.put(pos, true);
                    }
                } else {
                    if (neighbours.get(pos).stream()
                            .anyMatch(s -> oldSeats.getOrDefault(s, false))) {
                        // It has a neighbour
                        newSeats.put(pos, false);
                    } else {
                        // No neighbour, so become occupied
                        newSeats.put(pos, true);
                        modified = true;
                    }
                }
            }

            seats = newSeats;
        } while (modified);

        return seats.values().stream().filter(b -> b).count();
    }
}
