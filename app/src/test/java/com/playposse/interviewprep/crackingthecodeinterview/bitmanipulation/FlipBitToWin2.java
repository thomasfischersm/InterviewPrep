package com.playposse.interviewprep.crackingthecodeinterview.bitmanipulation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/27/2018.
 */
public class FlipBitToWin2 {

    @Test
    public void test() {
        assertEquals(1, countOneStreakWithOneFlip(0b0));
        assertEquals(2, countOneStreakWithOneFlip(0b1));
        assertEquals(2, countOneStreakWithOneFlip(0b10));
        assertEquals(2, countOneStreakWithOneFlip(0b100));
        assertEquals(2, countOneStreakWithOneFlip(0b1000));
        assertEquals(2, countOneStreakWithOneFlip(0b1001));
        assertEquals(4, countOneStreakWithOneFlip(0b1011));
        assertEquals(5, countOneStreakWithOneFlip(0b11011));
        assertEquals(6, countOneStreakWithOneFlip(0b111011011));

        // -> Misses sign
        // -> Misses if bit was flipped on the other side of a sequence of 1s (backtracking)
        // -> Misses if there is a sequence of 1's all the way at the end with multiple 0's on the
        // right of it.
    }

    private static int countOneStreakWithOneFlip(int num) {
        System.out.println("\n\n\nStarting number " + Integer.toBinaryString(num));
        int maxStreak = 1;
        int currentStreak = 0;
        int previousStreak = -1;
        boolean hasPreviousZero = false; // If the streak has a zero in front of it.
        boolean isLastDigitZero = false; // If the previous digit was zero.

        for (int i = 0; i < 32; i++) {
            int mask = 1 << i;
            int digit = num & mask;

            switch (digit) {
                case 0:
                    if (!isLastDigitZero && currentStreak > 0) {
                        int possibleStreak = currentStreak + 1;
                        maxStreak = Math.max(maxStreak, possibleStreak);
                        previousStreak = currentStreak;
                        currentStreak = 0;
                    } else {
                        previousStreak = 0;
                    }
                    hasPreviousZero = true;
                    isLastDigitZero = true;
                    break;
                default:
                    currentStreak++;
                    int possibleStreak = currentStreak;
                    if (previousStreak > 0) {
                        possibleStreak += previousStreak + 1;
                    } else if (hasPreviousZero) {
                        possibleStreak++;
                    }
                    maxStreak = Math.max(possibleStreak, maxStreak);
                    isLastDigitZero = false;
                    break;
            }

            System.out.println("Digit: " + ((digit > 0) ? 1 : 0));
            System.out.println("\tmaxStreak: " + maxStreak);
            System.out.println("\tcurrentStreak: " + currentStreak);
            System.out.println("\tpreviousStreak: " + previousStreak);
            System.out.println("\thasPreviousZero: " + hasPreviousZero);
            System.out.println("\tisLastDigitZero: " + isLastDigitZero);
        }
        // deal with sign?

        return maxStreak;
    }
}
