package net.redchamp.trains.util;

/**
 * An edge connecting two vertices.
 *
 * This edge has weight and direction (it connects two vertices from one
 * to the other).
 */
public class Edge<T> {
    private Vertex<T> vertex;
    private int weight;

    public Edge(Vertex<T> vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public Vertex<T> getVertex() {
        return vertex;
    }

    public int getWeight() {
        return weight;
    }

    public int hashCode() {
        int hash = 1;

        hash = 31 * hash + this.getVertex().hashCode();
        hash = 31 * hash + this.weight;

        return hash;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Edge)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Edge<T> e = (Edge<T>) obj;

        if (this.getVertex().equals(e.getVertex())) {
            if (this.getWeight() == e.getWeight()) {
                return true;
            }
        }

        return false;
    }

    public String toString() {
        return new StringBuilder(" -> ").append(vertex).append(" (").append(weight).append(")").toString();
    }
}
