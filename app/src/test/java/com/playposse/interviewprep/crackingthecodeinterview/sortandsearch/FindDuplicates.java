package com.playposse.interviewprep.crackingthecodeinterview.sortandsearch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by thoma on 8/6/2018.
 */
public class FindDuplicates {

    private static final int BIT_COUNT = Integer.BYTES * 8;

    @Test
    public void test() {
        assertTrue(findDuplicates(new int[]{}).isEmpty());
        assertTrue(findDuplicates(new int[]{1}).isEmpty());
        assertTrue(findDuplicates(new int[]{1, 2}).isEmpty());
        assertThat(findDuplicates(new int[]{1, 1}), is(Arrays.asList(1)));
        assertThat(findDuplicates(new int[]{1, 1, 2}), is(Arrays.asList(1)));
        assertThat(findDuplicates(new int[]{1, 1, 2, 2}), is(Arrays.asList(1, 2)));
        assertThat(findDuplicates(new int[]{1, 1, 2, 2, 3}), is(Arrays.asList(1, 2)));
        assertThat(findDuplicates(new int[]{1, 1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 14}), is(Arrays.asList(1, 2, 14)));
        assertThat(findDuplicates(new int[]{1, 1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 14, 15}), is(Arrays.asList(1, 2, 14)));
    }

    private static List<Integer> findDuplicates(int[] array) {
        int[] bits = new int[array.length / BIT_COUNT + 1];

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            int val = array[i];
            if (getBit(bits, val)) {
                result.add(val);
            } else {
                setBit(bits, val, true);
            }
        }

        return result;
    }

    private static boolean getBit(int[] bits, int position) {
        int b = bits[position / BIT_COUNT];
        int bitPosition = position % BIT_COUNT;
        return (b & (1 << bitPosition)) > 0;
    }

    private static void setBit(int[] bits, int position, boolean value) {
        int b = bits[position / BIT_COUNT];
        int bitPosition = position % BIT_COUNT;
        if (value) {
            b = b | (1 << bitPosition);
        } else {
            b = b & ~(1 << bitPosition);
        }
        bits[position / BIT_COUNT] = b;
    }
}
