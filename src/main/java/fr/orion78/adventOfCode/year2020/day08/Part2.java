package fr.orion78.adventOfCode.year2020.day08;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {
    private enum OpType {
        NOP,
        ACC,
        JMP
    }

    private static record Instruction(OpType operation, int argument) {
    }

    public static void main(String[] args) throws IOException {
        main();
    }

    public static void main() throws IOException {
        int accumulator = Utils.readFileForDay(8, Part2::compute);

        // Expected : 1877
        System.out.println("Accumulator when done : " + accumulator);
    }

    public static int compute(Stream<String> lines) {
        List<Instruction> instructions = lines.map(l -> {
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
                if (operationPointer == instructions.size()) {
                    return accumulator;
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

        throw new RuntimeException();
    }
}
