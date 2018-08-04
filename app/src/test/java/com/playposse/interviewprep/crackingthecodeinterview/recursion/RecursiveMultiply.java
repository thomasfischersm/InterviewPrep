package com.playposse.interviewprep.crackingthecodeinterview.recursion;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/30/2018.
 */
public class RecursiveMultiply {

    @Test
    public void test() {
        assertEquals(5, multiply(1, 5));
        assertEquals(5, multiply(5, 1));
        assertEquals(10, multiply(5, 2));
        assertEquals(15, multiply(5, 3));
        assertEquals(25, multiply(5, 5));
        assertEquals(30, multiply(5, 6));
        assertEquals(36, multiply(6, 6));
    }

    private static int multiply(int a, int b) {
        try {
            if (b < a) {
                int swap = a;
                a = b;
                b = swap;
            }

            HashMap<Integer, Integer> cache = new HashMap<>();
            cache.put(1, b);
            return multiply(a, b, cache);
        } finally {
            System.out.println();
        }
    }

    private static int multiply(int a, int b, Map<Integer, Integer> cache) {
        if (cache.containsKey(a)) {
            return cache.get(a);
        }

        int powerOfTwo = 1;
        while (powerOfTwo < a) {
            powerOfTwo <<= 1;
        }

        powerOfTwo >>>= 1;
        int complement = a - powerOfTwo;
        System.out.println("try: " + a + " from " + powerOfTwo + " + " + complement);
        int result = multiply(powerOfTwo, b, cache) + multiply(complement, b, cache);
        cache.put(a, result);
        return result;
    }
}
