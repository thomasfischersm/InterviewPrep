package com.playposse.interviewprep.crackingthecodeinterview.linkedlists;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Remove duplicate values from a linked list.
 */
public class RemoveDups {

    @Test
    public void test() {
        test(createLinkedList(1, 2, 3, 4, 5), createLinkedList(1, 2, 2, 3, 2, 2, 3, 1, 4, 5));
        test(createLinkedList(1, 2, 3), createLinkedList(1, 2, 3));
        test(createLinkedList(1, 2, 3), createLinkedList(1, 1, 1, 1, 1, 1, 2, 3));
        test(createLinkedList(1, 2, 3), createLinkedList(1, 2, 2, 3));
        test(createLinkedList(), createLinkedList());
        test(createLinkedList(1), createLinkedList(1));
    }

    private void test(Node expectedHead, Node inputHead) {
        System.out.println("\nstart test");
        removeDupsWithoutHash(inputHead);
        assertTrue(compareLists(expectedHead, inputHead));
    }

    private static Node removeDupsWithHash(Node head) {
        if ((head == null) || (head.next == null)) {
            return head;
        }

        Set<Integer> values = new HashSet<>();

        Node node = head;
        values.add(head.value);
        while (node.next != null) {
            if (values.contains(node.next.value)) {
                node.next = node.next.next;
                continue;
            } else {
                values.add(node.next.value);
            }

            node = node.next;
        }

        return head;
    }

    private static Node removeDupsWithoutHash(Node head) {
        if ((head == null) || (head.next == null)) {
            return head;
        }

        Node node = head;
        while (node != null) {
            int value = node.value;

            Node previous = node;
            Node runner = node.next;
            while (runner != null) {
                if (runner.value == value) {
                    previous.next = runner.next;
                } else {
                    previous = runner;
                }
                runner = runner.next;
            }

            node = node.next;
        }

        return head;
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

    private static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
