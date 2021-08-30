package net.redchamp.trains.data;

import net.redchamp.trains.data.format.*;
import net.redchamp.trains.util.*;

/**
 * Load an array of triplets.
 */
public class ArrayLoader<T> extends Loader<T> {
    private String[] triplets;

    public ArrayLoader(String[] triplets, TripletFormat<T> tripletFormat) {
        super(tripletFormat);
        this.triplets = triplets;
    }

    public Graph<T> load() {
        if (this.graph == null) {
            this.graph = new Graph<T>();
        }

        for (String tripletEntry: this.triplets) {
            Triplet<T> triplet = this.tripletFormat.parseTriplet(tripletEntry);

            this.graph.connect(triplet.d1(), triplet.d2(), triplet.weight());
        }

        return this.graph;
    }
}
