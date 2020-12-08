package fr.orion78.adventOfCode.year2020.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {
    private enum OpType {
        NOP,
        ACC,
        JMP
    }

    private static class Instruction {
        private OpType operation;
        private int argument;

        public Instruction(OpType operation, int argument) {
            this.operation = operation;
            this.argument = argument;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("day8.txt"))) {
            List<Instruction> instructions = r.lines().map(l -> {
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

            // Expected : 1553
            System.out.println("Accumulator after loop : " + accumulator);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
