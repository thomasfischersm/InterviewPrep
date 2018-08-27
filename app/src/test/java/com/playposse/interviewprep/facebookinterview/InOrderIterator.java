package com.playposse.interviewprep.facebookinterview;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by thoma on 8/27/2018.
 */
public class InOrderIterator {

    @Test
    public void test0() {
        System.out.println("single root");
        Node root = new Node(1);
        Iterator it = new Iterator(root);
        while (it.hasNext()) {
            it.next();
        }
    }

    @Test
    public void test1() {
        System.out.println("root with two children");
        Node root = new Node(
                1,
                new Node(2),
                new Node(3));
        Iterator it = new Iterator(root);
        while (it.hasNext()) {
            it.next();
        }
    }

    @Test
    public void test2() {
        System.out.println("more complex case");
        Node root = new Node(
                5,
                new Node(
                        2,
                        new Node(1),
                        null),
                new Node(
                        4,
                        null,
                        new Node(3)));
        Iterator it = new Iterator(root);
        while (it.hasNext()) {
            it.next();
        }
    }

    private static class Iterator {
        private final Stack<Node> stack = new Stack<>();

        private Iterator(Node root) {
            descent(root);
        }

        private void descent(Node node) {
            while (node != null) {
                stack.push(node);

                if (node.left != null) {
                    node = node.left;
                } else {
                    break;
                }
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public void next() {
            Node node = stack.pop();

            if (!stack.isEmpty()) {
                Node parent = stack.peek();
                if (parent.right != null) {
                    stack.pop();
                    descent(parent.right);
                    stack.push(parent);
                }
            }

            System.out.println(node.val);
        }
    }

    private static class Node {

        private int val;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
