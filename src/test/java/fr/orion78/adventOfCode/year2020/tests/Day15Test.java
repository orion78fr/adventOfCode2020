package fr.orion78.adventOfCode.year2020.tests;

import fr.orion78.adventOfCode.year2020.day15.Part1And2;
import fr.orion78.adventOfCode.year2020.util.Utils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class Day15Test {
    @DataProvider
    public Object[][] part1Examples() {
        return new Object[][]{
                new Object[]{List.of(0, 3, 6), 436},
                new Object[]{List.of(1, 3, 2), 1},
                new Object[]{List.of(2, 1, 3), 10},
                new Object[]{List.of(1, 2, 3), 27},
                new Object[]{List.of(2, 3, 1), 78},
                new Object[]{List.of(3, 2, 1), 438},
                new Object[]{List.of(3, 1, 2), 1836},
        };
    }

    @Test(dataProvider = "part1Examples")
    public void part1Example(List<Integer> input, int expectedResult) {
        int res = Part1And2.compute(input, 2020);
        Assert.assertEquals(res, expectedResult);
    }

    @DataProvider
    public Object[][] part2Examples() {
        return new Object[][]{
                new Object[]{List.of(0, 3, 6), 175594},
                new Object[]{List.of(1, 3, 2), 2578},
                new Object[]{List.of(2, 1, 3), 3544142},
                new Object[]{List.of(1, 2, 3), 261214},
                new Object[]{List.of(2, 3, 1), 6895259},
                new Object[]{List.of(3, 2, 1), 18},
                new Object[]{List.of(3, 1, 2), 362},
        };
    }

    @Test(dataProvider = "part2Examples")
    public void part2Example(List<Integer> input, int expectedResult) {
        int res = Part1And2.compute(input, 30_000_000);
        Assert.assertEquals(res, expectedResult);
    }

    @Test
    public void part1() throws IOException {
        int n = Utils.readFileForDay(15, Part1And2::compute, 2020);
        Assert.assertEquals(n, 1015);
    }

    @Test
    public void part2() throws IOException {
        int n = Utils.readFileForDay(15, Part1And2::compute, 30_000_000);
        Assert.assertEquals(n, 201);
    }
}
