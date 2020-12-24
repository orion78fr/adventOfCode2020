package fr.orion78.adventOfCode.year2020.day20;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.util.Arrays;
import java.util.List;

public class Tile {
    private final int tileNum;
    private final List<String> tileParts;
    private final long[] borders;
    private final long[] flipped;

    private final long[] both;

    public Tile(int tileNum, List<String> tileParts) {
        this.tileNum = tileNum;
        this.tileParts = tileParts;
        this.borders = parseBorders(tileParts);
        this.flipped = parseInvertedBorders(tileParts);

        this.both = Arrays.copyOf(this.borders, 8);
        System.arraycopy(this.flipped, 0, this.both, 4, 4);
    }

    private static long[] parseBorders(List<String> tileParts) {
        long[] res = new long[4];

        res[0] = Utils.fromBinary(tileParts.get(0), '#');
        res[1] = Utils.fromBinary(tileParts.stream().mapToInt(tilePart -> tilePart.charAt(tilePart.length() - 1)), '#');
        String lastLine = tileParts.get(tileParts.size() - 1);
        res[2] = Utils.fromBinary(Utils.revIntRange(0, lastLine.length()).map(lastLine::charAt), '#');
        res[3] = Utils.fromBinary(Utils.revIntRange(0, tileParts.size()).map(i -> tileParts.get(i).charAt(0)), '#');

        return res;
    }

    private static long[] parseInvertedBorders(List<String> tileParts) {
        long[] res = new long[4];

        String firstLine = tileParts.get(0);
        res[0] = Utils.fromBinary(Utils.revIntRange(0, firstLine.length()).map(firstLine::charAt), '#');
        res[1] = Utils.fromBinary(tileParts.stream().mapToInt(tilePart -> tilePart.charAt(0)), '#');
        res[2] = Utils.fromBinary(tileParts.get(tileParts.size() - 1), '#');
        res[3] = Utils.fromBinary(Utils.revIntRange(0, tileParts.size()).map(i -> {
            String s = tileParts.get(i);
            return s.charAt(s.length() - 1);
        }), '#');

        return res;
    }

    public int getTileNum() {
        return tileNum;
    }

    public List<String> getTileParts() {
        return tileParts;
    }

    public long[] getBorders() {
        return borders;
    }

    public long[] getFlipped() {
        return flipped;
    }

    public long[] getBoth() {
        return both;
    }

    public int getTileSize() {
        return tileParts.get(0).length() - 2;
    }
}
