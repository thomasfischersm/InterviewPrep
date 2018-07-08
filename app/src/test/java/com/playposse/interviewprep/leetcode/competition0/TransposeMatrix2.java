package com.playposse.interviewprep.leetcode.competition0;

import org.junit.Test;

/**
 * Created by thoma on 7/7/2018.
 */
public class TransposeMatrix2 {

    @Test
    public void test() {
        int[][] a = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        dump(a);
        a = new Solution().transpose(a);
        dump(a);
    }

    @Test
    public void test2() {
        int[][] a = new int[][]{{1, 2, 3}, {4, 5, 6}};
        dump(a);
        a = new Solution().transpose(a);
        dump(a);
    }

    private static void dump(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.print(String.format("%2d", A[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }

    class Solution {
        public int[][] transpose(int[][] A) {
            int[][] b = new int[A[0].length][A.length];

            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    b[j][i] = A[i][j];
                }
            }

            return b;
        }
    }
}
