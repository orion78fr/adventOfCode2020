package fr.orion78.adventOfCode.year2020.tests;

import fr.orion78.adventOfCode.year2020.day05.Part1;
import fr.orion78.adventOfCode.year2020.day05.Part2;
import fr.orion78.adventOfCode.year2020.util.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Day05Test {
    @Test
    public void testSeatNb() {
        Assert.assertEquals(Part1.getSeatNB("BFFFBBFRRR"), 567);
        Assert.assertEquals(Part1.getSeatNB("FFFBBBFRRR"), 119);
        Assert.assertEquals(Part1.getSeatNB("BBFFBBFRLL"), 820);
    }

    @Test
    public void part1() throws IOException {
        int res = Utils.readFileForDay(5, Part1::compute);
        Assert.assertEquals(res, 866);
    }

    @Test
    public void part2() throws IOException {
        int res = Utils.readFileForDay(5, Part2::compute);
        Assert.assertEquals(res, 583);
    }
}
