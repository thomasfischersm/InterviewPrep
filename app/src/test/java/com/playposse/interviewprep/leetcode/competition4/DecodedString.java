package com.playposse.interviewprep.leetcode.competition4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 8/4/2018.
 */
public class DecodedString {

    @Test
    public void test() {
        assertEquals(0, new Solution().getLength(""));
        assertEquals(1, new Solution().getLength("a"));
        assertEquals(2, new Solution().getLength("ab"));
        assertEquals(3, new Solution().getLength("abc"));
        assertEquals(2, new Solution().getLength("a2"));
        assertEquals(3, new Solution().getLength("a3"));
        assertEquals(7, new Solution().getLength("ab2cde"));
        assertEquals(21, new Solution().getLength("ab2cde3"));
        assertEquals(25, new Solution().getLength("ab2cde3fghi"));

        assertEquals("a", new Solution().decodeAtIndex("a", 0));
        assertEquals("a", new Solution().decodeAtIndex("abc", 0));
        assertEquals("b", new Solution().decodeAtIndex("abc", 1));
        assertEquals("c", new Solution().decodeAtIndex("abc", 2));

        assertEquals("a", new Solution().decodeAtIndex("ab2c", 0));
        assertEquals("b", new Solution().decodeAtIndex("ab2c", 1));
        assertEquals("a", new Solution().decodeAtIndex("ab2c", 2));
        assertEquals("b", new Solution().decodeAtIndex("ab2c", 3));
        assertEquals("c", new Solution().decodeAtIndex("ab2c", 4));

        assertEquals("a", new Solution().decodeAtIndex("ab2c2d", 0));
        assertEquals("b", new Solution().decodeAtIndex("ab2c2d", 1));
        assertEquals("a", new Solution().decodeAtIndex("ab2c2d", 2));
        assertEquals("b", new Solution().decodeAtIndex("ab2c2d", 3));
        assertEquals("c", new Solution().decodeAtIndex("ab2c2d", 4));
        assertEquals("a", new Solution().decodeAtIndex("ab2c2d", 5));
        assertEquals("b", new Solution().decodeAtIndex("ab2c2d", 6));
        assertEquals("a", new Solution().decodeAtIndex("ab2c2d", 7));
        assertEquals("b", new Solution().decodeAtIndex("ab2c2d", 8));
        assertEquals("c", new Solution().decodeAtIndex("ab2c2d", 9));
        assertEquals("d", new Solution().decodeAtIndex("ab2c2d", 10));

        assertEquals("o", new Solution().decodeAtIndex("leet2code3", 10));
        assertEquals("h", new Solution().decodeAtIndex("ha22", 5));
        assertEquals("a", new Solution().decodeAtIndex("a2345678999999999999999", 1));
    }

    class Solution {
        public String decodeAtIndex(String S, int K) {
            return decodeAtIndex(S, K, S.length() - 1);
        }

        private String decodeAtIndex(String str, int K, int endIndex) {
            for (int i = endIndex; i >= 0; i--) {
                char c = str.charAt(i);
                if ((c >= '0') && (c <= '9')) {
                    int multiplier = c - '0';
                    int segmentLength = getLength(str, i - 1);
                    int multipliedLength = segmentLength * multiplier;
                    if (multipliedLength <= K) {
                        int thisLength = getLength(str, endIndex);
//                        return "" + str.charAt((K - multipliedLength) + i + 1);
                        return "" + str.charAt(endIndex - thisLength + K + 1);
                    }

                    K = K % segmentLength;
                    return decodeAtIndex(str, K, i - 1);
                }
            }

            return "" + str.charAt(K);
        }

        private int getLength(String str) {
            return getLength(str, str.length() - 1);
        }

        private int getLength(String str, int endIndex) {
            for (int i = endIndex; i >= 0; i--) {
                char c = str.charAt(i);
                if ((c >= '0') && (c <= '9')) {
                    int multiplier = c - '0';
                    return getLength(str, i - 1) * multiplier + (endIndex - i);
                }
            }
            return endIndex + 1;
        }
    }
}
