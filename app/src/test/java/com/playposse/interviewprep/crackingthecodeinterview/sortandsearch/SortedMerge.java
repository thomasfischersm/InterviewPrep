package com.playposse.interviewprep.crackingthecodeinterview.sortandsearch;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Merge two sorted arrays. Assume that a has enough extra space in the end to contain b.
 */
public class SortedMerge {

    @Test
    public void test() {
        test(new int[]{}, new int[]{}, new int[]{});

        test(new int[]{1}, new int[]{1}, new int[]{});
        test(new int[]{1}, new int[]{}, new int[]{1});

        test(new int[]{1, 2}, new int[]{1}, new int[]{2});
        test(new int[]{1, 2}, new int[]{2}, new int[]{1});

        test(new int[]{1, 2, 3}, new int[]{1}, new int[]{2, 3});
        test(new int[]{1, 2, 3}, new int[]{1, 3}, new int[]{2});
    }

    private static void test(int[] expectedResult, int[] a, int[] b) {
        int[] combined = new int[a.length + b.length];
        System.arraycopy(a, 0, combined, 0, a.length);

        merge(combined, b, a.length - 1, b.length - 1);
        assertArrayEquals(expectedResult, combined);
    }

    private static void merge(int[] a, int[] b, int aEndIndex, int bEndIndex) {
        int index = a.length - 1;
        while ((aEndIndex >= 0) && (bEndIndex >= 0)) {
            if (a[aEndIndex] >= b[bEndIndex]) {
                a[index--] = a[aEndIndex--];
            } else {
                a[index--] = b[bEndIndex--];
            }
        }

        while ((aEndIndex >= 0)) {
            a[index--] = a[aEndIndex--];

        }

        while ((bEndIndex >= 0)) {
            a[index--] = b[bEndIndex--];

        }
    }
}
