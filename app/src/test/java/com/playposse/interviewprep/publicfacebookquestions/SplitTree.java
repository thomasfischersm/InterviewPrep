package com.playposse.interviewprep.publicfacebookquestions;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Determine if a binary tree can be split at a single edge, so that the sum of both trees equals
 * each other.
 */
public class SplitTree {

    @Test
    public void test() {
        assertFalse(canSplitInHalf(null));
        assertFalse(canSplitInHalf(new Node(0)));
        assertFalse(canSplitInHalf(new Node(1)));
        assertFalse(canSplitInHalf(new Node(2)));

        assertFalse(canSplitInHalf(new Node(0, new Node(-1), new Node(1))));
        assertFalse(canSplitInHalf(new Node(1, new Node(-1), new Node(2))));
        assertFalse(canSplitInHalf(
                new Node(
                        0,
                        new Node(
                                -1,
                                new Node(1),
                                null),
                        null)));

        assertFalse(canSplitInHalf(new Node(2, new Node(0), new Node(0))));
        assertTrue(canSplitInHalf(new Node(2, new Node(2), new Node(4))));
        assertTrue(canSplitInHalf(new Node(3, new Node(5), new Node(2))));

        assertTrue(canSplitInHalf(new Node(
                3,
                new Node(5,
                        new Node(10),
                        null),
                new Node(2))));
    }

    private static boolean canSplitInHalf(Node root) {
        if (root == null) {
            return false;
        }

        if ((root.getLeft() == null) && (root.getRight() == null)) {
            // Can't split a single node tree. (Actually if the value is zero, we could split it
            // into a null tree and a single node tree.
            return false;
        }

        HashSet<Integer> uniqueSums = new HashSet<>();
        int leftSum = getSum(root.left, uniqueSums);
        int rightSum = getSum(root.right, uniqueSums);
        int treeSum = leftSum + rightSum + root.getValue();
        uniqueSums.add(treeSum);

        if ((root.getValue() == 0) && (leftSum == -1* rightSum)) {
            // Special case of:
            //      0
            //     / \
            //    -1  1
            return false;
        }

        if (treeSum % 2 == 0) {
            return uniqueSums.contains(treeSum / 2);
        } else {
            return false;
        }
    }

    private static int getSum(Node node, Set<Integer> uniqueSums) {
        if (node == null) {
            return 0;
        }

        int sum = node.value
                + getSum(node.getLeft(), uniqueSums)
                + getSum(node.getRight(), uniqueSums);
        uniqueSums.add(sum);
        return sum;
    }

    private static class Node {

        private final int value;

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

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }
}
