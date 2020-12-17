package fr.orion78.adventOfCode.year2020.day16;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws IOException {
        long n = Utils.readFileForDay(16, Part2::compute);

        System.out.println("Product of departures " + n);
    }

    public static long compute(Stream<String> lines) {
        List<String> list = lines.collect(Collectors.toList());

        List<Integer> emptyLines = IntStream.range(0, list.size())
                .filter(i -> list.get(i).isEmpty())
                .boxed()
                .collect(Collectors.toList());

        if (emptyLines.size() != 2) {
            throw new RuntimeException("Too many empty lines");
        }

        int fieldCount = emptyLines.get(0);

        BitSet[] allowedNumbersList = new BitSet[fieldCount];
        BitSet allAllowedNumbers = new BitSet();

        for (int i = 0; i < fieldCount; i++) {
            String[] ranges = list.get(i).split(":")[1].split("or"); // We don't care about the name of the field
            BitSet allowedNumbers = new BitSet();

            for (String range : ranges) {
                String[] bounds = range.trim().split("-");
                int lowerBound = Integer.parseInt(bounds[0]);
                int upperBound = Integer.parseInt(bounds[1]); // Included
                allowedNumbers.set(lowerBound, upperBound + 1);
            }

            allowedNumbersList[i] = allowedNumbers;
            allAllowedNumbers.or(allowedNumbers);
        }

        BitSet[] possibleFields = new BitSet[fieldCount];
        for (int i = 0; i < possibleFields.length; i++) {
            possibleFields[i] = new BitSet();
            possibleFields[i].set(0, fieldCount);
        }

        for (int i = emptyLines.get(1) + 2, max = list.size(); i < max; i++) {
            List<Integer> fields = Arrays.stream(list.get(i).split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            if (fields.stream().anyMatch(f -> !allAllowedNumbers.get(f))) {
                // Ignore the lines that are invalid
                continue;
            }

            for (int fieldNumber = 0; fieldNumber < fieldCount; fieldNumber++) {
                int field = fields.get(fieldNumber);

                for (int k = 0; k < fieldCount; k++) {
                    if (!allowedNumbersList[k].get(field)) {
                        possibleFields[fieldNumber].clear(k);
                    }
                }
            }
        }

        List<Integer> myPassportFields = Arrays.stream(list.get(emptyLines.get(0) + 2).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (int j = 0; j < fieldCount; j++) {
            int f = myPassportFields.get(j);

            for (int k = 0; k < fieldCount; k++) {
                if (!allowedNumbersList[k].get(f)) {
                    possibleFields[j].clear(k);
                }
            }
        }

        Map<Integer, Integer> fieldOrder = new HashMap<>();

        boolean modified;
        do {
            modified = false;

            Optional<BitSet> field = Optional.empty();
            Optional<Integer> fieldNum = Optional.empty();
            for (int i = 0, possibleFieldsLength = possibleFields.length; i < possibleFieldsLength; i++) {
                BitSet f = possibleFields[i];
                if (f.cardinality() == 1) {
                    field = Optional.of(f);
                    fieldNum = Optional.of(i);
                    break;
                }
            }

            if (field.isPresent()) {
                modified = true;

                int fieldOrderNum = field.get().nextSetBit(0);
                fieldOrder.put(fieldOrderNum, fieldNum.get());

                for (BitSet possibleField : possibleFields) {
                    possibleField.clear(fieldOrderNum);
                }
            }
        } while (modified);

        return IntStream.range(0, fieldCount)
                .filter(i -> list.get(i).startsWith("departure"))
                .map(fieldOrder::get)
                .mapToLong(myPassportFields::get)
                .reduce(1, (a, b) -> a * b);
    }
}
