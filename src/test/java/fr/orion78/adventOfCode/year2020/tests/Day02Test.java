package fr.orion78.adventOfCode.year2020.tests;

import fr.orion78.adventOfCode.year2020.day02.Part1;
import fr.orion78.adventOfCode.year2020.day02.Part2;
import fr.orion78.adventOfCode.year2020.util.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class Day02Test {
    private final String[] example = {"1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc"};

    @Test
    public void part1Example() {
        long res = Part1.compute(Arrays.stream(example));
        Assert.assertEquals(res, 2);
    }

    @Test
    public void part2Example() {
        long res = Part2.compute(Arrays.stream(example));
        Assert.assertEquals(res, 1);
    }

    @Test
    public void part1() throws IOException {
        long res = Utils.readFileForDay(2, Part1::compute);
        Assert.assertEquals(res, 422);
    }

    @Test
    public void part2() throws IOException {
        long res = Utils.readFileForDay(2, Part2::compute);
        Assert.assertEquals(res, 451);
    }
}
