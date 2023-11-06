package ru.nsu.yakovleva.graph.types;

import java.util.List;

/**
 * Common interface for graph representations.
 */
public interface GraphRepresentation {
    void addEdge(int source, int destination, double weight);
    void removeEdge(int source, int destination);
    void addVertex();
    void removeVertex(int vertex);
    int getEdgeCount();
    List<Integer> getVertex(int vertex);
    double getEdge(int source, int destination);
    void changeWeight(int source, int destination, double newWeight);
}