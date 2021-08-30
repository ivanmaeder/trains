package net.redchamp.trains;

import java.nio.file.*;

import net.redchamp.trains.util.*;
import net.redchamp.trains.util.paths.*;
import net.redchamp.trains.data.*;
import net.redchamp.trains.data.format.*;

public class TrainsApp {
    private static final String DEFAULT_FILE_PATH = Paths.get(".").toAbsolutePath().normalize() + "/data/ThoughtWorks.txt";

    public static void main(String[] args) {
        String filePath = DEFAULT_FILE_PATH;

        if (args.length > 0) {
            filePath = args[0];
        }

        Loader loader = new FileLoader(filePath, new BasicTripletFormat());
        Graph<String> graph = loader.load();

        thoughtWorks1(graph);
        thoughtWorks2(graph);
        thoughtWorks3(graph);
        thoughtWorks4(graph);
        thoughtWorks5(graph);
        thoughtWorks6(graph);
        thoughtWorks7(graph);
        thoughtWorks8(graph);
        thoughtWorks9(graph);
        thoughtWorks10(graph);
    }

    private static void thoughtWorks1(Graph<String> graph) {
        System.out.println(graph.distance(new String[] { "A", "B", "C" }));
    }

    private static void thoughtWorks2(Graph<String> graph) {
        System.out.println(graph.distance(new String[] { "A", "D" }));
    }

    private static void thoughtWorks3(Graph<String> graph) {
        System.out.println(graph.distance(new String[] { "A", "D", "C" }));
    }

    private static void thoughtWorks4(Graph<String> graph) {
        System.out.println(graph.distance(new String[] { "A", "E", "B", "C", "D" }));
    }

    private static void thoughtWorks5(Graph<String> graph) {
        try {
            System.out.println(graph.distance(new String[] { "A", "E", "D" }));
        }
        catch (IllegalStateException e) {
            System.out.println("NO SUCH ROUTE");
        }
    }

    private static void thoughtWorks6(Graph<String> graph) {
        PathFinder<String> pathFinder = new MaximumVisitCountPathFinder<String>(graph, 3);
        System.out.println(pathFinder.paths("C", "C").size());
    }

    private static void thoughtWorks7(Graph<String> graph) {
        PathFinder<String>pathFinder = new ExactVisitCountPathFinder<String>(graph, 4);
        System.out.println(pathFinder.paths("A", "C").size());
    }

    private static void thoughtWorks8(Graph<String> graph) {
        PathFinder<String>pathFinder = new ShortestDistancePathFinder<String>(graph);
        System.out.println(pathFinder.paths("A", "C").get(0).getDistance());
    }

    private static void thoughtWorks9(Graph<String> graph) {
        PathFinder<String>pathFinder = new ShortestDistancePathFinder<String>(graph);
        System.out.println(pathFinder.paths("B", "B").get(0).getDistance());
    }

    private static void thoughtWorks10(Graph<String> graph) {
        PathFinder<String>pathFinder = new MaximumDistancePathFinder<String>(graph, 30);
        System.out.println(pathFinder.paths("C", "C").size());
    }
}
