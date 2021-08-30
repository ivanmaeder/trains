package net.redchamp.trains.util.paths;

import java.util.*;

import junit.framework.*;

import net.redchamp.trains.data.*;
import net.redchamp.trains.data.format.*;
import net.redchamp.trains.util.*;
import net.redchamp.trains.util.paths.*;

public class ExactVisitCountPathFinderTest extends TestCase {
    private Graph<String> testData;
    
    public void test1() {
        PathFinder<String> pathFinder = new ExactVisitCountPathFinder<String>(testData(), 1);
        int result = pathFinder.paths("A", "B").size();
        //A -> B

        assertEquals(1, result);
    }

    public void test2() {
        PathFinder<String> pathFinder = new ExactVisitCountPathFinder<String>(testData(), 2);
        int result = pathFinder.paths("A", "B").size();
        //A -> E -> B

        assertEquals(1, result);
    }

    public void test3() {
        PathFinder<String> pathFinder = new ExactVisitCountPathFinder<String>(testData(), 3);
        int result = pathFinder.paths("A", "B").size();
        //A -> D -> E -> B

        assertEquals(1, result);
    }

    public void test4() {
        PathFinder<String> pathFinder = new ExactVisitCountPathFinder<String>(testData(), 4);
        int result = pathFinder.paths("A", "B").size();
        //A -> D -> C -> E -> B
        //A -> B -> C -> E -> B

        assertEquals(2, result);
    }

    public void test5() {
        PathFinder<String> pathFinder = new ExactVisitCountPathFinder<String>(testData(), 5);
        int result = pathFinder.paths("A", "B").size();
        //A -> B -> C -> D -> E -> B
        //A -> D -> C -> D -> E -> B
        //A -> E -> B -> C -> E -> B

        assertEquals(3, result);
    }

    public void test6() {
        PathFinder<String> pathFinder = new ExactVisitCountPathFinder<String>(testData(), 6);
        int result = pathFinder.paths("A", "B").size();
        //A -> B -> C -> D -> C -> E -> B
        //A -> D -> C -> D -> C -> E -> B
        //A -> D -> E -> B -> C -> E -> B
        //A -> E -> B -> C -> D -> E -> B

        assertEquals(4, result);
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
