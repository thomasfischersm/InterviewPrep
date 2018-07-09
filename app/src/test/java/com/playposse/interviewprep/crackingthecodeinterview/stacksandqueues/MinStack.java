package com.playposse.interviewprep.crackingthecodeinterview.stacksandqueues;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Create a special stack, that keeps track of the min value. All operations (push, pop, peek, and
 * min) should be constant time.
 */
public class MinStack {

    @Test
    public void test() {
        SpecialStack stack = new SpecialStack();

        stack.push(10);
        assertEquals(10, stack.min());

        stack.push(11);
        assertEquals(10, stack.min());

        stack.push(9);
        assertEquals(9, stack.min());

        stack.push(9);
        assertEquals(9, stack.min());

        stack.push(8);
        assertEquals(8, stack.min());

        stack.pop();
        assertEquals(9, stack.min());

        stack.pop();
        assertEquals(9, stack.min());

        stack.pop();
        assertEquals(10, stack.min());

        stack.pop();
        assertEquals(10, stack.min());
    }

    public static class SpecialStack extends Stack<Integer> {

        private Stack<Integer> minStack = new Stack<>();

        public void push(int data) {
            if (data <= min()) {
                minStack.push(data);
            }
            super.push(data);
        }

        public Integer pop() {
            Integer value = super.pop();

            if (value == min()) {
                minStack.pop();
            }

            return value;
        }

        public int min() {
            if (minStack.isEmpty()) {
                return Integer.MAX_VALUE;
            } else {
                return minStack.peek();
            }
        }
    }
}
