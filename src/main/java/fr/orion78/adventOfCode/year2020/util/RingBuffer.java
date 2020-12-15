package fr.orion78.adventOfCode.year2020.util;

import java.util.Arrays;
import java.util.stream.Stream;

public class RingBuffer<T> {
    private final Class<T> clazz;
    private final Object[] buffer;

    private int pointer;

    public RingBuffer(Class<T> clazz, int size) {
        this.clazz = clazz;
        this.buffer = new Object[size];
        this.pointer = 0;
    }

    public int size() {
        return buffer.length;
    }

    public Stream<T> stream() {
        return Arrays.stream(buffer).map(clazz::cast);
    }

    public void put(T obj) {
        buffer[pointer++ % size()] = obj;
    }
}
