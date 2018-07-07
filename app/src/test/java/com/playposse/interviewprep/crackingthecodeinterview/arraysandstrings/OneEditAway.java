package com.playposse.interviewprep.crackingthecodeinterview.arraysandstrings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Check if a string is one edit away from another string. An edit can be an added, removed, or
 * replaced letter. No edit should also return true.
 */
public class OneEditAway {

    @Test
    public void test() {
        test("abc", "abc", true);
        test("adc", "abc", true);
        test("abc", "abcc", true);
        test("abc", "abcc", true);
        test("abc", "cabc", true);
        test("abc", "abc", true);
        test("abc", "bc", true);
        test("abc", "ac", true);
        test("abc", "ab", true);
        test("ac", "abc", true);
        test("abc", "abcd", true);
        test("abc", "aabc", true);
        test("ab", "abc", true);
        test("bc", "abc", true);
        test("", "", true);

        test("abc", "def", false);
        test("abc", "abbbc", false);
        test("abccc", "abc", false);
        test("a", "abc", false);
        test("abc", "a", false);
        test("ccc", "abc", false);
        test("abc", "aca", false);
        test("abc", "cba", false);
        test("abc", "b", false);
    }

    private void test(String a, String b, boolean expectedResult) {
        assertEquals(expectedResult, isOneEditAway(a, b));
    }

    private static boolean isOneEditAway(String a, String b) {
        if (a.length() == b.length()) {
            return hasOneReplaceOrNone(a, b);
        } else if (a.length() == b.length() - 1) {
            return hasOneAddition(a, b);
        } else if (a.length() == b.length() + 1) {
            return hasOneRemoval(a, b);
        } else {
            return false;
        }
    }

    private static boolean hasOneReplaceOrNone(String a, String b) {
        boolean hasOneReplace = false;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (hasOneReplace) {
                    return false;
                } else {
                    hasOneReplace = true;
                }
            }
        }

        return true;
    }

    private static boolean hasOneAddition(String a, String b) {
        int additionCount = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i + additionCount)) {
                if (additionCount >= 1) {
                    return false;
                } else {
                    additionCount++;
                }
            }
        }

        return true;
    }

    private static boolean hasOneRemoval(String a, String b) {
        int removalCount = 0;

        for (int i = 0; i < a.length() - 1 + removalCount; i++) {
            if (a.charAt(i) != b.charAt(i - removalCount)) {
                if (removalCount >= 1) {
                    return false;
                } else {
                    removalCount++;
                }
            }
        }

        return true;
    }
}
