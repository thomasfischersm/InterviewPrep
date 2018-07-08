package com.playposse.interviewprep.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by thoma on 7/8/2018.
 */
public class PalindromeNumber {

    @Test
    public void test() {
        assertFalse(new Solution().isPalindrome(10));
    }

    class Solution {
        public boolean isPalindrome(int x) {
            if (x < 0) {
                return false;
            }

            int count = (int) Math.floor(Math.log10(x) + 1);
            for (int i = 0; i < Math.ceil(count / 2); i++) {
                int sourceDigit = x / (int) Math.pow(10, i) % 10;
                int targetDigit = x / (int) Math.pow(10, count - i - 1) % 10;
                if (sourceDigit != targetDigit) {
                    return false;
                }
            }

            return true;
        }
    }
}
