package com.playposse.interviewprep.leetcode.competition5;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by thoma on 8/11/2018.
 */
public class PossibleBipartition2 {

    @Test
    public void test() {
        assertTrue(new Solution().possibleBipartition(4, new int[][]{{1, 2}, {1, 3}, {2, 4}}));
        assertFalse(new Solution().possibleBipartition(3, new int[][]{{1, 2}, {1, 3}, {2, 3}}));
        assertFalse(new Solution().possibleBipartition(5, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}}));
    }

    class Solution {
        public boolean possibleBipartition(int N, int[][] dislikes) {
            // Create nodes
            Map<Integer, Node> hateRegistry = new HashMap<>();
            for (int i = 0; i < dislikes.length; i++) {
                int a = dislikes[i][0];
                int b = dislikes[i][1];
                add(hateRegistry, a, b);
                add(hateRegistry, b, a);
            }

            List<Node> nodes = new ArrayList<>(hateRegistry.values());

            while (!nodes.isEmpty()) {
                Node node = nodes.get(nodes.size() - 1);
                if (node.isRed != null) {
                    continue;
                }

                Stack<Node> stack = new Stack<>();
                Stack<Node> children = new Stack<>();
                stack.push(node);
                boolean expectedIsRed = true;

                while (!stack.isEmpty()) {
                    expectedIsRed = !expectedIsRed;
                    while (!stack.isEmpty()) {
                        Node secondNode = stack.pop();
                        if (secondNode.isRed == null) {
                            secondNode.isRed = expectedIsRed;
                            for (int hate : secondNode.hates) {
                                children.push(hateRegistry.get(hate));
                            }
                            nodes.remove(secondNode);
                        } else if (secondNode.isRed != expectedIsRed) {
                            return false;
                        }
                    }

                    stack = children;
                    children = new Stack<>();
                }
            }

            return true;
        }

        private void add(Map<Integer, Node> hateRegistry, int a, int b) {
            if (hateRegistry.containsKey(a)) {
                hateRegistry.get(a).hates.add(b);
            } else {
                Node newNode = new Node(a);
                newNode.hates.add(b);
                hateRegistry.put(a, newNode);
            }
        }

        private class Node {

            private final int self;

            private Set<Integer> hates = new HashSet<>();

            private Boolean isRed = null;

            public Node(int self) {
                this.self = self;
            }

            public void addHate(int num) {
                hates.add(num);
            }

            public boolean hasMutualHates(Map<Integer, Node> hateRegistry) {
                for (int secondNum : hates) {
                    Node otherNode = hateRegistry.get(secondNum);
                    for (int thirdNum : otherNode.hates) {
                        if (hates.contains(thirdNum)) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
    }
}
