package com.playposse.interviewprep.crackingthecodeinterview.arraysandstrings;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Check if string a is a permutation of string b.
 */
public class IsStringPermutation {

    @Test
    public void test() {
        assertTrue(isPermutation("", ""));
        assertTrue(isPermutation("abc", "abc"));
        assertTrue(isPermutation("abcabc", "abcabc"));
        assertTrue(isPermutation("abc", "bca"));
        assertTrue(isPermutation("abcabc", "bcaabc"));
        assertFalse(isPermutation("abc", "bcaa"));
        assertFalse(isPermutation("abcabc", "bcaaabc"));
        assertFalse(isPermutation("abc", "def"));
    }

    public static boolean isPermutation(String a, String b) {
        if ((a == null) || (b == null) || (a.length() != b.length())) {
            return false;
        }

        int charSetSize = 128;
        int[] charCount = new int[charSetSize];

        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);

            if (c >= charSetSize) {
                throw new IllegalArgumentException();
            }

            charCount[c]++;
        }

        for (int i = 0; i < b.length(); i++) {
            char c = b.charAt(i);

            if (c >= charSetSize) {
                throw new IllegalArgumentException();
            }

            if (charCount[c] == 0) {
                return false;
            }

            charCount[c]--;
        }

        // Unneeded!
        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }
}
