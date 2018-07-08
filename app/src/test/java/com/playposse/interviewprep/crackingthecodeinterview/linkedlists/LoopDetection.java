package com.playposse.interviewprep.crackingthecodeinterview.linkedlists;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * A linked list is given. Detect if it has a loop. If so, return the first node of the loop.
 */
public class LoopDetection {

    @Test
    public void test0() {
        Node head = createLinkedList(1, 2, 3, 4, 5, 6);
        setTail(head, head.next.next);
        System.out.println(detectLoop(head).value);
        assertEquals(head.next.next, detectLoop(head));
    }

    @Test
    public void test1() {
        Node head = createLinkedList(1, 2, 3, 4, 5, 6);
        setTail(head, head);
        System.out.println(detectLoop(head).value);
        assertEquals(head, detectLoop(head));
    }

    @Test
    public void test2() {
        Node head = createLinkedList(1, 2, 3, 4, 5, 6);
        System.out.println(detectLoop(head));
        assertNull(detectLoop(head));
    }

    @Test
    public void test3() {
        Node head = createLinkedList(1);
        System.out.println(detectLoop(head));
        assertNull(detectLoop(head));
    }

    @Test
    public void test4() {
        Node head = createLinkedList();
        System.out.println(detectLoop(head));
        assertNull(detectLoop(head));
    }

    @Test
    public void test5() {
        Node head = createLinkedList(1);
        setTail(head, head);
        System.out.println(detectLoop(head).value);
        assertEquals(head, detectLoop(head));
    }

    private static Node detectLoop(Node head) {
        if ((head == null) || (head.next == null)) {
            return null;
        }

        Node fastNode = head.next.next;
        Node slowNode = head.next;
        int count = 1;

        while (fastNode != slowNode) {
            if ((fastNode == null) || (fastNode.next == null)) {
                return null;
            }

            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            count++;
        }

        int loopSize = count;

        Node thirdNode = head;
        while (thirdNode != fastNode) {
            thirdNode = thirdNode.next;
            fastNode = fastNode.next;
        }
        return fastNode;
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

    private static void setTail(Node head, Node addedNode) {
        Node node = head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = addedNode;
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
