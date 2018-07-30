package com.playposse.interviewprep.crackingthecodeinterview.bitmanipulation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/30/2018.
 */
public class PairwiseSwap {

    @Test
    public void test() {
        assertEquals(0b1010, swap(0b101));
        assertEquals(0b1011, swap(0b111));
        assertEquals(0b101, swap(0b1010));
    }

    private static int swap(int num) {
        return ((num & 0x55555555) << 1) | ((num & 0xAAAAAAAA) >>>1);
    }
}
