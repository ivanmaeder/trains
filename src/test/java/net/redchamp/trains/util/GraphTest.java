package net.redchamp.trains.util;

import junit.framework.*;

import net.redchamp.trains.util.*;

public class GraphTest extends TestCase {
    public void testSizeOfGraphUsingConnect() {
        Graph<String> graph = new Graph<String>();
        graph.connect("A", "B", 5);

        assertEquals(graph.getVertices().size(), 2);
    }

    public void testVerticesAddedUsingConnect() {
        Graph<String> graph = new Graph<String>();
        graph.connect("A", "B", 5);

        assertEquals(graph.getVertices().size(), 2);

        assertTrue(graph.contains("A"));
        assertTrue(graph.contains("B"));
    }

    public void testEdgeWeight() {
        Graph<String> graph = new Graph<String>();
        graph.connect("A", "B", 5);

        assertEquals(graph.getVertex("A").getEdges().get(0).getWeight(), 5);
    }

    public void testEdgeVertex() {
        Graph<String> graph = new Graph<String>();
        graph.connect("A", "B", 5);

        assertEquals(graph.getVertex("A").getEdges().get(0).getVertex().getData(), "B");
    }

    public void testAddingMultipleEdges() {
        Graph<String> graph = new Graph<String>();
        graph.connect("A", "B", 1);
        graph.connect("A", "C", 2);
        graph.connect("A", "D", 3);

        assertEquals(graph.getVertex("A").getEdges().size(), 3);
    }
}
