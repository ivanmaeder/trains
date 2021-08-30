# Trains
Exercises with graphs.

## How does it all work?
### Graphs, vertices and edges
Create a graph (with vertices and edges) using the `Graph` class, like this:

```
Graph<String> graph = new Graph<String>();

graph.connect("A", "B", 5);
graph.connect("B", "C", 3);
```

This connects a new set of vertices `A` to `B` (with weight 5), and `B` to `C` (with weight 3).

In this way, working directly with `Vertex` and `Edge` objects is avoided during construction (although those objects are used to work with an already constructed graph).

### Loading data
A `Loader` class is the interface for classes responsible for loading data from a specific source (e.g., a file) into a graph.

`Loader` classes rely on an implementation of `TripletFormat` which defines how the source data is formatted and parsed. It assumes that data is available in chunks comprised of three things:

1. The data of the first vertex
2. The data of the second
3. The weight connecting the first vertex to the second

The parsing (performed in a `TripletFormat` implementation) is independent of the loading (performed in a `Loader` implementation).

There are two `Loader` implementations: one for loading data from an array and another for loading data from a file.

Thers is one `TripletFormat` implementation based on a simple string in this example: `AB5` (where `A` refers to the data of the first vertex, `B` that of the second, and `5` the weight of the edge between `A` and `B`).

This example loads data from an array with elements formatted using the example above, into a new graph:

```
String[] triplets = { "AB5", "BC3" };

Loader<String> loader = new ArrayLoader<String>(triplets, new BasicTripletFormat());

Graph<String> graph = loader.load();
```

### Finding paths
Use an implementation of `PathFinder` to locate a set of paths connecting two vertices based on different criteria. For example, paths with a distance less than or equal to 30 (with `MaximumDistancePathFinder`), or paths with an exact number of stops (`ExactVisitCountPathFinder`).

Here is an example showing how to retrieve all the paths in `graph` between `A` and `B` with a maximum number of `3` stops:

```
PathFinder<String> pathFinder = new MaximumVisitCountPathFinder<String>(graph, 3);

List<PathInformation<String>> paths = pathFinder.paths("A", "B");
```

Again, instead of working directly with `Vertex` objects, the data objects are used instead.

### Custom path-finders
Method `paths()` of `PathFinder` implements a depth-first traversal that can be re-used in subclasses by overriding two methods that help `paths()` determine the conditions under which a set of paths should be constructed:

- `pathMatch()` which whilst traversing, is called when the destination vertex is found and must return `true` when a the path constructed is a suitable match
- `continueSearching()` which is invoked in every iteration and returns `true` while it is worthwhile continuing with the search for paths

The following is code from `ExactVisitCountPathFinder`. It informs `path()` when a suitable match as been found (`patchMatch()`) and how to limit searches (`continueSearch()`).

```
protected boolean pathMatch(PathInformation pathInformation) {
    return pathInformation.getDepth() == exactVisitCount;
}

protected boolean continueSearch(PathInformation pathInformation) {
    return pathInformation.getDepth() < exactVisitCount;
}
```

### Shortest paths
The class for finding the shortest path between two vertices is a special case: it does not use the depth-first algorithm implemented in `paths()`; it overrides `paths()` and uses Dijkstra's algorithm.

## Assumptions
The following has been assumed:

- Vertices are unique; the `data` field of each `Vertex` object in a graph will be different
- There is no need for creating dangling vertices (vertices not connected to another vertex)
- Vertices do not have edges to themselves
- Graphs are not modified whilst they're searched
- Files are encoded in a UTF-8 compatible format
- The format of files is one entry (e.g., `AB5`) per line

## How to run

### Using Maven...

#### Compile
In the project root directory (`trains/`), execute:

```
mvn compile
```

#### Run

The set of test cases provided in the assignment are hard-coded in `TrainsApp`. This loads data from `trains/data/ThoughtWorks.txt` and performs those test cases. Execute:

```
mvn exec:java -Dexec.mainClass="net.redchamp.trains.TrainsApp
```

A different file carefully put together to work with the same test cases can be run by executing `TrainsApp` and passing the file path as a parameter like this:

```
mvn exec:java -Dexec.mainClass="net.redchamp.trains.TrainsApp" -Dexec.args="data/ThoughtWorks.txt"
```

Replace `data/ThoughtWorks.txt` with an alternative input file.

#### Unit tests

The complete set of unit tests can be run like so:

```
mvn test
```

The test cases provided in the assignment are coded in `ThoughtWorksTest`.