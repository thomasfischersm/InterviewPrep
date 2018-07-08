package com.playposse.interviewprep.crackingthecodeinterview.linkedlists;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/8/2018.
 */
public class FindIntersection {

    @Test
    public void test0() {
        Node headA = createLinkedList(1, 2, 3, 4, 5);
        Node headB = headA.next;
        assertEquals(headB, findIntersection(headA, headB));
    }

    @Test
    public void test1() {
        Node headA = createLinkedList(1, 2, 3, 4, 5);
        Node headB = new Node(-1);
        headB.next = headA.next;
        assertEquals(headB.next, findIntersection(headA, headB));
    }

    @Test
    public void test2() {
        Node headA = createLinkedList(1, 2, 3, 4, 5);
        assertEquals(headA, findIntersection(headA, headA));
    }

    @Test
    public void test3() {
        Node headA = createLinkedList(1, 2, 3, 4, 5);
        Node headB = headA.next.next.next.next;
        assertEquals(headB, findIntersection(headA, headB));
    }

    @Test
    public void test4() {
        Node headA = createLinkedList(1, 2, 3, 4, 5);
        Node headB = createLinkedList(6, 7, 8, 9, 10, 11);
        headB.next.next.next.next.next = headA.next;
        assertEquals(headA.next, findIntersection(headA, headB));
    }

    private static Node findIntersection(Node headA, Node headB) {
        if ((headA == null) || (headB == null)) {
            return null;
        }

        if (headA == headB) {
            return headA;
        }

        int lengthA = 1;
        int lengthB = 1;
        Node tailA = null;
        Node tailB = null;

        // Find length.
        Node nodeA = headA;
        while (nodeA.next != null) {
            lengthA++;
            nodeA = nodeA.next;
        }
        tailA = nodeA;

        Node nodeB = headB;
        while (nodeB.next != null) {
            lengthB++;
            nodeB = nodeB.next;
        }
        tailB = nodeB;

        if (tailA != tailB) {
            return null;
        }

        nodeA = headA;
        nodeB = headB;
        while ((nodeA != null)) {
            if ((lengthA == lengthB) && (nodeA == nodeB)) {
                return nodeA;
            }

            if (lengthA >= lengthB) {
                nodeA = nodeA.next;
                lengthA--;
            }

            if (lengthB >= lengthA + 1) {
                nodeB = nodeB.next;
                lengthB--;
            }
        }

        throw new IllegalStateException();
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
