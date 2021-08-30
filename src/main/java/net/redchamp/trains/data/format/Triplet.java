package net.redchamp.trains.data.format;

import net.redchamp.trains.util.*;

/**
 * Three things used to connect vertices: the contents of the `from` vertex,
 * the contents of the `to` vertex, and the weight of the edge between them.
 */
public class Triplet<T> {
    private T d1;
    private T d2;
    private int weight;

    public Triplet(T d1, T d2, int weight) {
        this.d1 = d1;
        this.d2 = d2;

        this.weight = weight;
    }

    public T d1() {
        return this.d1;
    }

    public T d2() {
        return this.d2;
    }

    public int weight() {
        return this.weight;
    }
}
