package ru.nsu.yakovleva.graph;

import java.util.List;
import ru.nsu.yakovleva.graph.types.GraphRepresentation;

/**
 * Graph class representing a graph with multiple representations.
 */
public class Graph {
    private int vert;
    private final GraphRepresentation graphRepresentation;

    /**
     * Constructor to create a graph with a specific number of vertices and matrix type.
     *
     * @param vert              The number of vertices.
     * @param graphRepresentation The graph representation.
     */
    public Graph(int vert, GraphRepresentation graphRepresentation) {
        this.vert = vert;
        this.graphRepresentation = graphRepresentation;
    }

    /**
     * Add an edge to the graph.
     *
     * @param source      The source vertex.
     * @param destination The destination vertex.
     * @param weight      The weight of the edge.
     */
    public void addEdge(int source, int destination, double weight) {
        graphRepresentation.addEdge(source, destination, weight);
    }

    /**
     * Get the number of vertices in the graph.
     *
     * @return The number of vertices.
     */
    public int getVertexCount() {
        return vert;
    }

    /**
     * Get the number of edges in the graph.
     *
     * @return The number of edges.
     */
    public int getEdgeCount() {
        return graphRepresentation.getEdgeCount();
    }

    /**
     * Remove an edge from the graph.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     */
    public void removeEdge(int source, int destination) {
        graphRepresentation.removeEdge(source, destination);
    }

    /**
     * Add a vertex to the graph.
     */
    public void addVertex() {
        graphRepresentation.addVertex();
        vert++;
    }

    /**
     * Remove a vertex from the graph.
     *
     * @param vertex The vertex to be removed.
     */
    public void removeVertex(int vertex) {
        graphRepresentation.removeVertex(vertex);
        vert--;
    }

    /**
     * Get the weight of an edge in the graph.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @return The weight of the edge.
     */
    public double getEdgeWeight(int source, int destination) {
        return graphRepresentation.getEdge(source, destination);
    }

    /**
     * Change the weight of an edge in the graph.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @param newWeight   The new weight of the edge.
     */
    public void changeWeight(int source, int destination, double newWeight) {
        graphRepresentation.changeWeight(source, destination, newWeight);
    }

    /**
     * Get the vertices adjacent to a specific vertex in the representation.
     *
     * @param vertex The vertex for which adjacent vertices are requested.
     * @return List of adjacent vertices.
     */
    public List<Integer> getAdjacentVertices(int vertex) {
        return graphRepresentation.getVertex(vertex);
    }
}
