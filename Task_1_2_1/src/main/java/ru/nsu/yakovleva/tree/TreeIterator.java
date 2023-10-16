package ru.nsu.yakovleva.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TreeIterator class provides an iterator for traversing the nodes of a Tree.
 *
 * @param <T> - type of nodes in the Tree.
 */
public class TreeIterator<T> implements Iterator<T> {
    private final List<Tree<T>> nodes = new ArrayList<>();
    private int next;

    /**
     * Constructor for TreeIterator.
     *
     * @param node - the starting node of the tree to be iterated.
     */
    public TreeIterator(Tree<T> node) {
        nodes.addAll(creation(node));
        next = 0;
    }

    /**
     * Recursively create a list of nodes for traversal starting from the given node.
     *
     * @param node - the current node for which to create the list.
     * @return a list of nodes for traversal.
     */
    private List<Tree<T>> creation(Tree<T> node) {
        List<Tree<T>> returnList = new ArrayList<>();
        returnList.add(node);

        for (Tree<T> child : node.getChildren()) {
            returnList.addAll(creation(child));
        }

        return returnList;
    }

    /**
     * Check if there are more nodes to iterate.
     *
     * @return true if there are more nodes, false otherwise.
     */
    @Override
    public boolean hasNext() {
        return next < nodes.size();
    }

    /**
     * Get the next node in the traversal and advance the iterator.
     *
     * @return the node's data.
     */
    @Override
    public T next() {
        Tree<T> returnNode = nodes.get(next);
        next += 1;

        return returnNode.getNodeName();
    }

    /**
     * Remove the last node retrieved by the iterator.
     */
    @Override
    public void remove() {
        nodes.remove(next - 1);
        next -= 1;
    }
}
