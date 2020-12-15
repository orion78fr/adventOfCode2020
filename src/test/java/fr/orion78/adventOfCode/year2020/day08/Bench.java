package fr.orion78.adventOfCode.year2020.day08;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2,
        jvmArgsAppend = {"-server", "-disablesystemassertions"})
@Warmup(iterations = 1, time = 5)
@Measurement(iterations = 3, time = 3)
public class Bench {
    List<String> lines;
    List<String> worstCase;

    @Setup(Level.Trial)
    public void setup() throws IOException {
        try (var r = new BufferedReader(new FileReader("day08.txt"))) {
            lines = r.lines().collect(Collectors.toList());
        }
        worstCase = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            worstCase.add("nop +1");
        }
        worstCase.add("jmp -1");
        worstCase.add("nop +1");
    }

    @Benchmark
    public void part2() {
        long result = Part2.compute(lines.stream());
        if (result != 1877) {
            throw new RuntimeException();
        }
    }

    @Benchmark
    public void part2bis() {
        long result = Part2bis.compute(lines.stream());
        if (result != 1877) {
            throw new RuntimeException();
        }
    }

    @Benchmark
    public void part2WorstCase() {
        Part2.compute(worstCase.stream());
    }

    @Benchmark
    public void part2bisWorstCase() {
        Part2bis.compute(worstCase.stream());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(Bench.class.getCanonicalName()).build();

        new Runner(opt).run();
    }
}
