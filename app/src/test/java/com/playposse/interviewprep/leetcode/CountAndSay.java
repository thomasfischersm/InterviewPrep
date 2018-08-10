package com.playposse.interviewprep.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 8/9/2018.
 */
public class CountAndSay {

    @Test
    public void test() {
        assertEquals("1", new Solution().countAndSay(1));
        assertEquals("11", new Solution().countAndSay(2));
        assertEquals("21", new Solution().countAndSay(3));
        assertEquals("1211", new Solution().countAndSay(4));
        assertEquals("111221", new Solution().countAndSay(5));
        assertEquals("312211", new Solution().countAndSay(6));
    }

    class Solution {
        public String countAndSay(int n) {
            return countAndSay("1", n - 1);
        }

        private String countAndSay(String str, int n) {
            if (n <= 0) {
                return str;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                int count = 1;

                for (int j = i + 1; j < str.length(); j++) {
                    if (c == str.charAt(j)) {
                        count++;
                    } else {
                        break;
                    }
                }

                sb.append(count);
                sb.append(c);
                i += count - 1;
            }

            return countAndSay(sb.toString(), n - 1);
        }
    }

    class Solution2 {
        public String countAndSay(int n) {
            return countAndSay("1", n - 1);
        }

        private String countAndSay(String str, int n) {
            if (n <= 0) {
                return str;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '2') {
                    sb.append("12");
                    continue;
                }

                char d = (i + 1 < str.length()) ? str.charAt(i + 1) : '0';
                if ((c == '1') && (d == '1')) {
                    sb.append("21");
                    i++;
                } else {
                    sb.append("11");
                }
            }

            return countAndSay(sb.toString(), n - 1);
        }
    }
}
