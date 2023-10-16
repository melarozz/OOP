package ru.nsu.yakovleva.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Tree class.
 *
 * @param <T> - type of nodes in the Tree.
 */
public class Tree<T> implements Iterable<T> {
    private final List<Tree<T>> children = new ArrayList<>();
    private final T node;
    private Tree<T> parent;
    private int modCount = 0;
    public int flag = 0;

    /**
     * Constructor for the Tree class.
     *
     * @param root - the root node of the tree.
     */
    public Tree(T root) {
        this.node = root;
        this.parent = null;
    }

    /**
     * Calculates the hash code for this tree.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(node);
        if (parent != null) {
            result += Objects.hash(parent.getNodeName());
        }
        for (Tree<?> child : children) {
            result += child.hashCode();
        }
        return result;
    }

    /**
     * Checks if this tree is equal to another object.
     *
     * @param o - the object to compare with.
     * @return true if they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tree<?> otherTree = (Tree<?>) o;

        if (parent == null && otherTree.getParent() != null) {
            return false;
        }
        if (parent != null && !parent.getNodeName().equals(otherTree.parent.getNodeName())) {
            return false;
        }
        return node.equals(otherTree.getNodeName()) && this.hashCode() == o.hashCode();
    }



    /**
     * Get the node name of this tree.
     *
     * @return the node name.
     */
    public T getNodeName() {
        return this.node;
    }

    /**
     * Get the children of this tree.
     *
     * @return a list of child trees.
     */
    public List<Tree<T>> getChildren() {
        return children;
    }

    /**
     * Get the parent tree of this tree.
     *
     * @return the parent tree.
     */
    public Tree<T> getParent() {
        return parent;
    }

    /**
     * Set the parent tree for this tree.
     *
     * @param parent - the parent tree.
     */
    public void setParent(Tree<T> parent) {
        this.parent = parent;
    }

    /**
     * Set the flag iterator to Dfs for this tree.
     */
    public void setFlagDfs() {
        flag = 1;
    }

    /**
     * Set the flag iterator to Bfs for this tree.
     */
    public void setFlagBfs() {
        flag = 0;
    }

    /**
     * Add a child to this tree with the given node name.
     *
     * @param childName - the node name of the child.
     * @return the newly created child tree.
     */
    public Tree<T> addChild(T childName) {
        Tree<T> child = new Tree<>(childName);
        this.children.add(child);
        child.setParent(this);
        modCount++;
        return child;
    }

    /**
     * Add an existing tree as a child to this tree.
     *
     * @param child - the child tree to add.
     */
    public void addChild(Tree<T> child) {
        this.children.add(child);
        child.setParent(this);
        modCount++;
    }

    /**
     * Remove this tree from its parent and move its children to the parent.
     */
    public void remove() {
        if (parent != null) {
            for (Tree<T> child : this.children) {
                child.parent.setParent(this.parent);
            }
            this.parent.children.remove(this);
            this.parent.children.addAll(this.children);
            modCount++;
        }
    }


    /**
     * Get an iterator for this tree.
     *
     * @return an iterator for the tree.
     */
    public Iterator<T> iterator() {
        if (flag == 0) {
            return new BreadthFirstIterator();
        } else {
            return new DepthFirstIterator();
        }
    }



    /**
     * Iterator for performing breadth-first traversal of the tree.
     */
    private class BreadthFirstIterator implements Iterator<T> {
        private final Queue<Tree<T>> queue = new LinkedList<>();
        private int expectedModCount = modCount;

        BreadthFirstIterator() {
            queue.offer(Tree.this);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            checkConcurrentModification();
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Tree<T> current = queue.poll();
            for (Tree<T> child : current.children) {
                queue.offer(child);
            }
            return current.node;
        }

        private void checkConcurrentModification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * Iterator for performing depth-first traversal of the tree.
     */
    private class DepthFirstIterator implements Iterator<T> {
        private final List<Tree<T>> stack = new ArrayList<>();
        private int index = 0;
        private int expectedModCount = modCount;

        DepthFirstIterator() {
            stack.add(Tree.this);
        }

        @Override
        public boolean hasNext() {
            return index < stack.size();
        }

        @Override
        public T next() {
            checkConcurrentModification();
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Tree<T> current = stack.get(index);
            index++;
            stack.addAll(index, current.children);
            return current.node;
        }

        private void checkConcurrentModification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
