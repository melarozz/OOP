package ru.nsu.yakovleva.graph.types;

import ru.nsu.yakovleva.graph.init.WeightedEdge;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyList {
    private List<List<WeightedEdge>> adjList;

    public AdjacencyList(int V) {
        adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, double weight) {
        adjList.get(source).add(new WeightedEdge(destination, weight));
        adjList.get(destination).add(new WeightedEdge(source, weight));
    }

    public int getEdgeCount() {
        int edgeCount = 0;
        for (List<WeightedEdge> neighbors : adjList) {
            edgeCount += neighbors.size();
        }
        return edgeCount / 2; // Since it's an undirected graph, divide by 2
    }

    public double get(int source, int destination) {
        List<WeightedEdge> neighbors = adjList.get(source);
        for (WeightedEdge edge : neighbors) {
            if (edge.getDestination() == destination) {
                return edge.getWeight();
            }
        }

        return -1;
    }

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

    public void addVertex() {
        adjList.add(new ArrayList<>());
    }

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

    public List<WeightedEdge> getVertex(int vertex) {
        if (vertex >= 0 && vertex < adjList.size()) {
            return adjList.get(vertex);
        } else {
            return null; // Handle the case where the vertex doesn't exist
        }
    }

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

