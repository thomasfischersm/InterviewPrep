package com.playposse.interviewprep.crackingthecodeinterview.treesandgraphs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Given the root and two nodes, find the first common ancestor of those nodes in the tree.
 */
public class FindCommonAncestor {

    @Test
    public void test0() {
        assertNull(findAncestor(null, null, null));
    }

    @Test
    public void test1() {
        Node nodeA = new Node(1);
        Node nodeB = new Node(2);
        Node root = new Node(3, nodeA, nodeB);

        assertEquals(root, (findAncestor(root, nodeA, nodeB)));
        assertEquals(root, (findAncestor(root, root, nodeB)));
        assertEquals(root, (findAncestor(root, nodeA, root)));
    }

    @Test
    public void test2() {
        Node nodeA = new Node(1);
        Node nodeB = new Node(2);
        Node leftNode = new Node(4, nodeA, nodeB);
        Node root = new Node(3, leftNode, null);

        assertEquals(leftNode, (findAncestor(root, nodeA, nodeB)));
    }

    @Test
    public void test3() {
        Node nodeA = new Node(1);
        Node nodeB = new Node(2);
        Node rightNode = new Node(4, nodeA, nodeB);
        Node root = new Node(3, null, rightNode);

        assertEquals(rightNode, (findAncestor(root, nodeA, nodeB)));
    }

    @Test
    public void test4() {
        Node nodeA = new Node(1);
        Node nodeB = new Node(2);
        Node rightNode = new Node(4, null, nodeB);
        Node root = new Node(3, nodeA, rightNode);

        assertEquals(root, (findAncestor(root, nodeA, nodeB)));
    }

    private static Node findAncestor(Node root, Node nodeA, Node nodeB) {
        Result result = find(root, nodeA, nodeB);
        return result.getAncestor();
    }

    private static Result find(Node root, Node nodeA, Node nodeB) {
        if (root == null) {
            return new Result(null, false, false);
        }

        Result leftResult = null;
        if (root.left != null) {
            leftResult = find(root.left, nodeA, nodeB);
            if (leftResult.ancestor != null) {
                return leftResult;
            }
            if (((root == nodeA) && leftResult.isNodeBFound)
                    || ((root == nodeB) && leftResult.isNodeAFound)) {
                return new Result(root, true, true);
            }
        }

        Result rightResult = null;
        if (root.right != null) {
            rightResult = find(root.right, nodeA, nodeB);
            if (rightResult.ancestor != null) {
                return rightResult;
            }
        }

        boolean isNodeAFound =
                (root == nodeA)
                        || ((leftResult != null) && leftResult.isNodeAFound())
                        || ((rightResult != null) && rightResult.isNodeAFound());
        boolean isNodeBFound =
                (root == nodeB)
                        || ((leftResult != null) && leftResult.isNodeBFound())
                        || ((rightResult != null) && rightResult.isNodeBFound());

        if (isNodeAFound && isNodeBFound) {
            return new Result(root, true, true);
        } else {
            return new Result(null, isNodeAFound, isNodeBFound);
        }
    }

    private static class Result {
        private Node ancestor;
        private boolean isNodeAFound;
        private boolean isNodeBFound;

        public Result(Node ancestor, boolean isNodeAFound, boolean isNodeBFound) {
            this.ancestor = ancestor;
            this.isNodeAFound = isNodeAFound;
            this.isNodeBFound = isNodeBFound;
        }

        public Node getAncestor() {
            return ancestor;
        }

        public void setAncestor(Node ancestor) {
            this.ancestor = ancestor;
        }

        public boolean isNodeAFound() {
            return isNodeAFound;
        }

        public void setNodeAFound(boolean nodeAFound) {
            isNodeAFound = nodeAFound;
        }

        public boolean isNodeBFound() {
            return isNodeBFound;
        }

        public void setNodeBFound(boolean nodeBFound) {
            isNodeBFound = nodeBFound;
        }
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
