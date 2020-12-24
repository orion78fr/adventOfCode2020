package fr.orion78.adventOfCode.year2020.day20;

import fr.orion78.adventOfCode.year2020.util.Pair;
import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Part2 {
    private static final String[] SEA_MONSTER = new String[]{
            "                  # ",
            "#    ##    ##    ###",
            " #  #  #  #  #  #   "
    };

    public static void main(String[] args) throws IOException {
        long n = Utils.readFileForDay(20, Part2::compute);

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

        List<Integer> corners = borders.entrySet().stream()
                .filter(e -> e.getValue() == 4) // Corners have only 2 connections (x2 with the flipped entries)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (corners.size() != 4) {
            throw new RuntimeException("Corners");
        }

        Tile topLeftCorner = tiles.get(corners.get(0));

        List<Orientation> topLeftCornerConnectedOrientations = Arrays.stream(Orientation.NORMAL_ORIENTATIONS)
                .filter(o -> possibleConnections.get(topLeftCorner.getBorders()[o.getTabOffset()]).size() > 1)
                .collect(Collectors.toList());

        if (topLeftCornerConnectedOrientations.size() != 2) {
            throw new RuntimeException("Corner connections");
        }

        Orientation topLeftCornerConnectedOrientation;

        if (topLeftCornerConnectedOrientations.contains(Orientation.NORMAL_EAST)) {
            if (topLeftCornerConnectedOrientations.contains(Orientation.NORMAL_SOUTH)) {
                topLeftCornerConnectedOrientation = Orientation.NORMAL_NORTH;
            } else {
                topLeftCornerConnectedOrientation = Orientation.NORMAL_WEST;
            }
        } else {
            if (topLeftCornerConnectedOrientations.contains(Orientation.NORMAL_SOUTH)) {
                topLeftCornerConnectedOrientation = Orientation.NORMAL_EAST;
            } else {
                topLeftCornerConnectedOrientation = Orientation.NORMAL_SOUTH;
            }
        }

        TileMap tm = new TileMap(topLeftCorner, topLeftCornerConnectedOrientation);

        boolean continuey;
        int cury = 0;
        do {
            continuey = false;

            boolean continuex;
            int curx = 0;
            do {
                continuex = false;

                long curBorder = tm.getBorder(curx, cury, Orientation.NORMAL_EAST);
                int curTileNum = tm.get(curx, cury).getTileNum();
                Optional<Tile> correspondingTileOpt = possibleConnections.get(curBorder).stream()
                        .filter(t -> !t.equals(curTileNum))
                        .findAny()
                        .map(tiles::get);

                if (correspondingTileOpt.isPresent()) {
                    continuex = true;
                    Tile correspondingTile = correspondingTileOpt.get();

                    int idx = Utils.longList(correspondingTile.getBoth()).indexOf(curBorder);
                    Orientation o = switch (idx) {
                        case 0 -> Orientation.FLIPPED_EAST;
                        case 1 -> Orientation.FLIPPED_NORTH;
                        case 2 -> Orientation.FLIPPED_WEST;
                        case 3 -> Orientation.FLIPPED_SOUTH;
                        case 4 -> Orientation.NORMAL_EAST;
                        case 5 -> Orientation.NORMAL_NORTH;
                        case 6 -> Orientation.NORMAL_WEST;
                        case 7 -> Orientation.NORMAL_SOUTH;
                        default -> throw new RuntimeException("IMPOSSIBLE");
                    };

                    tm.put(curx + 1, cury, correspondingTile, o);
                }

                curx++;
            } while (continuex);

            long border = tm.getBorder(0, cury, Orientation.NORMAL_SOUTH);
            int curTileNum = tm.get(0, cury).getTileNum();
            Optional<Tile> correspondingTileOpt = possibleConnections.get(border).stream()
                    .filter(t -> !t.equals(curTileNum))
                    .findAny()
                    .map(tiles::get);

            if (correspondingTileOpt.isPresent()) {
                continuey = true;
                Tile correspondingTile = correspondingTileOpt.get();

                int idx = Utils.longList(correspondingTile.getBoth()).indexOf(border);
                Orientation o = switch (idx) {
                    case 0 -> Orientation.FLIPPED_NORTH;
                    case 1 -> Orientation.FLIPPED_WEST;
                    case 2 -> Orientation.FLIPPED_SOUTH;
                    case 3 -> Orientation.FLIPPED_EAST;
                    case 4 -> Orientation.NORMAL_NORTH;
                    case 5 -> Orientation.NORMAL_WEST;
                    case 6 -> Orientation.NORMAL_SOUTH;
                    case 7 -> Orientation.NORMAL_EAST;
                    default -> throw new RuntimeException("IMPOSSIBLE");
                };

                tm.put(0, cury + 1, correspondingTile, o);
            }

            cury++;
        } while (continuey);

        return 0;
    }
}
