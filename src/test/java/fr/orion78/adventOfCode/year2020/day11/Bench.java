package fr.orion78.adventOfCode.year2020.day11;

import fr.orion78.adventOfCode.year2020.util.Utils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2,
        jvmArgsAppend = {"-server", "-disablesystemassertions"})
@Warmup(iterations = 1, time = 5)
@Measurement(iterations = 3, time = 3)
public class Bench {
    @Benchmark
    public void part2() throws IOException {
        long result = Utils.readFileForDay(11, Part2::compute);
        if (result != 2089) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(Bench.class.getCanonicalName()).build();

        new Runner(opt).run();
    }
}
