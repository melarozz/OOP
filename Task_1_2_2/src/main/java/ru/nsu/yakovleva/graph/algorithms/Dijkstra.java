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
        if (matrixType == Graph.MatrixType.ADJACENCY_LIST) {

            List<String> shortestPaths = new ArrayList<>();

            // Initialize distances with infinity and set the source vertex distance to 0
            int[] distances = new int[V];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[source] = 0;

            // Create a priority queue to hold vertices based on their distances
            PriorityQueue<VertexDistancePair> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.distance));
            priorityQueue.add(new VertexDistancePair(source, 0));

            boolean[] visited = new boolean[V];

            while (!priorityQueue.isEmpty()) {
                VertexDistancePair pair = priorityQueue.poll();
                int currentVertex = pair.vertex;

                if (visited[currentVertex]) {
                    continue;
                }

                visited[currentVertex] = true;
                shortestPaths.add("Vertex " + currentVertex + ": " + distances[currentVertex]);

                List<WeightedEdge> neighbors = graph.getVertexAdjacencyList(currentVertex);

                for (WeightedEdge neighbor : neighbors) {
                    int neighborVertex = neighbor.getDestination();
                    double edgeWeight = neighbor.getWeight();

                    double newDistance = distances[currentVertex] + edgeWeight;

                    if (newDistance < distances[neighborVertex]) {
                        distances[neighborVertex] = (int) newDistance;
                        priorityQueue.add(new VertexDistancePair(neighborVertex, (int) newDistance));
                    }
                }
            }

            shortestPaths.sort(Comparator.comparing(s -> Integer.parseInt(s.split(":")[1].trim())));

            return shortestPaths;
        } else if (matrixType == Graph.MatrixType.ADJACENCY_MATRIX) {

            List<String> shortestPaths = new ArrayList<>();

            // Initialize distances with infinity and set the source vertex distance to 0
            double[] distances = new double[V];
            Arrays.fill(distances, Double.POSITIVE_INFINITY);
            distances[source] = 0;

            // Create a priority queue to hold vertices based on their distances
            PriorityQueue<VertexDistancePair> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(p -> p.distance));
            priorityQueue.add(new VertexDistancePair(source, 0));

            boolean[] visited = new boolean[V];

            while (!priorityQueue.isEmpty()) {
                VertexDistancePair pair = priorityQueue.poll();
                int currentVertex = pair.vertex;

                if (visited[currentVertex]) {
                    continue;
                }

                visited[currentVertex] = true;
                shortestPaths.add("Vertex " + currentVertex + ": " + distances[currentVertex]);

                List<Integer> neighbors = graph.getVertex(currentVertex);

                for (int neighbor : neighbors) {
                    double edgeWeight = graph.getEdgeAdjacencyMatrix(currentVertex, neighbor);

                    double newDistance = distances[currentVertex] + edgeWeight;

                    if (newDistance < distances[neighbor]) {
                        distances[neighbor] = newDistance;
                        priorityQueue.add(new VertexDistancePair(neighbor, (int) newDistance));
                    }
                }
            }

            shortestPaths.sort(Comparator.comparing(s -> Double.parseDouble(s.split(":")[1].trim())));

            return shortestPaths;

        } else if (matrixType == Graph.MatrixType.INCIDENCE_MATRIX) {
            Graph newGraph = new Graph(V, Graph.MatrixType.ADJACENCY_MATRIX);
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    double weight = graph.getIncidenceMatrix().matrix[i][j];
                    if (weight != 0.0) {
                        newGraph.addEdge(i, j, weight);
                    }
                }
            }
            List<String> shortestPaths = new ArrayList<>();

            // Initialize distances with infinity and set the source vertex distance to 0
            double[] distances = new double[V];
            Arrays.fill(distances, Double.POSITIVE_INFINITY);
            distances[source] = 0;

            // Create a priority queue to hold vertices based on their distances
            PriorityQueue<VertexDistancePair> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(p -> p.distance));
            priorityQueue.add(new VertexDistancePair(source, 0));

            boolean[] visited = new boolean[V];

            while (!priorityQueue.isEmpty()) {
                VertexDistancePair pair = priorityQueue.poll();
                int currentVertex = pair.vertex;

                if (visited[currentVertex]) {
                    continue;
                }

                visited[currentVertex] = true;
                shortestPaths.add("Vertex " + currentVertex + ": " + distances[currentVertex]);

                List<Integer> neighbors = newGraph.getVertex(currentVertex);

                for (int neighbor : neighbors) {
                    double edgeWeight = newGraph.getEdgeAdjacencyMatrix(currentVertex, neighbor);

                    double newDistance = distances[currentVertex] + edgeWeight;

                    if (newDistance < distances[neighbor]) {
                        distances[neighbor] = newDistance;
                        priorityQueue.add(new VertexDistancePair(neighbor, (int) newDistance));
                    }
                }
            }

            shortestPaths.sort(Comparator.comparing(s -> Double.parseDouble(s.split(":")[1].trim())));

            return shortestPaths;
        }

        return null;
    }

    /**
     * Private inner class to represent a pair of vertex and its distance for priority queue.
     */
    private static class VertexDistancePair {
        int vertex;
        int distance;

        VertexDistancePair(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}
