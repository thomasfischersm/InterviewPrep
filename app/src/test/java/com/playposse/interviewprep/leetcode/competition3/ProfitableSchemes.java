package com.playposse.interviewprep.leetcode.competition3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/28/2018.
 */
public class ProfitableSchemes {

    @Test
    public void test() {
        assertEquals(2, new Solution().profitableSchemes(5, 3, new int[]{2, 2}, new int[]{2, 3}));
        assertEquals(7, new Solution().profitableSchemes(10, 5, new int[]{2,3,5}, new int[]{6,7,8}));
    }

    class Solution {
        public int profitableSchemes(int G, int P, int[] group, int[] profit) {
            return tryCrime(G, P, group, profit, G, 0, 0);
        }

        private int tryCrime(
                int G,
                int P,
                int[] group,
                int[] profit,
                int remainingG,
                int currentProfit,
                int minCrimeIndex) {

            int crimeCount = 0;

            for (int i = minCrimeIndex; i < group.length; i++) {
                if (remainingG < group[i]) {
                    continue;
                }

                int subCrimes = tryCrime(G, P, group, profit, remainingG - group[i], currentProfit + profit[i], i + 1);
                crimeCount += subCrimes;
                if (currentProfit + profit[i] >= P) {
                    crimeCount += 1;
                }
            }
            return crimeCount;
        }
    }
}
