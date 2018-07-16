package com.playposse.interviewprep.crackingthecodeinterview.treesandgraphs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/16/2018.
 */
public class Successor {

    @Test
    public void test() {
        assertEquals(-1, findSuccessor(null));
        assertEquals(-1, findSuccessor(new Node(5)));
        assertEquals(-1, findSuccessor(new Node(5).setParent(new Node(4))));

        Node node3 = new Node(5).setParent(new Node(4).setParent(new Node(10)));
        node3.getParent().setRight(node3);
        assertEquals(10, findSuccessor(node3));

        assertEquals(8, findSuccessor(new Node(5).setRight(new Node(8)).setParent(new Node(4).setParent(new Node(10)))));
        assertEquals(7, findSuccessor(new Node(5).setRight(new Node(8).setLeft(new Node(7))).setParent(new Node(4).setParent(new Node(10)))));
        assertEquals(6, findSuccessor(new Node(5).setLeft(new Node(1)).setRight(new Node(8).setLeft(new Node(7).setLeft(new Node(6)))).setParent(new Node(4).setParent(new Node(10)))));

        Node node7 = new Node(10).setLeft(new Node(5));
        assertEquals(10, findSuccessor(node7.left));
    }

    private static int findSuccessor(Node node) {
        if (node == null) {
            return -1;
        }

        // Find smallest right child.
        if (node.right != null) {
            Node result = node.right;
            while (result.left != null) {
                result = result.left;
            }
            return result.value;
        }

        if (node.parent != null) {
            if (node.parent.right == node) {
                Node result = node;
                while ((result.parent != null) && (result.parent.right == node)) {
                    result = result.parent;
                }
                if (result.parent != null) {
                    return result.parent.value;
                }
            } else if (node.parent.left == node) {
                return node.parent.value;
            }
        }

        return -1;
    }


    private static class Node {

        private int value;
        private Node parent;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getParent() {
            return parent;
        }

        public Node setParent(Node parent) {
            this.parent = parent;
            return this;
        }

        public Node getLeft() {
            return left;
        }

        public Node setLeft(Node left) {
            this.left = left;
            left.parent = this;
            return this;
        }

        public Node getRight() {
            return right;
        }

        public Node setRight(Node right) {
            this.right = right;
            right.parent = this;
            return this;
        }
    }
}
