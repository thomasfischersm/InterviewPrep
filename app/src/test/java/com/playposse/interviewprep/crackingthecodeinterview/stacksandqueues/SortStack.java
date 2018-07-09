package com.playposse.interviewprep.crackingthecodeinterview.stacksandqueues;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Create a stack, so that the smallest item is always on top.
 */
public class SortStack {

    @Test
    public void test() {
        SpecialStack stack = new SpecialStack();

        assertEquals(0, stack.size());

        stack.push(1);
        assertEquals(1, stack.size());
        assertEquals((Integer) 1, stack.peek());

        stack.push(2);
        assertEquals(2, stack.size());
        assertEquals((Integer) 1, stack.peek());

        stack.push(0);
        assertEquals(3, stack.size());
        assertEquals((Integer) 0, stack.peek());

        stack.push(3);
        assertEquals(4, stack.size());
        assertEquals((Integer) 0, stack.peek());

        assertEquals((Integer) 0, stack.pop());
        assertEquals((Integer) 1, stack.pop());
        assertEquals((Integer) 2, stack.pop());
        assertEquals((Integer) 3, stack.pop());
    }

    private static class SpecialStack extends Stack<Integer> {

        private final Stack<Integer> tmpStack = new Stack<>();

        @Override
        public Integer push(Integer item) {
            while (!isEmpty() && (item > peek())) {
                tmpStack.push(pop());
            }

            super.push(item);

            while (!tmpStack.isEmpty()) {
                super.push(tmpStack.pop());
            }

            return item;
        }
    }
}
