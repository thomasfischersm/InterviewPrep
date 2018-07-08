package com.playposse.interviewprep.crackingthecodeinterview.linkedlists;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Two numbers are provided as linked lists of digits in reversed order. Return the sum of them in
 * the same formatting. Part 2 is to do the same with forward order.
 */
public class SumLists {

    @Test
    public void test0() {
        test(
                createLinkedList(2, 1, 9),
                createLinkedList(7, 1, 6),
                createLinkedList(5, 9, 2));
    }

    @Test
    public void test1() {
        test(
                createLinkedList(2, 1, 7),
                createLinkedList(7, 1, 6),
                createLinkedList(5, 9));
    }

    @Test
    public void test2() {
        test(
                createLinkedList(2, 1, 3),
                createLinkedList(7, 1),
                createLinkedList(5, 9, 2));
    }

    @Test
    public void test3() {
        test(
                createLinkedList(2, 1, 9),
                createLinkedList(),
                createLinkedList(2, 1, 9));
    }

    @Test
    public void test4() {
        test(
                createLinkedList(2, 1, 3, 1),
                createLinkedList(7, 1, 6),
                createLinkedList(5, 9, 6));
    }

    @Test
    public void test5() {
        test(
                createLinkedList(),
                createLinkedList(),
                createLinkedList());
    }

    private void test(Node expected, Node nodeA, Node nodeB) {
        Node result = sum(nodeA, nodeB);
        assertTrue(compareLists(expected, result));
    }

    private static Node sum(Node nodeA, Node nodeB) {
        int carry = 0;
        Node resultHead = null;
        Node resultNode = null;
        while ((nodeA != null) || (nodeB != null)) {
            int number = carry;

            if (nodeA != null) {
                number += nodeA.value;
                nodeA = nodeA.next;
            }

            if (nodeB != null) {
                number += nodeB.value;
                nodeB = nodeB.next;
            }

            carry = number / 10;
            number = number % 10;

            if (resultNode == null) {
                resultNode = new Node(number);
                resultHead = resultNode;
            } else {
                resultNode.next = new Node(number);
                resultNode = resultNode.next;
            }
        }

        if (carry > 0) {
            resultNode.next = new Node(carry);
        }
        return resultHead;
    }

    private static Node createLinkedList(int... values) {
        Node head = null;
        Node tail = null;

        for (int value : values) {
            Node node = new Node(value);

            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }

        return head;
    }

    private static boolean compareLists(Node head0, Node head1) {
        boolean isMismatch = false;
        while ((head0 != null) && (head1 != null)) {
            System.out.println(head0.value + " - " + head1.value);
            if (head0.value != head1.value) {
                isMismatch = true;
                System.out.println("Mismatch!");
            }

            head0 = head0.next;
            head1 = head1.next;
        }

        if (head0 != null) {
            System.out.println("The first head is longer");
            isMismatch = true;
        }

        if (head1 != null) {
            System.out.println("The second head is longer");
            isMismatch = true;
        }

        System.out.println();

        return !isMismatch;
    }

    private static void dump(Node node) {
        while (node != null) {
            System.out.print(node.value);
            if (node.next != null) {
                System.out.print(" -> ");
            }

            node = node.next;
        }
    }

    private static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
