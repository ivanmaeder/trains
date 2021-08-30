package net.redchamp.trains.util.paths;

import java.util.*;

import net.redchamp.trains.util.*;

/**
 * Obtain a set of paths within the maximum distance given.
 */
public class MaximumDistancePathFinder<T> extends PathFinder<T> {
    private int maximumDistance;

    public MaximumDistancePathFinder(Graph<T> graph, int maximumDistance) {
        super(graph);

        this.maximumDistance = maximumDistance;
    }

    protected boolean pathMatch(PathInformation pathInformation) {
        return pathInformation.getDistance() < maximumDistance;
    }

    protected boolean continueSearch(PathInformation pathInformation) {
        return pathInformation.getDistance() < maximumDistance;
    }
}
