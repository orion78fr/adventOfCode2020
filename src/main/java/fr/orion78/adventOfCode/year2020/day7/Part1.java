package fr.orion78.adventOfCode.year2020.day7;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("day7.txt"))) {
            SimpleDirectedWeightedGraph<String, DefaultEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultEdge.class);

            r.lines().forEach(l -> {
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

            Set<String> bagsCanContainShinyGold = new HashSet<>();
            Stack<String> bagsToExplore = new Stack<>();
            bagsToExplore.add("shiny gold");

            while (!bagsToExplore.isEmpty()) {
                String bagToExplore = bagsToExplore.pop();

                List<String> bags = graph.incomingEdgesOf(bagToExplore).stream()
                        .map(graph::getEdgeSource)
                        .collect(Collectors.toList());
                for (String bag : bags) {
                    if (bagsCanContainShinyGold.add(bag)) {
                        bagsToExplore.add(bag);
                    }
                }
            }

            // Expected : 124
            System.out.println("Number of bags that can contain shiny gold : " + bagsCanContainShinyGold.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
