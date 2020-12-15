package fr.orion78.adventOfCode.year2020.tests;

import fr.orion78.adventOfCode.year2020.day07.Part1;
import fr.orion78.adventOfCode.year2020.day07.Part2;
import fr.orion78.adventOfCode.year2020.util.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day07Test {
    private final String[] example = new String[]{
            "light red bags contain 1 bright white bag, 2 muted yellow bags.",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
            "bright white bags contain 1 shiny gold bag.",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
            "faded blue bags contain no other bags.",
            "dotted black bags contain no other bags."
    };

    @Test
    public void part1Example() {
        int count = Part1.compute(Arrays.stream(example));
        Assert.assertEquals(count, 4);
    }

    @Test
    public void part2Example() {
        int count = Part2.compute(Arrays.stream(example));
        Assert.assertEquals(count, 32);
    }

    @Test
    public void part2Example2() {
        int count = Part2.compute(Stream.of(
                "shiny gold bags contain 2 dark red bags.",
                "dark red bags contain 2 dark orange bags.",
                "dark orange bags contain 2 dark yellow bags.",
                "dark yellow bags contain 2 dark green bags.",
                "dark green bags contain 2 dark blue bags.",
                "dark blue bags contain 2 dark violet bags.",
                "dark violet bags contain no other bags."
        ));
        Assert.assertEquals(count, 126);
    }

    @Test
    public void part1() throws IOException {
        int res = Utils.readFileForDay(7, Part1::compute);
        Assert.assertEquals(res, 124);
    }

    @Test
    public void part2() throws IOException {
        int res = Utils.readFileForDay(7, Part2::compute);
        Assert.assertEquals(res, 34862);
    }
}
