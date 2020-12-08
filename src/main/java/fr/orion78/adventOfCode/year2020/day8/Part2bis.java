package fr.orion78.adventOfCode.year2020.day8;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Part2bis {
    private static class Edge {
        private int acc;

        public Edge(int acc) {
            this.acc = acc;
        }

        public Edge() {
            this(0);
        }

        public int getAcc() {
            return acc;
        }
    }

    private enum OpType {
        NOP,
        ACC,
        JMP
    }

    private static class Instruction {
        private OpType operation;
        private int argument;

        public Instruction(OpType operation, int argument) {
            this.operation = operation;
            this.argument = argument;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("day8.txt"))) {
            List<Instruction> instructions = r.lines().map(l -> {
                String[] split = l.split(" ");
                return new Instruction(OpType.valueOf(split[0].toUpperCase()), Integer.parseInt(split[1]));
            }).collect(Collectors.toList());

            DirectedWeightedMultigraph<Instruction, Edge> graph = new DirectedWeightedMultigraph<>(Edge.class);

            // O(N)
            instructions.forEach(graph::addVertex);

            // O(N)
            for (int i = 0, instructionsSize = instructions.size(); i < instructionsSize - 1; i++) {
                Instruction instruction = instructions.get(i);

                switch (instruction.operation) {
                    case NOP -> {
                        Edge e = new Edge();
                        graph.addEdge(instruction, instructions.get(i + 1), e);
                        graph.setEdgeWeight(e, 0);

                        e = new Edge();
                        graph.addEdge(instruction, instructions.get(i + instruction.argument), e);
                        graph.setEdgeWeight(e, 1);
                    }
                    case ACC -> {
                        Edge e = new Edge(instruction.argument);
                        graph.addEdge(instruction, instructions.get(i + 1), e);
                        graph.setEdgeWeight(e, 0);
                    }
                    case JMP -> {
                        Edge e = new Edge();
                        graph.addEdge(instruction, instructions.get(i + instruction.argument), e);
                        graph.setEdgeWeight(e, 0);

                        e = new Edge();
                        graph.addEdge(instruction, instructions.get(i + 1), e);
                        graph.setEdgeWeight(e, 1);
                    }
                }
            }

            DijkstraShortestPath<Instruction, Edge> shortestPath = new DijkstraShortestPath<>(graph);
            // O(N * log(N))
            GraphPath<Instruction, Edge> path = shortestPath.getPath(instructions.get(0), instructions.get(instructions.size() - 1));

            if (path.getWeight() != 1) {
                System.out.println("Wut ?");
                return;
            }

            int accumulator = path.getEdgeList().stream().mapToInt(Edge::getAcc).sum();
            // Expected : 1877
            System.out.println("Accumulator when done : " + accumulator);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
