package com.playposse.interviewprep.crackingthecodeinterview.treesandgraphs;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/26/2018.
 */
public class FindSum {

    @Test
    public void test() {
        assertEquals(0, findSum(null, 10));

        assertEquals(1, findSum(new Node(10), 10));
        assertEquals(0, findSum(new Node(9), 10));

        assertEquals(
                3,
                findSum(
                        new Node(10, new Node(0), new Node(0)),
                        10));

        assertEquals(
                1,
                findSum(
                        new Node(10, new Node(1), new Node(2)),
                        10));

        assertEquals(
                3,
                findSum(
                        new Node(8,
                                new Node(
                                        1,
                                        new Node(1),
                                        new Node(2, new Node(-1), null)),
                                new Node(2)),
                        10));
    }

    private static int findSum(Node root, int sum) {
        if (root == null) {
            return 0;
        }

        return findSum(root, sum, 0, new HashMap<Integer, Integer>());
    }

    private static int findSum(
            Node node,
            int sum,
            int runningTotal,
            Map<Integer, Integer> history) {

        runningTotal += node.getValue();
        int complement = sum - runningTotal;
        int additionalPathCount = 0;

        if (complement == 0) {
            additionalPathCount++;
        }

        if (history.containsKey(complement)) {
            additionalPathCount += history.get(complement);
        }

        if (history.containsKey(runningTotal)) {
            history.put(runningTotal, history.get(runningTotal) + 1);
        } else {
            history.put(runningTotal, 1);
        }

        if (node.getLeft() != null) {
            additionalPathCount += findSum(node.getLeft(), sum, runningTotal, history);
        }

        if (node.getRight() != null) {
            additionalPathCount += findSum(node.getRight(), sum, runningTotal, history);
        }

        // Back out history
        history.put(runningTotal, history.get(runningTotal) - 1);

        return additionalPathCount;
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
