package com.playposse.interviewprep.leetcode.competition0;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thoma on 7/7/2018.
 */
public class PrimePalindrome {

    @Test
    public void test() {
        System.out.println(new Solution().primePalindrome(6));
        System.out.println(new Solution().primePalindrome(8));
        System.out.println(new Solution().primePalindrome(13));
        System.out.println(new Solution().primePalindrome(1));
        System.out.println(new Solution().primePalindrome(1215122));
        System.out.println(new Solution().primePalindrome(3991994));
        System.out.println(new Solution().primePalindrome(13));
        System.out.println(new Solution().primePalindrome(7256528));

//        7
//        11
//        55
//        2
//        1216121
//        3992993
    }

    class Solution {
        public int primePalindrome(int N) {
            if ((N == 1)) {
                return 2;
            }
            if ((N == 2) || (N == 3)) {
                return N;
            }

            if (N % 2 == 0) {
                N++;
            }

            List<Integer> primes = new ArrayList<>();
            primes.add(3);

            outer:
            for (int i = 5; true; i += 2) {
                // Check if prime.
                int sqrt = (int) Math.sqrt(i);
                for (int j = 0; j < primes.size(); j++) {
                    int prime = primes.get(j);
                    if (i % prime == 0) {
                        continue outer;
                    }
                    if (prime >= sqrt) {
                        break;
                    }
                }

                if ((i >= N) && isPalindrome(i)) {
                    return i;
                }

                primes.add(i);
            }
        }

        List<Integer> list = new ArrayList<>();

        private boolean isPalindrome(int n) {
            list.clear();

            int remainder = n;
            while (remainder > 0) {
                int digit = remainder % 10;
                remainder = remainder / 10;
                list.add(digit);
            }

            for (int i = 0; i < list.size() / 2; i++) {
                if (!list.get(i).equals(list.get(list.size() - 1 - i))) {
                    return false;
                }
            }

            return true;
        }
    }
}
