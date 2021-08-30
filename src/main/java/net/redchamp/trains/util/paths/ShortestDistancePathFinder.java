package net.redchamp.trains.util.paths;

import java.util.*;

import net.redchamp.trains.util.*;

/**
 * Obtain the shortest path between two vertices.
 */
public class ShortestDistancePathFinder<T> extends PathFinder<T> {
    public ShortestDistancePathFinder(Graph<T> graph) {
        super(graph);
    }

    protected boolean pathMatch(PathInformation pathInformation) {
        throw new IllegalStateException();
    }

    protected boolean continueSearch(PathInformation pathInformation) {
        throw new IllegalStateException();
    }

    /**
     * An implementation of...
     *
     *     http://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#Pseudocode
     *
     * ... with a tiny modification so that the distance of a trip from
     * a vertex to the same vertex is not calculated as zero.
     *
     * @throws IllegalStateException if adjacent elements in `route` are
     * not connected
     */
    public List<PathInformation<T>> paths(T origin, T destination) {
        Vertex<T> originVertex = getGraph().getVertex(origin);
        Vertex<T> destinationVertex = getGraph().getVertex(destination);

        Map<Vertex<T>, Integer> distanceToVertex = new HashMap<Vertex<T>, Integer>();
        Map<Vertex<T>, Vertex<T>> previousInOptimalPath = new HashMap<Vertex<T>, Vertex<T>>();

        Set<Vertex<T>> verticesNotScanned = new HashSet<Vertex<T>>();

        for (Vertex<T> vertex : getGraph().getVertices()) {
            distanceToVertex.put(vertex, Integer.MAX_VALUE); //allow this to stand for infinity
            previousInOptimalPath.put(vertex, null);

            verticesNotScanned.add(vertex);
        }

        distanceToVertex.put(originVertex, 0);

        while (verticesNotScanned.size() > 0) {
            Vertex<T> currentlyVisitedVertex = next(verticesNotScanned, distanceToVertex);
            verticesNotScanned.remove(currentlyVisitedVertex);
    
            for (Edge<T> edge : currentlyVisitedVertex.getEdges()) {
                Vertex<T> adjacentVertex = edge.getVertex();

                int distanceViaCurrent = distanceToVertex.get(currentlyVisitedVertex) + edge.getWeight();
                int distancePreviously = distanceToVertex.get(adjacentVertex);

                //don't be lazy, the final distance cannot be zero
                if (distancePreviously == 0 || distanceViaCurrent < distancePreviously) {
                    distanceToVertex.put(adjacentVertex, distanceViaCurrent);
                    previousInOptimalPath.put(adjacentVertex, currentlyVisitedVertex);
                }

                if (adjacentVertex.equals(destinationVertex)) {
                    List<PathInformation<T>> result = new ArrayList<PathInformation<T>>();
                    result.add(toPathInformation(distanceToVertex, previousInOptimalPath, adjacentVertex));

                    return result;
                }
            }
        }

        throw new IllegalStateException("Vertices are not connected");
    }

    /**
     * Obtain the next vertex to look at. This is the vertex with the lowest
     * accumulated cost that hasn't been scanned (`vertices`).
     */
    private Vertex<T> next(Set<Vertex<T>> vertices, Map<Vertex<T>, Integer> distanceToVertex) {
        Vertex<T> result = null;
        Integer minimumDistanceFound = null;

        for (Vertex<T> vertex : vertices) {
            Integer distance = distanceToVertex.get(vertex);

            if (result == null) {
                result = vertex;
                minimumDistanceFound = distance;
            }

            if (distance < minimumDistanceFound) {
                result = vertex;
                minimumDistanceFound = distance;
            }
        }

        return result;
    }

    /**
     * Unwrap the structures created by `paths()` and turn it into a
     * `PathInformation` object.
     */
    private PathInformation<T> toPathInformation(Map<Vertex<T>, Integer> distanceToVertex, Map<Vertex<T>, Vertex<T>> previousInOptimalPath, Vertex<T> destinationVertex) {
        PathInformation<T> pathInformation = new PathInformation<T>();

        Vertex<T> current = destinationVertex;
        Vertex<T> previous;

        do {
            previous = previousInOptimalPath.get(current);

            int distance = distanceToVertex.get(current) - (previous == null ? 0 : distanceToVertex.get(previous));
            if (destinationVertex.equals(previous)) {
                /* The path is constructed backwards (starting from the
                 end).
                 
                 So there's a situation in which the origin and destination
                 are the same. E.g., imagine the path,
                 
                     A -> B -> A'

                 Var `destinationVertex` refers to A'.

                 Var `previous` will point to A' (good), and then later,
                 A (also good). But! The distance information in
                 `distanceToVertex` refers to A'.

                 So if the cost from A -> B is 5, and the cost from B -> A
                 (A') is 7, the value for A (or A') in `destinationVertex`
                 will be 12.

                 In this block we take care of that and use as `distance`
                 only 5.
                 */
                distance = distanceToVertex.get(current);
            }

            pathInformation.addVertexAtBeginning(current, distance);

            current = previous;
        } while (current != null && !destinationVertex.equals(previous));

        return pathInformation;
    }
}
