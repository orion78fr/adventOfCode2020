package fr.orion78.adventOfCode.year2020.day17;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {
    public record Point3d(int x, int y, int z) {
        public Set<Point3d> getNeighbours() {
            return Set.of(
                    this.translate(-1, -1, -1),
                    this.translate(-1, -1, 0),
                    this.translate(-1, -1, 1),
                    this.translate(-1, 0, -1),
                    this.translate(-1, 0, 0),
                    this.translate(-1, 0, 1),
                    this.translate(-1, 1, -1),
                    this.translate(-1, 1, 0),
                    this.translate(-1, 1, 1),

                    this.translate(0, -1, -1),
                    this.translate(0, -1, 0),
                    this.translate(0, -1, 1),
                    this.translate(0, 0, -1),
                    this.translate(0, 0, 0),
                    this.translate(0, 0, 1),
                    this.translate(0, 1, -1),
                    this.translate(0, 1, 0),
                    this.translate(0, 1, 1),

                    this.translate(1, -1, -1),
                    this.translate(1, -1, 0),
                    this.translate(1, -1, 1),
                    this.translate(1, 0, -1),
                    this.translate(1, 0, 0),
                    this.translate(1, 0, 1),
                    this.translate(1, 1, -1),
                    this.translate(1, 1, 0),
                    this.translate(1, 1, 1)
            );
        }

        public Point3d translate(int x, int y, int z) {
            return new Point3d(x + this.x, y + this.y, z + this.z);
        }
    }

    public static class State {
        private final Set<Point3d> activeCells;

        public State() {
            this.activeCells = new HashSet<>();
        }

        public void addCell(Point3d cell) {
            this.activeCells.add(cell);
        }

        public Set<Point3d> neighbours() {
            Set<Point3d> neighbours = new HashSet<>();

            for (Point3d activeCell : activeCells) {
                neighbours.addAll(activeCell.getNeighbours());
            }

            return neighbours;
        }

        public int size(){
            return activeCells.size();
        }

        public int activeNeighbours(Point3d p) {
            return (int) p.getNeighbours()
                    .stream()
                    .filter(point -> !point.equals(p))
                    .filter(activeCells::contains)
                    .count();
        }

        public boolean contains(Point3d p) {
            return activeCells.contains(p);
        }
    }

    public static void main(String[] args) throws IOException {
        int n = Utils.readFileForDay(17, Part1::compute, 6);

        System.out.println("Number of active cubes " + n);
    }

    public static int compute(Stream<String> lines, int maxIter) {
        List<String> initialState = lines.collect(Collectors.toList());

        State currentState = new State();

        for (int y = 0; y < initialState.size(); y++) {
            String stateLine = initialState.get(y);
            char[] stateLineChars = stateLine.toCharArray();
            for (int x = 0; x < stateLineChars.length; x++) {
                char stateChar = stateLineChars[x];
                if (stateChar == '#') {
                    currentState.addCell(new Point3d(x, y, 0));
                }
            }
        }

        for (int i = 0; i < maxIter; i++) {
            State newState = new State();

            for (Point3d p : currentState.neighbours()) {
                int activeNeighbours = currentState.activeNeighbours(p);
                if(currentState.contains(p)) {
                    if (activeNeighbours == 2 || activeNeighbours == 3) {
                        newState.addCell(p);
                    }
                } else {
                    if(activeNeighbours == 3) {
                        newState.addCell(p);
                    }
                }
            }

            currentState = newState;
        }

        return currentState.size();
    }
}
