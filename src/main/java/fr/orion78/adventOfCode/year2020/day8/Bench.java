package fr.orion78.adventOfCode.year2020.day8;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Bench {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Part2.class.getSimpleName())
                .include(Part2bis.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
