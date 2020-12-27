package fr.orion78.adventOfCode.year2020.day20;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class TileMap {
    private final int tileSize;
    private final Map<Point2d, Tile> tiles;
    private final Map<Point2d, Orientation> tileOrientations;

    private int maxX = 0;
    private int maxY = 0;

    public TileMap(Tile topLeftCorner, Orientation topLeftCornerConnectedOrientation) {
        this.tileSize = topLeftCorner.getTileSize();

        this.tiles = new HashMap<>();
        this.tiles.put(new Point2d(0, 0), topLeftCorner);

        this.tileOrientations = new HashMap<>();
        this.tileOrientations.put(new Point2d(0, 0), topLeftCornerConnectedOrientation);
    }

    public Tile getTile(int x, int y) {
        return tiles.get(new Point2d(x, y));
    }

    public long getBorder(int x, int y, Orientation orientation) {
        Point2d p = new Point2d(x, y);
        Orientation tileOrientation = tileOrientations.get(p);

        if (!tileOrientation.isFlipped()) {
            // Normal
            return tiles.get(p).getBorders()[(tileOrientation.getTabOffset() + orientation.getTabOffset()) % 4];
        } else {
            // Flipped
            return tiles.get(p).getFlipped()[(tileOrientation.getTabOffset() + orientation.getTabOffset()) % 4];
        }
    }

    public void put(int x, int y, Tile t, Orientation orientation) {
        Point2d p = new Point2d(x, y);
        tiles.put(p, t);
        tileOrientations.put(p, orientation);

        maxX = Math.max(maxX, x);
        maxY = Math.max(maxY, y);
    }

    public long cardinality() {
        return this.tiles.values().stream()
                .mapToLong(Tile::cardinality)
                .sum();
    }

    public long countPattern(List<String> pattern) {
        long patternCount = pattern.stream().flatMap(s -> s.chars().boxed()).filter(c -> c == '#').count();

        int patternX = pattern.get(0).length();
        int patternY = pattern.size();

        int maxX = tileSize * (this.maxX + 1) - patternX;
        int maxY = tileSize * (this.maxY + 1) - patternY;

        return IntStream.range(0, maxY).parallel()
                .boxed()
                .flatMap(y -> IntStream.range(0, maxX).mapToObj(x -> new Point2d(x, y)))
                .filter(p -> IntStream.range(0, pattern.size())
                        .boxed()
                        .flatMap(py -> IntStream.range(0, pattern.get(py).length())
                                .filter(px -> pattern.get(py).charAt(px) == '#')
                                .mapToObj(px -> new Point2d(p.x() + px, p.y() + py)))
                        .filter(this::getVal)
                        .count() == patternCount)
                .count();
    }

    public boolean getVal(Point2d p) {
        return getVal(p.x(), p.y());
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public boolean getVal(int x, int y) {
        int tileX = x / tileSize;
        int tileY = y / tileSize;
        Point2d tileP = new Point2d(tileX, tileY);

        Orientation orientation = tileOrientations.get(tileP);
        if (orientation == null) {
            return false;
        }

        int localX = x % tileSize;
        int localY = y % tileSize;

        int orientedX;
        int orientedY;

        switch (orientation) {
            case NORMAL_NORTH -> {
                orientedX = localX;
                orientedY = localY;
            }
            case NORMAL_EAST -> {
                orientedX = tileSize - localY - 1;
                orientedY = localX;
            }
            case NORMAL_SOUTH -> {
                orientedX = tileSize - localX - 1;
                orientedY = tileSize - localY - 1;
            }
            case NORMAL_WEST -> {
                orientedX = localY;
                orientedY = tileSize - localX - 1;
            }
            case FLIPPED_NORTH -> {
                orientedX = tileSize - localX - 1;
                orientedY = localY;
            }
            case FLIPPED_EAST -> {
                orientedX = localY;
                orientedY = localX;
            }
            case FLIPPED_SOUTH -> {
                orientedX = localX;
                orientedY = tileSize - localY - 1;
            }
            case FLIPPED_WEST -> {
                orientedX = tileSize - localY - 1;
                orientedY = tileSize - localX - 1;
            }
            default -> throw new RuntimeException();
        }

        return tiles.get(tileP).getVal(orientedX, orientedY);
    }
}
