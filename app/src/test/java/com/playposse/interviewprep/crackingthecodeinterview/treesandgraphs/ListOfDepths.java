package com.playposse.interviewprep.crackingthecodeinterview.treesandgraphs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/13/2018.
 */
public class ListOfDepths {

    @Test
    public void test() {
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node0.getChildren().add(node1);
        node0.getChildren().add(node2);
        node1.getChildren().add(node3);
        node1.getChildren().add(node4);

        List<List<Node>> lists = convertTreeToListOfDepths(node0);

        assertEquals(3, lists.size());

        assertEquals(1, lists.get(0).size());
        assertEquals(0, lists.get(0).get(0).getValue());

        assertEquals(2, lists.get(1).size());
        assertEquals(1, lists.get(1).get(0).getValue());
        assertEquals(2, lists.get(1).get(1).getValue());

        assertEquals(2, lists.get(2).size());
        assertEquals(3, lists.get(2).get(0).getValue());
        assertEquals(4, lists.get(2).get(1).getValue());
    }

    private static List<List<Node>> convertTreeToListOfDepths(Node node) {
        List<List<Node>> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            LinkedList<Node> lastRow = new LinkedList<>();
            result.add(lastRow);

            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                lastRow.add(currentNode);
                queue.addAll(currentNode.getChildren());
            }
        }

        return result;
    }

    private static class Node {

        private int value;
        private List<Node> children = new ArrayList<>();

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
