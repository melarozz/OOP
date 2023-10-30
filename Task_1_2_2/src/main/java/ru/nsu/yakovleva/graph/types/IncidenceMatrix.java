package ru.nsu.yakovleva.graph.types;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an incidence matrix for an undirected graph with weighted edges.
 */
public class IncidenceMatrix {
    public double[][] matrix;
    private int vertexCount; // Track the current number of vertices

    /**
     * Initializes an incidence matrix with the given number of vertices.
     *
     * @param initialVertexCount The initial number of vertices in the graph.
     */
    public IncidenceMatrix(int initialVertexCount) {
        this.vertexCount = initialVertexCount;
        matrix = new double[initialVertexCount][initialVertexCount];
    }

    /**
     * Adds a weighted edge between two vertices in the graph.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @param weight      The weight of the edge.
     */
    public void addEdge(int source, int destination, double weight) {
        if (source >= vertexCount || destination >= vertexCount) {
            expandMatrix(Math.max(source, destination) + 1);
        }

        matrix[source - 1][destination - 1] = weight;
        matrix[destination - 1][source - 1] = weight;
    }

    /**
     * Returns the total number of edges in the graph.
     *
     * @return The number of edges in the graph.
     */
    public int getEdgeCount() {
        int edgeCount = 0;
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (matrix[i][j] != 0) {
                    edgeCount++;
                }
            }
        }
        return edgeCount / 2;
    }

    /**
     * Gets the weight of the edge between two vertices.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @return The weight of the edge.
     */
    public double get(int source, int destination) {
        return matrix[source - 1][destination - 1];
    }

    /**
     * Removes the edge between two vertices if it exists.
     *
     * @param source      The source vertex of the edge to be removed.
     * @param destination The destination vertex of the edge to be removed.
     */
    public void removeEdge(int source, int destination) {
        if (source > 0 && source <= vertexCount && destination > 0 && destination <= vertexCount) {
            matrix[source - 1][destination - 1] = 0;
            matrix[destination - 1][source - 1] = 0;
        }
    }

    /**
     * Adds a new vertex to the graph.
     */
    public void addVertex() {
        expandMatrix(vertexCount + 1);
    }

    /**
     * Removes a vertex and all edges connected to it from the graph.
     *
     * @param vertex The index of the vertex to be removed.
     */
    public void removeVertex(int vertex) {
        if (vertex > 0 && vertex <= vertexCount) {
            // Shift rows and columns to remove the vertex
            for (int i = vertex - 1; i < vertexCount - 1; i++) {
                for (int j = 0; j < vertexCount; j++) {
                    matrix[i][j] = matrix[i + 1][j];
                }
            }
            for (int j = vertex - 1; j < vertexCount - 1; j++) {
                for (int i = 0; i < vertexCount; i++) {
                    matrix[i][j] = matrix[i][j + 1];
                }
            }
            vertexCount--;
        }
    }

    private void expandMatrix(int newVertexCount) {
        if (newVertexCount > vertexCount) {
            double[][] newMatrix = new double[newVertexCount][newVertexCount];
            for (int i = 0; i < vertexCount; i++) {
                for (int j = 0; j < vertexCount; j++) {
                    newMatrix[i][j] = matrix[i][j];
                }
            }
            matrix = newMatrix;
            vertexCount = newVertexCount;
        }
    }

    /**
     * Gets the weight of a specific edge in the graph.
     *
     * @param edgeIndex The index of the edge.
     * @return The weight of the edge.
     */
    public double getEdge(int edgeIndex) {
        int edgeCount = 0;
        for (int i = 0; i < vertexCount; i++) {
            for (int j = i + 1; j < vertexCount; j++) {
                if (matrix[i][j] != 0) {
                    if (edgeCount == edgeIndex) {
                        return matrix[i][j];
                    }
                    edgeCount++;
                }
            }
        }
        return 0; // Return 0 if the edge index is out of bounds
    }

    /**
     * Gets the list of neighboring vertices for a specific vertex.
     *
     * @param vertex The index of the vertex.
     * @return A list of neighboring vertices.
     */
    public List<Integer> getVertex(int vertex) {
        if (vertex > 0 && vertex <= vertexCount) {
            List<Integer> incidentEdges = new ArrayList<>();
            for (int j = 0; j < vertexCount; j++) {
                if (matrix[vertex - 1][j] != 0) {
                    incidentEdges.add(j + 1);
                }
            }
            return incidentEdges;
        }
        return null; // Return null for an invalid vertex
    }

    /**
     * Changes the weight of the edge between two vertices.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @param newWeight   The new weight to be set for the edge.
     */
    public void changeWeight(int source, int destination, double newWeight) {
        if (source > 0 && source <= vertexCount && destination > 0 && destination <= vertexCount) {
            matrix[source - 1][destination - 1] = newWeight;
            matrix[destination - 1][source - 1] = newWeight;
        }
    }


}
