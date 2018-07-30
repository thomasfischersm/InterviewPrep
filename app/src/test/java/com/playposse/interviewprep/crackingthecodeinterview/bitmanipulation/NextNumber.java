package com.playposse.interviewprep.crackingthecodeinterview.bitmanipulation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/30/2018.
 */
public class NextNumber {

    @Test
    public void test() {
        test(0b10, -1, 0b1);
        test(0b100, 0b01, 0b10);
        test(0b101, -1, 0b11);
        test(0b110, 0b11, 0b101);
        test(0b1001, 0b101, 0b110);
        test(0b110011, 0b101101, 0b101110);
        test(0b101011, 0b11110, 0b100111);
    }

    private static void test(int expectedLarger, int expectedSmaller, int num) {
        System.out.println(
                Integer.toBinaryString(nextSmallest(num)) + " <- "
                + Integer.toBinaryString(num) + " -> "
                + Integer.toBinaryString(nextLargest(num)));
        assertEquals(expectedLarger, nextLargest(num));
        assertEquals(expectedSmaller, nextSmallest(num));
    }

    private static int nextLargest(int num) {
        int indexLastOne = -1;
        int oneCount = 0;

        for (int i = 0; i < Integer.BYTES * 8; i++) {
            int mask = 1 << i;
            int digit = ((num & mask) > 0) ? 1 : 0;

            if (digit == 0) {
                if (indexLastOne >= 0) {
                    num = num | (1 << i); // Set 1 at higher position.
                    // Set everything to zero on the right
                    num &= ~((1 << i) - 1);
                    // Add the 1's all the way on the right.
                    num |= ((1 << oneCount - 1) - 1);
                    return num;
                }
            }

            if (digit == 1) {
                indexLastOne = i;
                oneCount++;
            }
        }

        return -1;
    }

    private static int nextSmallest(int num) {
        int indexLastZero = -1;
        int zeroCount = 0;

        for (int i = 0; i < Integer.BYTES * 8; i++) {
            int mask = 1 << i;
            int digit = ((num & mask) > 0) ? 1 : 0;

            if (digit == 1) {
                if (indexLastZero >= 0) {
                    num = num & ~(1 << i); // Set current bit to zero
                    // Set bits to the left to 1.
                    num |= (1 << i) - 1;
                    // Set zeroes all the way on the right.
                    num &= ~(1 << (zeroCount - 1) - 1);
                    return num;
                }
            }

            if (digit == 0) {
                indexLastZero = i;
                zeroCount++;
            }
        }

        return -1;
    }
}
