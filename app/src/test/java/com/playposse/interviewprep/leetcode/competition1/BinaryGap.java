package com.playposse.interviewprep.leetcode.competition1;

import org.junit.Test;

/**
 * Created by thoma on 7/14/2018.
 */
public class BinaryGap {

    @Test
    public void test() {
        new Solution().binaryGap(4);
    }

    class Solution {
        public int binaryGap(int N) {
            int longestDistance = 0;
            int lastOneIndex = -1;

            int index = 0;
            while (N > 0) {
                int digit = N % 2;
                N = N / 2;
                if (digit == 1) {
                    if (lastOneIndex >= 0) {
                        longestDistance = Math.max(longestDistance, index - lastOneIndex);
                    }
                    lastOneIndex = index;
                }

                index++;
            }
            return longestDistance;
        }
    }
}
