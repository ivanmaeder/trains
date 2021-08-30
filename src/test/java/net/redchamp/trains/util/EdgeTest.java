package net.redchamp.trains.util;

import junit.framework.*;

import net.redchamp.trains.util.*;

public class EdgeTest extends TestCase {
    public void testConstruction() {
        Edge<String> e = new Edge<String>(new Vertex<String>("A"), 1);
        
        assertEquals(e.getVertex(), new Vertex<String>("A"));
        assertEquals(e.getWeight(), 1);
    }

    public void testEquality() {
        Edge<String> e1 = new Edge<String>(new Vertex<String>("A"), 1);
        Edge<String> e2 = new Edge<String>(new Vertex<String>("A"), 1);

        assertEquals(e1, e2);
    }

    public void testEqualityWithDifferentVertices() {
        Edge<String> e1 = new Edge<String>(new Vertex<String>("A"), 1);
        Edge<String> e2 = new Edge<String>(new Vertex<String>("B"), 1);

        assertFalse(e1.equals(e2));
    }

    public void testEqualityWithDifferentWeight() {
        Edge<String> e1 = new Edge<String>(new Vertex<String>("A"), 1);
        Edge<String> e2 = new Edge<String>(new Vertex<String>("A"), 9);

        assertFalse(e1.equals(e2));
    }

    public void testHashCode() {
        Edge<String> e1 = new Edge<String>(new Vertex<String>("A"), 1);
        Edge<String> e2 = new Edge<String>(new Vertex<String>("A"), 1);

        assertEquals(e1.hashCode(), e2.hashCode());
    }
}
