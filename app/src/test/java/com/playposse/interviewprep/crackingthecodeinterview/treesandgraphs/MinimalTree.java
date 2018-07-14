package com.playposse.interviewprep.crackingthecodeinterview.treesandgraphs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Given a sorted array with unique values, create a binary search tree with minimal depth.
 */
public class MinimalTree {

    @Test
    public void numbers() {
        for (int i = 1; i < 30; i++) {
            int depth = (int) (Math.log(i) / Math.log(2)) + 1;
            System.out.println(i + " nodes have a minimum depth of " + depth);
        }
    }

    @Test
    public void createTree0() {
        Node rootNode = createTree(new int[]{1});

        assertEquals(1, rootNode.getValue());
        assertNull(rootNode.getLeft());
        assertNull(rootNode.getRight());
    }

    @Test
    public void createTree1() {
        Node rootNode = createTree(new int[]{1, 2});

        assertEquals(2, rootNode.getValue());
        assertNotNull(rootNode.getLeft());
        assertEquals(1, rootNode.getLeft().getValue());
        assertNull(rootNode.getRight());
    }

    @Test
    public void createTree2() {
        Node rootNode = createTree(new int[]{1, 2, 3});

        assertEquals(2, rootNode.getValue());
        assertNotNull(rootNode.getLeft());
        assertEquals(1, rootNode.getLeft().getValue());
        assertNotNull(rootNode.getRight());
        assertEquals(3, rootNode.getRight().getValue());
    }

    @Test
    public void createTree3() {
        Node rootNode = createTree(new int[]{1, 2, 3, 4});

        assertEquals(3, rootNode.getValue());
        assertNotNull(rootNode.getLeft());
        assertEquals(2, rootNode.getLeft().getValue());
        assertNotNull(rootNode.getLeft().getLeft());
        assertEquals(1, rootNode.getLeft().getLeft().getValue());
        assertNull(rootNode.getLeft().getRight());
        assertNotNull(rootNode.getRight());
        assertEquals(4, rootNode.getRight().getValue());
        assertNull(rootNode.getRight().getRight());
        assertNull(rootNode.getRight().getLeft());
    }

    @Test
    public void createTree4() {
        Node rootNode = createTree(new int[]{1, 2, 3, 4, 5});

        assertEquals(3, rootNode.getValue());
        assertNotNull(rootNode.getLeft());
        assertEquals(2, rootNode.getLeft().getValue());
        assertNotNull(rootNode.getLeft().getLeft());
        assertEquals(1, rootNode.getLeft().getLeft().getValue());
        assertNull(rootNode.getLeft().getRight());
        assertNotNull(rootNode.getRight());
        assertEquals(5, rootNode.getRight().getValue());
        assertNotNull(rootNode.getRight().getLeft());
        assertEquals(4, rootNode.getRight().getLeft().getValue());
        assertNull(rootNode.getRight().getRight());
    }

    @Test
    public void createTree5() {
        Node rootNode = createTree(new int[]{1, 2, 3, 4, 5, 6});

        assertEquals(4, rootNode.getValue());

        assertNotNull(rootNode.getLeft());
        assertEquals(2, rootNode.getLeft().getValue());
        assertNotNull(rootNode.getRight());
        assertEquals(6, rootNode.getRight().getValue());

        assertNotNull(rootNode.getLeft().getLeft());
        assertEquals(1, rootNode.getLeft().getLeft().getValue());
        assertNotNull(rootNode.getLeft().getRight());
        assertEquals(3, rootNode.getLeft().getRight().getValue());

        assertNotNull(rootNode.getRight().getLeft());
        assertEquals(5, rootNode.getRight().getLeft().getValue());
        assertNull(rootNode.getRight().getRight());
    }

    private static Node createTree(int[] values) {
        return createNodes(values, 0, values.length);
    }

    private static Node createNodes(int[] values, int start, int end) {
        int index = (end - start) / 2 + start;
        Node node = new Node(values[index]);

        if (start < index) {
            node.left = createNodes(values, start, index);
        }

        if (index + 1 < end) {
            node.right = createNodes(values, index + 1, end);
        }

        return node;
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
