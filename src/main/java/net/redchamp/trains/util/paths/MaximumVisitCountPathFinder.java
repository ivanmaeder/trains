package net.redchamp.trains.util.paths;

import java.util.*;

import net.redchamp.trains.util.*;

/**
 * Obtain a set of paths within the maximum number of stops given.
 */
public class MaximumVisitCountPathFinder<T> extends PathFinder<T> {
    private int maximumVisitCount;

    public MaximumVisitCountPathFinder(Graph<T> graph, int maximumVisitCount) {
        super(graph);

        this.maximumVisitCount = maximumVisitCount;
    }

    protected boolean pathMatch(PathInformation pathInformation) {
        return pathInformation.getDepth() <= maximumVisitCount;
    }

    protected boolean continueSearch(PathInformation pathInformation) {
        return pathInformation.getDepth() < maximumVisitCount;
    }
}
