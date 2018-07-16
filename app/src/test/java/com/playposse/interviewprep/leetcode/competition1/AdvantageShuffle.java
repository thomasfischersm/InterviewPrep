package com.playposse.interviewprep.leetcode.competition1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by thoma on 7/14/2018.
 */
public class AdvantageShuffle {

    @Test
    public void test() {
        assertArrayEquals(new int[]{2, 11, 7, 15}, new Solution().advantageCount(new int[]{2, 7, 11, 15}, new int[]{1, 10, 4, 11}));
        assertArrayEquals(new int[]{24, 32, 8, 12}, new Solution().advantageCount(new int[]{12, 24, 8, 32}, new int[]{13, 25, 32, 11}));
    }

    class Solution {
        public int[] advantageCount(int[] A, int[] B) {
            List<Integer> sortedA = asList(A);
            Collections.sort(sortedA, Collections.<Integer>reverseOrder());

            List<Integer> sortedB = asList(B);
            Collections.sort(sortedB, Collections.<Integer>reverseOrder());

            List<Integer> winningA = new ArrayList<>();
            List<Integer> winningB = new ArrayList<>();
            List<Integer> losingA = new ArrayList<>();
            List<Integer> losingB = new ArrayList<>();
            int indexA = 0;
            for (int indexB = 0; indexB < sortedB.size(); indexB++) {
                int a = sortedA.get(indexA);
                int b = sortedB.get(indexB);
                if (a > b) {
                    winningA.add(a);
                    winningB.add(b);
                    indexA++;
                } else {
                    losingB.add(b);
                }
            }
            for (int i = indexA; i < A.length; i++) {
                losingA.add(sortedA.get(i));
            }

            int[] resultA = new int[B.length];
            for (int i = 0; i < B.length; i++) {
                int b = B[i];
                int indexWinning = winningB.indexOf(b);
                if (indexWinning >= 0) {
                    resultA[i] = winningA.get(indexWinning);
                    winningA.remove(indexWinning);
                    winningB.remove(indexWinning);
                } else {
                    resultA[i] = losingA.get(0);
                    losingA.remove(0);
                }
            }
            return resultA;
        }

        private List<Integer> asList(int[] array) {
            List<Integer> list = new ArrayList<>(array.length);
            for (int i = 0; i < array.length; i++) {
                list.add(array[i]);
            }
            return list;
        }
    }
}
