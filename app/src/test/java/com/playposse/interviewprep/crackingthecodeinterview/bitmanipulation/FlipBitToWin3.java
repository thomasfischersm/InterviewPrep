package com.playposse.interviewprep.crackingthecodeinterview.bitmanipulation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is the book solution. I copied it because I suspect that there is a bug in it, and I want to
 * try it out.
 */
public class FlipBitToWin3 {

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

    private static int countOneStreakWithOneFlip(int a) {
        if (~a == 0) return Integer.BYTES *8;

        int currentLength = 0;
        int previousLength = 0;
        int maxLength = 1;
        while (a != 0) {
            if ((a & 1) == 1) {
                currentLength++;
            } else if ((a & 1) == 0) {
                previousLength = (a & 2) == 0 ? 0 : currentLength;
                currentLength = 0;
            }
            maxLength = Math.max(previousLength + currentLength + 1, maxLength);
            a >>>= 1;
        }
        return maxLength;
    }
}
