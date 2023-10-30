package ru.nsu.yakovleva.graph.algorithms;

import ru.nsu.yakovleva.graph.Graph;
import ru.nsu.yakovleva.graph.init.WeightedEdge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Dijkstra class provides methods for finding the shortest paths in a graph using Dijkstra's algorithm.
 */
public class Dijkstra {
    /**
     * Find and return the shortest paths from a source vertex to all other vertices in the graph.
     *
     * @param graph  The input graph.
     * @param source The source vertex for finding shortest paths.
     * @return A list of strings representing the shortest paths.
     */
    public static List<String> findShortestPaths(Graph graph, int source) {
        Graph.MatrixType matrixType = graph.getMatrixType();
        int V = graph.getVertexCount();
        if (matrixType == Graph.MatrixType.ADJ_LIST) {

            List<String> result = new ArrayList<>();

            // Initialize distances with infinity and set the source vertex distance to 0
            int[] distances = new int[V];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[source] = 0;

            // Create a priority queue to hold vertices based on their distances
            PriorityQueue<VertexDistancePair> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.dist));
            queue.add(new VertexDistancePair(source, 0));

            boolean[] visited = new boolean[V];

            while (!queue.isEmpty()) {
                VertexDistancePair pair = queue.poll();
                int currentVertex = pair.vertex;

                if (visited[currentVertex]) {
                    continue;
                }

                visited[currentVertex] = true;
                result.add("Vertex " + currentVertex + ": " + distances[currentVertex]);

                List<WeightedEdge> neighbors = graph.getVertexAdjacencyList(currentVertex);

                for (WeightedEdge neighbor : neighbors) {
                    int neighborVertex = neighbor.getDestination();
                    double edgeWeight = neighbor.getWeight();

                    double newDistance = distances[currentVertex] + edgeWeight;

                    if (newDistance < distances[neighborVertex]) {
                        distances[neighborVertex] = (int) newDistance;
                        queue.add(new VertexDistancePair(neighborVertex, (int) newDistance));
                    }
                }
            }

            result.sort(Comparator.comparing(s -> Integer.parseInt(s.split(":")[1].trim())));

            return result;
        } else if (matrixType == Graph.MatrixType.ADJ_MATR) {

            List<String> result = new ArrayList<>();

            // Initialize distances with infinity and set the source vertex distance to 0
            double[] distances = new double[V];
            Arrays.fill(distances, Double.POSITIVE_INFINITY);
            distances[source] = 0;

            // Create a priority queue to hold vertices based on their distances
            PriorityQueue<VertexDistancePair> queue = new PriorityQueue<>(Comparator.comparingDouble(p -> p.dist));
            queue.add(new VertexDistancePair(source, 0));

            boolean[] visited = new boolean[V];

            while (!queue.isEmpty()) {
                VertexDistancePair pair = queue.poll();
                int currentVertex = pair.vertex;

                if (visited[currentVertex]) {
                    continue;
                }

                visited[currentVertex] = true;
                result.add("Vertex " + currentVertex + ": " + distances[currentVertex]);

                List<Integer> neighbors = graph.getVertex(currentVertex);

                for (int neighbor : neighbors) {
                    double edgeWeight = graph.getEdgeAdjacencyMatrix(currentVertex, neighbor);

                    double newDistance = distances[currentVertex] + edgeWeight;

                    if (newDistance < distances[neighbor]) {
                        distances[neighbor] = newDistance;
                        queue.add(new VertexDistancePair(neighbor, (int) newDistance));
                    }
                }
            }

            result.sort(Comparator.comparing(s -> Double.parseDouble(s.split(":")[1].trim())));

            return result;

        } else if (matrixType == Graph.MatrixType.INC_MATR) {
            Graph newGraph = new Graph(V, Graph.MatrixType.ADJ_MATR);
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    double weight = graph.getIncidenceMatrix().matrix[i][j];
                    if (weight != 0.0) {
                        newGraph.addEdge(i, j, weight);
                    }
                }
            }
            List<String> result = new ArrayList<>();

            // Initialize distances with infinity and set the source vertex distance to 0
            double[] distances = new double[V];
            Arrays.fill(distances, Double.POSITIVE_INFINITY);
            distances[source] = 0;

            // Create a priority queue to hold vertices based on their distances
            PriorityQueue<VertexDistancePair> queue = new PriorityQueue<>(Comparator.comparingDouble(p -> p.dist));
            queue.add(new VertexDistancePair(source, 0));

            boolean[] visited = new boolean[V];

            while (!queue.isEmpty()) {
                VertexDistancePair pair = queue.poll();
                int currentVertex = pair.vertex;

                if (visited[currentVertex]) {
                    continue;
                }

                visited[currentVertex] = true;
                result.add("Vertex " + currentVertex + ": " + distances[currentVertex]);

                List<Integer> neighbors = newGraph.getVertex(currentVertex);

                for (int neighbor : neighbors) {
                    double edgeWeight = newGraph.getEdgeAdjacencyMatrix(currentVertex, neighbor);

                    double newDistance = distances[currentVertex] + edgeWeight;

                    if (newDistance < distances[neighbor]) {
                        distances[neighbor] = newDistance;
                        queue.add(new VertexDistancePair(neighbor, (int) newDistance));
                    }
                }
            }

            result.sort(Comparator.comparing(s -> Double.parseDouble(s.split(":")[1].trim())));

            return result;
        }

        return null;
    }

    /**
     * Private inner class to represent a pair of vertex and its distance for priority queue.
     */
    private static class VertexDistancePair {
        int vertex;
        int dist;

        VertexDistancePair(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }
}
