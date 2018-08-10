package com.playposse.interviewprep.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 8/9/2018.
 */
public class MaximumSubarray {

    @Test
    public void test() {
        assertEquals(0, new Solution().maxSubArray(new int[]{0}));
        assertEquals(1, new Solution().maxSubArray(new int[]{1}));
        assertEquals(-1, new Solution().maxSubArray(new int[]{-1}));

        assertEquals(1, new Solution().maxSubArray(new int[]{-2, 1}));

        assertEquals(-1, new Solution().maxSubArray(new int[]{-1, -2, -3, -4, -5, -6}));
        assertEquals(-1, new Solution().maxSubArray(new int[]{-2, -3, -4, -5, -6, -1}));
        assertEquals(21, new Solution().maxSubArray(new int[]{1, 2, 3, 4, 5, 6}));

        assertEquals(11, new Solution().maxSubArray(new int[]{1, 2, -2, -2, 5, 6}));
        assertEquals(12, new Solution().maxSubArray(new int[]{1, 2, -1, -1, 5, 6}));
        assertEquals(102, new Solution().maxSubArray(new int[]{1, 2, -2, -2, 5, 16, -10, 1, -10, 100}));
    }

    class Solution {

        public int maxSubArray(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }

            int sum = 0;
            int maxSum = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                maxSum = Math.max(maxSum, sum);
                sum = Math.max(0, sum);
            }
            return maxSum;
        }
    }
}
