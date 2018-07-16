package com.playposse.interviewprep.leetcode.competition1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by thoma on 7/14/2018.
 */
public class ReorderedPowerOfTwo {

    @Test
    public void test() {
        assertTrue(new Solution().reorderedPowerOf2(1));
        assertFalse(new Solution().reorderedPowerOf2(10));
        assertTrue(new Solution().reorderedPowerOf2(16));
        assertFalse(new Solution().reorderedPowerOf2(24));
        assertTrue(new Solution().reorderedPowerOf2(46));
    }

    class Solution {
        public boolean reorderedPowerOf2(int N) {
            List<Integer> digits = new ArrayList<>();

            while (N > 0) {
                int digit = N % 10;
                digits.add(digit);
                N /= 10;
            }

            return eval("", digits);
        }

        private boolean eval(String start, List<Integer> remainingDigits) {
            if (remainingDigits.size() == 0) {
                return isPowerOfTwo(Integer.parseInt(start));
            }

            for (int i = 0; i < remainingDigits.size(); i++) {
                String str = start + remainingDigits.get(i).toString();

                if (str.equals("0")) {
                    continue;
                }

                List<Integer> nextDigits = new ArrayList<>(remainingDigits);
                nextDigits.remove((int) i);
                if (eval(str, nextDigits)) {
                    return true;
                }
            }
            return false;
        }

        private boolean isPowerOfTwo(int num) {
            while (num > 0) {
                if (num == 1) {
                    return true;
                }

                if (num % 2 != 0) {
                    return false;
                }
                num /= 2;
            }

            return false;
        }
    }
}
