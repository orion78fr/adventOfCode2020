package fr.orion78.adventOfCode.year2020.tests;

import fr.orion78.adventOfCode.year2020.day19.Part1;
import fr.orion78.adventOfCode.year2020.day19.Part2;
import fr.orion78.adventOfCode.year2020.util.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class Day19Test {
    @Test
    public void part1() throws IOException {
        long n = Utils.readFileForDay(19, Part1::compute);
        Assert.assertEquals(n, 136);
    }

    @Test
    public void part2() throws IOException {
        long n = Utils.readFileForDay(19, Part2::compute);
        Assert.assertEquals(n, 256);
    }

    @Test
    public void example1() {
        long n = Part1.compute(Stream.of(
                "0: 4 1 5",
                "1: 2 3 | 3 2",
                "2: 4 4 | 5 5",
                "3: 4 5 | 5 4",
                "4: \"a\"",
                "5: \"b\"",
                "",
                "ababbb",
                "bababa",
                "abbbab",
                "aaabbb",
                "aaaabbb"
        ));
        Assert.assertEquals(n, 2);
    }

    List<String> example2 = List.of(
            "42: 9 14 | 10 1",
            "9: 14 27 | 1 26",
            "10: 23 14 | 28 1",
            "1: \"a\"",
            "11: 42 31",
            "5: 1 14 | 15 1",
            "19: 14 1 | 14 14",
            "12: 24 14 | 19 1",
            "16: 15 1 | 14 14",
            "31: 14 17 | 1 13",
            "6: 14 14 | 1 14",
            "2: 1 24 | 14 4",
            "0: 8 11",
            "13: 14 3 | 1 12",
            "15: 1 | 14",
            "17: 14 2 | 1 7",
            "23: 25 1 | 22 14",
            "28: 16 1",
            "4: 1 1",
            "20: 14 14 | 1 15",
            "3: 5 14 | 16 1",
            "27: 1 6 | 14 18",
            "14: \"b\"",
            "21: 14 1 | 1 14",
            "25: 1 1 | 1 14",
            "22: 14 14",
            "8: 42",
            "26: 14 22 | 1 20",
            "18: 15 15",
            "7: 14 5 | 1 21",
            "24: 14 1",
            "",
            "abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa",
            "bbabbbbaabaabba",
            "babbbbaabbbbbabbbbbbaabaaabaaa",
            "aaabbbbbbaaaabaababaabababbabaaabbababababaaa",
            "bbbbbbbaaaabbbbaaabbabaaa",
            "bbbababbbbaaaaaaaabbababaaababaabab",
            "ababaaaaaabaaab",
            "ababaaaaabbbaba",
            "baabbaaaabbaaaababbaababb",
            "abbbbabbbbaaaababbbbbbaaaababb",
            "aaaaabbaabaaaaababaa",
            "aaaabbaaaabbaaa",
            "aaaabbaabbaaaaaaabbbabbbaaabbaabaaa",
            "babaaabbbaaabaababbaabababaaab",
            "aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba"
    );

    @Test
    public void example2part1() {
        long n = Part1.compute(example2.stream());
        Assert.assertEquals(n, 3);
    }

    @Test
    public void example2part2() {
        long n = Part2.compute(example2.stream());
        Assert.assertEquals(n, 12);
    }
}
