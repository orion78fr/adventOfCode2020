package fr.orion78.adventOfCode.year2020.day20;

import java.util.HashMap;
import java.util.Map;

public class TileMap {
    private final int tileSize;
    private final Map<Point2d, Tile> tiles;
    private final Map<Point2d, Orientation> tileOrientations;

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
    }

    public long cardinality(){
        return this.tiles.values().stream()
                .mapToLong(Tile::cardinality)
                .sum();
    }
}
