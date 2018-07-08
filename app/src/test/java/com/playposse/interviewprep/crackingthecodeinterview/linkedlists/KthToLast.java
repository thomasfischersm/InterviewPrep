package com.playposse.interviewprep.crackingthecodeinterview.linkedlists;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Return the kth to last element of a single linked list.
 */
public class KthToLast {

    @Test
    public void test() {
        test(-1, 10, 1);
        test(-1, 10);
        test(9, 0, 9);
        test(2, 2, 1,2,3,4);
        test(-1, 7, 1, 2, 3, 4, 5);
        test(7, 2, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    private void test(int expected, int k, int... values) {
        Node head = createLinkedList(values);
        int result = findKthLast(head, k);
        assertEquals(expected, result);
    }

    private int findKthLast(Node head, int k) {
        Node leader = head;
        Node trailer = null;

        while (leader != null) {
            if (k == 0) {
                if (trailer == null) {
                    trailer = head;
                } else {
                    trailer = trailer.next;
                }
            } else {
                k--;
            }

            leader = leader.next;
        }

        return (trailer != null) ? trailer.value : -1;
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
