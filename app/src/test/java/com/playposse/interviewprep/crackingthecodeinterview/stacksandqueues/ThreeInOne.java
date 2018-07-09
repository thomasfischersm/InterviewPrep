package com.playposse.interviewprep.crackingthecodeinterview.stacksandqueues;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Implement three stacks with a single array.
 */
public class ThreeInOne {

    @Test
    public void test() {
        SpecialStack stack = new SpecialStack(2);

        assertTrue(stack.isEmpty(0));
        assertTrue(stack.isEmpty(1));
        assertTrue(stack.isEmpty(2));

        stack.push(0, 1);
        stack.push(0, 2);
        stack.push(1, 3);
        stack.push(1, 4);
        stack.push(2, 5);
        stack.push(2, 6);

        assertEquals(2, stack.sizes[0]);
        assertEquals(2, stack.sizes[1]);
        assertEquals(2, stack.sizes[2]);

        assertFalse(stack.isEmpty(0));
        assertFalse(stack.isEmpty(1));
        assertFalse(stack.isEmpty(2));

        assertEquals(2, stack.peek(0));
        assertEquals(4, stack.peek(1));
        assertEquals(6, stack.peek(2));

        assertEquals(1, stack.array[0]);
        assertEquals(2, stack.array[1]);
        assertEquals(3, stack.array[2]);
        assertEquals(4, stack.array[3]);
        assertEquals(5, stack.array[4]);
        assertEquals(6, stack.array[5]);

        assertEquals(2, stack.sizes[0]);
        assertEquals(2, stack.sizes[1]);
        assertEquals(2, stack.sizes[2]);

        assertEquals(2, stack.pop(0));
        assertEquals(4, stack.pop(1));
        assertEquals(6, stack.pop(2));

        stack.push(0, 7);
        stack.push(1, 8);
        stack.push(2, 9);

        assertEquals(7, stack.pop(0));
        assertEquals(1, stack.pop(0));
        assertEquals(8, stack.pop(1));
        assertEquals(3, stack.pop(1));
        assertEquals(9, stack.pop(2));
        assertEquals(5, stack.pop(2));

        assertTrue(stack.isEmpty(0));
        assertTrue(stack.isEmpty(1));
        assertTrue(stack.isEmpty(2));
    }

    private static class SpecialStack {

        private final int capacity;
        private final int[] array;
        private final int[] sizes = new int[]{0, 0, 0};

        private SpecialStack(int capacity) {
            this.capacity = capacity;

            array = new int[capacity * 3];
        }

        private void push(int stack, int value) {
            if (isFull(stack)) {
                throw new RuntimeException("Stack is full!");
            }

            int tailIndex = getTailIndex(stack) + 1;
            array[tailIndex] = value;
            sizes[stack]++;
        }

        private int peek(int stack) {
            int tailIndex = getTailIndex(stack);
            if (tailIndex <= -1) {
                throw new RuntimeException("Stack is already empty!");
            }

            return array[tailIndex];
        }

        private int pop(int stack) {
            int value = peek(stack);
            sizes[stack]--;
            return value;
        }

        private boolean isEmpty(int stack) {
            return sizes[stack] == 0;
        }

        private int getTailIndex(int stack) {
            return capacity * stack + sizes[stack] - 1;
        }

        private boolean isFull(int stack) {
            return (sizes[stack] == capacity);
        }
    }
}
