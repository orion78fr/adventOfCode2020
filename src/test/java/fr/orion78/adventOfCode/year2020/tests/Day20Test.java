package fr.orion78.adventOfCode.year2020.tests;

import fr.orion78.adventOfCode.year2020.day20.Orientation;
import fr.orion78.adventOfCode.year2020.day20.Part1;
import fr.orion78.adventOfCode.year2020.day20.Part2;
import fr.orion78.adventOfCode.year2020.day20.Tile;
import fr.orion78.adventOfCode.year2020.day20.TileMap;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Day20Test {
    public static final List<String> example = List.of(
            "Tile 2311:",
            "..##.#..#.",
            "##..#.....",
            "#...##..#.",
            "####.#...#",
            "##.##.###.",
            "##...#.###",
            ".#.#.#..##",
            "..#....#..",
            "###...#.#.",
            "..###..###",
            "",
            "Tile 1951:",
            "#.##...##.",
            "#.####...#",
            ".....#..##",
            "#...######",
            ".##.#....#",
            ".###.#####",
            "###.##.##.",
            ".###....#.",
            "..#.#..#.#",
            "#...##.#..",
            "",
            "Tile 1171:",
            "####...##.",
            "#..##.#..#",
            "##.#..#.#.",
            ".###.####.",
            "..###.####",
            ".##....##.",
            ".#...####.",
            "#.##.####.",
            "####..#...",
            ".....##...",
            "",
            "Tile 1427:",
            "###.##.#..",
            ".#..#.##..",
            ".#.##.#..#",
            "#.#.#.##.#",
            "....#...##",
            "...##..##.",
            "...#.#####",
            ".#.####.#.",
            "..#..###.#",
            "..##.#..#.",
            "",
            "Tile 1489:",
            "##.#.#....",
            "..##...#..",
            ".##..##...",
            "..#...#...",
            "#####...#.",
            "#..#.#.#.#",
            "...#.#.#..",
            "##.#...##.",
            "..##.##.##",
            "###.##.#..",
            "",
            "Tile 2473:",
            "#....####.",
            "#..#.##...",
            "#.##..#...",
            "######.#.#",
            ".#...#.#.#",
            ".#########",
            ".###.#..#.",
            "########.#",
            "##...##.#.",
            "..###.#.#.",
            "",
            "Tile 2971:",
            "..#.#....#",
            "#...###...",
            "#.#.###...",
            "##.##..#..",
            ".#####..##",
            ".#..####.#",
            "#..#.#..#.",
            "..####.###",
            "..#.#.###.",
            "...#.#.#.#",
            "",
            "Tile 2729:",
            "...#.#.#.#",
            "####.#....",
            "..#.#.....",
            "....#..#.#",
            ".##..##.#.",
            ".#.####...",
            "####.#.#..",
            "##.####...",
            "##..#.##..",
            "#.##...##.",
            "",
            "Tile 3079:",
            "#.#.#####.",
            ".#..######",
            "..#.......",
            "######....",
            "####.#..#.",
            ".#...#.##.",
            "#.#####.##",
            "..#.###...",
            "..#.......",
            "..#.###...");

    public static final List<String> PATTERN = List.of(
            "..#.",
            ".#.#",
            "#..."
    );

    @Test
    public void example1() {
        Assert.assertEquals(Part1.compute(example.stream()), 20899048083289L);
    }

    @Test
    public void example2() {
        Assert.assertEquals(Part2.compute(example.stream()), 273);
    }

    @Test
    public void testPattern() {
        TileMap tm = new TileMap(new Tile(1, List.of(
                "......",
                "......",
                "...#..",
                "..#.#.",
                ".#....",
                "......"
        )), Orientation.NORMAL_NORTH);
        Assert.assertEquals(tm.countPattern(PATTERN), 1);

        tm = new TileMap(new Tile(1, List.of(
                "......",
                "...#..",
                "..#.#.",
                ".#....",
                "......",
                "......"
        )), Orientation.NORMAL_NORTH);
        Assert.assertEquals(tm.countPattern(PATTERN), 1);
    }

    @Test
    public void testPattern2() {
        TileMap tm = new TileMap(new Tile(1, List.of(
                "......",
                "......",
                "....#.",
                "...#..",
                "..#...",
                "......"
        )), Orientation.NORMAL_NORTH);

        tm.put(1, 0, new Tile(2, List.of(
                "......",
                "......",
                "......",
                ".#....",
                "......",
                "......"
        )), Orientation.NORMAL_NORTH);
        Assert.assertEquals(tm.countPattern(PATTERN), 1);

        tm.put(1, 0, new Tile(2, List.of(
                "......",
                "......",
                "......",
                "......",
                "...#..",
                "......"
        )), Orientation.NORMAL_WEST);

        Assert.assertEquals(tm.countPattern(PATTERN), 1);
        tm.put(1, 0, new Tile(2, List.of(
                "......",
                "......",
                "....#.",
                "......",
                "......",
                "......"
        )), Orientation.NORMAL_SOUTH);
        Assert.assertEquals(tm.countPattern(PATTERN), 1);

        tm.put(1, 0, new Tile(2, List.of(
                "......",
                "..#...",
                "......",
                "......",
                "......",
                "......"
        )), Orientation.NORMAL_EAST);
        Assert.assertEquals(tm.countPattern(PATTERN), 1);

        tm.put(1, 0, new Tile(2, List.of(
                "......",
                "......",
                "......",
                "....#.",
                "......",
                "......"
        )), Orientation.FLIPPED_NORTH);
        Assert.assertEquals(tm.countPattern(PATTERN), 1);

        tm.put(1, 0, new Tile(2, List.of(
                "......",
                "......",
                "......",
                "......",
                "..#...",
                "......"
        )), Orientation.FLIPPED_WEST);
        Assert.assertEquals(tm.countPattern(PATTERN), 1);

        tm.put(1, 0, new Tile(2, List.of(
                "......",
                "......",
                ".#....",
                "......",
                "......",
                "......"
        )), Orientation.FLIPPED_SOUTH);
        Assert.assertEquals(tm.countPattern(PATTERN), 1);

        tm.put(1, 0, new Tile(2, List.of(
                "......",
                "...#..",
                "......",
                "......",
                "......",
                "......"
        )), Orientation.FLIPPED_EAST);
        Assert.assertEquals(tm.countPattern(PATTERN), 1);
    }
}
