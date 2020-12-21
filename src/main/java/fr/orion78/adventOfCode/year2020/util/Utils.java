package fr.orion78.adventOfCode.year2020.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;
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

    public static long fromBinary(String str, char positiveChar) {
        return fromBinary(str.chars(), List.of(positiveChar));
    }

    public static long fromBinary(String str, List<Character> positiveChars) {
        return fromBinary(str.chars(), positiveChars);
    }

    public static long fromBinary(IntStream chars, char positiveChar) {
        return fromBinary(chars, List.of(positiveChar));
    }

    public static long fromBinary(IntStream chars, List<Character> positiveChars) {
        PrimitiveIterator.OfInt it = chars.iterator();

        int bits = 0;
        long result = 0;

        while (it.hasNext()) {
            char next = (char) it.nextInt();

            bits++;
            result = 2 * result + (positiveChars.contains(next) ? 1 : 0);
        }

        if (bits >= Long.SIZE) {
            throw new RuntimeException("Too many bits");
        }

        return result;
    }

    public static IntStream revIntRange(int from, int to) {
        return IntStream.range(from, to).map(i -> to - i + from - 1);
    }

    public interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }
}
