package ru.nsu.yakovleva.graph.types;

import java.util.ArrayList;
import java.util.List;
import ru.nsu.yakovleva.graph.init.WeightedEdge;

/**
 * This class represents an adjacency list for an undirected graph with weighted edges.
 */
public class AdjacencyList {
    private List<List<WeightedEdge>> adjList;

    /**
     * Initializes an empty adjacency list with the given number of vertices.
     *
     * @param v The number of vertices in the graph.
     */
    public AdjacencyList(int v) {
        adjList = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    /**
     * Adds a weighted edge between two vertices in the graph.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @param weight      The weight of the edge.
     */
    public void addEdge(int source, int destination, double weight) {
        adjList.get(source).add(new WeightedEdge(destination, weight));
        adjList.get(destination).add(new WeightedEdge(source, weight));
    }

    /**
     * Returns the total number of edges in the graph.
     *
     * @return The number of edges in the graph.
     */
    public int getEdgeCount() {
        int edgeCount = 0;
        for (List<WeightedEdge> neighbors : adjList) {
            edgeCount += neighbors.size();
        }
        return edgeCount / 2; // Since it's an undirected graph, divide by 2
    }

    /**
     * Gets the weight of the edge between two vertices.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @return The weight of the edge, or -1 if the edge does not exist.
     */
    public double get(int source, int destination) {
        List<WeightedEdge> neighbors = adjList.get(source);
        for (WeightedEdge edge : neighbors) {
            if (edge.getDestination() == destination) {
                return edge.getWeight();
            }
        }
        return -1;
    }

    /**
     * Removes the edge between two vertices if it exists.
     *
     * @param source      The source vertex of the edge to be removed.
     * @param destination The destination vertex of the edge to be removed.
     */
    public void removeEdge(int source, int destination) {
        if (source >= 0 && source < adjList.size()) {
            List<WeightedEdge> sourceNeighbors = adjList.get(source);
            int edgeToRemoveIndex = -1;

            // Find the index of the edge to remove in the source vertex's neighbors
            for (int i = 0; i < sourceNeighbors.size(); i++) {
                if (sourceNeighbors.get(i).getDestination() == destination) {
                    edgeToRemoveIndex = i;
                    break;
                }
            }

            if (edgeToRemoveIndex != -1) {
                sourceNeighbors.remove(edgeToRemoveIndex);
            }
        }
    }

    /**
     * Adds a new vertex to the graph.
     */
    public void addVertex() {
        adjList.add(new ArrayList<>());
    }

    /**
     * Removes a vertex and all edges connected to it from the graph.
     *
     * @param vertex The index of the vertex to be removed.
     */
    public void removeVertex(int vertex) {
        if (vertex >= 0 && vertex < adjList.size()) {
            // Remove the vertex's list from the adjacency list
            adjList.remove(vertex);

            // Remove all edges connected to the vertex in other vertices' lists
            for (List<WeightedEdge> neighbors : adjList) {
                neighbors.removeIf(edge -> edge.getDestination() == vertex);
            }
        }
    }

    /**
     * Gets the list of weighted edges for a specific vertex.
     *
     * @param vertex The index of the vertex.
     * @return The list of weighted edges for the given vertex, or null if the vertex doesn't exist.
     */
    public List<WeightedEdge> getVertex(int vertex) {
        if (vertex >= 0 && vertex < adjList.size()) {
            return adjList.get(vertex);
        } else {
            return null; // Handle the case where the vertex doesn't exist
        }
    }

    /**
     * Gets the weighted edge between two vertices.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @return The weighted edge, or null if the edge does not exist.
     */
    public WeightedEdge getEdge(int source, int destination) {
        if (source >= 0 && source < adjList.size()) {
            List<WeightedEdge> sourceNeighbors = adjList.get(source);
            for (WeightedEdge edge : sourceNeighbors) {
                if (edge.getDestination() == destination) {
                    return edge;
                }
            }
        }
        return null;
    }

    /**
     * Sets the weight of the edge between two vertices.
     *
     * @param source     The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @param newWeight The new weight to be set for the edge.
     */
    public void setWeight(int source, int destination, double newWeight) {
        if (source >= 0 && source < adjList.size()) {
            List<WeightedEdge> sourceNeighbors = adjList.get(source);

            for (WeightedEdge edge : sourceNeighbors) {
                if (edge.getDestination() == destination) {
                    edge.setWeight(newWeight);
                    break;
                }
            }
        }

        if (destination >= 0 && destination < adjList.size()) {
            List<WeightedEdge> destinationNeighbors = adjList.get(destination);

            for (WeightedEdge edge : destinationNeighbors) {
                if (edge.getDestination() == source) {
                    edge.setWeight(newWeight);
                    break;
                }
            }
        }
    }
}
