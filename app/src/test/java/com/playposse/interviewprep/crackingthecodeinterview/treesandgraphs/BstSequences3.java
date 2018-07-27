package com.playposse.interviewprep.crackingthecodeinterview.treesandgraphs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary search tree, reconstruct which input arrays could have constructed the BST.
 */
public class BstSequences3 {

    @Test
    public void test0() {
        Node root = new Node(2);
        List<List<Integer>> result = findInputArrays(root);
        printResult(result);
    }

    @Test
    public void test1() {
        Node root = new Node(2, new Node(1), new Node(3));
        List<List<Integer>> result = findInputArrays(root);
        printResult(result);
    }

    @Test
    public void test2() {
        Node root = new Node(
                2,
                new Node(
                        1,
                        new Node(0),
                        null),
                new Node(3));
        List<List<Integer>> result = findInputArrays(root);
        printResult(result);
    }

    private static List<List<Integer>> findInputArrays(Node root) {
        if ((root.getLeft() == null) && (root.getRight() == null)) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new LinkedList<Integer>());
            result.get(0).add(root.getValue());
            return result;
        }

        if (root.getLeft() == null) {
            List<List<Integer>> result = findInputArrays(root.getRight());
            for (List<Integer> innerList : result) {
                innerList.add(0, root.getValue());
            }
            return result;
        }

        if (root.getRight() == null) {
            List<List<Integer>> result = findInputArrays(root.getLeft());
            for (List<Integer> innerList : result) {
                innerList.add(0, root.getValue());
            }
            return result;
        }

        List<List<Integer>> leftResult = findInputArrays(root.getLeft());
        List<List<Integer>> rightResult = findInputArrays(root.getRight());
        List<List<Integer>> result = new ArrayList<>();

        for (List<Integer> leftInner : leftResult) {
            for (List<Integer> rightInner : rightResult) {
                weave(
                        result,
                        new LinkedList<Integer>(),
                        leftInner,
                        rightInner,
                        0,
                        0);
            }
        }

        // Add itself to the beginning of the sequences.
        for (List<Integer> innerList : result) {
            innerList.add(0, root.getValue());
        }
        return result;
    }

    private static void weave(
            List<List<Integer>> results,
            List<Integer> currentSequence,
            List<Integer> leftResult,
            List<Integer> rightResult,
            int leftIndex,
            int rightIndex) {

        if ((leftIndex == leftResult.size()) && (rightIndex == rightResult.size())) {
            results.add(new ArrayList<>(currentSequence));
        }

        if (leftIndex < leftResult.size()) {
            currentSequence.add(leftResult.get(leftIndex));
            weave(
                    results,
                    currentSequence,
                    leftResult,
                    rightResult,
                    leftIndex + 1,
                    rightIndex);
            currentSequence.remove(currentSequence.size() - 1);
        }

        if (rightIndex < rightResult.size()) {
            currentSequence.add(rightResult.get(rightIndex));
            weave(
                    results,
                    currentSequence,
                    leftResult,
                    rightResult,
                    leftIndex,
                    rightIndex + 1);
            currentSequence.remove(currentSequence.size() - 1);
        }
    }

    private static void printResult(List<List<Integer>> result) {
        for (List<Integer> innerList: result) {
            for (int i = 0; i < innerList.size(); i++) {
                System.out.print(innerList.get(i) + ", ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------\n");
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
