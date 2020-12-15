package fr.orion78.adventOfCode.year2020.tests;

import fr.orion78.adventOfCode.year2020.day06.Part1;
import fr.orion78.adventOfCode.year2020.day06.Part2;
import fr.orion78.adventOfCode.year2020.util.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class Day06Test {
    private final String[] example = new String[]{
            "abc", "",
            "a", "b", "c", "",
            "ab", "ac", "",
            "a", "a", "a", "a", "",
            "b"
    };

    @Test
    public void part1Example() {
        int count = Part1.compute(Arrays.stream(example));
        Assert.assertEquals(count, 11);
    }

    @Test
    public void part2Example() {
        int count = Part2.compute(Arrays.stream(example));
        Assert.assertEquals(count, 6);
    }

    @Test
    public void part1() throws IOException {
        int res = Utils.readFileForDay(6, Part1::compute);
        Assert.assertEquals(res, 6310);
    }

    @Test
    public void part2() throws IOException {
        int res = Utils.readFileForDay(6, Part2::compute);
        Assert.assertEquals(res, 3193);
    }
}
