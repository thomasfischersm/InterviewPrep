package com.playposse.interviewprep.crackingthecodeinterview.linkedlists;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Delete a node in the middle of a list (neither head nor tail). The trick is that you aren't given
 * the head of the linked list but only the particular node.
 */
public class DeleteMiddleNode {

    @Test
    public void test() {
        Node expectedList = createLinkedList(1, 2, 4, 5);
        Node inputList = createLinkedList(1, 2, 3, 4, 5);
        test(expectedList, inputList, inputList.next.next);
    }

    private void test(Node expectedList, Node inputList, Node removeNode) {
        removeNode(removeNode);
        assertTrue(compareLists(expectedList, inputList));
    }

    private void removeNode(Node node) {
        node.value = node.next.value;
        node.next = node.next.next;
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
