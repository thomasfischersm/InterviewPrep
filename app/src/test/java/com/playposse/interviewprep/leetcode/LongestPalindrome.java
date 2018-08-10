package com.playposse.interviewprep.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 8/9/2018.
 */
public class LongestPalindrome {

    @Test
    public void test() {
        assertEquals("bab", new Solution().longestPalindrome("babad"));
        assertEquals("bb", new Solution().longestPalindrome("cbbd"));
        assertEquals("", new Solution().longestPalindrome(""));
        assertEquals("aaaa", new Solution().longestPalindrome("aaaa"));

        assertEquals("bb", new Solution().longestPalindrome("bb"));
        assertEquals("bb", new Solution().longestPalindrome("abb"));
        assertEquals("abba", new Solution().longestPalindrome("abba"));
        assertEquals("bb", new Solution().longestPalindrome("abbd"));
        assertEquals("aba", new Solution().longestPalindrome("aba"));
        assertEquals("aba", new Solution().longestPalindrome("daba"));
        assertEquals("aba", new Solution().longestPalindrome("abad"));
        assertEquals("bbb", new Solution().longestPalindrome("aabbb"));
        assertEquals("dddd", new Solution().longestPalindrome("aabbbdddd"));
        assertEquals("eeeee", new Solution().longestPalindrome("aabbbddddeeeee"));
    }

    class Solution {
        public String longestPalindrome(String s) {
            if ((s.length() == 2) && (s.charAt(0) == s.charAt(1))) {
                return s;
            }

            String p = "";

            for (int i = 1; i < s.length() -1; i++) {
                char left = s.charAt(i - 1);
                char current = s.charAt(i);
                char right = s.charAt(i + 1);

                if (left == current) {
                    p = check(s, i - 2, i + 1, p);
                }

                if (right == current) {
                    p = check(s, i - 1, i + 2, p);
                }

                if (right == left) {
                    p = check(s, i - 2, i + 2, p);
                }
            }

            if ((p.isEmpty()) && (s.length() > 0)) {
                return s.substring(0, 1);
            }

            return p;
        }

        private String check(String s, int left, int right, String p) {
            while ((left >= 0)
                    && (right < s.length())
                    && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }

            int length = right - left - 1;
            if ((p == null) || (length > p.length())) {
                return s.substring(left + 1, right);
            } else {
                return p;
            }
        }
    }
}
