package ru.nsu.yakovleva.graph.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import ru.nsu.yakovleva.graph.Graph;

/**
 * Dijkstra class provides methods for finding the shortest paths
 * in a graph using Dijkstra's algorithm.
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
        int vert = graph.getVertexCount();
        List<String> result = new ArrayList<>();

        if (source < 0 || source >= vert) {
            throw new IllegalArgumentException("Source vertex is out of range.");
        }

        double[] distances = new double[vert];
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        distances[source] = 0;

        PriorityQueue<Pair> queue = new PriorityQueue<>(
                Comparator.comparingDouble(p -> p.dist));
        queue.add(new Pair(source, 0));

        boolean[] visited = new boolean[vert];

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int currentVertex = pair.vertex;

            if (visited[currentVertex]) {
                continue;
            }

            visited[currentVertex] = true;
            result.add("Vertex " + currentVertex + ": " + distances[currentVertex]);

            List<Integer> neighbors = graph.getAdjacentVertices(currentVertex);

            for (int neighbor : neighbors) {
                double edgeWeight = graph.getEdgeWeight(currentVertex, neighbor);

                double newDistance = distances[currentVertex] + edgeWeight;

                if (newDistance < distances[neighbor]) {
                    distances[neighbor] = newDistance;
                    queue.add(new Pair(neighbor, newDistance));
                }
            }
        }

        result.sort(Comparator.comparing(
                s -> Double.parseDouble(s.split(":")[1].trim())));

        return result;
    }

    /**
     * Private inner class to represent a pair of vertex and its distance for priority queue.
     */
    private static class Pair {
        int vertex;
        double dist;

        Pair(int vertex, double dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }
}
