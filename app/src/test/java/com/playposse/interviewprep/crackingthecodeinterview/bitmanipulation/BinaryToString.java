package com.playposse.interviewprep.crackingthecodeinterview.bitmanipulation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Convert a double between 0 and 1 to a binary string.
 */
public class BinaryToString {

    @Test
    public void test() {
        assertEquals("0", toString(0));
        assertEquals("0.1", toString(.5));
        assertEquals("0.01", toString(.25));
        assertEquals("0.11", toString(.75));
        assertEquals("0.001", toString(0.125));
        assertEquals("0.011", toString(0.375));
        assertEquals("0.101", toString(0.625));
        assertEquals("ERROR", toString(0.1));
    }

    private static String toString(double number) {
        if ((number < 0) || (number > 1)) {
            throw new IllegalArgumentException();
        }

        if (number == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder("0.");

        while (number != 0) {
            if (sb.length() >= 32 + 2) {
                return "ERROR";
            }            number *= 2;

            int digit = (int) number;
            number -= digit;
            sb.append(digit);
        }

        return sb.toString();
    }
}
