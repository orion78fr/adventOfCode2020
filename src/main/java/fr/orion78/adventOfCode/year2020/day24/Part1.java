package fr.orion78.adventOfCode.year2020.day24;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {
    private record Point2d(double x, double y) {
        public static final Point2d ZERO = new Point2d(0, 0);

        public Point2d translate(Point2d p) {
            return new Point2d(this.x + p.x, this.y + p.y);
        }

        public Point2d translate(Direction dir) {
            return new Point2d(this.x + dir.x, this.y + dir.y);
        }
    }

    private enum Direction {
        NORTH_EAST("ne", .5, -1),
        NORTH_WEST("nw", -.5, -1),
        SOUTH_EAST("se", .5, 1),
        SOUTH_WEST("sw", -.5, 1),
        EAST("e", 1, 0),
        WEST("w", -1, 0);

        private String dirStr;
        private double x;
        private double y;

        Direction(String dirStr, double x, double y) {
            this.dirStr = dirStr;
            this.x = x;
            this.y = y;
        }

        public String getDirStr() {
            return dirStr;
        }

        private static Map<String, Direction> m;

        static {
            m = Arrays.stream(Direction.values()).collect(Collectors.toMap(Direction::getDirStr, Function.identity()));
        }

        static Direction getDir(String str) {
            return m.get(str);
        }
    }

    public static void main(String[] args) throws IOException {
        long n = Utils.readFileForDay(24, Part1::compute);

        System.out.println("Result is " + n);
    }

    public static long compute(Stream<String> inputStream) {
        Pattern pattern = Pattern.compile(Arrays.stream(Direction.values())
                .map(Direction::getDirStr)
                .collect(Collectors.joining("|", "(", ")")));


        Map<Point2d, Integer> tiles = inputStream.map(s ->
                pattern.matcher(s).results()
                        .reduce(Point2d.ZERO, (a, b) -> a.translate(Direction.getDir(b.group())), Point2d::translate))
                .collect(Collectors.toMap(Function.identity(), p -> 1, Integer::sum));

        return tiles.values().stream()
                .filter(i -> i % 2 == 1)
                .count();
    }
}
