package ru.nsu.yakovleva.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;
import org.junit.jupiter.api.Test;


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
    void checkEqualsFalse() {
        Tree<String> tree1 = new Tree<>("root1");
        tree1.addChild("child1");
        Tree<String> tree2 = new Tree<>("root1");
        tree2.addChild("child2");

        assertNotSame(tree1, tree2);

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
    void testEqualsFalseBigWithModifiedTreeStructure() {
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

        assertNotEquals(tree1, tree2);
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
    void checkBfsIteratorWithRemove() {

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
            if (!Objects.equals(item, "child1")) {
                actual = actual.concat(item);
            }
        }


        assertEquals("root1root2child2child3child4", actual);
    }

    @Test
    void checkDfsIteratorWithRemove() {

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
            if (!Objects.equals(item, "child1")) {
                actual = actual.concat(item);
            }
        }


        assertEquals("root1child2root2child3child4", actual);
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

}





