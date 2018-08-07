package com.playposse.interviewprep.crackingthecodeinterview.sortandsearch;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 8/7/2018.
 */
public class RankFromStream {

    @Test
    public void test() {
        SpecialRanker ranker = new SpecialRanker();
        assertEquals(-1, ranker.getRank(0));

        ranker.feed(1);
        assertEquals(-1, ranker.getRank(0));
        assertEquals(0, ranker.getRank(1));

        ranker.feed(2);
        assertEquals(-1, ranker.getRank(0));
        assertEquals(0, ranker.getRank(1));
        assertEquals(1, ranker.getRank(2));

        ranker.feed(5);
        assertEquals(-1, ranker.getRank(0));
        assertEquals(0, ranker.getRank(1));
        assertEquals(1, ranker.getRank(2));
        assertEquals(2, ranker.getRank(5));

        ranker.feed(3);
        assertEquals(-1, ranker.getRank(0));
        assertEquals(0, ranker.getRank(1));
        assertEquals(1, ranker.getRank(2));
        assertEquals(2, ranker.getRank(3));
        assertEquals(3, ranker.getRank(5));

        ranker.feed(-2);
        assertEquals(-1, ranker.getRank(0));
        assertEquals(0, ranker.getRank(-2));
        assertEquals(1, ranker.getRank(1));
        assertEquals(2, ranker.getRank(2));
        assertEquals(3, ranker.getRank(3));
        assertEquals(4, ranker.getRank(5));

        ranker.feed(-3);
        assertEquals(-1, ranker.getRank(0));
        assertEquals(0, ranker.getRank(-3));
        assertEquals(1, ranker.getRank(-2));
        assertEquals(2, ranker.getRank(1));
        assertEquals(3, ranker.getRank(2));
        assertEquals(4, ranker.getRank(3));
        assertEquals(5, ranker.getRank(5));
        assertEquals(-1, ranker.getRank(6));
    }

    private static class SpecialRanker {

        private Node root;

        private void feed(int num) {
            if (root == null) {
                root = new Node(num);
            } else {
                root.addChild(new Node(num));
            }
        }

        private int getRank(int num) {
            int count = 0;
            Node node = root;

            while (node != null) {
                if (node.value == num) {
                    return count + node.leftChildSize;
                } else if (num < node.value) {
                    node = node.left;
                } else {
                    count += node.leftChildSize + 1;
                    node = node.right;
                }
            }

            return -1;
        }

        private static class Node {

            private final int value;

            private int leftChildSize = 0;
            private Node left;
            private Node right;

            private Node(int value) {
                this.value = value;
            }

            private void addChild(Node node) {
                if (node.value <= value) {
                    leftChildSize += node.leftChildSize + 1;
                    if (left == null) {
                        left = node;
                    } else {
                        left.addChild(node);
                    }
                } else {
                    if (right == null) {
                        right = node;
                    } else {
                        right.addChild(node);
                    }
                }
            }
        }
    }
}
