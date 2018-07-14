package com.playposse.interviewprep.crackingthecodeinterview.treesandgraphs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Find the distance between two nodes on a bi-directed graph.
 */
public class FindDistanceBetweenTwoNodes {

    @Test
    public void test0() {
        Node nodeA = new Node(1);
        Node nodeB = new Node(2);
        link(nodeA, nodeB);

        assertEquals(0, findDistance(nodeA, nodeB));
        assertEquals(0, findDistance(nodeB, nodeA));
    }

    @Test
    public void test1() {
        Node nodeA = new Node(1);
        Node nodeB = new Node(2);
        Node nodeC = new Node(3);

        link(nodeA, nodeC);
        link(nodeB, nodeC);

        assertEquals(1, findDistance(nodeA, nodeB));
        assertEquals(1, findDistance(nodeB, nodeA));
    }

    @Test
    public void test2() {
        Node nodeA = new Node(1);
        Node nodeB = new Node(2);
        Node nodeC = new Node(3);
        Node nodeD = new Node(4); // Doesn't go anywhere.

        link(nodeA, nodeC);
        link(nodeB, nodeC);
        link(nodeA, nodeD);

        assertEquals(1, findDistance(nodeA, nodeB));
        assertEquals(1, findDistance(nodeB, nodeA));
    }

    @Test
    public void test3() {
        Node nodeA = new Node(1);
        Node nodeB = new Node(2);
        Node nodeC = new Node(3);
        Node nodeD = new Node(4); // Doesn't go anywhere.
        Node nodeE = new Node(5); // Longer route to connect A and B
        Node nodeF = new Node(6);

        link(nodeA, nodeC);
        link(nodeB, nodeC);
        link(nodeA, nodeD);
        link(nodeA, nodeE);
        link(nodeE, nodeF);
        link(nodeF, nodeB);

        assertEquals(1, findDistance(nodeA, nodeB));
        assertEquals(1, findDistance(nodeB, nodeA));
    }

    @Test
    public void test4() {
        Node nodeA = new Node(1);
        Node nodeB = new Node(2);
        Node nodeC = new Node(3);
        Node nodeD = new Node(4); // Doesn't go anywhere.
        Node nodeE = new Node(5); // Longer route to connect A and B
        Node nodeF = new Node(6);
        Node nodeG = new Node(7);

        link(nodeA, nodeC);
        link(nodeC, nodeG);
        link(nodeB, nodeG);
        link(nodeA, nodeD);
        link(nodeA, nodeE);
        link(nodeE, nodeF);
        link(nodeF, nodeB);

        assertEquals(2, findDistance(nodeA, nodeB));
        assertEquals(2, findDistance(nodeB, nodeA));
    }

    private static int findDistance(Node nodeA, Node nodeB) {
        // Could trade space for speed by keeping a list of nodes that are already visited.
        // Code assumes that all nodes are double linked.

        Set<Node> foundNodesA = new HashSet<>();
        foundNodesA.add(nodeA);

        Set<Node> foundNodesB = new HashSet<>();
        foundNodesB.add(nodeB);

        int count = 0; // debugging;
        int distance = -1;
        while (!foundNodesA.isEmpty() && !foundNodesB.isEmpty()) {
            Set<Node> currentFoundNodes = new HashSet<>();
            for (Node currentNode : foundNodesA) {
                count++;
                if (foundNodesB.contains(currentNode)) {
                    System.out.println("Compared nodes: " + count);
                    return distance;
                }

                currentFoundNodes.addAll(currentNode.children);
            }
            foundNodesA = currentFoundNodes;
            distance++;

            currentFoundNodes = new HashSet<>();
            for (Node currentNode : foundNodesB) {
                count++;
                if (foundNodesA.contains(currentNode)) {
                    System.out.println("Compared nodes: " + count);
                    return distance;
                }

                currentFoundNodes.addAll(currentNode.children);
            }
            foundNodesB = currentFoundNodes;
            distance++;
        }

        System.out.println("Compared nodes: " + count);
        return -1;
    }

    private static void link(Node nodeA, Node nodeB) {
        nodeA.getChildren().add(nodeB);
        nodeB.getChildren().add(nodeA);
    }

    private static class Node {

        private int value;
        private List<Node> children = new ArrayList<>();

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }
    }
}
