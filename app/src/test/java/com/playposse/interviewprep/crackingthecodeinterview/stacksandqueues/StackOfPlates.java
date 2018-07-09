package com.playposse.interviewprep.crackingthecodeinterview.stacksandqueues;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Implement a stack with multiple stacks. Each internal stack should have a max capacity at which
 * point a new stack is started.
 */
public class StackOfPlates {

    @Test
    public void test() {
        SpecialStack<Integer> stack = new SpecialStack<>(2);

        assertEquals(0, stack.size());

        stack.push(1);
        assertEquals((Integer) 1, stack.peek());
        assertEquals(1, stack.size());

        stack.push(2);
        assertEquals((Integer) 2, stack.peek());
        assertEquals(2, stack.size());

        assertEquals((Integer) 2, stack.pop());
        assertEquals((Integer) 1, stack.peek());
        assertEquals(1, stack.size());

        stack.push(3);
        assertEquals((Integer) 3, stack.peek());
        assertEquals(2, stack.size());

        stack.push(4);
        assertEquals((Integer) 4, stack.peek());
        assertEquals(3, stack.size());

        stack.push(5);
        assertEquals((Integer) 5, stack.peek());
        assertEquals(4, stack.size());

        assertEquals((Integer) 3, stack.popAt(0));
        assertEquals((Integer) 5, stack.peek());
        assertEquals(3, stack.size());

        assertEquals((Integer) 1, stack.popAt(0));
        assertEquals((Integer) 5, stack.peek());
        assertEquals(2, stack.size());

        assertEquals((Integer) 5, stack.pop());
        assertEquals((Integer) 4, stack.peek());
        assertEquals(1, stack.size());

        assertEquals((Integer) 4, stack.pop());
        assertEquals(0, stack.size());

        stack.push(6);
        assertEquals((Integer) 6, stack.peek());
        assertEquals(1, stack.size());
    }

    public class SpecialStack<T> {

        private final int threshold;

        private List<Stack<T>> stacks = new ArrayList<>();

        public SpecialStack(int threshold) {
            this.threshold = threshold;

            stacks.add(new Stack<T>());
        }

        public void push(T data) {
            if (stacks.get(stacks.size() - 1).size() == threshold) {
                stacks.add(new Stack<T>());
            }

            stacks.get(stacks.size() - 1).push(data);
        }

        public T popAt(int stackIndex) {
            if ((stackIndex < 0) || (stackIndex >= stacks.size())) {
                throw new IllegalArgumentException("out of range!");
            }

            Stack<T> stack = stacks.get(stackIndex);
            T data = stack.pop();

            if (stack.size() == 0) {
                stacks.remove(stackIndex);
            }

            return data;
        }

        public T pop() {
            Stack<T> lastStack = stacks.get(stacks.size() - 1);
            T data = lastStack.pop();

            if ((lastStack.size() == 0) && (stacks.size() > 1)) {
                stacks.remove(stacks.size() - 1);
            }

            return data;
        }

        public T peek() {
            return stacks.get(stacks.size() - 1).peek();
        }

        public int size() {
            int size = 0;
            for (Stack<T> stack : stacks) {
                size += stack.size();
            }
            return size;
        }
    }
}
