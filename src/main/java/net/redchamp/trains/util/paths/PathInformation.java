package net.redchamp.trains.util.paths;

import java.util.*;

import net.redchamp.trains.util.*;

/**
 * Represents a path through a `Graph`.
 */
public class PathInformation<T> {
    int distance;
    private Stack<Vertex<T>> path = new Stack<Vertex<T>>();

    protected PathInformation() {
    }

    protected PathInformation(Vertex<T> vertex) {
        addVertex(vertex, 0);
    }

    protected PathInformation(PathInformation copy) {
        this.path.addAll(copy.path);
    }

    protected void addVertexAtBeginning(Vertex<T> vertex, int distance) {
        path.insertElementAt(vertex, 0);
        this.distance += distance;
    }

    protected void addVertex(Vertex<T> vertex, int distance) {
        this.path.push(vertex);
        this.distance += distance;
    }

    protected Vertex<T> removeVertex(int distance) {
        this.distance -= distance;
        return path.pop();
    }

    public String toString() {
        return path.toString();
    }

    public int getDistance() {
        return distance;
    }

    public Stack<Vertex<T>> getPath() {
        return path;
    }

    public int getDepth() {
        return getPath().size() - 1;
    }
}
