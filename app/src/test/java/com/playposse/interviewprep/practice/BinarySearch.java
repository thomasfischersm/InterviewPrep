package com.playposse.interviewprep.practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/3/2018.
 */
public class BinarySearch {

    @Test
    public void test() {
        assertTest(new int[]{1, 3, 5}, 1, 0);
        assertTest(new int[]{1, 3, 5}, 3, 1);
        assertTest(new int[]{1, 3, 5}, 5, 2);
        assertTest(new int[]{1, 3, 5}, 4, -1);
        assertTest(new int[]{1, 3, 5}, 2, -1);

        assertTest(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 1999}, 1999, 8);

        assertTest(new int[]{1, 2}, 2, 1);
        assertTest(new int[]{1, 2}, 1, 0);
    }

    private static void assertTest(int[] sortedArray, int num, int expectedResult) {
        int actualResult = findIndex(sortedArray, num);
        assertEquals(expectedResult, actualResult);
    }


    public static int findIndex(int[] sortedArray, int num) {
        int minIndex = 0;
        int maxIndex = sortedArray.length;

        while (minIndex != maxIndex) {
            int i = (minIndex + maxIndex) / 2;
            System.out.println("Checking index: " + i);
            int currentNum = sortedArray[i];
            if (num == currentNum) {
                return i;
            } else if (num < currentNum) {
                maxIndex = i;
                System.out.println("Moving left ");
            } else {
                minIndex = i + 1;
                System.out.println("Moving right");
            }
        }

        return -1;
    }
}
