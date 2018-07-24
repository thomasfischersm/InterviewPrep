package com.playposse.interviewprep.crackingthecodeinterview.bitmanipulation;

import org.junit.Test;

/**
 * Created by thoma on 7/19/2018.
 */
public class Insertion {

    @Test
    public void test_insert() {
        insert(0b10000000000, 0b11, 0, 1);
        insert(0b10000000000, 0b11, 0, 2);
        insert(0b10000000000, 0b11, 0, 3);
        insert(0b10000000000, 0b11, 1, 3);
        insert(0b10000000000, 0b10011, 2, 7);
    }

    @Test
    public void test_getDigitCount() {
        getDigitCount(0);
        getDigitCount(1);
        getDigitCount(2);
        getDigitCount(3);
        getDigitCount(4);
        getDigitCount(-1);
        getDigitCount(-2);
        getDigitCount(-3);
    }

    /**
     * Returns how many digits the number has in binary form.
     */
    private static int getDigitCount(int num) {
        int digitCount = 0;
        for (int i = 0; i < 32; i++) {
            boolean hasDigit = (num & (1 << i)) > 0;
            if (hasDigit) {
                digitCount = i + 1;
            }
        }

        System.out.println(Integer.toString(num) + " is binary " + Integer.toBinaryString(num) + " and has " + digitCount + " digits.");

        return digitCount;
    }

    private static int insert(int number, int insert, int i, int j) {
        int result = number;
        for (int k = i; k < j; k++) {
            int sourceMask = 1 << k - i;
            boolean isDigitOne = (insert & sourceMask) > 0;

            int targetMask = 1 << k;
            if (isDigitOne) {
                result = result | targetMask;
            } else {
                result = result & ~targetMask;
            }
            System.out.println("k = " + k + " isDigitOne " + isDigitOne + " new number " + Integer.toBinaryString(result));
        }

        System.out.println("Starting with " + Integer.toBinaryString(number) + " and inserting " + Integer.toBinaryString(insert) + " to result in " + Integer.toBinaryString(result));

        return number;
    }
}
