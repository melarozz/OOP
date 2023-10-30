package ru.nsu.yakovleva.graph.types;

import java.util.ArrayList;
import java.util.List;

public class IncidenceMatrix {
    public double[][] matrix;
    private int vertexCount; // Track the current number of vertices

    public IncidenceMatrix(int initialVertexCount) {
        this.vertexCount = initialVertexCount;
        matrix = new double[initialVertexCount][initialVertexCount];
    }

    public void addEdge(int source, int destination, double weight) {
        if (source >= vertexCount || destination >= vertexCount) {
            expandMatrix(Math.max(source, destination) + 1);
        }

        matrix[source - 1][destination - 1] = weight;
        matrix[destination - 1][source - 1] = weight;
    }

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

    public double get(int source, int destination) {
        return matrix[source - 1][destination - 1];
    }

    public void removeEdge(int source, int destination) {
        if (source > 0 && source <= vertexCount && destination > 0 && destination <= vertexCount) {
            matrix[source - 1][destination - 1] = 0;
            matrix[destination - 1][source - 1] = 0;
        }
    }

    public void addVertex() {
        expandMatrix(vertexCount + 1);
    }

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

    public List<Integer> getVertex(int vertex) {
        if (vertex > 0 && vertex <= vertexCount) {
            List<Integer> incidentEdges = new ArrayList<>();
            for (int j = 0; j < vertexCount; j++) {
                if (matrix[vertex - 1][j] != 0) {
                    incidentEdges.add(j + 1); // Add the incident edge to the list (adding 1 to adjust for 1-based indexing)
                }
            }
            return incidentEdges;
        }
        return null; // Return null for an invalid vertex
    }

    public void changeWeight(int source, int destination, double newWeight) {
        if (source > 0 && source <= vertexCount && destination > 0 && destination <= vertexCount) {
            matrix[source - 1][destination - 1] = newWeight;
            matrix[destination - 1][source - 1] = newWeight; // For undirected graph
        }
    }


}
