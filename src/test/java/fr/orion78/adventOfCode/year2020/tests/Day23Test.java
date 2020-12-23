package fr.orion78.adventOfCode.year2020.tests;

import fr.orion78.adventOfCode.year2020.day23.Part1;
import fr.orion78.adventOfCode.year2020.day23.Part2;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.stream.Stream;

public class Day23Test {
    @Test
    public void example1(){
        Assert.assertEquals(Part1.compute(Stream.of("389125467"), 0), "25467389");
        Assert.assertEquals(Part1.compute(Stream.of("389125467"), 1), "54673289");
        Assert.assertEquals(Part1.compute(Stream.of("389125467"), 2), "32546789");
        Assert.assertEquals(Part1.compute(Stream.of("389125467"), 3), "34672589");
        Assert.assertEquals(Part1.compute(Stream.of("389125467"), 4), "32584679");
        Assert.assertEquals(Part1.compute(Stream.of("389125467"), 5), "36792584");
        Assert.assertEquals(Part1.compute(Stream.of("389125467"), 6), "93672584");
        Assert.assertEquals(Part1.compute(Stream.of("389125467"), 7), "92583674");
        Assert.assertEquals(Part1.compute(Stream.of("389125467"), 8), "58392674");
        Assert.assertEquals(Part1.compute(Stream.of("389125467"), 9), "83926574");
        Assert.assertEquals(Part1.compute(Stream.of("389125467"), 10), "92658374");
    }

    @Test
    public void example2(){
        Assert.assertEquals(Part2.compute(Stream.of("389125467"), 0, 9), 2*5);
        Assert.assertEquals(Part2.compute(Stream.of("389125467"), 1, 9), 5*4);
        Assert.assertEquals(Part2.compute(Stream.of("389125467"), 2, 9), 3*2);
        Assert.assertEquals(Part2.compute(Stream.of("389125467"), 3, 9), 3*4);
        Assert.assertEquals(Part2.compute(Stream.of("389125467"), 4, 9), 3*2);
        Assert.assertEquals(Part2.compute(Stream.of("389125467"), 5, 9), 3*6);
        Assert.assertEquals(Part2.compute(Stream.of("389125467"), 6, 9), 9*3);
        Assert.assertEquals(Part2.compute(Stream.of("389125467"), 7, 9), 9*2);
        Assert.assertEquals(Part2.compute(Stream.of("389125467"), 8, 9), 5*8);
        Assert.assertEquals(Part2.compute(Stream.of("389125467"), 9, 9), 8*3);
        Assert.assertEquals(Part2.compute(Stream.of("389125467"), 10, 9), 9*2);
    }
}
