package fr.orion78.adventOfCode.year2020.day8;

import fr.orion78.adventOfCode.year2020.util.Utils;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2bis {
    private static record Edge(int acc) {
        public Edge() {
            this(0);
        }
    }

    private enum OpType {
        NOP,
        ACC,
        JMP
    }

    private static record Instruction(OpType operation, int argument) {
    }

    public static void main(String[] args) throws IOException {
        main();
    }

    public static void main() throws IOException {
        int accumulator = Utils.readFileForDay(8, Part2bis::compute);

        // Expected : 1877
        System.out.println("Accumulator when done : " + accumulator);
    }

    public static int compute(Stream<String> lines) {
        List<Instruction> instructions = lines.map(l -> {
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
            throw new RuntimeException();
        }

        return path.getEdgeList().stream().mapToInt(Edge::acc).sum();
    }
}
