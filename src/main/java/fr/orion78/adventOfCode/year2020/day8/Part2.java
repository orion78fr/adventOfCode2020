package fr.orion78.adventOfCode.year2020.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {
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

            for (int modifiedInstruction = 0; modifiedInstruction < instructions.size(); modifiedInstruction++) {
                if (instructions.get(modifiedInstruction).operation.equals(OpType.ACC)) {
                    // We don't modify ACC instruction
                    continue;
                }

                BitSet visitedOps = new BitSet();
                int accumulator = 0;
                int operationPointer = 0;

                // While we've not looped
                while (!visitedOps.get(operationPointer)) {
                    if(operationPointer == instructions.size()) {
                        // Expected : 1877
                        System.out.println("Accumulator when done : " + accumulator);
                        return;
                    }

                    visitedOps.set(operationPointer);

                    Instruction instruction = instructions.get(operationPointer);

                    if (operationPointer == modifiedInstruction) {
                        switch (instruction.operation) {
                            case NOP -> operationPointer += instruction.argument - 1;
                            case JMP -> {
                            }
                        }
                    } else {
                        switch (instruction.operation) {
                            case NOP -> {
                            }
                            case ACC -> accumulator += instruction.argument;
                            case JMP -> operationPointer += instruction.argument - 1;
                        }
                    }
                    operationPointer++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
