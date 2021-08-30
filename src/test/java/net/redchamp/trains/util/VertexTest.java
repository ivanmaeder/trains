package net.redchamp.trains.util;

import junit.framework.*;

import net.redchamp.trains.util.*;

public class VertexTest extends TestCase {
    public void testConstruction() {
        Vertex<String> v = new Vertex<String>("A");
        
        assertEquals(v.getData(), "A");
    }

    public void testEdges() {
        Vertex<String> v1 = new Vertex<String>("A");

        v1.addEdge(new Edge<String>(new Vertex<String>("B"), 1));
        v1.addEdge(new Edge<String>(new Vertex<String>("C"), 2));
        v1.addEdge(new Edge<String>(new Vertex<String>("D"), 3));

        assertEquals(v1.getEdges().size(), 3);
    }

    public void testEquality() {
        Vertex<String> v1 = new Vertex<String>("A");
        Vertex<String> v2 = new Vertex<String>("A");

        assertEquals(v1, v2);
    }

    public void testInequality() {
        Vertex<String> v1 = new Vertex<String>("A");
        Vertex<String> v2 = new Vertex<String>("B");

        assertFalse(v1.equals(v2));
    }

    public void testHashCode() {
        Vertex<String> v1 = new Vertex<String>("A");
        Vertex<String> v2 = new Vertex<String>("A");

        assertEquals(v1.hashCode(), v2.hashCode());
    }

    public void testToStringDoesNotResultInCircularReference() {
        Vertex<String> v1 = new Vertex<String>("A");
        Vertex<String> v2 = new Vertex<String>("B");
        v1.addEdge(new Edge<String>(v2, 1));
        v2.addEdge(new Edge<String>(v1, 1));

        assertNotNull(v1.toString());
    }

    public void testEqualityDoesNotResultInCircularReference() {
        Vertex<String> v1 = new Vertex<String>("A");
        Vertex<String> v2 = new Vertex<String>("B");
        v1.addEdge(new Edge<String>(v2, 1));
        v2.addEdge(new Edge<String>(v1, 1));

        assertFalse(v1.equals(v2));
    }
}
