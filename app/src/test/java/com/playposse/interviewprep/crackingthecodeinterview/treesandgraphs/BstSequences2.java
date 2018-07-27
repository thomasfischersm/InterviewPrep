package com.playposse.interviewprep.crackingthecodeinterview.treesandgraphs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree, reconstruct which input arrays could have constructed the BST.
 */
public class BstSequences2 {

    @Test
    public void test0() {
        Node root = new Node(2);
        List<int[]> result = findInputArrays(root);
        printResult(result);
    }

    @Test
    public void test1() {
        Node root = new Node(2, new Node(1), new Node(3));
        List<int[]> result = findInputArrays(root);
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
        List<int[]> result = findInputArrays(root);
        printResult(result);
    }

    private static List<int[]> findInputArrays(Node root) {
        if (root == null) {
            return null;
        }

        List<Integer> currentSequence = new ArrayList<>();
        currentSequence.add(root.getValue());

        List<Node> reachedNodes = new ArrayList<>();
        reachedNodes.add(root);

        List<List<Integer>> result = findNextInput(currentSequence, reachedNodes);

        List<int[]> arrays = new ArrayList<>(result.size());
        for (List<Integer> list : result) {
            int[] array = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i);
            }
            arrays.add(array);
        }
        return arrays;
    }

    private static List<List<Integer>> findNextInput(
            List<Integer> currentSequence,
            List<Node> reachedNodes) {

        List<List<Integer>> results = new ArrayList<>();

        for (Node reachedNode : reachedNodes) {
            if ((reachedNode.getLeft() != null)
                    && (!reachedNodes.contains(reachedNode.getLeft()))) {
                results.addAll(pickNextNode(reachedNode.getLeft(), currentSequence, reachedNodes));
            }

            if ((reachedNode.getRight() != null)
                    && (!reachedNodes.contains(reachedNode.getRight()))) {
                results.addAll(pickNextNode(reachedNode.getRight(), currentSequence, reachedNodes));
            }
        }

        if (results.size() == 0) {
            results.add(currentSequence);
        }
        return results;
    }

    private static List<List<Integer>> pickNextNode(
            Node nextNode,
            List<Integer> currentSequence,
                                                    List<Node> reachedNodes) {
        List<Integer> newSequence = new ArrayList<>(currentSequence);
        newSequence.add(nextNode.getValue());

        List<Node> newlyReachedNodes = new ArrayList<>(reachedNodes);
        newlyReachedNodes.add(nextNode);
        return findNextInput(newSequence, newlyReachedNodes);
    }

    private static void printResult(List<int[]> result) {
        for (int[] array: result) {
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i] + ", ");
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
