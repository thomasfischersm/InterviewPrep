package com.playposse.interviewprep.crackingthecodeinterview.sortandsearch;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Search for a number in a sorted array that has been rotated an unknown number of times.
 */
public class SearchRotatedArray {

    @Test
    public void test() {
        assertEquals(-1, findIndex(null, 5));
        assertEquals(-1, findIndex(new int[]{4}, 5));
        assertEquals(0, findIndex(new int[]{5}, 5));

        assertEquals(1, findIndex(new int[]{1, 5}, 5));
        assertEquals(0, findIndex(new int[]{5, 1}, 5));
        assertEquals(-1, findIndex(new int[]{4, 1}, 5));
        assertEquals(-1, findIndex(new int[]{1, 4}, 5));

        assertEquals(-1, findIndex(new int[]{1, 2, 3}, 0));
        assertEquals(0, findIndex(new int[]{1, 2, 3}, 1));
        assertEquals(1, findIndex(new int[]{1, 2, 3}, 2));
        assertEquals(2, findIndex(new int[]{1, 2, 3}, 3));

        assertEquals(-1, findIndex(new int[]{3, 1, 2}, 0));
        assertEquals(1, findIndex(new int[]{3, 1, 2}, 1));
        assertEquals(2, findIndex(new int[]{3, 1, 2}, 2));
        assertEquals(0, findIndex(new int[]{3, 1, 2}, 3));

        assertEquals(-1, findIndex(new int[]{2, 3, 1}, 0));
        assertEquals(2, findIndex(new int[]{2, 3, 1}, 1));
        assertEquals(0, findIndex(new int[]{2, 3, 1}, 2));
        assertEquals(1, findIndex(new int[]{2, 3, 1}, 3));

        assertEquals(2, findIndex(new int[]{2, 2, 1}, 1));
        assertEquals(2, findIndex(new int[]{2, 2, 3}, 3));

        assertEquals(3, findIndex(new int[]{2, 2, 2, 1}, 1));
        assertEquals(3, findIndex(new int[]{2, 2, 2, 3}, 3));

        assertEquals(4, findIndex(new int[]{2, 2, 2, 2, 1}, 1));
        assertEquals(4, findIndex(new int[]{2, 2, 2, 2, 3}, 3));
    }

    private static int findIndex(int[] array, int number) {
        if (array == null) {
            return -1;
        }

        if (array.length == 1) {
            return (array[0] == number) ? 0 : -1;
        }

        int lowerBound = 0;
        int upperBound = array.length - 1;
        int startNumber = array[0];

        while (lowerBound <= upperBound) {
            int midIndex = (lowerBound + upperBound) / 2;
            int mid = array[midIndex];

            if (mid == number) {
                return midIndex;
            }

            if (startNumber <= mid) { // special case for equal
                // Normal order
                if ((number < mid) && (number >= startNumber)) {
                    upperBound = midIndex - 1;
                } else {
                    lowerBound = midIndex + 1;
                }
            } else {
                if ((number > mid) && (number < startNumber)) {
                    lowerBound = midIndex + 1;
                } else {
                    upperBound = midIndex - 1;
                }
            }
        }
        return -1;
    }
}
