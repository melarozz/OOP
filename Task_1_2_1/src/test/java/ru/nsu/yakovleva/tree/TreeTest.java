package ru.nsu.yakovleva.tree;

import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Test class.
 */
public class TreeTest {

    @Test
    void checkRoot() {
        Tree<String> tree = new Tree<>("root1");
        assertEquals("root1", tree.getNodeName());
    }

    @Test
    void checkNullParent() {
        Tree<String> tree = new Tree<>("root1");
        assertNull(tree.getParent());
    }

    @Test
    void checkAddChild() {
        Tree<String> tree = new Tree<>("root1");
        var a = tree.addChild("child1");
        assertEquals("child1", a.getNodeName());
    }

    @Test
    void checkAddChildInt() {
        Tree<Integer> tree = new Tree<>(1);
        var a = tree.addChild(2);
        assertEquals(2, a.getNodeName());
    }

    @Test
    void checkEqualsTrueRoots() {
        Tree<String> tree1 = new Tree<>("root1");
        Tree<String> tree2 = new Tree<>("root1");

        assertEquals(tree1, tree2);
    }

    @Test
    void testEqualsTrue() {
        Tree<String> tree1 = new Tree<>("root1");
        tree1.addChild("child1");
        Tree<String> tree2 = new Tree<>("root1");
        tree2.addChild("child1");

        assertEquals(tree1, tree2);
    }

    @Test
    void testEqualsTrueInt() {
        Tree<Integer> tree1 = new Tree<>(1);
        tree1.addChild(2);
        Tree<Integer> tree2 = new Tree<>(1);
        tree2.addChild(2);

        assertEquals(tree1, tree2);
    }


    @Test
    void checkEqualsFalse() {
        Tree<String> tree1 = new Tree<>("root1");
        tree1.addChild("child1");
        Tree<String> tree2 = new Tree<>("root1");
        tree2.addChild("child2");

        assertNotSame(tree1, tree2);

    }

    @Test
    void checkRemoveOne() {
        Tree<String> tree1 = new Tree<>("root1");
        var a = tree1.addChild("child1");
        tree1.remove(a);

        Tree<String> tree2 = new Tree<>("root1");

        assertEquals(tree1, tree2);

    }

    @Test
    void checkRemoveSubtree() {
        Tree<String> tree1 = new Tree<>("root1");
        var a1 = tree1.addChild("child1");
        a1.addChild("child2");

        Tree<String> subtree1 = new Tree<>("root2");
        subtree1.addChild("child3");
        subtree1.addChild("child4");

        tree1.addChild(subtree1);

        tree1.remove(subtree1);
        tree1.remove(a1);

        Tree<String> tree2 = new Tree<>("root1");

        assertEquals(tree1, tree2);

    }

    @Test
    void testEqualsWithModifiedTreeStructure() {
        Tree<String> tree1 = new Tree<>("root1");
        var a1 = tree1.addChild("child1");
        a1.addChild("child2");

        Tree<String> subtree1 = new Tree<>("root2");
        subtree1.addChild("child3");
        subtree1.addChild("child4");

        tree1.addChild(subtree1);

        Tree<String> tree2 = new Tree<>("newRoot1");
        var a2 = tree2.addChild("newChild1");
        a2.addChild("newChild2");

        Tree<String> subtree2 = new Tree<>("newRoot2");
        subtree2.addChild("newChild3");
        subtree2.addChild("newChild4");

        tree2.addChild(subtree2);

        assertNotEquals(tree1, tree2);
    }


    @Test
    void testEqualsTrueBigWithModifiedTreeStructure() {
        Tree<String> tree1 = new Tree<>("root1");
        var a1 = tree1.addChild("child1");
        a1.addChild("child2");
        Tree<String> subtree1 = new Tree<>("root2");
        subtree1.addChild("child3");
        subtree1.addChild("child4");
        tree1.addChild(subtree1);

        Tree<String> tree2 = new Tree<>("root1");
        var a2 = tree2.addChild("child1");
        a2.addChild("child2");
        Tree<String> subtree2 = new Tree<>("root2");
        subtree2.addChild("child5");
        tree2.addChild(subtree2);
        subtree2.addChild("child6");

        assertEquals(tree1, tree2);
    }


    @Test
    void testIteratorWithModifiedTreeStructure() {
        Tree<String> tree = new Tree<>("root1");
        var a = tree.addChild("child1");
        a.addChild("child2");
        Tree<String> subtree = new Tree<>("root2");
        subtree.addChild("child3");
        subtree.addChild("child4");
        a.addChild(subtree);

        StringBuilder actualBuilder = new StringBuilder();
        for (String label : tree) {
            actualBuilder.append(label);
        }
        String actual = actualBuilder.toString();

        assertEquals("root1child1child2root2child3child4", actual);
    }

    @Test
    void checkBfsIterator() {

        Tree<String> tree = new Tree<>("root1");
        var a = tree.addChild("child1");
        a.addChild("child2");
        Tree<String> subtree = new Tree<>("root2");
        subtree.addChild("child3");
        subtree.addChild("child4");
        tree.addChild(subtree);

        tree.setFlagBfs();

        String actual = "";
        Iterator<String> iterator = tree.iterator();

        while (iterator.hasNext()) {
            String item = iterator.next();
            actual = actual.concat(item);
        }


        assertEquals("root1child1root2child2child3child4", actual);
    }

    @Test
    void checkDfsIterator() {

        Tree<String> tree = new Tree<>("root1");
        var a = tree.addChild("child1");
        a.addChild("child2");
        Tree<String> subtree = new Tree<>("root2");
        subtree.addChild("child3");
        subtree.addChild("child4");
        tree.addChild(subtree);

        tree.setFlagDfs();

        String actual = "";
        Iterator<String> iterator = tree.iterator();

        while (iterator.hasNext()) {
            String item = iterator.next();
            actual = actual.concat(item);
        }

        assertEquals("root1child1child2root2child3child4", actual);
    }


    @Test
    public void testConcurrentModificationExceptionInBfs() {
        Tree<String> root = new Tree<>("A");
        Tree<String> b = root.addChild("B");
        b.addChild("child5");
        root.setFlagBfs();

        Iterator<String> iterator = root.iterator();
        root.addChild("C"); // Modify the tree after getting the iterator.

        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void testConcurrentModificationExceptionInDfs() {
        Tree<String> root = new Tree<>("A");
        Tree<String> b = root.addChild("B");
        b.addChild("child5");
        root.setFlagDfs();

        Iterator<String> iterator = root.iterator();
        root.addChild("C"); // Modify the tree after getting the iterator.

        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void testNoSuchElementExceptionBfs() {
        Tree<String> root = new Tree<>("Root");
        Iterator<String> bfsIterator = root.iterator();

        assertDoesNotThrow(bfsIterator::next);
        assertThrows(NoSuchElementException.class, bfsIterator::next);

    }

    @Test
    public void testNoSuchElementExceptionDfs() {
        Tree<String> root = new Tree<>("Root");
        root.setFlagDfs();

        Iterator<String> dfsIterator = root.iterator();

        assertDoesNotThrow(dfsIterator::next);
        assertThrows(NoSuchElementException.class, dfsIterator::next);
    }

    @Test
    public void testNoSuchElementExceptionDfsInt() {
        Tree<Integer> root = new Tree<>(1);
        root.setFlagDfs();

        Iterator<Integer> dfsIterator = root.iterator();

        assertDoesNotThrow(dfsIterator::next);
        assertThrows(NoSuchElementException.class, dfsIterator::next);
    }

    @Test
    void checkAddChildForDifferentType() {
        Tree<Double> tree = new Tree<>(3.14);
        var a = tree.addChild(2.718);
        assertEquals(2.718, a.getNodeName());
    }

    @Test
    void checkRootForBooleanType() {
        Tree<Boolean> tree = new Tree<>(true);
        assertEquals(true, tree.getNodeName());
    }

    @Test
    void checkRootForDateType() {
        Date currentDate = new Date();
        Tree<Date> tree = new Tree<>(currentDate);
        assertEquals(currentDate, tree.getNodeName());
    }

    @Test
    void checkAddChildForExistingType() {
        Date currentDate = new Date();
        Tree<Date> tree = new Tree<>(currentDate);
        Date childDate = new Date();
        var a = tree.addChild(childDate);
        assertEquals(childDate, a.getNodeName());
    }

    @Test
    void testSetFlagDfs() {
        Tree<String> tree = new Tree<String>("Root");
        tree.setFlagDfs();
        assertEquals(Tree.TraversalFlag.DFS, ((Tree<String>) tree).flag);
    }

    @Test
    void testSetFlagBfs() {
        Tree<String> tree = new Tree<String>("Root");
        tree.setFlagBfs();
        assertEquals(Tree.TraversalFlag.BFS, ((Tree<String>) tree).flag);
    }


}
