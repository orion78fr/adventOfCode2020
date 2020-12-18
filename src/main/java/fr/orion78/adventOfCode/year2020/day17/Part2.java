package fr.orion78.adventOfCode.year2020.day17;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {
    public record Point(int[] coordinates) {
        public static Point of(int dimensions, int... coordinates) {
            return new Point(Arrays.copyOf(coordinates, dimensions));
        }

        public Set<Point> getNeighbours() {
            return getNeighboursTranslations(this.coordinates.length)
                    .map(this::translate)
                    .collect(Collectors.toSet());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return Arrays.equals(coordinates, point.coordinates);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(coordinates);
        }

        private static Stream<int[]> getNeighboursTranslations(int length) {
            if (length == 1) {
                return Stream.of(new int[]{-1}, new int[]{0}, new int[]{1});
            } else {
                return getNeighboursTranslations(length - 1)
                        .flatMap(t -> {
                            int[] a1 = Arrays.copyOf(t, length);
                            int[] a2 = Arrays.copyOf(t, length);
                            int[] a3 = Arrays.copyOf(t, length);
                            a1[length - 1] = -1;
                            a2[length - 1] = 0;
                            a3[length - 1] = 1;
                            return Stream.of(a1, a2, a3);
                        });
            }


        }

        public Point translate(int[] translation) {
            int[] res = new int[translation.length];

            for (int i = 0; i < translation.length; i++) {
                res[i] = this.coordinates[i] + translation[i];
            }

            return new Point(res);
        }
    }

    public static class State {
        private final Set<Point> activeCells;

        public State() {
            this.activeCells = new HashSet<>();
        }

        public void addCell(Point cell) {
            this.activeCells.add(cell);
        }

        public Set<Point> neighbours() {
            Set<Point> neighbours = new HashSet<>();

            for (Point activeCell : activeCells) {
                neighbours.addAll(activeCell.getNeighbours());
            }

            return neighbours;
        }

        public int size() {
            return activeCells.size();
        }

        public int activeNeighbours(Point p) {
            return (int) p.getNeighbours()
                    .stream()
                    .filter(point -> !point.equals(p))
                    .filter(activeCells::contains)
                    .count();
        }

        public boolean contains(Point p) {
            return activeCells.contains(p);
        }
    }

    public static void main(String[] args) throws IOException {
        int n = Utils.readFileForDay(17, Part2::compute, 4, 6);

        System.out.println("Number of active cubes " + n);
    }

    public static int compute(Stream<String> lines, int dimensions, int cycles) {
        List<String> initialState = lines.collect(Collectors.toList());

        State currentState = new State();

        for (int y = 0; y < initialState.size(); y++) {
            String stateLine = initialState.get(y);
            char[] stateLineChars = stateLine.toCharArray();
            for (int x = 0; x < stateLineChars.length; x++) {
                char stateChar = stateLineChars[x];
                if (stateChar == '#') {
                    currentState.addCell(Point.of(dimensions, x, y));
                }
            }
        }

        for (int i = 0; i < cycles; i++) {
            State newState = new State();

            for (Point p : currentState.neighbours()) {
                int activeNeighbours = currentState.activeNeighbours(p);
                if (currentState.contains(p)) {
                    if (activeNeighbours == 2 || activeNeighbours == 3) {
                        newState.addCell(p);
                    }
                } else {
                    if (activeNeighbours == 3) {
                        newState.addCell(p);
                    }
                }
            }

            currentState = newState;
        }

        return currentState.size();
    }
}
