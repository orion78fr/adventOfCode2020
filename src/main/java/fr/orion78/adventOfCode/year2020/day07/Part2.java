package fr.orion78.adventOfCode.year2020.day07;

import fr.orion78.adventOfCode.year2020.util.Utils;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws IOException {
        int count = Utils.readFileForDay(7, Part2::compute);

        System.out.println("Number of bags that must be contained in shiny gold : " + count);
    }

    public static int compute(Stream<String> lines) {
        SimpleDirectedWeightedGraph<String, DefaultEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultEdge.class);

        lines.forEach(l -> {
            String[] w = l.split(" ");

            String srcColor = w[0] + " " + w[1];
            graph.addVertex(srcColor);

            if (w.length == 7) {
                // [...] no other bags.
                return;
            }

            for (int i = 4; i < w.length; i += 4) {
                String color = w[i + 1] + " " + w[i + 2];
                int weight = Integer.parseInt(w[i]);

                graph.addVertex(color);
                DefaultEdge edge = graph.addEdge(srcColor, color);
                graph.setEdgeWeight(edge, weight);
            }
        });

        HashMap<String, Integer> bagsToExplore = new HashMap<>();
        bagsToExplore.put("shiny gold", 1);
        int count = 0;

        while (!bagsToExplore.isEmpty()) {
            String bagToExplore = bagsToExplore.keySet().iterator().next();
            int multiplicator = bagsToExplore.remove(bagToExplore);

            List<String> bags = graph.outgoingEdgesOf(bagToExplore).stream()
                    .map(graph::getEdgeTarget)
                    .collect(Collectors.toList());

            for (String bag : bags) {
                int nbOfBags = (int) graph.getEdgeWeight(graph.getEdge(bagToExplore, bag));

                count += multiplicator * nbOfBags;

                bagsToExplore.put(bag, nbOfBags * multiplicator + bagsToExplore.getOrDefault(bag, 0));
            }
        }

        return count;
    }
}
