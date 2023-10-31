package ru.nsu.yakovleva.graph;

import java.util.List;
import ru.nsu.yakovleva.graph.init.WeightedEdge;
import ru.nsu.yakovleva.graph.types.AdjacencyMatrix;
import ru.nsu.yakovleva.graph.types.IncidenceMatrix;

/**
 * Graph class representing a graph with multiple representations.
 */
public class Graph {
    private int vert; // Number of vertices
    private final MatrixType matrixType;
    private AdjacencyMatrix adjacencyMatrix;
    private IncidenceMatrix incidenceMatrix;

    /**
     * Enumeration representing the possible matrix types for the graph.
     */
    public enum MatrixType {
        ADJ_MATR,
        ADJ_LIST,
        INC_MATR
    }

    /**
     * Constructor to create a graph with a specific number of vertices and matrix type.
     *
     * @param vert          The number of vertices.
     * @param matrixType The type of matrix representation.
     */
    public Graph(int vert, MatrixType matrixType) {
        this.vert = vert;
        this.matrixType = matrixType;

        // Initialize the appropriate matrix representation based on the chosen matrix type.
        if (matrixType == MatrixType.ADJ_MATR) {
            adjacencyMatrix = new AdjacencyMatrix(vert);
        } else if (matrixType == MatrixType.ADJ_LIST) {
            adjacencyMatrix = new AdjacencyMatrix(vert);
        } else if (matrixType == MatrixType.INC_MATR) {
            incidenceMatrix = new IncidenceMatrix(vert);
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
        if (matrixType == MatrixType.ADJ_MATR) {
            if (adjacencyMatrix != null) {
                adjacencyMatrix.addEdge(source, destination, weight);
            }
        } else if (matrixType == MatrixType.ADJ_LIST) {
            if (adjacencyMatrix != null) {
                adjacencyMatrix.addEdge(source, destination, weight);
            }
        } else if (matrixType == MatrixType.INC_MATR) {
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
        return vert;
    }

    /**
     * Get the number of edges in the graph.
     *
     * @return The number of edges.
     */
    public int getEdgeCount() {
        if (matrixType == MatrixType.ADJ_MATR) {
            if (adjacencyMatrix != null) {
                return adjacencyMatrix.getEdgeCount();
            }
        } else if (matrixType == MatrixType.ADJ_LIST) {
            if (adjacencyMatrix != null) {
                return adjacencyMatrix.getEdgeCount();
            }
        } else if (matrixType == MatrixType.INC_MATR) {
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
        if (matrixType == MatrixType.INC_MATR && incidenceMatrix != null) {
            incidenceMatrix.removeEdge(source, destination);
        } else if (matrixType == MatrixType.ADJ_LIST && adjacencyMatrix != null) {
            adjacencyMatrix.removeEdge(source, destination);
        } else if (matrixType == MatrixType.ADJ_MATR && adjacencyMatrix != null) {
            adjacencyMatrix.removeEdge(source, destination);
        }
    }

    /**
     * Add a vertex to the graph.
     */
    public void addVertex() {
        if (matrixType == MatrixType.ADJ_LIST && adjacencyMatrix != null) {
            adjacencyMatrix.addVertex();
        } else if (matrixType == MatrixType.ADJ_MATR && adjacencyMatrix != null) {
            adjacencyMatrix.addVertex();
        } else if (matrixType == MatrixType.INC_MATR && incidenceMatrix != null) {
            incidenceMatrix.addVertex();
        }
        vert++;
    }

    /**
     * Remove a vertex from the graph.
     *
     * @param vertex The vertex to be removed.
     */
    public void removeVertex(int vertex) {
        if (matrixType == MatrixType.ADJ_LIST && adjacencyMatrix != null) {
            adjacencyMatrix.removeVertex(vertex);
        } else if (matrixType == MatrixType.ADJ_MATR && adjacencyMatrix != null) {
            adjacencyMatrix.removeVertex(vertex);
        } else if (matrixType == MatrixType.INC_MATR && incidenceMatrix != null) {
            incidenceMatrix.removeVertex(vertex);
        }
        vert--;
    }

    /**
     * Get the weight of an edge in the adjacency matrix representation.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @return The weight of the edge.
     */
    public double getEdgeAdjacencyMatrix(int source, int destination) {
        if (matrixType == MatrixType.ADJ_MATR || matrixType == MatrixType.ADJ_LIST) {
            if (adjacencyMatrix != null) {
                return adjacencyMatrix.getEdge(source, destination);
            }
        }
        return 0;
    }


    /**
     * Get the weight of an edge in the incidence matrix representation.
     *
     * @param index The index of the edge in the incidence matrix.
     * @return The weight of the edge.
     */
    public double getEdgeIncidenceMatrix(int index) {
        if (matrixType == MatrixType.INC_MATR) {
            if (incidenceMatrix != null) {
                return incidenceMatrix.getEdge(index);
            }
        }
        return 0;
    }

    /**
     * Get the vertices adjacent to a specific vertex in the adjacency
     * matrix or incidence matrix representation.
     *
     * @param vertex The vertex for which adjacent vertices are requested.
     * @return List of adjacent vertices.
     */
    public List<Integer> getVertex(int vertex) {
        if (matrixType == MatrixType.ADJ_MATR || matrixType == MatrixType.ADJ_LIST) {
            if (adjacencyMatrix != null) {
                return adjacencyMatrix.getVertex(vertex);
            }
        } else if (matrixType == MatrixType.INC_MATR) {
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
    public List<Integer> getVertexadjacencyMatrix(int vertex) {
        if (matrixType == MatrixType.ADJ_LIST) {
            if (adjacencyMatrix != null) {
                return adjacencyMatrix.getVertex(vertex);
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
        if (matrixType == MatrixType.ADJ_LIST && adjacencyMatrix != null) {
            adjacencyMatrix.changeWeight(source, destination, newWeight);
        } else if (matrixType == MatrixType.ADJ_MATR && adjacencyMatrix != null) {
            adjacencyMatrix.changeWeight(source, destination, newWeight);
        } else if (matrixType == MatrixType.INC_MATR && incidenceMatrix != null) {
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
