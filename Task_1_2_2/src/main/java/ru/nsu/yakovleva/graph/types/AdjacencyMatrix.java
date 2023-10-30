package ru.nsu.yakovleva.graph.types;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrix {
    private double[][] matrix;
    private int vertexCount; // Track the current number of vertices

    public AdjacencyMatrix(int initialVertexCount) {
        this.vertexCount = initialVertexCount;
        matrix = new double[initialVertexCount][initialVertexCount];
    }

    public void addEdge(int source, int destination, double weight) {
        if (source >= vertexCount || destination >= vertexCount) {
            expandMatrix(Math.max(source, destination) + 1);
        }

        matrix[source][destination] = weight;
        matrix[destination][source] = weight;
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
        return matrix[source][destination];
    }

    public void removeEdge(int source, int destination) {
        if (source >= 0 && source < vertexCount && destination >= 0 && destination < vertexCount) {
            matrix[source][destination] = 0;
            matrix[destination][source] = 0;
        }
    }

    public void addVertex() {
        expandMatrix(vertexCount + 1);
    }

    public void removeVertex(int vertex) {
        if (vertex >= 0 && vertex < vertexCount) {
            // Shift rows and columns to remove the vertex
            for (int i = vertex; i < vertexCount - 1; i++) {
                for (int j = 0; j < vertexCount; j++) {
                    matrix[i][j] = matrix[i + 1][j];
                }
            }
            for (int j = vertex; j < vertexCount - 1; j++) {
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
                System.arraycopy(matrix[i], 0, newMatrix[i], 0, vertexCount);
            }
            matrix = newMatrix;
            vertexCount = newVertexCount;
        }
    }

    public List<Integer> getVertex(int vertex) {
        List<Integer> neighbors = new ArrayList<>();
        if (vertex >= 0 && vertex < vertexCount) {
            for (int i = 0; i < vertexCount; i++) {
                if (matrix[vertex][i] != 0) {
                    neighbors.add(i);
                }
            }
        }
        return neighbors;
    }

    public double getEdge(int source, int destination) {
        if (source >= 0 && source < vertexCount && destination >= 0 && destination < vertexCount) {
            return matrix[source][destination];
        }
        return 0;
    }

    public void changeWeight(int source, int destination, double newWeight) {
        if (source >= 0 && source < vertexCount && destination >= 0 && destination < vertexCount) {
            matrix[source][destination] = newWeight;
            matrix[destination][source] = newWeight;
        }
    }

}
