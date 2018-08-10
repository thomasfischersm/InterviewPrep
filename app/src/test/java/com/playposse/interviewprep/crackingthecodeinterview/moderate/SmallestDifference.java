package com.playposse.interviewprep.crackingthecodeinterview.moderate;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 8/8/2018.
 */
public class SmallestDifference {

    @Test
    public void test() {
        assertEquals(1, findSmallestDifference(new int[]{1, 2, 11, 15}, new int[]{4, 12, 19, 23, 127, 235}));

        assertEquals(Integer.MAX_VALUE, findSmallestDifference(new int[]{}, new int[]{}));
        assertEquals(Integer.MAX_VALUE, findSmallestDifference(new int[]{}, new int[]{1}));
        assertEquals(0, findSmallestDifference(new int[]{1}, new int[]{1}));
        assertEquals(1, findSmallestDifference(new int[]{1}, new int[]{2}));
        assertEquals(1, findSmallestDifference(new int[]{2}, new int[]{1}));
        assertEquals(1, findSmallestDifference(new int[]{1, 10, 100, 1000}, new int[]{999}));
    }

    private static int findSmallestDifference(int[] array0, int[] array1) {
        Arrays.sort(array0);
        Arrays.sort(array1);

        int difference = Integer.MAX_VALUE;
        int index0 = 0;
        int index1 = 0;

        while ((index0 < array0.length) && (index1 < array1.length)) {
            int currDiff = Math.abs(array0[index0] - array1[index1]);
            difference = Math.min(currDiff, difference);

            if (array0[index0] < array1[index1]) {
                index0++;
            } else {
                index1++;
            }
        }
        return difference;
    }
}
