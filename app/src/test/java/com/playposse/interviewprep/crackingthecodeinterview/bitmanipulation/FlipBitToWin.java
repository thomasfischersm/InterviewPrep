package com.playposse.interviewprep.crackingthecodeinterview.bitmanipulation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/27/2018.
 */
public class FlipBitToWin {

    @Test
    public void test() {
        assertEquals(0, countOneStreakWithOneFlip(0b0));
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
        int maxStreak = -1;
        int currentStreak = 0;
        boolean isLookingForOne = true;
        boolean hasCarry = false;

        for (int i = 0; i < 32; i++) {
            int mask = 1 << i;
            int digit = num & mask;

            if (isLookingForOne) {
                if (digit > 0) {
                    currentStreak = 1;
                    isLookingForOne = false;
                    hasCarry = true;
                }
            } else {
                if (digit > 0) {
                    currentStreak++;
                } else if (hasCarry) {
                    hasCarry = false;
                    currentStreak++;
                } else {
                    maxStreak = Math.max(maxStreak, currentStreak);
                    isLookingForOne = true;
                }
            }
        }
        // deal with sign?

        maxStreak = Math.max(maxStreak, currentStreak);
        return maxStreak;
    }
}
