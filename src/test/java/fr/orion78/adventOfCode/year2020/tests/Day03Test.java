package fr.orion78.adventOfCode.year2020.tests;

import fr.orion78.adventOfCode.year2020.day03.Part1;
import fr.orion78.adventOfCode.year2020.day03.Part2;
import fr.orion78.adventOfCode.year2020.util.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class Day03Test {
    private final String[] example = {"..##.......", "#...#...#..", ".#....#..#.", "..#.#...#.#", ".#...##..#.",
            "..#.##.....", ".#.#.#....#", ".#........#", "#.##...#...", "#...##....#", ".#..#...#.#"};

    @Test
    public void part1Example() {
        int count = Part1.compute(Arrays.stream(example));
        Assert.assertEquals(count, 7);
    }

    @Test
    public void part2Example() {
        long count = Part2.compute(Arrays.stream(example));
        Assert.assertEquals(count, 336);
    }

    @Test
    public void part1() throws IOException {
        int res = Utils.readFileForDay(3, Part1::compute);
        Assert.assertEquals(res, 195);
    }

    @Test
    public void part2() throws IOException {
        long res = Utils.readFileForDay(3, Part2::compute);
        Assert.assertEquals(res, 3772314000L);
    }
}
