package com.playposse.interviewprep.crackingthecodeinterview.stacksandqueues;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Implement a queue with two stacks.
 */
public class QueueImplementedByStacks {

    @Test
    public void test() {
        SpecialQueue<Integer> queue = new SpecialQueue<>();
        assertEquals(0, queue.getSize());

        queue.push(1);
        assertEquals(1, queue.getSize());
        assertEquals((Integer) 1, queue.peek());

        queue.push(2);
        assertEquals(2, queue.getSize());

        queue.push(3);
        assertEquals(3, queue.getSize());

        queue.push(4);
        assertEquals(4, queue.getSize());
        assertEquals((Integer) 1, queue.peek());

        assertEquals((Integer) 1, queue.pop());
        assertEquals(3, queue.getSize());

        assertEquals((Integer) 2, queue.pop());
        assertEquals(2, queue.getSize());

        assertEquals((Integer) 3, queue.pop());
        assertEquals(1, queue.getSize());

        assertEquals((Integer) 4, queue.pop());
        assertEquals(0, queue.getSize());
    }

    private static class SpecialQueue<T> {

        private final Stack<T> in = new Stack<>();
        private final Stack<T> out = new Stack<>();

        public void push(T data) {
            in.push(data);
        }

        public T pop() {
            if (out.size() != 0) {
                return out.pop();
            }

            shiftStacks();

            if (out.size() == 0) {
                throw new RuntimeException("No more elements left!");
            }

            return out.pop();
        }

        private void shiftStacks() {
            while (in.size() > 0) {
                out.push(in.pop());
            }
        }

        public T peek() {
            if (out.size() != 0) {
                return out.peek();
            }

            shiftStacks();

            if (out.size() == 0) {
                throw new RuntimeException("No more elements left!");
            }

            return out.peek();
        }

        public int getSize() {
            return in.size() + out.size();
        }
    }
}
