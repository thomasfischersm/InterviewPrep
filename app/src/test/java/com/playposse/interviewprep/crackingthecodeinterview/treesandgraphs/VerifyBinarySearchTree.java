package com.playposse.interviewprep.crackingthecodeinterview.treesandgraphs;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by thoma on 7/15/2018.
 */
public class VerifyBinarySearchTree {

    private static int[] UNBALANCED_MIN_MAX = new int[3];

    @Test
    public void test() {
        assertTrue(isBinarySearchTree(null));
        assertTrue(isBinarySearchTree(new Node(1)));
        assertTrue(isBinarySearchTree(new Node(2, new Node(1), new Node(3))));
        assertTrue(isBinarySearchTree(new Node(2, null, new Node(3))));
        assertTrue(isBinarySearchTree(new Node(2, new Node(1), null)));

        assertFalse(isBinarySearchTree(new Node(2, new Node(3), new Node(3))));
        assertFalse(isBinarySearchTree(new Node(2, new Node(1), new Node(1))));
        assertFalse(isBinarySearchTree(new Node(2, new Node(1), new Node(2))));
        assertFalse(isBinarySearchTree(new Node(2, new Node(2), new Node(3))));

        assertFalse(isBinarySearchTree(new Node(2, new Node(1, new Node(0), new Node(2)), new Node(3))));
    }

    private boolean isBinarySearchTree(Node node) {
        return getMinMax(node) != UNBALANCED_MIN_MAX;
    }

    private static int[] getMinMax(Node node) {
        if (node == null) {
            return null;
        }

        int[] leftMinMax = getMinMax(node.left);
        if ((leftMinMax == UNBALANCED_MIN_MAX) || ((leftMinMax != null) && (leftMinMax[1] >= node.value))) {
            return UNBALANCED_MIN_MAX;
        }

        int[] rightMinMax = getMinMax(node.right);
        if ((rightMinMax == UNBALANCED_MIN_MAX) || ((rightMinMax != null) && (rightMinMax[0] <= node.value))) {
            return UNBALANCED_MIN_MAX;
        }

        int min = (leftMinMax != null) ? leftMinMax[0] : node.value;
        int max = (rightMinMax != null) ? rightMinMax[1] : node.value;
        return new int[]{min, max};
    }

    private static class Node {

        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
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
