package com.playposse.interviewprep.crackingthecodeinterview.recursion;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by thoma on 7/30/2018.
 */
public class Hanoi {

    @Test
    public void test0() {
        Stack<Integer> source = new Stack<>();
        source.push(1);
        solve(source, new Stack<Integer>(), new Stack<Integer>());
    }

    @Test
    public void test1() {
        Stack<Integer> source = new Stack<>();
        source.push(2);
        source.push(1);
        solve(source, new Stack<Integer>(), new Stack<Integer>());
    }

    @Test
    public void test2() {
        Stack<Integer> source = new Stack<>();
        source.push(3);
        source.push(2);
        source.push(1);
        solve(source, new Stack<Integer>(), new Stack<Integer>());
    }

    @Test
    public void test3() {
        Stack<Integer> source = new Stack<>();
        source.push(4);
        source.push(3);
        source.push(2);
        source.push(1);
        solve(source, new Stack<Integer>(), new Stack<Integer>());
    }

    @Test
    public void test4() {
        Stack<Integer> source = new Stack<>();
        source.push(5);
        source.push(4);
        source.push(3);
        source.push(2);
        source.push(1);
        solve(source, new Stack<Integer>(), new Stack<Integer>());
    }

    private static void solve(
            Stack<Integer> source,
            Stack<Integer> destination,
            Stack<Integer> buffer) {

        Printer printer = new Printer(source, destination, buffer);
        moveStack(source.size(), source, buffer, destination, printer);
        printer.print();
    }

    private static void moveStack(
            int n,
            Stack<Integer> source,
            Stack<Integer> destination,
            Stack<Integer> buffer, Printer printer) {

        if (n == 1) {
            printer.print();
            Integer disc = source.pop();
            if ((destination.size() > 0) && (destination.peek() <= disc)) {
                throw new IllegalStateException();
            }
            destination.push(disc);
            return;
        }

        moveStack(n - 1, source, buffer, destination, printer);

        moveStack(1, source, destination, buffer, printer);

        moveStack(n - 1, buffer, destination, source, printer);
    }

    private static class Printer {

        private Stack<Integer> source;
        private Stack<Integer> destination;
        private Stack<Integer> buffer;

        public Printer(Stack<Integer> source, Stack<Integer> destination, Stack<Integer> buffer) {
            this.source = source;
            this.destination = destination;
            this.buffer = buffer;

            System.out.println("\n\n====================================\n");
        }

        private void print() {
            int size = Math.max(source.size(), Math.max(destination.size(), buffer.size()));

            for (int i = size - 1; i >= 0; i--) {
                printDisc(i, source);
                printDisc(i, buffer);
                printDisc(i, destination);
                System.out.println();
            }
            System.out.println("------------");
        }

        private void printDisc(int i, Stack<Integer> stack) {
            if (i < stack.size()) {
                System.out.print(String.format("%1$2d  ", stack.get(i)));
            } else {
                System.out.print("    ");
            }
        }
    }
}
