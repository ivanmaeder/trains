package net.redchamp.trains;

import junit.framework.*;

import java.io.*;
import java.nio.file.*;

import net.redchamp.trains.util.*;
import net.redchamp.trains.data.*;
import net.redchamp.trains.data.format.*;

public class FileLoaderTest extends TestCase {
    private static final String FILE_PATH = Paths.get(".").toAbsolutePath().normalize() + "/data/ThoughtWorks.txt";

    public void testFile() {
        Loader loader = new FileLoader(FILE_PATH, new BasicTripletFormat());
        
        Graph<String> graph = loader.load();

        assertEquals(5, graph.getVertices().size());

        assertTrue(graph.getVertices().contains(new Vertex<String>("A")));
        assertTrue(graph.getVertices().contains(new Vertex<String>("B")));
        assertTrue(graph.getVertices().contains(new Vertex<String>("C")));
        assertTrue(graph.getVertices().contains(new Vertex<String>("D")));
        assertTrue(graph.getVertices().contains(new Vertex<String>("E")));
    }
}
