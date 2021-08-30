package net.redchamp.trains;

import junit.framework.*;

import net.redchamp.trains.data.*;
import net.redchamp.trains.data.format.*;
import java.util.*;
import net.redchamp.trains.util.*;
import net.redchamp.trains.util.paths.*;

public class ThoughtWorksTest extends TestCase {
    private Graph<String> testData;

    public void test1() {
        Graph<String> graph = testData();

        int distance = graph.distance(new String[] { "A", "B", "C"});

        assertEquals(9, distance);
    }

    public void test2() {
        Graph<String> graph = testData();

        int distance = graph.distance(new String[] { "A", "D" });

        assertEquals(5, distance);
    }

    public void test3() {
        Graph<String> graph = testData();

        int distance = graph.distance(new String[] { "A", "D", "C" });

        assertEquals(13, distance);
    }

    public void test4() {
        Graph<String> graph = testData();

        int distance = graph.distance(new String[] { "A", "E", "B", "C", "D" });

        assertEquals(22, distance);
    }

    public void test5() {
        try {
            Graph<String> graph = testData();

            int distance = graph.distance(new String[] { "A", "E", "D" });

            assertTrue(false);
        } catch (IllegalStateException e) {
            assertTrue(true);
        }
    }

    public void test6() {
        PathFinder<String> pathFinder = new MaximumVisitCountPathFinder<String>(testData(), 3);
        int result = pathFinder.paths("C", "C").size();

        assertEquals(2, result);
    }

    public void test7() {
        PathFinder<String> pathFinder = new ExactVisitCountPathFinder<String>(testData(), 4);
        int result = pathFinder.paths("A", "C").size();

        assertEquals(3, result);
    }

    public void test8() {
        PathFinder<String> pathFinder = new ShortestDistancePathFinder<String>(testData());
        int result = pathFinder.paths("A", "C").get(0).getDistance();

        assertEquals(9, result);
    }

    public void test9() {
        PathFinder<String> pathFinder = new ShortestDistancePathFinder<String>(testData());
        int result = pathFinder.paths("B", "B").get(0).getDistance();

        assertEquals(9, result);
    }

    public void test10() {
        PathFinder<String> pathFinder = new MaximumDistancePathFinder<String>(testData(), 30);
        int result = pathFinder.paths("C", "C").size();

        assertEquals(7, result);
    }

    private Graph<String> testData() {
        if (this.testData == null) {
            String[] triplets = { "AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7" };

            Loader<String> loader = new ArrayLoader<String>(triplets, new BasicTripletFormat());

            this.testData = loader.load();
        }
        
        return this.testData;
    }
}
