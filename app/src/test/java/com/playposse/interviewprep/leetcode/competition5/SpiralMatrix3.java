package com.playposse.interviewprep.leetcode.competition5;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by thoma on 8/11/2018.
 */
public class SpiralMatrix3 {

    @Test
    public void test() {
        assertThat(new Solution().spiralMatrixIII(1, 4, 0, 0), is(new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}}));
        assertThat(new Solution().spiralMatrixIII(2, 1, 1, 0), is(new int[][]{{1, 0}, {0, 0}}));
        assertThat(new Solution().spiralMatrixIII(5, 6, 1, 4), is(new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}}));
    }

//    2
//            1
//            1
//            0
//    Output:
//            [[1,0]]
//    Expected:
//            [[1,0],[0,0]]

//    Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]

    class Solution {
        public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
            List<int[]> result = new ArrayList<>();

            int minCol = c0;
            int maxCol = c0;
            int minRow = r0;
            int maxRow = r0;

            result.add(new int[]{r0, c0});

            while ((minCol >= 0) || (maxCol < C) || (minRow >= 0) || (maxRow < R)) {
                // Go right.
                maxCol++;
                for (int column = minCol + 1; column <= maxCol; column++) {
                    if ((minRow >= 0) && (minRow < R) && (column >= 0) && (column < C)) {
                        result.add(new int[]{minRow, column});
                    }
                }

                // Go down.
                maxRow++;
                for (int row = minRow + 1; row <= maxRow; row++) {
                    if ((row >= 0) && (row < R) && (maxCol >= 0) && (maxCol < C)) {
                        result.add(new int[]{row, maxCol});
                    }
                }

                // Go left.
                minCol--;
                for (int column = maxCol - 1; column >= minCol; column--) {
                    if ((maxRow >= 0) && (maxRow < R) && (column >= 0) && (column < C)) {
                        result.add(new int[]{maxRow, column});
                    }
                }

                // Go up.
                minRow--;
                for (int row = maxRow - 1; row >= minRow; row--) {
                    if ((row >= 0) && (row < R) && (minCol >= 0) && (minCol < C)) {
                        result.add(new int[]{row, minCol});
                    }
                }
            }

            int[][] array = new int[result.size()][2];
            for (int i = 0; i < result.size(); i++) {
                array[i][0] = result.get(i)[0];
                array[i][1] = result.get(i)[1];
            }
            return array;
        }
    }
}
