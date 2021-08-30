package net.redchamp.trains.data;

import net.redchamp.trains.data.format.*;
import net.redchamp.trains.util.*;

/**
 * Load a set of triplets into a graph. Each triplet consists of two
 * vertices and the weight of an edge that should connect the first
 * vertex to the second.
 */
public abstract class Loader<T> {
    protected Graph<T> graph;
    protected TripletFormat<T> tripletFormat;
    
    public Loader(TripletFormat<T> tripletFormat) {
        this.tripletFormat = tripletFormat;
    }

    public abstract Graph<T> load();
}
