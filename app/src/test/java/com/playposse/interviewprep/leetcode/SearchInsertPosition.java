package com.playposse.interviewprep.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 8/9/2018.
 */
public class SearchInsertPosition {

    @Test
    public void test() {
        assertEquals(2, new Solution().searchInsert(new int[]{1, 3, 5 ,6}, 5));
        assertEquals(1, new Solution().searchInsert(new int[]{1, 3, 5 ,6}, 2));
        assertEquals(4, new Solution().searchInsert(new int[]{1, 3, 5 ,6}, 7));
        assertEquals(0, new Solution().searchInsert(new int[]{1, 3, 5 ,6}, 0));
    }

    class Solution {
        public int searchInsert(int[] nums, int target) {
            int lowerBound = 0;
            int upperBound = nums.length - 1;

            while (lowerBound <= upperBound) {
                int midIndex = (lowerBound + upperBound) / 2;
                int midVal = nums[midIndex];

//                if ((lowerBound == upperBound) && (midVal != target)) {
//                    return lowerBound;
//                }

                if (target < midVal) {
                    upperBound = midIndex - 1;
                } else if (target > midVal) {
                    lowerBound = midIndex + 1;
                } else {
                    return midIndex;
                }
            }

            int min = Math.min(lowerBound, upperBound);
            int max = Math.max(lowerBound, upperBound);

            if (min < 0) {
                return 0;
            }

            return (nums[min] < target) ? max : min;
        }
    }
}
