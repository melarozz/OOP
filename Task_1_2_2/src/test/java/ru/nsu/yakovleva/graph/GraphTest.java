package ru.nsu.yakovleva.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.nsu.yakovleva.graph.algorithms.Dijkstra.findShortestPaths;

import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.yakovleva.graph.init.GraphInitializer;
import ru.nsu.yakovleva.graph.types.AdjacencyMatrix;
import ru.nsu.yakovleva.graph.types.IncidenceMatrix;


/**
 * Test class.
 */
public class GraphTest {
    private Graph adjMatGraph;
    private Graph adjListGraph;
    private Graph incMatGraph;

    /**
     * Setup function.
     */
    @BeforeEach
    public void setup() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();

            String amPath = Objects.requireNonNull(
                    classLoader.getResource("adjacency_matrix_input.txt")).getPath();
            adjMatGraph = GraphInitializer.initAdjMatGraph(amPath, Graph.MatrixType.ADJ_MATR);

            String alPath = Objects.requireNonNull(
                    classLoader.getResource("adjacency_input.txt")).getPath();
            adjListGraph = GraphInitializer.initAdjListGraph(alPath, Graph.MatrixType.ADJ_LIST);

            String imPath = Objects.requireNonNull(
                    classLoader.getResource("incidence_input.txt")).getPath();
            incMatGraph = GraphInitializer.initIncMatGraph(imPath, Graph.MatrixType.INC_MATR);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNonExistingFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        assertThrows(NullPointerException.class, () -> {
            Objects.requireNonNull(classLoader.getResource("doesntexist.txt")).getPath();
        });

    }



    @Test
    public void testAdjacencyMatrixExists() {
        assertNotNull(adjMatGraph);
    }

    @Test
    public void testAdjacencyListExists() {
        assertNotNull(adjListGraph);
    }

    @Test
    public void testIncidenceMatrixExists() {
        assertNotNull(incMatGraph);
    }

    @Test
    public void testAdjacencyMatrixCreation() {
        assertEquals(4, adjMatGraph.getVertexCount());
    }

    @Test
    public void testIncidenceMatrixCreation() {
        assertEquals(5, incMatGraph.getVertexCount());
    }

    @Test
    public void testAdjacencyListCreation() {
        assertEquals(4, adjListGraph.getVertexCount());
    }

    @Test
    public void testAdjacencyMatrixEdgeAddition() {
        assertEquals(4, adjMatGraph.getEdgeCount());
        adjMatGraph.addEdge(1, 3, 2.5); // Add an edge with weight 2.5
        assertEquals(5, adjMatGraph.getEdgeCount());
    }

    @Test
    public void testAdjacencyListEdgeAddition() {
        assertEquals(4, adjListGraph.getEdgeCount());
        adjListGraph.addEdge(1, 3, 2.5); // Add an edge with weight 2.5
        assertEquals(5, adjListGraph.getEdgeCount());
    }

    @Test
    public void testIncidenceMatrixEdgeAddition() {
        assertEquals(5, incMatGraph.getEdgeCount());
        incMatGraph.addEdge(1, 4, 2); // Add an edge with weight 2.5
        assertEquals(6, incMatGraph.getEdgeCount());
    }

    @Test
    public void testAdjacencyMatrix() {
        AdjacencyMatrix adjacencyMatrix = adjMatGraph.getAdjacencyMatrix();
        assertEquals(5, adjacencyMatrix.get(2, 3)); // Check the weight of the edge
    }

    @Test
    public void testAdjacencyList() {
        AdjacencyMatrix adjacencyMatrix = adjListGraph.getAdjacencyMatrix();
        assertEquals(5, adjacencyMatrix.get(2, 3)); // Check the weight of the edge
    }

    @Test
    public void testIncidenceMatrix() {
        IncidenceMatrix incidenceMatrix = incMatGraph.getIncidenceMatrix();
        assertEquals(2, incidenceMatrix.get(1, 2)); // Check the weight of the edge
    }

    @Test
    public void testRemoveEdgeAdjacencyList() {
        adjListGraph.removeEdge(0, 1);
        assertEquals(3, adjListGraph.getEdgeCount());
    }

    @Test
    public void testRemoveEdgeAdjacencyListNotExisting() {
        assertEquals(4, adjListGraph.getEdgeCount());
        adjListGraph.removeEdge(0, 3);
        assertEquals(4, adjListGraph.getEdgeCount());
    }

    @Test
    public void testRemoveEdgeAdjacencyMatrix() {
        assertEquals(4, adjMatGraph.getEdgeCount());
        adjMatGraph.removeEdge(0, 2);
        assertEquals(3, adjMatGraph.getEdgeCount());
    }

    @Test
    public void testRemoveEdgeAdjacencyMatrixNotExisting() {
        assertEquals(4, adjMatGraph.getEdgeCount());
        adjMatGraph.removeEdge(0, 3);
        assertEquals(4, adjMatGraph.getEdgeCount());
    }

    @Test
    public void testRemoveEdgeIncidenceMatrix() {
        assertEquals(5, incMatGraph.getEdgeCount());
        incMatGraph.removeEdge(1, 2);
        assertEquals(4, incMatGraph.getEdgeCount());
    }

    @Test
    public void testRemoveEdgeIncidenceMatrixNotExisting() {
        assertEquals(5, incMatGraph.getEdgeCount());
        incMatGraph.removeEdge(1, 4);
        assertEquals(5, incMatGraph.getEdgeCount());
    }

    @Test
    public void testAddVertexAdjacencyList() {
        assertEquals(4, adjListGraph.getVertexCount());
        adjListGraph.addVertex();
        assertEquals(5, adjListGraph.getVertexCount());
    }

    @Test
    public void testRemoveVertexAdjacencyList() {
        assertEquals(4, adjListGraph.getEdgeCount());
        assertEquals(4, adjListGraph.getVertexCount());
        adjListGraph.removeVertex(2);
        assertEquals(3, adjListGraph.getVertexCount());
        assertEquals(1, adjListGraph.getEdgeCount());
    }

    @Test
    public void testAddVertexAdjacencyMatrix() {
        assertEquals(4, adjMatGraph.getVertexCount());
        adjMatGraph.addVertex();
        assertEquals(5, adjMatGraph.getVertexCount());
    }

    @Test
    public void testRemoveVertexAdjacencyMatrix() {
        assertEquals(4, adjMatGraph.getEdgeCount());
        assertEquals(4, adjMatGraph.getVertexCount());
        adjMatGraph.removeVertex(2);
        assertEquals(3, adjMatGraph.getVertexCount());
        assertEquals(1, adjMatGraph.getEdgeCount());
    }

    @Test
    public void testAddVertexIncidenceMatrix() {
        assertEquals(5, incMatGraph.getVertexCount());
        incMatGraph.addVertex();
        assertEquals(6, incMatGraph.getVertexCount());
    }

    @Test
    public void testRemoveVertexIncidenceMatrix() {
        assertEquals(5, incMatGraph.getEdgeCount());
        assertEquals(5, incMatGraph.getVertexCount());
        incMatGraph.removeVertex(2);
        assertEquals(4, incMatGraph.getVertexCount());
        assertEquals(2, incMatGraph.getEdgeCount());
    }

    @Test
    public void getEdgeIncidenceMatrix() {
        assertEquals(2.0, incMatGraph.getEdgeIncidenceMatrix(0));
    }

    @Test
    public void getEdgeAdjacencyMatrix() {
        assertEquals(4.0, adjMatGraph.getEdgeAdjacencyMatrix(1, 2));
    }

    @Test
    public void getEdgeAdjacencyList() {
        assertEquals(4.0, adjListGraph.getEdgeAdjacencyMatrix(1, 2));
    }

    @Test
    public void getVertexIncidenceMatrix() {
        List<Integer> neighbors1 = incMatGraph.getVertex(3);
        assertNotNull(neighbors1);
        assertIterableEquals(List.of(1, 5), neighbors1);
    }

    @Test
    public void getVertexAdjacencyMatrix() {
        List<Integer> neighbors1 = adjMatGraph.getVertex(0);
        assertNotNull(neighbors1);
        assertIterableEquals(List.of(1, 2), neighbors1);
    }

    @Test
    public void getVertexAdjacencyList() {
        List<Integer> neighbors1 = adjListGraph.getVertex(0);
        assertIterableEquals(List.of(1, 2), neighbors1);
    }

    @Test
    public void changeWeightOfEdgeAdjacencyList() {
        adjListGraph.changeWeight(0, 1, 3);
        assertEquals(3, adjListGraph.getEdgeAdjacencyMatrix(0, 1));
    }

    @Test
    public void changeWeightOfEdgeAdjacencyListNonExist() {
        adjListGraph.changeWeight(0, 4, 3); //non-existing
        assertEquals(0, adjListGraph.getEdgeAdjacencyMatrix(0, 4));
    }

    @Test
    public void changeWeightOfEdgeAdjacencyMatrix() {
        adjMatGraph.changeWeight(0, 1, 3);
        assertEquals(3, adjMatGraph.getEdgeAdjacencyMatrix(0, 1));
    }

    @Test
    public void changeWeightOfEdgeAdjacencyMatrixNonExist() {
        adjMatGraph.changeWeight(0, 4, 3); //non-existing
        assertEquals(0, adjMatGraph.getEdgeAdjacencyMatrix(0, 4));
    }

    @Test
    public void changeWeightOfEdgeIncidenceMatrix() {
        incMatGraph.changeWeight(1, 2, 3);
        assertEquals(3, incMatGraph.getEdgeIncidenceMatrix(0));
    }

    @Test
    public void changeWeightOfEdgeIncidenceMatrixNonExist() {
        incMatGraph.changeWeight(1, 5, 3);
        assertEquals(0, incMatGraph.getEdgeIncidenceMatrix(6));
    }

    @Test
    public void dijkstraAdjacencyList() {
        List<String> sortedVertices = findShortestPaths(adjListGraph, 0);
        String n1 = "Vertex 0: 0.0";
        String n2 = "Vertex 1: 2.0";
        String n3 = "Vertex 2: 3.0";
        String n4 = "Vertex 3: 8.0";
        List<String> expected = Arrays.asList(n1, n2, n3, n4);
        assertEquals(expected, sortedVertices);
    }

    @Test
    public void dijkstraAdjacencyListAnother() {
        List<String> sortedVertices = findShortestPaths(adjListGraph, 1);
        String n1 = "Vertex 1: 0.0";
        String n2 = "Vertex 0: 2.0";
        String n3 = "Vertex 2: 4.0";
        String n4 = "Vertex 3: 9.0";
        List<String> expected = Arrays.asList(n1, n2, n3, n4);
        assertEquals(expected, sortedVertices);
    }

    @Test
    public void dijkstraAdjacencyMatrix() {
        List<String> sortedVertices = findShortestPaths(adjMatGraph, 0);
        String n1 = "Vertex 0: 0.0";
        String n2 = "Vertex 1: 2.0";
        String n3 = "Vertex 2: 3.0";
        String n4 = "Vertex 3: 8.0";

        List<String> expected = Arrays.asList(n1, n2, n3, n4);
        assertEquals(expected, sortedVertices);
    }

    @Test
    public void dijkstraAdjacencyMatrixAnother() {
        List<String> sortedVertices = findShortestPaths(adjMatGraph, 2);
        String n1 = "Vertex 2: 0.0";
        String n2 = "Vertex 0: 3.0";
        String n3 = "Vertex 1: 4.0";
        String n4 = "Vertex 3: 5.0";

        List<String> expected = Arrays.asList(n1, n2, n3, n4);
        assertEquals(expected, sortedVertices);
    }

    @Test
    public void dijkstraIncidenceMatrix() {
        List<String> sortedVertices = findShortestPaths(incMatGraph, 0);
        String n1 = "Vertex 0: 0.0";
        String n2 = "Vertex 1: 2.0";
        String n3 = "Vertex 2: 3.0";
        String n4 = "Vertex 3: 6.0";
        String n5 = "Vertex 4: 7.0";
        List<String> expected = Arrays.asList(n1, n2, n3, n4, n5);
        assertEquals(expected, sortedVertices);
    }

    @Test
    public void dijkstraIncidenceMatrixAnother() {
        List<String> sortedVertices = findShortestPaths(incMatGraph, 3);
        String n1 = "Vertex 3: 0.0";
        String n2 = "Vertex 1: 4.0";
        String n3 = "Vertex 0: 6.0";
        String n4 = "Vertex 4: 9.0";
        String n5 = "Vertex 2: 9.0";
        List<String> expected = Arrays.asList(n1, n2, n3, n4, n5);
        assertEquals(expected, sortedVertices);
    }

    @Test
    public void dijkstraIncidenceMatrixBreak() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            findShortestPaths(incMatGraph, -1);
        });
    }

    @Test
    public void dijkstraAdjacencyMatrixBreak() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            findShortestPaths(adjMatGraph, -1);
        });
    }

    @Test
    public void dijkstraAdjacencyListBreak() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            findShortestPaths(adjListGraph, -1);
        });
    }

    @Test
    public void measureDijkstraTime() {
        int numIterations = 100; // Adjust the number of iterations as needed

        long adjacencyMatrixTime = 0;
        long adjacencyListTime = 0;
        long incidenceMatrixTime = 0;

        for (int i = 0; i < numIterations; i++) {
            long startTime = System.nanoTime();
            findShortestPaths(adjMatGraph, 0);
            long endTime = System.nanoTime();
            adjacencyMatrixTime += (endTime - startTime);

            startTime = System.nanoTime();
            findShortestPaths(adjListGraph, 0);
            endTime = System.nanoTime();
            adjacencyListTime += (endTime - startTime);

            startTime = System.nanoTime();
            findShortestPaths(incMatGraph, 0);
            endTime = System.nanoTime();
            incidenceMatrixTime += (endTime - startTime);
        }

        adjacencyMatrixTime /= numIterations;
        adjacencyListTime /= numIterations;
        incidenceMatrixTime /= numIterations;

        System.out.println("Average Dijkstra execution time with Adjacency Matrix: "
                + adjacencyMatrixTime + " nanoseconds.");
        System.out.println("Average Dijkstra execution time with Adjacency List: "
                + adjacencyListTime + " nanoseconds.");
        System.out.println("Average Dijkstra execution time with Incidence Matrix: "
                + incidenceMatrixTime + " nanoseconds.");
        //the Incidence Matrix is always the slowest, the Adjacency List is usually the fastest,
        //but not so much faster than Adjacency Matrix
    }

}
