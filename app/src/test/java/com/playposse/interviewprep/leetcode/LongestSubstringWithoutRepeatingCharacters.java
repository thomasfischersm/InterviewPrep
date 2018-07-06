package com.playposse.interviewprep.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by thoma on 7/3/2018.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    @Test
    public void firstVersion() {
        test("abcabcbb");
        test("aba");
        test("zdddddz");
        test("zddddd");
        test("dddddz");
        test("ddddd");
        test("zdabcd");
    }

    @Test
    public void leetCodeVersion() {
        String s = "acbdbefa";
        int result = new Solution3().lengthOfLongestSubstring(s);
        System.out.println(s + " -> " + result);
    }

    private static void dump(String s, int start, int end) {
        System.out.println(s.substring(start, end));
    }

    private static void test(String s) {
        int result = new Solution2().lengthOfLongestSubstring(s);
        System.out.println(s + " -> " + result);
    }

    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            int start = 0;
            int maxLength = 0;
            HashSet<Character> passedChars = new HashSet<>();
            for (int i = 0; i < s.length() - maxLength; i++) {
                char c = s.charAt(i);

                if (passedChars.contains(c)) {
                    int currentLength = i - start;
                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                        dump(s, start, i);
                    }

                    while (s.charAt(start) != c) {
                        start++;
                    }
                    start++;
                } else {
                    passedChars.add(c);
                }
            }

            int lastLength = s.length() - start;
            if (lastLength > maxLength) {
                maxLength = lastLength;
                dump(s, start, s.length());
            }

            return maxLength;
        }
    }

    static class Solution2 {
        public int lengthOfLongestSubstring(String s) {
            int start = 0;
            int maxLength = 0;
            HashSet<Character> passedChars = new HashSet<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (passedChars.contains(c)) {
                    int currentLength = i - start;
                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                        if (start + 1 + maxLength > s.length()) {
                            break;
                        }
                        dump(s, start, i);
                    }

                    i = start;
                    start++;
                    passedChars.clear();
                } else {
                    passedChars.add(c);
                }
            }

            int endLength = s.length() - start;
            if (endLength > maxLength) {
                maxLength = endLength;
                dump(s, start, s.length());
            }

            return maxLength;
        }
    }

    public static class Solution3 {
        public int lengthOfLongestSubstring(String s) {
            int n = s.length();
            Set<Character> set = new HashSet<>();
            int ans = 0, i = 0, j = 0;
            while (i < n && j < n) {
                // try to extend the range [i, j]
                if (!set.contains(s.charAt(j))){
                    set.add(s.charAt(j++));
                    ans = Math.max(ans, j - i);
                }
                else {
                    set.remove(s.charAt(i++));
                }
            }
            return ans;
        }
    }
}
