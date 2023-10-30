package ru.nsu.yakovleva.graph.init;

import ru.nsu.yakovleva.graph.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * GraphInitializer class provides methods to initialize graphs from input files.
 */
public class GraphInitializer {
    /**
     * Initialize an adjacency list graph from an input file.
     *
     * @param inputFile   The path to the input file.
     * @param matrixType  The type of matrix representation to create (e.g., Adjacency List, Adjacency Matrix, Incidence Matrix).
     * @return The initialized graph.
     * @throws FileNotFoundException if the input file is not found.
     */
    public static Graph initializeAdjacencyListGraph(String inputFile, Graph.MatrixType matrixType) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFile));
        int vertexCount = scanner.nextInt();
        Graph graph = new Graph(vertexCount, matrixType);

        while (scanner.hasNext()) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            double weight = scanner.nextDouble();
            graph.addEdge(source, destination, weight);
        }
        scanner.close();

        return graph;
    }

    /**
     * Initialize an adjacency matrix graph from an input file.
     *
     * @param inputFile   The path to the input file.
     * @param matrixType  The type of matrix representation to create (e.g., Adjacency List, Adjacency Matrix, Incidence Matrix).
     * @return The initialized graph.
     * @throws FileNotFoundException if the input file is not found.
     */
    public static Graph initializeAdjacencyMatrixGraph(String inputFile, Graph.MatrixType matrixType) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFile));
        int V = scanner.nextInt();
        Graph graph = new Graph(V, matrixType);
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                double weight = scanner.nextDouble();
                if (weight != 0.0) {
                    graph.addEdge(i, j, weight);
                }
            }
        }
        scanner.close();
        return graph;
    }

    /**
     * Initialize an incidence matrix graph from an input file.
     *
     * @param inputFile   The path to the input file.
     * @param matrixType  The type of matrix representation to create (e.g., Adjacency List, Adjacency Matrix, Incidence Matrix).
     * @return The initialized graph.
     * @throws FileNotFoundException if the input file is not found.
     */
    public static Graph initializeIncidenceMatrixGraph(String inputFile, Graph.MatrixType matrixType) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFile));
        int V = scanner.nextInt();
        int E = scanner.nextInt();
        Graph graph = new Graph(V, matrixType);
        List<List<Integer>> edges = new ArrayList<>();
        scanner.nextLine();
        String edgePairsLine = scanner.nextLine();
        String[] edgePairs = edgePairsLine.split(" ");
        for (String edgePair : edgePairs) {
            String[] vertices = edgePair.split("-");
            int startVertex = Integer.parseInt(vertices[0]);
            int endVertex = Integer.parseInt(vertices[1]);
            List<Integer> edge = new ArrayList<>();
            edge.add(startVertex);
            edge.add(endVertex);
            edges.add(edge);
        }

        for (int i = 0; i < V; i++) {
            int source = scanner.nextInt();
            for (int j = 0; j < E; j++) {
                double weight = scanner.nextDouble();
                int destination;
                if (edges.get(j).get(0) == source) {
                    destination = edges.get(j).get(1);
                } else if (edges.get(j).get(1) == source) {
                    destination = edges.get(j).get(0);
                } else {
                    continue;
                }
                if (weight != 0.0) {
                    graph.addEdge(source, destination, weight);
                }
            }
        }

        scanner.close();
        return graph;
    }
}
