package com.playposse.interviewprep.crackingthecodeinterview.arraysandstrings;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Convert all spaces in a string to %20. The string is given as a character array that has enough
 * space at the end for the expansion.
 */
public class Urlify {

    @Test
    public void test() {
        assertUrlify("", "");
        assertUrlify("abc", "abc");
        assertUrlify("abc%20abc", "abc abc");
        assertUrlify("abc%20%20a%20", "abc  a ");
    }

    private void assertUrlify(String expected, String input) {
        char[] inputSimple = toCharArray(input);
        char[] inputExtended = new char[expected.length()];
        System.arraycopy(inputSimple, 0, inputExtended, 0, inputSimple.length);

        urlify(inputExtended, input.length());

        assertArrayEquals(toCharArray(expected), inputExtended);
    }

    private static char[] toCharArray(String str) {
        char[] chars = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            chars[i] = str.charAt(i);
        }
        return chars;
    }

    private static void urlify(char[] str, int trueLength) {
        int j = str.length - 1;
        for (int i = trueLength - 1; i >= 0; i--) {
            char c = str[i];

            if (c == ' ') {
                str[j--] = '0';
                str[j--] = '2';
                str[j--] = '%';
            } else {
                str[j--] = c;
            }
        }
    }
}
