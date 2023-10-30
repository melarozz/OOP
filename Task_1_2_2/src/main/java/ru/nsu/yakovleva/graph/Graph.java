package ru.nsu.yakovleva.graph;

import ru.nsu.yakovleva.graph.init.WeightedEdge;
import ru.nsu.yakovleva.graph.types.AdjacencyList;
import ru.nsu.yakovleva.graph.types.AdjacencyMatrix;
import ru.nsu.yakovleva.graph.types.IncidenceMatrix;

import java.util.List;

/**
 * Graph class representing a graph with multiple representations.
 */
public class Graph {
    private int V; // Number of vertices
    private final MatrixType matrixType;
    private AdjacencyMatrix adjacencyMatrix;
    private AdjacencyList adjacencyList;
    private IncidenceMatrix incidenceMatrix;

    /**
     * Enumeration representing the possible matrix types for the graph.
     */
    public enum MatrixType {
        ADJACENCY_MATRIX,
        ADJACENCY_LIST,
        INCIDENCE_MATRIX
    }

    /**
     * Constructor to create a graph with a specific number of vertices and matrix type.
     *
     * @param V          The number of vertices.
     * @param matrixType The type of matrix representation.
     */
    public Graph(int V, MatrixType matrixType) {
        this.V = V;
        this.matrixType = matrixType;

        // Initialize the appropriate matrix representation based on the chosen matrix type.
        if (matrixType == MatrixType.ADJACENCY_MATRIX) {
            adjacencyMatrix = new AdjacencyMatrix(V);
        } else if (matrixType == MatrixType.ADJACENCY_LIST) {
            adjacencyList = new AdjacencyList(V);
        } else if (matrixType == MatrixType.INCIDENCE_MATRIX) {
            incidenceMatrix = new IncidenceMatrix(V);
        }
    }

    /**
     * Add an edge to the graph.
     *
     * @param source     The source vertex.
     * @param destination The destination vertex.
     * @param weight     The weight of the edge.
     */
    public void addEdge(int source, int destination, double weight) {
        // Add the edge to the appropriate matrix representation based on the matrix type.
        if (matrixType == MatrixType.ADJACENCY_MATRIX) {
            if (adjacencyMatrix != null) {
                adjacencyMatrix.addEdge(source, destination, weight);
            }
        } else if (matrixType == MatrixType.ADJACENCY_LIST) {
            if (adjacencyList != null) {
                adjacencyList.addEdge(source, destination, weight);
            }
        } else if (matrixType == MatrixType.INCIDENCE_MATRIX) {
            if (incidenceMatrix != null) {
                incidenceMatrix.addEdge(source, destination, weight);
            }
        }
    }

    /**
     * Get the number of vertices in the graph.
     *
     * @return The number of vertices.
     */
    public int getVertexCount() {
        return V;
    }

    /**
     * Get the number of edges in the graph.
     *
     * @return The number of edges.
     */
    public int getEdgeCount() {
        if (matrixType == MatrixType.ADJACENCY_MATRIX) {
            if (adjacencyMatrix != null) {
                return adjacencyMatrix.getEdgeCount();
            }
        } else if (matrixType == MatrixType.ADJACENCY_LIST) {
            if (adjacencyList != null) {
                return adjacencyList.getEdgeCount();
            }
        } else if (matrixType == MatrixType.INCIDENCE_MATRIX) {
            if (incidenceMatrix != null) {
                return incidenceMatrix.getEdgeCount();
            }
        }
        return 0;
    }

    /**
     * Remove an edge from the graph.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     */
    public void removeEdge(int source, int destination) {
        if (matrixType == MatrixType.INCIDENCE_MATRIX && incidenceMatrix != null) {
            incidenceMatrix.removeEdge(source, destination);
        } else if (matrixType == MatrixType.ADJACENCY_LIST && adjacencyList != null) {
            adjacencyList.removeEdge(source, destination);
        } else if (matrixType == MatrixType.ADJACENCY_MATRIX && adjacencyMatrix != null) {
            adjacencyMatrix.removeEdge(source, destination);
        }
    }

    /**
     * Add a vertex to the graph.
     */
    public void addVertex() {
        if (matrixType == MatrixType.ADJACENCY_LIST && adjacencyList != null) {
            adjacencyList.addVertex();
        } else if (matrixType == MatrixType.ADJACENCY_MATRIX && adjacencyMatrix != null) {
            adjacencyMatrix.addVertex();
        } else if (matrixType == MatrixType.INCIDENCE_MATRIX && incidenceMatrix != null) {
            incidenceMatrix.addVertex();
        }
        V++;
    }

    /**
     * Remove a vertex from the graph.
     *
     * @param vertex The vertex to be removed.
     */
    public void removeVertex(int vertex) {
        if (matrixType == MatrixType.ADJACENCY_LIST && adjacencyList != null) {
            adjacencyList.removeVertex(vertex);
        } else if (matrixType == MatrixType.ADJACENCY_MATRIX && adjacencyMatrix != null) {
            adjacencyMatrix.removeVertex(vertex);
        } else if (matrixType == MatrixType.INCIDENCE_MATRIX && incidenceMatrix != null) {
            incidenceMatrix.removeVertex(vertex);
        }
        V--;
    }

    /**
     * Get the weight of an edge in the adjacency matrix representation.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @return The weight of the edge.
     */
    public double getEdgeAdjacencyMatrix(int source, int destination) {
        if (matrixType == MatrixType.ADJACENCY_MATRIX) {
            if (adjacencyMatrix != null) {
                return adjacencyMatrix.getEdge(source, destination);
            }
        }
        return 0;
    }

    /**
     * Get the edge in the adjacency list representation.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @return The edge object with weight.
     */
    public WeightedEdge getEdgeAdjacencyList(int source, int destination) {
        if (matrixType == MatrixType.ADJACENCY_LIST) {
            if (adjacencyList != null) {
                return adjacencyList.getEdge(source, destination);
            }
        }
        return null;
    }

    /**
     * Get the weight of an edge in the incidence matrix representation.
     *
     * @param index The index of the edge in the incidence matrix.
     * @return The weight of the edge.
     */
    public double getEdgeIncidenceMatrix(int index) {
        if (matrixType == MatrixType.INCIDENCE_MATRIX) {
            if (incidenceMatrix != null) {
                return incidenceMatrix.getEdge(index);
            }
        }
        return 0;
    }

    /**
     * Get the vertices adjacent to a specific vertex in the adjacency matrix or incidence matrix representation.
     *
     * @param vertex The vertex for which adjacent vertices are requested.
     * @return List of adjacent vertices.
     */
    public List<Integer> getVertex(int vertex) {
        if (matrixType == MatrixType.ADJACENCY_MATRIX) {
            if (adjacencyMatrix != null) {
                return adjacencyMatrix.getVertex(vertex);
            }
        } else if (matrixType == MatrixType.INCIDENCE_MATRIX) {
            if (incidenceMatrix != null) {
                return incidenceMatrix.getVertex(vertex);
            }
        }
        return null;
    }

    /**
     * Get the edges incident to a specific vertex in the adjacency list representation.
     *
     * @param vertex The vertex for which incident edges are requested.
     * @return List of incident edges.
     */
    public List<WeightedEdge> getVertexAdjacencyList(int vertex) {
        if (matrixType == MatrixType.ADJACENCY_LIST) {
            if (adjacencyList != null) {
                return adjacencyList.getVertex(vertex);
            }
        }
        return null;
    }

    /**
     * Change the weight of an edge in the graph.
     *
     * @param source    The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @param newWeight The new weight of the edge.
     */
    public void changeWeight(int source, int destination, double newWeight) {
        if (matrixType == MatrixType.ADJACENCY_LIST && adjacencyList != null) {
            adjacencyList.setWeight(source, destination, newWeight);
        } else if (matrixType == MatrixType.ADJACENCY_MATRIX && adjacencyMatrix != null) {
            adjacencyMatrix.changeWeight(source, destination, newWeight);
        } else if (matrixType == MatrixType.INCIDENCE_MATRIX && incidenceMatrix != null) {
            incidenceMatrix.changeWeight(source, destination, newWeight);
        }
    }

    /**
     * Get the adjacency matrix representation of the graph.
     *
     * @return The adjacency matrix.
     */
    public AdjacencyMatrix getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    /**
     * Get the adjacency list representation of the graph.
     *
     * @return The adjacency list.
     */
    public AdjacencyList getAdjacencyList() {
        return adjacencyList;
    }

    /**
     * Get the incidence matrix representation of the graph.
     *
     * @return The incidence matrix.
     */
    public IncidenceMatrix getIncidenceMatrix() {
        return incidenceMatrix;
    }

    /**
     * Get the matrix type (Adjacency Matrix, Adjacency List, or Incidence Matrix) of the graph.
     *
     * @return The matrix type.
     */
    public MatrixType getMatrixType() {
        return matrixType;
    }
}
