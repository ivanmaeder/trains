package net.redchamp.trains.data;

import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

import net.redchamp.trains.data.format.*;
import net.redchamp.trains.util.*;

public class FileLoader<T> extends Loader<T> {
    private Path path;

    public FileLoader(String path, TripletFormat tripletFormat) {
        super(tripletFormat);

        this.path = Paths.get(path);
    }

    public Graph<T> load() {
        if (!Files.isReadable(this.path)) {
            throw new IllegalArgumentException("File cannot be read");
        }

        try {
            List<String> lines = Files.readAllLines(this.path, Charset.forName("UTF-8"));

            if (this.graph == null) {
                this.graph = new Graph<T>();
            }

            for (String line : lines) {
                Triplet<T> triplet = this.tripletFormat.parseTriplet(line);

                this.graph.connect(triplet.d1(), triplet.d2(), triplet.weight());
            }

            return this.graph;
        }
        catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}
