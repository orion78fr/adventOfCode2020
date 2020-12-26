package fr.orion78.adventOfCode.year2020.tests;

import fr.orion78.adventOfCode.year2020.day25.Part1;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.stream.Stream;

public class Day25Test {
    @Test
    public void example1(){
        Assert.assertEquals(Part1.compute(Stream.of("5764801", "17807724")), 14897079);
    }
}
