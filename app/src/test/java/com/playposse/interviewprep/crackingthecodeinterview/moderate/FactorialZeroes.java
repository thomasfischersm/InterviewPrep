package com.playposse.interviewprep.crackingthecodeinterview.moderate;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 8/8/2018.
 */
public class FactorialZeroes {

    @Test
    public void test() {
        assertEquals(0, countTrailingZeroes(-1));
        assertEquals(0, countTrailingZeroes(0));
        assertEquals(0, countTrailingZeroes(1));
        assertEquals(0, countTrailingZeroes(4));
        assertEquals(1, countTrailingZeroes(5));
        assertEquals(1, countTrailingZeroes(6));
        assertEquals(1, countTrailingZeroes(9));
        assertEquals(2, countTrailingZeroes(10));
        assertEquals(2, countTrailingZeroes(11));
        assertEquals(3, countTrailingZeroes(15));
        assertEquals(4, countTrailingZeroes(20));
        assertEquals(6, countTrailingZeroes(25));
        assertEquals(7, countTrailingZeroes(30));
        assertEquals(8, countTrailingZeroes(35));
        assertEquals(9, countTrailingZeroes(40));
        assertEquals(10, countTrailingZeroes(45));
        assertEquals(12, countTrailingZeroes(50));
        assertEquals(28, countTrailingZeroes(124));
        assertEquals(31, countTrailingZeroes(125));
        assertEquals(31, countTrailingZeroes(126));
        assertEquals(37, countTrailingZeroes(150));
    }

    private static int countTrailingZeroes(int n) {
        int count = 0;

        for (int i = 5; i <= n; i = i * 5) {
            count += n / i;
        }

        return count;
    }
}
