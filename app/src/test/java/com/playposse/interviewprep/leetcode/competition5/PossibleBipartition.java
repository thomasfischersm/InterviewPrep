package com.playposse.interviewprep.leetcode.competition5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by thoma on 8/11/2018.
 */
public class PossibleBipartition {

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

            for (Node node : hateRegistry.values()) {
                if (node.hasMutualHates(hateRegistry)) {
                    return false;
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
