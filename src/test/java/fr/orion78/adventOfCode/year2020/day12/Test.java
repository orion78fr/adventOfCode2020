package fr.orion78.adventOfCode.year2020.day12;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        Part2.State finalState = Part2.compute(List.of("F10", "N3", "F7", "R90", "F11").stream(), new Part2.State(0, 0, 10,-1));
        System.out.println(finalState);
    }
}
