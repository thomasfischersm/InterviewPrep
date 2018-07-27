package com.playposse.interviewprep.crackingthecodeinterview.treesandgraphs;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by thoma on 7/26/2018.
 */
public class IsSubTree {

    @Test
    public void test() {
        assertTrue(isSubTree(new Node(1), new Node(1)));
        assertFalse(isSubTree(new Node(1), new Node(2)));

        assertTrue(isSubTree(
                new Node(1, new Node(2), null),
                new Node(2)));

        assertFalse(isSubTree(
                new Node(1, new Node(3), null),
                new Node(2)));

        assertTrue(isSubTree(
                new Node(1,
                        new Node(
                                2,
                                new Node(
                                        3,
                                        new Node(5),
                                        null),
                                new Node(4)),
                        null),
                new Node(
                        2,
                        new Node(
                                3,
                                new Node(5),
                                null),
                        new Node(4))));

        assertFalse(isSubTree(
                new Node(1,
                        new Node(
                                2,
                                new Node(
                                        3,
                                        new Node(5),
                                        null),
                                new Node(4)),
                        null),
                new Node(
                        2,
                        new Node(
                                3,
                                new Node(5),
                                null),
                        new Node(6))));
    }

    private static boolean isSubTree(Node largeTreeNode, Node smallTreeNode) {
        if ((largeTreeNode == null) && (smallTreeNode == null)) {
            return true;
        }

        if ((largeTreeNode == null) || (smallTreeNode == null)) {
            return false;
        }

        if (largeTreeNode.getValue() == smallTreeNode.getValue()) {
            boolean isMutualRoot = isSubTree(largeTreeNode.getLeft(), smallTreeNode.getLeft())
                    && isSubTree(largeTreeNode.getRight(), smallTreeNode.getRight());
            if (isMutualRoot) {
                return true;
            }
        }

        return isSubTree(largeTreeNode.getLeft(), smallTreeNode)
                || isSubTree(largeTreeNode.getRight(), smallTreeNode);

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
