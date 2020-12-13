package fr.orion78.adventOfCode.year2020.day13;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {
    public record Bus(int id, int arrivalTime) {
    }

    public static void main(String[] args) throws IOException {
        int id = Utils.readFileForDay(13, Part1::compute);

        // Expected : 2238
        System.out.println("Id " + id);
    }

    public static int compute(Stream<String> lines) {
        List<String> values = lines.collect(Collectors.toList());
        int arrivalTime = Integer.parseInt(values.get(0));

        Bus bus = Arrays.stream(values.get(1).split(","))
                .filter(s -> !s.equals("x"))
                .map(Integer::parseInt)
                .map(i -> new Bus(i, (int) (Math.ceil(arrivalTime / (double) i) * i)))
                .min(Comparator.comparingInt(Bus::arrivalTime))
                .get();

        return bus.id * (bus.arrivalTime - arrivalTime);
    }
}
