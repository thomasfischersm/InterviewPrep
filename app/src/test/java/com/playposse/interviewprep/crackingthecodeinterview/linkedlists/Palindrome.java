package com.playposse.interviewprep.crackingthecodeinterview.linkedlists;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Check if a linked list is a palindrome.
 */
public class Palindrome {

    @Test
    public void test0() {
        assertTrue(isPalindrome(createLinkedList(1, 2, 1)));
    }

    @Test
    public void test1() {
        assertFalse(isPalindrome(createLinkedList(1, 2)));
    }

    @Test
    public void test2() {
        assertTrue(isPalindrome(createLinkedList(1, 2, 2, 1)));
    }

    @Test
    public void test3() {
        assertTrue(isPalindrome(createLinkedList()));
    }

    @Test
    public void test4() {
        assertFalse(isPalindrome(createLinkedList(1, 2, 2, 1, 3)));
    }

    @Test
    public void test5() {
        assertTrue(isPalindrome(createLinkedList(1, 2, 3, 4, 4, 3, 2, 1)));
    }

    private boolean isPalindrome(Node node) {
        if (node == null) {
            return true;
        }

        Stack<Integer> stack = new Stack<>();

        Node fastNode = node;
        Node slowNode = node;
        stack.push(slowNode.value);
        boolean isUneven = true;
        while (fastNode.next != null) {
            fastNode = fastNode.next;
            if (isUneven) {
                isUneven = false;
            } else {
                isUneven = true;
                slowNode = slowNode.next;
                stack.push(slowNode.value);
            }
        }

        if (!isUneven) {
            slowNode = slowNode.next;
        }

        while (!stack.isEmpty()) {
            int value = stack.pop();
            if (value != slowNode.value) {
                return false;
            } else {
                slowNode = slowNode.next;
            }
        }
        return (slowNode == null);
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
