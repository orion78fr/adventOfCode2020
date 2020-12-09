package fr.orion78.adventOfCode.year2020.day8;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {
    private enum OpType {
        NOP,
        ACC,
        JMP
    }

    private static record Instruction(OpType operation, int argument) {
    }

    public static void main(String[] args) throws IOException {
        int accumulator = Utils.readFileForDay(8, Part1::compute);

        // Expected : 1553
        System.out.println("Accumulator after loop : " + accumulator);
    }

    public static int compute(Stream<String> lines) {
        List<Instruction> instructions = lines.map(l -> {
            String[] split = l.split(" ");
            return new Instruction(OpType.valueOf(split[0].toUpperCase()), Integer.parseInt(split[1]));
        }).collect(Collectors.toList());

        BitSet visitedOps = new BitSet();
        int accumulator = 0;
        int operationPointer = 0;

        // While we've not looped
        while (!visitedOps.get(operationPointer)) {
            visitedOps.set(operationPointer);

            Instruction instruction = instructions.get(operationPointer);
            switch (instruction.operation) {
                case NOP -> {
                }
                case ACC -> accumulator += instruction.argument;
                case JMP -> operationPointer += instruction.argument - 1;
            }
            operationPointer++;
        }

        return accumulator;
    }
}
