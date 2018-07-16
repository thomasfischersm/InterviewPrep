package com.playposse.interviewprep.crackingthecodeinterview.treesandgraphs;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Check that a binary tree is balanced. Balanced is defined as the depth of both children being at
 * most one different.
 */
public class CheckBalanced {

    @Test
    public void test() {
        assertTrue(isBalanced(null));
        assertTrue(isBalanced(new Node(1)));
        assertTrue(isBalanced(new Node(1, new Node(2), null)));
        assertTrue(isBalanced(new Node(1, new Node(2), new Node(3))));
        assertFalse(isBalanced(new Node(1, new Node(2, new Node(3), null), null)));
        assertFalse(isBalanced(new Node(1, new Node(2, new Node(3, new Node(4), null), null), null)));
    }

    private static boolean isBalanced(Node node) {
        return getDepth(node) != -2;
    }

    private static int getDepth(Node node) {
        if (node == null) {
            return -1;
        }

        int leftDepth = getDepth(node.left);
        if (leftDepth == -2) {
            return -2;
        }

        int rightDepth = getDepth(node.right);
        if (rightDepth == -2) {
            return -2;
        }

        if (Math.abs(leftDepth - rightDepth) > 1) {
            return -2;
        }

        return Math.max(leftDepth, rightDepth) + 1;
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
