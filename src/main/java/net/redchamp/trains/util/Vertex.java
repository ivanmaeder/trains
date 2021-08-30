package net.redchamp.trains.util;

import java.util.*;

/**
 * A vertex in a graph (`Graph`), connected to other vertices through one
 * or more edges (`Edge`).
 */
public class Vertex<T> {
    private T data;
    private List<Edge<T>> edges = new ArrayList<Edge<T>>();

    public Vertex(T data) {
        this.data = data;
    }

    protected Vertex(Vertex<T> vertex) {
        this.data = vertex.getData();
        this.edges = vertex.getEdges();
    }

    public List<Edge<T>> getEdges() {
        return this.edges;
    }

    public void addEdge(Edge<T> edge) {
        this.edges.add(edge);
    }

    public T getData() {
        return this.data;
    }

    public int hashCode() {
        int hash = 1;

        hash = 31 * hash + this.getData().hashCode();
        hash = 31 * hash + (this.getEdges() != null ? this.getEdges().size() : 1);

        return hash;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Vertex)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Vertex<T> v = (Vertex<T>) obj;

        /* Assume data elements are unique; checking edges can result in
           a circular reference. */
        return this.getData().equals(v.getData());
    }

    public String toString() {
        return data.toString();
    }
}
