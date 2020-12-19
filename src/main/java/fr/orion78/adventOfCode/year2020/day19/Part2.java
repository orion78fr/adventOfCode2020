package fr.orion78.adventOfCode.year2020.day19;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Part2 {
    private static final int RECURSIVE_REGEX_REPETITIONS = 10;

    public static void main(String[] args) throws IOException {
        long n = Utils.readFileForDay(19, Part2::compute);

        System.out.println("Result is " + n);
    }

    public static long compute(Stream<String> inputStream) {
        List<String> lines = inputStream.collect(Collectors.toList());

        int blank = IntStream.range(0, lines.size())
                .filter(i -> lines.get(i).isBlank())
                .findFirst().getAsInt();

        Map<Integer, String> rules = new HashMap<>();

        for (int i = 0; i < blank; i++) {
            String rule = lines.get(i);
            String[] splits = rule.split(":");

            rules.put(Integer.parseInt(splits[0]), splits[1].trim());
        }

        String matchStr = '^' + generateRegexMatch(rules, 0) + '$';
        int match = 0;

        for (int i = blank + 1; i < lines.size(); i++) {
            if (lines.get(i).matches(matchStr)) {
                match++;
            }
        }

        return match;
    }

    private static String generateRegexMatch(Map<Integer, String> rules, int ruleNum) {
        // Special cases (fixed rules)
        if (ruleNum == 8) {
            // 8: 42 | 42 8
            return "(" + generateRegexMatch(rules, 42) + ")+";
        } else if (ruleNum == 11) {
            // 11: 42 31 | 42 11 31
            // TODO There are no recursive regex in java, how to match balanced repetitions ?
            return IntStream.range(1, RECURSIVE_REGEX_REPETITIONS)
                    .mapToObj(i -> {
                        StringBuilder sb = new StringBuilder();
                        for (int j = 0; j < i; j++) {
                            sb.append(generateRegexMatch(rules, 42));
                        }
                        for (int j = 0; j < i; j++) {
                            sb.append(generateRegexMatch(rules, 31));
                        }
                        return sb.toString();
                    }).collect(Collectors.joining("|", "(", ")"));
        }

        String rule = rules.get(ruleNum);

        if (rule.startsWith("\"")) {
            // Literal char
            // X: "a"
            return rule.substring(1, 2);
        } else if (!rule.contains("|")) {
            // X: A B C
            return Arrays.stream(rule.split(" "))
                    .map(r -> generateRegexMatch(rules, Integer.parseInt(r)))
                    .collect(Collectors.joining());
        } else {
            return Arrays.stream(rule.split("\\|"))
                    .map(String::trim)
                    .map(s -> Arrays.stream(s.split(" "))
                            .map(r -> generateRegexMatch(rules, Integer.parseInt(r)))
                            .collect(Collectors.joining()))
                    .collect(Collectors.joining("|", "(", ")"));
        }
    }
}
