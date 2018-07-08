package com.playposse.interviewprep.crackingthecodeinterview.linkedlists;

import org.junit.Test;

/**
 * You are given a linked list and a value. The value is the partition. All nodes with the value
 * should form a center. All nodes with a value less than the specified value should be left of the
 * partition. Nodes with larger values should be on the other side.
 */
public class Partition {

    @Test
    public void test() {
        Node head = createLinkedList(5, 3, 1, 7, 6, 7);
        head = partition(head, 7);
        dump(head);
    }

    @Test
    public void test1() {
        Node head = createLinkedList(5, 3, 1, 7, 6, 7, 8, 9);
        head = partition(head, 7);
        dump(head);
    }

    private Node partition(Node node, int partitionValue) {
        Node lesserHead = null;
        Node lesserTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node greaterHead = null;
        Node greaterTail = null;

        while (node != null) {
            if (node.value < partitionValue) {
                if (lesserHead == null) {
                    lesserHead = node;
                    lesserTail = node;
                } else {
                    lesserTail.next = node;
                    lesserTail = node;
                }
            } else if (node.value == partitionValue) {
                if (equalHead == null) {
                    equalHead = node;
                    equalTail = node;
                } else {
                    equalTail.next = node;
                    equalTail = node;
                }
            } else {
                if (greaterHead == null) {
                    greaterHead = node;
                    greaterTail = node;
                } else {
                    greaterTail.next = node;
                    greaterTail = node;
                }
            }

            Node next = node.next;
            node.next = null;
            node = next;
        }

        Node head = null;

        if (lesserHead != null) {
            lesserTail.next = equalHead;
            head = lesserHead;
        }

        if (greaterHead != null) {
            equalTail.next = greaterHead;
        }

        return (head != null) ? head : equalHead;
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

        return !isMismatch;
    }

    private static void dump(Node node) {
        while (node != null) {
            System.out.print(node.value);
            if (node.next != null) {
                System.out.print(" -> ");
            }

            node =  node.next;
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
