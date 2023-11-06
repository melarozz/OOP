package ru.nsu.yakovleva.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.nsu.yakovleva.graph.algorithms.Dijkstra.findShortestPaths;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.yakovleva.graph.init.GraphInitializer;

public class GraphTest {
    public Graph adjMatrGraph;
    public Graph adjListGraph;
    public Graph incMatrGraph;
    @BeforeEach
    public void testInitialize() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();

        String adjacencyMatrixInputFile = Objects.requireNonNull(
                classLoader.getResource("adjacency_matrix_input.txt")).getPath();

        adjMatrGraph = GraphInitializer.initAdjacencyMatrixGraph(adjacencyMatrixInputFile);

        assertNotNull(adjMatrGraph);
        assertEquals(4, adjMatrGraph.getVertexCount());

        String incidenceMatrixInputFile = Objects.requireNonNull(
                classLoader.getResource("incidence_input.txt")).getPath();

        incMatrGraph = GraphInitializer.initIncidenceMatrixGraph(incidenceMatrixInputFile);

        assertNotNull(incMatrGraph);
        assertEquals(5, incMatrGraph.getVertexCount());

        String adjacencyListInputFile = Objects.requireNonNull(
                classLoader.getResource("adjacency_input.txt")).getPath();

        adjListGraph = GraphInitializer.initAdjacencyListGraph(adjacencyListInputFile);

        assertNotNull(adjListGraph);
        assertEquals(4, adjListGraph.getVertexCount());
    }


    @Test
    public void testNonExistingFile() {
        assertThrows(FileNotFoundException.class, () -> {
            GraphInitializer.initAdjacencyMatrixGraph("non_existing_file.txt");
        });
    }

    @Test
    public void addEdge(){
        assertEquals(4, adjMatrGraph.getEdgeCount());
        adjMatrGraph.addEdge(0,3,4);
        assertEquals(5, adjMatrGraph.getEdgeCount());

        assertEquals(4, adjListGraph.getEdgeCount());
        adjListGraph.addEdge(0,3,4);
        assertEquals(5, adjListGraph.getEdgeCount());

        assertEquals(5, incMatrGraph.getEdgeCount());
        incMatrGraph.addEdge(1,5,4);
        assertEquals(6, incMatrGraph.getEdgeCount());
    }

    @Test
    public void removeEdge(){
        assertEquals(4, adjMatrGraph.getEdgeCount());
        adjMatrGraph.removeEdge(0,1);
        assertEquals(3, adjMatrGraph.getEdgeCount());

        assertEquals(4, adjListGraph.getEdgeCount());
        adjListGraph.removeEdge(0,1);
        assertEquals(3, adjListGraph.getEdgeCount());

        assertEquals(5, incMatrGraph.getEdgeCount());
        incMatrGraph.removeEdge(1,3);
        assertEquals(4, incMatrGraph.getEdgeCount());
    }

    @Test
    public void addVertex(){
        assertEquals(4, adjMatrGraph.getVertexCount());
        adjMatrGraph.addVertex();
        assertEquals(5, adjMatrGraph.getVertexCount());

        assertEquals(4, adjListGraph.getVertexCount());
        adjListGraph.addVertex();
        assertEquals(5, adjListGraph.getVertexCount());

        assertEquals(5, incMatrGraph.getVertexCount());
        incMatrGraph.addVertex();
        assertEquals(6, incMatrGraph.getVertexCount());
    }

    @Test
    public void removeVertex(){
        assertEquals(4, adjMatrGraph.getVertexCount());
        assertEquals(4, adjMatrGraph.getEdgeCount());
        adjMatrGraph.removeVertex(1);
        assertEquals(3, adjMatrGraph.getVertexCount());
        assertEquals(2, adjMatrGraph.getEdgeCount());

        assertEquals(4, adjListGraph.getVertexCount());
        assertEquals(4, adjListGraph.getEdgeCount());
        adjListGraph.removeVertex(1);
        assertEquals(3, adjListGraph.getVertexCount());
        assertEquals(2, adjListGraph.getEdgeCount());

        assertEquals(5, incMatrGraph.getVertexCount());
        assertEquals(5, incMatrGraph.getEdgeCount());
        incMatrGraph.removeVertex(1);
        assertEquals(4, incMatrGraph.getVertexCount());
        assertEquals(2, incMatrGraph.getEdgeCount());
    }

    @Test
    public void changeWeight(){
        assertEquals(2, adjMatrGraph.getEdgeWeight(0,1));
        adjMatrGraph.changeWeight(0,1,3);
        assertEquals(3, adjMatrGraph.getEdgeWeight(0,1));

        assertEquals(2, adjListGraph.getEdgeWeight(0,1));
        adjListGraph.changeWeight(0,1,3);
        assertEquals(3, adjListGraph.getEdgeWeight(0,1));

        assertEquals(2, incMatrGraph.getEdgeWeight(0, 1));
        incMatrGraph.changeWeight(0, 1, 3);
        assertEquals(3, incMatrGraph.getEdgeWeight(0, 1));
    }

    @Test
    public void testDijkstraAdjacencyMatrix() {
        List<String> shortestPaths = findShortestPaths(adjMatrGraph, 0);
        List<String> expectedPaths = Arrays.asList(
                "Vertex 0: 0.0",
                "Vertex 1: 2.0",
                "Vertex 2: 3.0",
                "Vertex 3: 8.0"
        );
        assertIterableEquals(expectedPaths, shortestPaths);
    }

    @Test
    public void testDijkstraAdjacencyList() {
        List<String> shortestPaths = findShortestPaths(adjListGraph, 0);
        List<String> expectedPaths = Arrays.asList(
                "Vertex 0: 0.0",
                "Vertex 1: 2.0",
                "Vertex 2: 3.0",
                "Vertex 3: 8.0"
        );
        assertIterableEquals(expectedPaths, shortestPaths);
    }

    @Test
    public void testDijkstraIncidenceMatrix() {
        List<String> shortestPaths = findShortestPaths(incMatrGraph, 0);
        List<String> expectedPaths = Arrays.asList(
                "Vertex 0: 0.0",
                "Vertex 1: 2.0",
                "Vertex 2: 3.0",
                "Vertex 3: 6.0",
                "Vertex 4: 7.0"
        );
        assertIterableEquals(expectedPaths, shortestPaths);
    }



}
