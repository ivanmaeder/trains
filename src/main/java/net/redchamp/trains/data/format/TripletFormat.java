package net.redchamp.trains.data.format;

public abstract class TripletFormat<T> {
    public abstract Triplet<T> parseTriplet(String tripletEntry);
}
