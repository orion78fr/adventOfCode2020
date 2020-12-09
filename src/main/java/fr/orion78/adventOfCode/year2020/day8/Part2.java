package fr.orion78.adventOfCode.year2020.day8;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 3,
        jvmArgsAppend = {"-server", "-disablesystemassertions"})
@Warmup(iterations = 1)
@Measurement(iterations = 3)
public class Part2 {
    private enum OpType {
        NOP,
        ACC,
        JMP
    }

    private static record Instruction(OpType operation, int argument) {
    }

    public static void main(String[] args) {
        test();
    }

    @Benchmark
    public static void test() {
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
                    if (operationPointer == instructions.size()) {
                        // Expected : 1877
                        //System.out.println("Accumulator when done : " + accumulator);
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
