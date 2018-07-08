package com.playposse.interviewprep.leetcode.competition0;

import org.junit.Test;

/**
 * Created by thoma on 7/7/2018.
 */
public class TransposeMatrix {

    @Test
    public void test() {
        int[][] a = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        dump(a);
        new Solution().transpose(a);
        dump(a);
    }

    private static void dump(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                System.out.print(String.format("%2d", A[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }

    class Solution {
        public int[][] transpose(int[][] A) {
            for (int i = 0; i < A.length; i++) {
                for (int j = i; j < A.length; j++) {
                    int swap = A[i][j];
                    A[i][j] = A[j][i];
                    A[j][i] = swap;
                }
            }

            return A;
        }
    }
}
