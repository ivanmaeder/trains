package net.redchamp.trains.util.paths;

import java.util.*;

import net.redchamp.trains.util.*;

/**
 * Obtain the set of paths with the exact number of stops given.
 */
public class ExactVisitCountPathFinder<T> extends PathFinder<T> {
    private int exactVisitCount;

    public ExactVisitCountPathFinder(Graph<T> graph, int exactVisitCount) {
        super(graph);

        this.exactVisitCount = exactVisitCount;
    }

    protected boolean pathMatch(PathInformation pathInformation) {
        return pathInformation.getDepth() == exactVisitCount;
    }

    protected boolean continueSearch(PathInformation pathInformation) {
        return pathInformation.getDepth() < exactVisitCount;
    }
}
