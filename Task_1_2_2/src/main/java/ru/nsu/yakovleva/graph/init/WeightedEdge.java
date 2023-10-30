package ru.nsu.yakovleva.graph.init;

/**
 * WeightedEdge class represents an edge in a graph with a destination vertex and a weight.
 */
public class WeightedEdge {
    private int destination; // The destination vertex of the edge.
    private double weight;   // The weight or cost associated with the edge.

    /**
     * Creates a new WeightedEdge with the given destination and weight.
     *
     * @param destination The destination vertex of the edge.
     * @param weight      The weight or cost associated with the edge.
     */
    public WeightedEdge(int destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }

    /**
     * Get the destination vertex of the edge.
     *
     * @return The destination vertex.
     */
    public int getDestination() {
        return destination;
    }

    /**
     * Get the weight or cost associated with the edge.
     *
     * @return The weight of the edge.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Set the weight or cost associated with the edge.
     *
     * @param weight The new weight to be set.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
