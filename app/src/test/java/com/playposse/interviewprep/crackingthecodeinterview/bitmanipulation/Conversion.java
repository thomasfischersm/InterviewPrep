package com.playposse.interviewprep.crackingthecodeinterview.bitmanipulation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/30/2018.
 */
public class Conversion {

    @Test
    public void test() {
        test(0, 0b1, 0b1);
        test(1, 0b1, 0b0);
        test(2, 0b1, 0b10);
        test(1, 0b11, 0b10);
        test(4, 0b1001, 0b110);
        test(0, 0b1010101, 0b1010101);
        test(6, 0b111000, 0b111);
        test(11, 0b1010101010, 0b10101010101);
        test(0, 0b0, 0b0);
    }

    private static void test(int expected, int a, int b) {
        assertEquals(expected, countBitDifferent(a, b));
        assertEquals(expected, countBitDifferent2(a, b));
        assertEquals(expected, countBitDifferent3(a, b));
    }

    private static int countBitDifferent(int a, int b) {
        int diffCount = 0;
        for (int i = 0; i < Integer.BYTES * 8; i++) {
            int mask = 1 << i;
            int aBit = a & mask;
            int bBit = b & mask;
            if (aBit != bBit) {
                diffCount++;
            }
        }
        return diffCount;
    }

    private static int countBitDifferent2(int a, int b) {
        int count = 0;
        for (int c = a ^ b; c > 0; c >>>= 1) {
            if ((c & 1) == 1) {
                count++;
            }
        }
        return count;
    }

    private static int countBitDifferent3(int a, int b) {
        int count = 0;
        for (int c = a ^ b; c > 0; c = c & (c - 1)) {
            count++;
        }
        return count;
    }
}
