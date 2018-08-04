package com.playposse.interviewprep.crackingthecodeinterview.recursion;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/30/2018.
 */
public class MagicIndex {

    @Test
    public void test() {
        assertEquals(0, findMagicIndex(new int[]{0}));
        assertEquals(Integer.MIN_VALUE, findMagicIndex(new int[]{}));
        assertEquals(Integer.MIN_VALUE, findMagicIndex(new int[]{1}));
        assertEquals(Integer.MIN_VALUE, findMagicIndex(new int[]{-1}));
        assertEquals(1, findMagicIndex(new int[]{-1, 1}));
        assertEquals(2, findMagicIndex(new int[]{-1, 0, 2}));
        assertEquals(3, findMagicIndex(new int[]{-1, 0, 1, 3, 5, 6, 7}));
        assertEquals(9, findMagicIndex(new int[]{-10, -9,-1, 0, 1, 3, 5, 6, 7, 9, 11, 500}));
    }

    private static int findMagicIndex(int[] array) {
        int lowerIndex = 0;
        int upperIndex = array.length - 1;

        while (lowerIndex <= upperIndex) {
            int midIndex = (upperIndex + lowerIndex) / 2;
            if (array[midIndex] == midIndex) {
                return midIndex;
            }
            if (array[midIndex] > midIndex) {
                upperIndex = midIndex - 1;
            } else {
                lowerIndex = midIndex + 1;
            }
        }
        return Integer.MIN_VALUE;
    }
}
