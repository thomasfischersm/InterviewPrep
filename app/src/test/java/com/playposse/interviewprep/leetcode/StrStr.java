package com.playposse.interviewprep.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/14/2018.
 */
public class StrStr {

    @Test
    public void test() {
        assertEquals(2, new Solution().strStr("Hello", "ll"));
        assertEquals(2, new Solution().strStr("Hell", "ll"));
        assertEquals(-1, new Solution().strStr("Hel", "ll"));
        assertEquals(0, new Solution().strStr("ll", "ll"));
        assertEquals(9, new Solution().strStr("aaatlatttallll", "al"));
    }

    class Solution {
        public int strStr(String haystack, String needle) {
            if ((haystack == null) || (needle == null)) {
                return 0;
            }

            if (haystack.length() < needle.length()) {
                return -1;
            }

            int hash = 0;
            for (int i = 0; i < needle.length(); i++) {
                hash += needle.charAt(i);
            }

            int movingHash = 0;
            for (int i = 0; i < needle.length(); i++) {
                movingHash += haystack.charAt(i);
            }

            outer: for (int i = needle.length(); i <= haystack.length(); i++) {
                if (movingHash == hash) {
                    for (int j = i - needle.length(); j < i; j++) {
                        if (haystack.charAt(j) != needle.charAt(j + needle.length() - i)) {
                            continue outer;
                        }
                    }
                    return i - needle.length();
                }

                if (i == haystack.length()) {
                    break;
                }

                movingHash = movingHash - haystack.charAt(i - needle.length()) + haystack.charAt(i);
            }

            return -1;
        }
    }
}
