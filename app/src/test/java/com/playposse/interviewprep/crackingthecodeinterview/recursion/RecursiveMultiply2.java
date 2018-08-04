package com.playposse.interviewprep.crackingthecodeinterview.recursion;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/30/2018.
 */
public class RecursiveMultiply2 {

    @Test
    public void test() {
        assertEquals(5, multiply(1, 5));
        assertEquals(5, multiply(5, 1));
        assertEquals(10, multiply(5, 2));
        assertEquals(15, multiply(5, 3));
        assertEquals(25, multiply(5, 5));
        assertEquals(30, multiply(5, 6));
        assertEquals(36, multiply(6, 6));
    }

    private static int multiply(int a, int b) {
        try {
            if (b < a) {
                int swap = a;
                a = b;
                b = swap;
            }

            return multiplyRecursive(a, b);
        } finally {
            System.out.println();
        }
    }

    private static int multiplyRecursive(int a, int b) {
        if (a == 1) {
            return b;
        }

        int half = a / 2;
        int left = multiplyRecursive(half, b);
        int right = left;

        if (a % 2 == 1) {
            right += b;
        }

        return left + right;
    }
}
