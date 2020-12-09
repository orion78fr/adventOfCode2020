package fr.orion78.adventOfCode.year2020.day8;

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
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 3,
        jvmArgsAppend = {"-server", "-disablesystemassertions"})
@Warmup(iterations = 1)
@Measurement(iterations = 3)
public class Bench {
    BufferedReader r;

    @Setup(Level.Iteration)
    public void setup() throws FileNotFoundException {
        r = new BufferedReader(new FileReader("day8.txt"));
    }

    @TearDown(Level.Iteration)
    public void tearDown() throws IOException {
        r.close();
    }

    @Benchmark
    public void part2() {
        long result = Part2.compute(r.lines());
        if (result != 1877) {
            throw new RuntimeException();
        }
    }

    @Benchmark
    public void part2bis() {
        long result = Part2bis.compute(r.lines());
        if (result != 1877) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) throws RunnerException, IOException {
        Options opt = new OptionsBuilder().include(Bench.class.getSimpleName()).build();

        new Runner(opt).run();
    }
}
