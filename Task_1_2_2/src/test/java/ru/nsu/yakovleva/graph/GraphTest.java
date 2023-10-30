package ru.nsu.yakovleva.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.nsu.yakovleva.graph.algorithms.Dijkstra.findShortestPaths;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.yakovleva.graph.init.GraphInitializer;
import ru.nsu.yakovleva.graph.init.WeightedEdge;
import ru.nsu.yakovleva.graph.types.AdjacencyList;
import ru.nsu.yakovleva.graph.types.AdjacencyMatrix;
import ru.nsu.yakovleva.graph.types.IncidenceMatrix;


/**
 * Test class.
 */
public class GraphTest {
    private Graph adjacencyMatrixGraph;
    private Graph adjacencyListGraph;
    private Graph incidenceMatrixGraph;


    @BeforeEach
    public void setup() {
        try {

            String amPath = "src/main/java/adjacency_matrix_input.txt";
            adjacencyMatrixGraph = GraphInitializer.initAdjMatGraph(amPath, Graph.MatrixType.ADJ_MATR);
            String alPath = "src/main/java/adjacency_input.txt";
            adjacencyListGraph = GraphInitializer.initAdjListGraph(alPath, Graph.MatrixType.ADJ_LIST);
            String imPath = "src/main/java/incidence_input.txt";
            incidenceMatrixGraph = GraphInitializer.initIncMatGraph(imPath, Graph.MatrixType.INC_MATR);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdjacencyMatrixCreation() {
        assertEquals(4, adjacencyMatrixGraph.getVertexCount());
    }

    @Test
    public void testIncidenceMatrixCreation() {
        assertEquals(5, incidenceMatrixGraph.getVertexCount());
    }

    @Test
    public void testAdjacencyListCreation() {
        assertEquals(4, adjacencyListGraph.getVertexCount());
    }

    @Test
    public void testAdjacencyMatrixEdgeAddition() {
        assertEquals(4, adjacencyMatrixGraph.getEdgeCount());
        adjacencyMatrixGraph.addEdge(1, 3, 2.5); // Add an edge with weight 2.5
        assertEquals(5, adjacencyMatrixGraph.getEdgeCount());
    }

    @Test
    public void testAdjacencyListEdgeAddition() {
        assertEquals(4, adjacencyListGraph.getEdgeCount());
        adjacencyListGraph.addEdge(1, 3, 2.5); // Add an edge with weight 2.5
        assertEquals(5, adjacencyListGraph.getEdgeCount());
    }

    @Test
    public void testIncidenceMatrixEdgeAddition() {
        assertEquals(5, incidenceMatrixGraph.getEdgeCount());
        incidenceMatrixGraph.addEdge(1, 4, 2); // Add an edge with weight 2.5
        assertEquals(6, incidenceMatrixGraph.getEdgeCount());
    }

    @Test
    public void testAdjacencyMatrix() {
        AdjacencyMatrix adjacencyMatrix = adjacencyMatrixGraph.getAdjacencyMatrix();
        assertEquals(5, adjacencyMatrix.get(2, 3)); // Check the weight of the edge
    }

    @Test
    public void testAdjacencyList() {
        AdjacencyList adjacencyList = adjacencyListGraph.getAdjacencyList();
        assertEquals(5, adjacencyList.get(2, 3)); // Check the weight of the edge
    }

    @Test
    public void testIncidenceMatrix() {
        IncidenceMatrix incidenceMatrix = incidenceMatrixGraph.getIncidenceMatrix();
        assertEquals(2, incidenceMatrix.get(1, 2)); // Check the weight of the edge
    }

    @Test
    public void testRemoveEdgeAdjacencyList() {
        adjacencyListGraph.removeEdge(0, 1);
        assertEquals(3, adjacencyListGraph.getEdgeCount());
    }

    @Test
    public void testRemoveEdgeAdjacencyMatrix() {
        assertEquals(4, adjacencyMatrixGraph.getEdgeCount());
        adjacencyMatrixGraph.removeEdge(0, 2);
        assertEquals(3, adjacencyMatrixGraph.getEdgeCount());
    }

    @Test
    public void testRemoveEdgeIncidenceMatrix() {
        assertEquals(5, incidenceMatrixGraph.getEdgeCount());
        incidenceMatrixGraph.removeEdge(1, 2);
        assertEquals(4, incidenceMatrixGraph.getEdgeCount());
    }

    @Test
    public void testAddVertexAdjacencyList() {
        assertEquals(4, adjacencyListGraph.getVertexCount());
        adjacencyListGraph.addVertex();
        assertEquals(5, adjacencyListGraph.getVertexCount());
    }

    @Test
    public void testRemoveVertexAdjacencyList() {
        assertEquals(4, adjacencyListGraph.getVertexCount());
        adjacencyListGraph.removeVertex(2);
        assertEquals(3, adjacencyListGraph.getVertexCount());
    }

    @Test
    public void testAddVertexAdjacencyMatrix() {
        assertEquals(4, adjacencyMatrixGraph.getVertexCount());
        adjacencyMatrixGraph.addVertex();
        assertEquals(5, adjacencyMatrixGraph.getVertexCount());
    }

    @Test
    public void testRemoveVertexAdjacencyMatrix() {
        assertEquals(4, adjacencyMatrixGraph.getVertexCount());
        adjacencyMatrixGraph.removeVertex(2);
        assertEquals(3, adjacencyMatrixGraph.getVertexCount());
    }

    @Test
    public void testAddVertexIncidenceMatrix() {
        assertEquals(5, incidenceMatrixGraph.getVertexCount());
        incidenceMatrixGraph.addVertex();
        assertEquals(6, incidenceMatrixGraph.getVertexCount());
    }

    @Test
    public void testRemoveVertexIncidenceMatrix() {
        assertEquals(5, incidenceMatrixGraph.getVertexCount());
        incidenceMatrixGraph.removeVertex(2);
        assertEquals(4, incidenceMatrixGraph.getVertexCount());
    }

    @Test
    public void getEdgeIncidenceMatrix() {
        assertEquals(2.0, incidenceMatrixGraph.getEdgeIncidenceMatrix(0));
    }

    @Test
    public void getEdgeAdjacencyMatrix() {
        assertEquals(4.0, adjacencyMatrixGraph.getEdgeAdjacencyMatrix(1, 2));
    }

    @Test
    public void getEdgeAdjacencyList() {
        WeightedEdge resultEdge1 = adjacencyListGraph.getEdgeAdjacencyList(1, 2);
        assertNotNull(resultEdge1);
        assertEquals(4, resultEdge1.getWeight());
    }

    @Test
    public void getVertexIncidenceMatrix() {
        List<Integer> neighbors1 = incidenceMatrixGraph.getVertex(3);
        assertNotNull(neighbors1);
        assertIterableEquals(List.of(1, 5), neighbors1);
    }

    @Test
    public void getVertexAdjacencyMatrix() {
        List<Integer> neighbors1 = adjacencyMatrixGraph.getVertex(0);
        assertNotNull(neighbors1);
        assertIterableEquals(List.of(1, 2), neighbors1);
    }

    @Test
    public void getVertexAdjacencyList() {
        List<WeightedEdge> neighbors1 = adjacencyListGraph.getVertexAdjacencyList(0);
        assertNotNull(neighbors1);
        assertEquals(2, neighbors1.size());
        assertEquals(1, neighbors1.get(0).getDestination());
        assertEquals(2, neighbors1.get(0).getWeight());
        assertEquals(2, neighbors1.get(1).getDestination());
        assertEquals(3, neighbors1.get(1).getWeight());
    }

    @Test
    public void changeWeightOfEdgeAdjacencyList() {
        adjacencyListGraph.changeWeight(0, 1, 3);
        WeightedEdge edge1 = new WeightedEdge(1, 3);
        WeightedEdge result = adjacencyListGraph.getEdgeAdjacencyList(0, 1);
        assertEquals(edge1.getWeight(), result.getWeight());
    }

    @Test
    public void changeWeightOfEdgeAdjacencyMatrix() {
        adjacencyMatrixGraph.changeWeight(0, 1, 3);
        assertEquals(3, adjacencyMatrixGraph.getEdgeAdjacencyMatrix(0, 1));
    }

    @Test
    public void changeWeightOfEdgeIncidenceMatrix() {
        incidenceMatrixGraph.changeWeight(1, 2, 3);
        assertEquals(3, incidenceMatrixGraph.getEdgeIncidenceMatrix(0));
    }

    @Test
    public void DijkstraAdjacencyList() {
        List<String> sortedVertices = findShortestPaths(adjacencyListGraph, 0);
        List<String> expectedSortedVertices = Arrays.asList("Vertex 0: 0", "Vertex 1: 2", "Vertex 2: 3", "Vertex 3: 8");
        assertEquals(expectedSortedVertices, sortedVertices);
    }

    @Test
    public void DijkstraAdjacencyMatrix() {
        List<String> sortedVertices = findShortestPaths(adjacencyMatrixGraph, 0);
        List<String> expectedSortedVertices = Arrays.asList("Vertex 0: 0.0", "Vertex 1: 2.0", "Vertex 2: 3.0", "Vertex 3: 8.0");
        assertEquals(expectedSortedVertices, sortedVertices);
    }

    @Test
    public void DijkstraIncidenceMatrix() {
        List<String> sortedVertices = findShortestPaths(incidenceMatrixGraph, 0);
        List<String> expectedSortedVertices = Arrays.asList("Vertex 0: 0.0", "Vertex 1: 2.0", "Vertex 2: 3.0", "Vertex 3: 6.0", "Vertex 4: 7.0");
        assertEquals(expectedSortedVertices, sortedVertices);
    }


}
