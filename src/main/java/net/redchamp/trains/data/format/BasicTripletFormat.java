package net.redchamp.trains.data.format;

import net.redchamp.trains.util.*;

public class BasicTripletFormat extends TripletFormat<String> {
    public Triplet<String> parseTriplet(String tripletEntry) {
        String d1 = String.valueOf(tripletEntry.charAt(0));
        String d2 = String.valueOf(tripletEntry.charAt(1));

        int weight = Integer.parseInt(String.valueOf(tripletEntry.charAt(2)));

        return new Triplet<String>(d1, d2, weight);
    }
}
