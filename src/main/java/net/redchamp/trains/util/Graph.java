package net.redchamp.trains.util;

import java.util.*;

/**
 * A graph data structure; vertices (`Vertex`) connected via edges (`Edge`).
 */
public class Graph<T> {
    private Map<T, Vertex<T>> vertices = new HashMap<T, Vertex<T>>();

    public Collection<Vertex<T>> getVertices() {
        return this.vertices.values();
    }

    public void connect(T d1, T d2, int weight) {
        Vertex<T> v1 = addOrRetrieve(d1);
        Vertex<T> v2 = addOrRetrieve(d2);

        v1.addEdge(new Edge<T>(v2, weight));
    }

    private Vertex<T> addOrRetrieve(T data) {
        Vertex<T> vertex;

        if (this.contains(data)) {
            vertex = getVertex(data);
        }
        else {
            vertex = new Vertex<T>(data);
            add(vertex);
        }

        return vertex;
    }

    public boolean contains(T data) {
        return getVertex(data) != null;
    }

    private void add(Vertex<T> vertex) {
        this.vertices.put(vertex.getData(), vertex);
    }

    public Vertex<T> getVertex(T data) {
        return this.vertices.get(data);
    }

    private Vertex<T> getVertex(Vertex<T> vertex) {
        return getVertex(vertex.getData());
    }

    /**
     * Find the distance of the given `route`.
     *
     * @throws IllegalStateException if adjacent elements in `route` are
     * not connected
     */
    public int distance(T[] route) {
        int distance = 0;

        int i = 0;
        Vertex<T> currentVertex;
        Vertex<T> nextVertex = getVertex(route[i]);
        while (i + 1 < route.length) {
            currentVertex = nextVertex;
            nextVertex = getVertex(route[i + 1]);
            distance += distanceBetweenAdjacentVertices(currentVertex, nextVertex);
            i++;
        }

        return distance;
    }

    /**
     *
     * @throws IllegalStateException if `a` and `b` are not connected
     */
    private int distanceBetweenAdjacentVertices(Vertex<T> a, Vertex<T> b) {
        for (Edge<T> edge : a.getEdges()) {
            if (edge.getVertex().equals(b)) {
                return edge.getWeight();
            }
        }

        throw new IllegalStateException("Vertices are not connected");
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<T, Vertex<T>> entry : this.vertices.entrySet()) {
            for (Edge<T> e : entry.getValue().getEdges()) {
                result.append(entry.getKey()).append(" -> ");
                result.append(e.getVertex());
                result.append(" (").append(e.getWeight()).append(")\n");
            }
        }

        return result.toString();
    }
}
