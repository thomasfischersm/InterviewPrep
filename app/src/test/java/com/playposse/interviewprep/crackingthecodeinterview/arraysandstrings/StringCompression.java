package com.playposse.interviewprep.crackingthecodeinterview.arraysandstrings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Compress a string by replacing repeating characters this way: aaa -> a3. If the compression
 * doesn't shorten the string size, return the original.
 */
public class StringCompression {

    @Test
    public void test() {
        test("", "");
        test("a", "a");
        test("abc", "abc");
        test("aabc", "aabc");
        test("a3bc", "aaabc");
        test("aabbcc", "aabbcc");
        test("abc4", "abcccc");
        test("a2b3c4", "aabbbcccc");
    }

    private void test(String expected, String input) {
        assertEquals(expected, compress(input));
    }

    private static String compress(String input) {
        if ((input == null) || (input.length() <= 1)) {
            return input;
        }

        StringBuilder sb = new StringBuilder();

        char lastChar = input.charAt(0);
        int charCount = 1;
        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);

            if (lastChar == c) {
                charCount++;
            } else {
                sb.append(lastChar);
                if (charCount > 1) {
                    sb.append(charCount);
                }

                lastChar = c;
                charCount = 1;
            }
        }

        sb.append(lastChar);
        if (charCount > 1) {
            sb.append(charCount);
        }

        String result = sb.toString();
        return (result.length() < input.length()) ? result : input;
    }
}
