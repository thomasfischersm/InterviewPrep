package com.playposse.interviewprep.crackingthecodeinterview.sortandsearch;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 8/6/2018.
 */
public class SearchSparseArray {

    @Test
    public void test() {
        assertEquals(-1, findIndex(new String[]{}, "abc"));
        assertEquals(0, findIndex(new String[]{"abc"}, "abc"));
        assertEquals(-1, findIndex(new String[]{"def"}, "abc"));
        assertEquals(1, findIndex(new String[]{"", "abc"}, "abc"));
        assertEquals(0, findIndex(new String[]{"abc", ""}, "abc"));
        assertEquals(1, findIndex(new String[]{"", "abc", ""}, "abc"));
        assertEquals(2, findIndex(new String[]{"", "", "abc", "", ""}, "abc"));
        assertEquals(3, findIndex(new String[]{"aaa", "", "", "abc", "", "", "ddd","", "eee"}, "abc"));
    }

    private static int findIndex(String[] array, String str) {
        int lowerBound = 0;
        int upperBound = array.length - 1;

        while (lowerBound <= upperBound) {
            int midIndex = (lowerBound + upperBound) / 2;
            int left = midIndex;
            int right = midIndex + 1;
            while ((left >= lowerBound) && (right <= upperBound)) {
                if (!array[left].isEmpty()) {
                    midIndex = left;
                    break;
                }
                if (!array[right].isEmpty()) {
                    midIndex = right;
                    break;
                }
                if (left > lowerBound) {
                    left--;
                }
                if (right < upperBound) {
                    right++;
                }
            }

            int comp = str.compareTo(array[midIndex]);
            if (comp < 0) {
                upperBound = midIndex - 1;
            } else if (comp > 0) {
                lowerBound = midIndex + 1;
            } else {
                return midIndex;
            }
        }
        return -1;
    }
}
