package fr.orion78.adventOfCode.year2020.day11;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {
    public record Point(int x, int y) {
        public Point translate(Point p) {
            return new Point(x + p.x, y + p.y);
        }
    }

    public static Point[] neighbours = new Point[]{
            new Point(0, 1),
            new Point(0, -1),
            new Point(1, 0),
            new Point(-1, 0),
            new Point(1, 1),
            new Point(-1, 1),
            new Point(1, -1),
            new Point(-1, -1),
    };

    public static void main(String[] args) throws IOException {
        long nbSeat = Utils.readFileForDay(11, Part1::compute);

        // Expected : 2296
        System.out.println("Number of occupied seat : " + nbSeat);
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
                    if (Arrays.stream(neighbours).map(pos::translate)
                            .filter(s -> oldSeats.getOrDefault(s, false)).count() >= 4) {
                        // Too much neighbours
                        newSeats.put(pos, false);
                        modified = true;
                    } else {
                        newSeats.put(pos, true);
                    }
                } else {
                    if (Arrays.stream(neighbours).map(pos::translate)
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
