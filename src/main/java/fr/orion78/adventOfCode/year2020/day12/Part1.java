package fr.orion78.adventOfCode.year2020.day12;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {
    public enum ActionType {
        NORTH('N'),
        SOUTH('S'),
        EAST('E'),
        WEST('W'),
        LEFT('L'),
        RIGHT('R'),
        FORWARD('F');

        private static final Map<Character, ActionType> m = new HashMap<>();
        char c;

        ActionType(char c) {
            this.c = c;
        }

        static {
            Arrays.stream(ActionType.values()).forEach(t -> m.put(t.c, t));
        }

        public static ActionType getFromChar(char c) {
            return m.get(c);
        }
    }

    public enum Direction {
        EAST(1, 0),
        SOUTH(0, 1),
        WEST(-1, 0),
        NORTH(0, -1);

        int x;
        int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Direction rotateClockwise(int degrees) {
            int idxToRotate = degrees / 90;

            Direction[] d = Direction.values();
            for (int i = 0; i < d.length; i++) {
                if (d[i] == this) {
                    return d[(i + idxToRotate) % d.length];
                }
            }

            throw new RuntimeException();
        }

        public Direction rotateCounterClockwise(int degrees) {
            return rotateClockwise(360 - degrees);
        }
    }

    public record Action(ActionType type, int value) {
        public static Action parse(String str) {
            return new Action(ActionType.getFromChar(str.charAt(0)), Integer.parseInt(str.substring(1)));
        }
    }

    public record State(int x, int y, Direction facingDirection) {
        public State(State oldState, int xTranslation, int yTranslation) {
            this(oldState.x + xTranslation, oldState.y + yTranslation, oldState.facingDirection);
        }

        public State(State oldState, Direction newFacingDirection) {
            this(oldState.x, oldState.y, newFacingDirection);
        }

        public State applyAction(Action a) {
            switch (a.type) {
                case NORTH -> {
                    return new State(this, 0, -a.value);
                }
                case SOUTH -> {
                    return new State(this, 0, a.value);
                }
                case EAST -> {
                    return new State(this, a.value, 0);
                }
                case WEST -> {
                    return new State(this, -a.value, 0);
                }
                case LEFT -> {
                    return new State(this, this.facingDirection.rotateCounterClockwise(a.value));
                }
                case RIGHT -> {
                    return new State(this, this.facingDirection.rotateClockwise(a.value));
                }
                case FORWARD -> {
                    return new State(this, a.value * this.facingDirection.x, a.value * this.facingDirection.y);
                }
                default -> throw new RuntimeException();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        State initialState = new State(0, 0, Direction.EAST);

        State finalState = Utils.readFileForDay(12, Part1::compute, initialState);

        long manhattanDistance = Math.abs(finalState.x) + Math.abs(finalState.y);

        // Expected : 1645
        System.out.println("Manhattan distance to " + finalState + " is : " + manhattanDistance);
    }

    public static State compute(Stream<String> lines, State initialState) {
        List<Action> actions = lines.map(Action::parse).collect(Collectors.toList());

        State currentState = initialState;
        for (Action action : actions) {
            currentState = currentState.applyAction(action);
        }

        return currentState;
    }
}
