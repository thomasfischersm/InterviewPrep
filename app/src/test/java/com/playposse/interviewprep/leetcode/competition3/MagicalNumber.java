package com.playposse.interviewprep.leetcode.competition3;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/28/2018.
 */
public class MagicalNumber {

//    1000000000
//            40000
//            40000
//1
//        2
//        3
//
//        4
//        2
//        3
//
//        5
//        2
//        4
//
//        3
//        6
//        4
//        1000000000
//        40000
//        40000
//    2, 6, 10, 8
    @Test
    public void test() {
        assertEquals(2, new Solution().nthMagicalNumber(1, 2, 3));
        assertEquals(6, new Solution().nthMagicalNumber(4, 2, 3));
        assertEquals(10, new Solution().nthMagicalNumber(5, 2, 4));
        assertEquals(8, new Solution().nthMagicalNumber(3, 6, 4));
        new Solution().nthMagicalNumber(1000000000, 40000, 40000);
    }

    class Solution {
        public int nthMagicalNumber2(int N, int A, int B) {
            int number = 0;
            int count = 0;

            while (count < N) {
                number++;
                if ((number % A == 0) || (number % B == 0)) {
                    count++;
                }
            }
            return number;
        }

        public int nthMagicalNumber(int N, int A, int B) {
            if ((A % B == 0) || (B % A == 0)) {
                return handleSimpleCase(N, A, B);
            }

            long upperNumber = findUpperBound(N, A, B);
            long lowerNumber = upperNumber / 10;

            while (lowerNumber < upperNumber) {
                long middleNumber = (upperNumber + lowerNumber) / 2;
                if (getPosition(middleNumber, A, B) == N) {
                    while (getPosition(middleNumber - 1, A, B) == N) {
                        middleNumber--;
                    }
                    return (int) (middleNumber % (10 ^ 9 + 7));
                }

                if (getPosition(middleNumber, A, B) < N) {
                    lowerNumber = middleNumber;
                } else {
                    upperNumber = middleNumber;
                }
            }

            return (int) (lowerNumber % (10 ^ 9 + 7));
        }

        private int handleSimpleCase(long N, int A, int B) {
            int min = Math.min(A, B);
            return (int) ((N * min) % (10 ^ 9 + 7));
        }

        private long getPosition(long number, int A, int B) {
            int gcd = new BigInteger(Integer.toString(A)).gcd(new BigInteger(Integer.toString(B))).intValue();
            return (number / A) + (number / B) - (number / ((A * B) / gcd));
        }

        private long findUpperBound(int N, int A, int B) {
            long number = 10;

            while (getPosition(number, A, B) < N) {
                number *= 10;
            }
            return number;
        }
    }
}
