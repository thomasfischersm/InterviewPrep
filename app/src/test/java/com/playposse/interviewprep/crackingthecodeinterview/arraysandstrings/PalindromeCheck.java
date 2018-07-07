package com.playposse.interviewprep.crackingthecodeinterview.arraysandstrings;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Check if a word is a palindrome. Apparently spaces have to be ignored. The question didn't state
 * it explicitly but the example implied it.
 */
public class PalindromeCheck {

    @Test
    public void test() {
        assertTrue(isPalindrome("", ""));
        assertTrue(isPalindrome("ada", "ada"));
        assertTrue(isPalindrome("abc", "cba"));
        assertTrue(isPalindrome("ab c", "cba"));
        assertTrue(isPalindrome("abc", "cb a"));
        assertTrue(isPalindrome("ab c", "c   b a"));
        assertTrue(isPalindrome("abc", "cba      "));

        assertFalse(isPalindrome("", "abc"));
        assertFalse(isPalindrome("abc", "abc"));
        assertFalse(isPalindrome("abc", "abc      a"));
        assertFalse(isPalindrome("cat", "dog"));
    }

    private static boolean isPalindrome(String a, String b) {
        int i = 0;
        int j = b.length() - 1;

        while ((i < a.length()) || (j >= 0)) {
            if ((i < a.length()) && (a.charAt(i) == ' ')) {
                i++;
                continue;
            }

            if ((j >= 0) && (b.charAt(j) == ' ')) {
                j--;
                continue;
            }

            if ((i >= a.length()) || (j < 0) || (a.charAt(i) != b.charAt(j))) {
                return false;
            }

            i++;
            j--;
        }
        return (i == a.length()) && (j == -1);
    }
}
