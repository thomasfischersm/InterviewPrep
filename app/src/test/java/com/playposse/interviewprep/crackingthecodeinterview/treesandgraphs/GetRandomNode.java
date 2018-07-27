package com.playposse.interviewprep.crackingthecodeinterview.treesandgraphs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by thoma on 7/26/2018.
 */
public class GetRandomNode {

    @Test
    public void test() {
        Tree tree = new Tree();
        assertNull(tree.getRootNode());
        assertNull(tree.getRandom());

        tree.insert(new Node(5));
        assertEquals(5, tree.getRootNode().getValue());
        assertEquals(5, tree.getRandom().getValue());

        tree.insert(new Node(4));
        assertEquals(4, tree.getRootNode().getLeft().getValue());
        testRandom(tree);

        tree.insert(new Node(3));
        assertEquals(3, tree.getRootNode().getLeft().getLeft().getValue());
        testRandom(tree);

        tree.insert(new Node(7));
        assertEquals(7, tree.getRootNode().getRight().getValue());
        testRandom(tree);

        tree.insert(new Node(6));
        assertEquals(6, tree.getRootNode().getRight().getLeft().getValue());
        testRandom(tree);
    }

    private void testRandom(Tree tree) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            int value = tree.getRandom().getValue();
            if (frequency.containsKey(value)) {
                int count = frequency.get(value) + 1;
                frequency.put(value, count);
            } else {
                frequency.put(value, 1);
            }
        }

        ArrayList<Integer> values = new ArrayList<>(frequency.keySet());
        Collections.sort(values);
        for (int value : values) {
            System.out.println(value + ": " + (frequency.get(value) / 100));
        }
        System.out.println("========================\n");
    }

    private static class Tree {

        private Node root;

        private void insert(Node node) {
            if (root == null) {
                root = node;
            } else {
                root.insert(node);
            }
        }

        private Node getRandom() {
            if (root == null) {
                return null;
            }

            int rand = new Random().nextInt(root.getChildSize());
            return root.getByIndex(rand);
        }

        private Node getRootNode() {
            return root;
        }
    }

    private static class Node {

        private final int value;
        private Node left;
        private Node right;

        private int childSize = 1; // Including self.

        private Node(int value) {
            this.value = value;
        }

        private Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;

            if (left != null) {
                childSize += left.getChildSize();
            }

            if (right != null) {
                childSize += right.getChildSize();
            }
        }

        private void insert(Node node) {
            if (node.getValue() <= value) {
                if (left == null) {
                    left = node;
                } else {
                    left.insert(node);
                }
            } else {
                if (right == null) {
                    right = node;
                } else {
                    right.insert(node);
                }
            }

            childSize += node.getChildSize();
        }

        private Node getByIndex(int index) {
            int leftSize = (left != null) ? left.getChildSize() : 0;

            if (index < leftSize) {
                return left.getByIndex(index);
            } else if (index == leftSize) {
                return this;
            } else {
                return right.getByIndex(index - leftSize - 1);
            }
        }

        private int getValue() {
            return value;
        }

        private Node getLeft() {
            return left;
        }

        private Node getRight() {
            return right;
        }

        private int getChildSize() {
            return childSize;
        }
    }
}
