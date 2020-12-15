package fr.orion78.adventOfCode.year2020.tests;

import fr.orion78.adventOfCode.year2020.day01.Part1;
import fr.orion78.adventOfCode.year2020.day01.Part2;
import fr.orion78.adventOfCode.year2020.util.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class Day01Test {
    private final String[] example = {"1721", "979", "366", "299", "675", "1456"};

    @Test
    public void part1Example() {
        int res = Part1.compute(Arrays.stream(example));
        Assert.assertEquals(res * (2020 - res), 514579);
    }

    @Test
    public void part2Example() {
        Part2.Expenses res = Part2.compute(Arrays.stream(example));
        Assert.assertEquals(res.expense1() * res.expense2() * res.expense3(), 241861950);
    }

    @Test
    public void part1() throws IOException {
        int res = Utils.readFileForDay(1, Part1::compute);
        Assert.assertEquals(res * (2020 - res), 319531);
    }

    @Test
    public void part2() throws IOException {
        Part2.Expenses res = Utils.readFileForDay(1, Part2::compute);
        Assert.assertEquals(res.expense1() * res.expense2() * res.expense3(), 244300320);
    }
}
