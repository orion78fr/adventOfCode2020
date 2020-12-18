package fr.orion78.adventOfCode.year2020.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class Utils {
    public static <T> T readFileForDay(int day, Function<Stream<String>, T> f) throws IOException {
        try (var r = new BufferedReader(new FileReader(getFile(day)))) {
            return f.apply(r.lines());
        }
    }

    public static <T, U> T readFileForDay(int day, BiFunction<Stream<String>, U, T> f, U arg) throws IOException {
        try (var r = new BufferedReader(new FileReader(getFile(day)))) {
            return f.apply(r.lines(), arg);
        }
    }

    public static <T, U, V> T readFileForDay(int day, TriFunction<Stream<String>, U, V, T> f, U arg1, V arg2) throws IOException {
        try (var r = new BufferedReader(new FileReader(getFile(day)))) {
            return f.apply(r.lines(), arg1, arg2);
        }
    }

    private static String getFile(int day) {
        return String.format("day%02d.txt", day);
    }

    public interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }
}
