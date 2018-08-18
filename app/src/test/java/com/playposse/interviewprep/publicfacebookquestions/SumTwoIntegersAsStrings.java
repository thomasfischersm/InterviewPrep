package com.playposse.interviewprep.publicfacebookquestions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 8/10/2018.
 */
public class SumTwoIntegersAsStrings {

    @Test
    public void test() {
        assertEquals("2", sum("1", "1"));
        assertEquals("11", sum("5", "6"));
        assertEquals("11", sum("10", "1"));
        assertEquals("11", sum("1", "10"));
        assertEquals("111", sum("56", "55"));
        assertEquals("10000", sum("9999", "1"));
        assertEquals("2469135780", sum("1234567890", "1234567890"));
    }

    private static String sum(String a, String b) {
        StringBuilder sb = new StringBuilder();

        // Make sure that the longer string is in a.
        if (a.length() < b.length()) {
            String tmp = a;
            a = b;
            a = tmp;
        }

        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        while ((i >= 0) || (j >= 0) || (carry > 0)) {
            int sum = carry;
            sum += (i >= 0) ? (a.charAt(i) - '0') : 0;
            sum += (j >= 0) ? (b.charAt(j) - '0') : 0;
            carry =  sum / 10;
            sb.insert(0,sum % 10);
            i--;
            j--;
        }

        return sb.toString();
    }
}
