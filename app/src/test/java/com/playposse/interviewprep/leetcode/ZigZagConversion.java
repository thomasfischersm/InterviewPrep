package com.playposse.interviewprep.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 8/9/2018.
 */
public class ZigZagConversion {

    @Test
    public void test() {
        assertEquals("abc", new Solution().convert("abc", 1));
        assertEquals("acb", new Solution().convert("abc", 2));

        assertEquals("PAHNAPLSIIGYIR", new Solution().convert("PAYPALISHIRING", 3));
        assertEquals("PINALSIGYAHRPI", new Solution().convert("PAYPALISHIRING", 4));
        assertEquals("ABDC", new Solution().convert("ABCD", 3));
    }

    class Solution {
        public String convert(String s, int numRows) {
            StringBuilder sb = new StringBuilder();
            int x = Math.max(1, 2 * numRows - 2);

            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < s.length() + i; j += x) {
                    int a = j - i;
                    int b = j + i;

                    if (i == 0) {
                        sb.append(s.charAt(a));
                    } else if (i == numRows - 1) {
                        if (b < s.length()) {
                            sb.append(s.charAt(b));
                        }
                    } else {
                        if (a >= 0) {
                            sb.append(s.charAt(a));
                        }
                        if (b < s.length()) {
                            sb.append(s.charAt(b));
                        }
                    }
                }
            }

            return sb.toString();
        }
    }
}
