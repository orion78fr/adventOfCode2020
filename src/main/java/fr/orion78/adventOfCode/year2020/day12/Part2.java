package fr.orion78.adventOfCode.year2020.day12;

import fr.orion78.adventOfCode.year2020.util.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {
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

    public record Action(ActionType type, long value) {
        public static Action parse(String str) {
            return new Action(ActionType.getFromChar(str.charAt(0)), Long.parseLong(str.substring(1)));
        }
    }

    public record State(long x, long y, long wx, long wy) {
        public State moveWaypoint(long dx, long dy) {
            return new State(x, y, wx + dx, wy + dy);
        }

        public State moveTowardsWaypoint(long factor) {
            return new State(x + factor * wx, y + factor * wy, wx, wy);
        }

        public State rotateWaypoint(long degrees) {
            if (degrees == 90) {
                return new State(x, y, -wy, wx);
            } else if (degrees == 180) {
                return new State(x, y, -wx, -wy);
            } else if (degrees == 270) {
                return new State(x, y, wy, -wx);
            } else {
                throw new RuntimeException();
            }
        }

        public State applyAction(Action a) {
            switch (a.type) {
                case NORTH -> {
                    return moveWaypoint(0, -a.value);
                }
                case SOUTH -> {
                    return moveWaypoint(0, a.value);
                }
                case EAST -> {
                    return moveWaypoint(a.value, 0);
                }
                case WEST -> {
                    return moveWaypoint(-a.value, 0);
                }
                case LEFT -> {
                    return rotateWaypoint(360 - a.value);
                }
                case RIGHT -> {
                    return rotateWaypoint(a.value);
                }
                case FORWARD -> {
                    return moveTowardsWaypoint(a.value);
                }
                default -> throw new RuntimeException();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        State initialState = new State(0, 0, 10, -1);

        State finalState = Utils.readFileForDay(12, Part2::compute, initialState);

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
