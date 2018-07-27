package com.playposse.interviewprep.facebookinterview;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by thoma on 7/24/2018.
 */
public class ScratchPadForInterview {

    @Test
    public void test() {
        assertNull(hasSumOfZero(new int[]{}));
        assertNull(hasSumOfZero(new int[]{1}));
        assertNull(hasSumOfZero(new int[]{1, 2}));
        assertNull(hasSumOfZero(new int[]{1, 2, 3}));

        assertNull(hasSumOfZero(new int[]{-10, -5, -8}));
        assertNull(hasSumOfZero(new int[]{-10, -5, -8, 6, 10}));

        assertNotNull(hasSumOfZero(new int[]{-4, 1, 3}));
        assertNotNull(hasSumOfZero(new int[]{-4, 1, 6, -20, 3}));
        assertNull(hasSumOfZero(new int[]{-14, 1, 6, -20, 3, 1, 1, 20}));
        assertNotNull(hasSumOfZero(new int[]{-14, 1, 6, -20, 3, 1, 1, 20, 0}));

        assertNotNull(hasSumOfZero(new int[]{-1000, -999, 1999}));
        assertNotNull(hasSumOfZero(new int[]{0, 0, 0}));
        assertNotNull(hasSumOfZero(new int[]{-1, 0, 1}));
        assertNotNull(hasSumOfZero(new int[]{-1, -1, 2}));
        assertNotNull(hasSumOfZero(new int[]{-1, -1, 0, 0, 0, 0, 2}));
    }

    public int[] hasSumOfZero(int[] array) {
        if (array.length < 3) {
            return null;
        }

        Arrays.sort(array);

        for (int i = 0; i < array.length - 2; i++) {
            int num0 = array[i];
            if (num0 + array[i + 1] + array[i + 2] > 0) {
                // Exit because no small enough complement can be found.
                return null;
            }

            // The complement is walked from the large numbers. Except for the 0 case, the
            // complement has to be positive enough to balance the negative partial sum of the first
            // two numbers. As the first two loops increase, the complement has to decrease.
            int k = array.length - 1;
            for (int j = i + 1; j < array.length - 1; j++) {
                int num1 = array[j];
                if (num0 + num1 + array[array.length - 1] < 0) {
                    // Early exit because no large enough complement can be found.
                    continue;
                }

                while (j < k) { // Walk pointer from right to left to avoid starting over with binary search each time.
                    int num2 = array[k];

                    int sum = num0 + num1 + num2;
                    if (sum == 0) {
                        return new int[]{num0, num1, num2};
                    }
                    if (num0 + num1 + array[k - 1] >= 0) {
                        k--;
                    } else {
                        break;
                    }
                }
            }
        }

        return null;
    }
}
