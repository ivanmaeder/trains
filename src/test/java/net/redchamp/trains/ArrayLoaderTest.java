package net.redchamp.trains;

import junit.framework.*;

import net.redchamp.trains.util.*;
import net.redchamp.trains.data.*;
import net.redchamp.trains.data.format.*;

public class ArrayLoaderTest extends TestCase {
    public void testLoadPair() {
        String[] triplets = { "AB1" };

        Loader<String> loader = new ArrayLoader<String>(triplets, new BasicTripletFormat());

        Graph<String> graph = loader.load();

        Vertex<String> v1 = new Vertex<String>("A");
        Vertex<String> v2 = new Vertex<String>("B");
        v1.addEdge(new Edge<String>(v2, 1));

        assertEquals(graph.getVertices().size(), 2);

        assertTrue(graph.getVertices().contains(v1));
        assertTrue(graph.getVertices().contains(v2));
    }

    public void testLoadV() {
        String[] triplets = { "AB1", "AC2" };

        Loader<String> loader = new ArrayLoader<String>(triplets, new BasicTripletFormat());

        Graph<String> graph = loader.load();

        for (Vertex<String> vertex : graph.getVertices()) {
            if (vertex.getData().equals("A")) {
                assertEquals(vertex.getEdges().size(), 2);
            }
        }
    }
}
