package ru.nsu.yakovleva.graph.types;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an incidence matrix for an
 * undirected graph with weighted edges.
 */
public class IncidenceMatrix implements GraphRepresentation {
    public double[][] matrix;
    private int vertexCount;

    /**
     * Initializes an incidence matrix with the given number of vertices.
     *
     * @param initialVertexCount The initial number of vertices in the graph.
     */
    public IncidenceMatrix(int initialVertexCount) {
        this.vertexCount = initialVertexCount;
        matrix = new double[initialVertexCount][initialVertexCount];
    }

    @Override
    public void addEdge(int source, int dest, double weight) {
        if (source >= vertexCount || dest >= vertexCount) {
            expandMatrix(Math.max(source, dest) + 1);
        }

        matrix[source][dest] = weight;
        matrix[dest][source] = weight;
    }

    @Override
    public void removeEdge(int source, int dest) {
        if (source >= 0 && source < vertexCount && dest >= 0 && dest < vertexCount) {
            matrix[source][dest] = 0;
            matrix[dest][source] = 0;
        }
    }

    @Override
    public void addVertex() {
        expandMatrix(vertexCount + 1);
    }

    @Override
    public void removeVertex(int vertex) {
        if (vertex >= 0 && vertex < vertexCount) {
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

    @Override
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

    @Override
    public List<Integer> getVertex(int vertex) {
        if (vertex >= 0 && vertex < vertexCount) {
            List<Integer> incidentEdges = new ArrayList<>();
            for (int j = 0; j < vertexCount; j++) {
                if (matrix[vertex][j] != 0) {
                    incidentEdges.add(j);
                }
            }
            return incidentEdges;
        }
        return null;
    }

    @Override
    public double getEdge(int source, int dest) {
        return matrix[source][dest];
    }

    @Override
    public void changeWeight(int source, int dest, double newWeight) {
        if (source >= 0 && source < vertexCount && dest >= 0 && dest < vertexCount) {
            matrix[source][dest] = newWeight;
            matrix[dest][source] = newWeight;
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
}
