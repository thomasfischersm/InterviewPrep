package com.playposse.interviewprep.leetcode.competition4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 8/4/2018.
 */
public class ProjectionArea {

    @Test
    public void test() {
        assertEquals(5, new Solution().projectionArea(new int[][]{{2}}));
        assertEquals(17, new Solution().projectionArea(new int[][]{{1, 2}, {3, 4}}));
        assertEquals(8, new Solution().projectionArea(new int[][]{{1, 0}, {0, 2}}));
        assertEquals(14, new Solution().projectionArea(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}));
        assertEquals(21, new Solution().projectionArea(new int[][]{{2, 2, 2}, {2, 1, 2}, {2, 2, 2}}));
    }

    class Solution {
        public int projectionArea(int[][] grid) {
            return computeTopProjection(grid)
                    + computeFrontProjection(grid)
                    + computeSideProjection(grid);
        }

        private int computeTopProjection(int[][] grid) {
            int count = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] > 0) {
                        count++;
                    }
                }
            }
            return count;
        }

        private int computeFrontProjection(int[][] grid) {
            int count = 0;
            for (int i = 0; i < grid.length; i++) {
                int maxRow = 0;
                for (int j = 0; j < grid[i].length; j++) {
                    maxRow = Math.max(maxRow, grid[j][i]);
                }
                count += maxRow;
            }
            return count;
        }

        private int computeSideProjection(int[][] grid) {
            int count = 0;
            for (int i = 0; i < grid[0].length; i++) {
                int maxColumn = 0;
                for (int j = 0; j < grid.length; j++) {
                    maxColumn = Math.max(maxColumn, grid[i][j]);
                }
                count += maxColumn;
            }
            return count;
        }
    }
}
