package net.redchamp.trains.util.paths;

import java.util.*;

import net.redchamp.trains.util.*;

/**
 * Find paths (AKA routes) through a graph.
 *
 * Implement `continueSearch()` and `pathMatch()` to use the default `paths()`
 * method.
 */
abstract public class PathFinder<T> {
    private Graph<T> graph;

    public PathFinder(Graph<T> graph) {
        this.graph = graph;
    }

    /**
     * Limit the work done during search (and avoid an infinite loop!)
     * by overriding this method to return `false` when the conditions
     * are such that any further searches will no longer yield any
     * relevant results.
     *
     * This condition is invoked in each iteration of the graph traversal
     * in `path()`.
     *
     * For example, in a search that is depth-limited (a user may want
     * the number of paths within a certain number of stops), the method
     * may be implemented by returning `true` only when the depth is
     * less than the desired amount.
     *
     * @param pathInformation contains the relevant distance, depth and
     *        vertex information
     */
    abstract protected boolean continueSearch(PathInformation pathInformation);

    /**
     * In addition to the conditions set in `continueSearch()`,
     * return `true` here when the conditions are met that determine
     * that a suitable path is found.
     *
     * This condition is invoked when the `destination` node is encountered
     * whilst traversing the graph in `path()`.
     *
     * For example, in a search for paths with an exact number of stops
     * (`n`), this method may be implemented by checking that `n` and depth
     * are the same.
     *
     * @param pathInformation contains the relevant distance, depth and
     *        vertex information
     */
    abstract protected boolean pathMatch(PathInformation pathInformation);

    private List<PathInformation<T>> paths(Vertex<T> origin, Vertex<T> destination, List<PathInformation<T>> pathsInfo, PathInformation<T> pathInfo) {
        for (Edge<T> edge : origin.getEdges()) {
            Vertex<T> currentlyVisitedVertex = edge.getVertex();

            //enqueue
            pathInfo.addVertex(currentlyVisitedVertex, edge.getWeight());

            if (currentlyVisitedVertex.equals(destination) && pathMatch(pathInfo)) {
                pathsInfo.add(new PathInformation(pathInfo));
            }

            if (continueSearch(pathInfo)) {
                paths(currentlyVisitedVertex, destination, pathsInfo, pathInfo);
            }

            //dequeue
            pathInfo.removeVertex(edge.getWeight());
        }

        return pathsInfo;
    }

    /**
     * Retrieve a `PathInformation` object for each of the possible routes
     * between `origin` and `destination`.
     * 
     * Visit vertices starting at `origin` using a depth-first algorithm,
     * and obtain a set of paths to `destination` using the criteria defined
     * in `pathMatch()` and `continueSearch()`.
     */
    public List<PathInformation<T>> paths(T origin, T destination) {
        PathInformation<T> pathInformation = new PathInformation<T>(this.graph.getVertex(origin));

        return paths(this.graph.getVertex(origin), this.graph.getVertex(destination), new LinkedList<PathInformation<T>>(), pathInformation);
    }

    private String indent(int level) {
        StringBuilder result = new StringBuilder("> ");

        for (int i = 0; i < level; i++) {
            result.append("  ");
        }

        return result.toString();
    }

    protected Graph<T> getGraph() {
        return this.graph;
    }
}
