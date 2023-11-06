package ru.nsu.yakovleva.graph.init;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ru.nsu.yakovleva.graph.Graph;
import ru.nsu.yakovleva.graph.types.AdjacencyMatrix;
import ru.nsu.yakovleva.graph.types.IncidenceMatrix;

/**
 * Class for initializing graph.
 */
public class GraphInitializer {

    /**
     * Initialize an adjacency matrix graph from an input file.
     *
     * @param inputFile The path to the input file.
     * @return The initialized adjacency matrix graph.
     * @throws FileNotFoundException if the input file is not found.
     */
    public static Graph initAdjacencyMatrixGraph(String inputFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFile));
        int v = scanner.nextInt();
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(v);

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                double weight = scanner.nextDouble();
                if (weight != 0.0) {
                    adjacencyMatrix.addEdge(i, j, weight);
                }
            }
        }

        return new Graph(v, adjacencyMatrix);
    }

    /**
     * Initialize an incidence matrix graph from an input file.
     *
     * @param inputFile The path to the input file.
     * @return The initialized incidence matrix graph.
     * @throws FileNotFoundException if the input file is not found.
     */
    public static Graph initIncidenceMatrixGraph(String inputFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFile));
        int v = scanner.nextInt();
        int e = scanner.nextInt();
        IncidenceMatrix incidenceMatrix = new IncidenceMatrix(v);

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

        for (int i = 0; i < v; i++) {
            int source = scanner.nextInt();
            for (int j = 0; j < e; j++) {
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
                    incidenceMatrix.addEdge(source, destination, weight);
                }
            }
        }

        return new Graph(v, incidenceMatrix);
    }

    /**
     * Initialize an adjacency list graph from an input file and
     * convert it to an adjacency matrix.
     *
     * @param inputFile The path to the input file.
     * @return The initialized adjacency matrix graph.
     * @throws FileNotFoundException if the input file is not found.
     */
    public static Graph initAdjacencyListGraph(String inputFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFile));

        int v = scanner.nextInt();
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(v);

        while (scanner.hasNextInt()) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            double weight = scanner.nextDouble();

            adjacencyMatrix.addEdge(source, destination, weight);
        }

        return new Graph(v, adjacencyMatrix);
    }

}
