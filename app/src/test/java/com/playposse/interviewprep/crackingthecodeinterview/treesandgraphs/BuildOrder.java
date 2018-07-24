package com.playposse.interviewprep.crackingthecodeinterview.treesandgraphs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * A node is a project. A child of a node is a dependency for the project. A dependency is a project
 * itself. Figure out in which order hte projects have to be built.
 */
public class BuildOrder {

    @Test
    public void test0() {
        Node node0 = new Node(0);
        List<Node> nodes = new ArrayList<>();
        nodes.add(node0);
        assertEquals(nodes, orderNodes(nodes));
    }

    @Test
    public void test1() {
        Node node0 = new Node(0);
        Node node1 = new Node(1);

        List<Node> nodes = new ArrayList<>();
        nodes.add(node0);
        nodes.add(node1);

        assertEquals(nodes, orderNodes(nodes));
    }

    @Test
    public void test2a() {
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);

        node1.getChildren().add(node2);

        List<Node> nodes = new ArrayList<>();
        nodes.add(node0);
        nodes.add(node2);
        nodes.add(node1);

        assertEquals(nodes, orderNodes(nodes));
    }

    @Test
    public void test2b() {
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);

        node1.getChildren().add(node2);

        List<Node> nodes = new ArrayList<>();
        nodes.add(node0);
        nodes.add(node1);
        nodes.add(node2);

        List<Node> result = new ArrayList<>();
        result.add(node0);
        result.add(node2);
        result.add(node1);

        assertEquals(result, orderNodes(nodes));
    }

    @Test
    public void test3() {
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);

        node1.getChildren().add(node2);
        node1.getChildren().add(node0);
        node2.getChildren().add(node0);

        List<Node> nodes = new ArrayList<>();
        nodes.add(node0);
        nodes.add(node2);
        nodes.add(node1);

        assertEquals(nodes, orderNodes(nodes));
    }


    @Test(expected = RuntimeException.class)
    public void test4() {
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);

        node1.getChildren().add(node2);
        node1.getChildren().add(node0);
        node2.getChildren().add(node0);
        node0.getChildren().add(node1);

        List<Node> nodes = new ArrayList<>();
        nodes.add(node0);
        nodes.add(node2);
        nodes.add(node1);

        assertEquals(nodes, orderNodes(nodes));
    }

    private List<Node> orderNodes(List<Node> nodes) {
        List<Node> order = new ArrayList<>();
        Set<Node> encountered = new HashSet<>();

        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);

            if (order.contains(node)) {
                continue;
            }

            buildChildren(node, order, encountered);
            order.add(node);
        }

        return order;
    }

    private void buildChildren(Node node, List<Node> order, Set<Node> encountered) {
        if (order.contains(node)) {
            return;
        }

        if (encountered.contains(node)) {
            throw new RuntimeException("Detected cycle");
        }
        encountered.add(node);

        for (int i = 0; i < node.getChildren().size(); i++) {
            Node child = node.getChildren().get(0);

            if (order.contains(child)) {
                continue;
            }

            buildChildren(child, order, encountered);
            order.add(child);
        }
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
