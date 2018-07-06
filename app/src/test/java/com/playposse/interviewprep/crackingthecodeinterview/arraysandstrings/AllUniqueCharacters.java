package com.playposse.interviewprep.crackingthecodeinterview.arraysandstrings;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Check that a string has all unique characters.
 */
public class AllUniqueCharacters {

    @Test
    public void test() {
        assertTrue(hashVersion("abcdef"));
        assertFalse(hashVersion("aaa"));
        assertFalse(hashVersion("abcdefa"));
        assertTrue(hashVersion(""));
    }

    public static boolean hashVersion(String str) {
        // Check if string has more characters than the character set.

        Set<Character> charMap = new HashSet<>(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (charMap.contains(c)) {
                return false;
            }
            charMap.add(c);
        }
        return true;
    }
}
