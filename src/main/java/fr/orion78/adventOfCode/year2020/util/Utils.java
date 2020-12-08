package fr.orion78.adventOfCode.year2020.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Stream;

public class Utils {
    public static <T> T readFileForDay(int day, Function<Stream<String>, T> f) throws IOException {
        try (var r = new BufferedReader(new FileReader("day" + day + ".txt"))) {
            return f.apply(r.lines());
        }
    }
}
