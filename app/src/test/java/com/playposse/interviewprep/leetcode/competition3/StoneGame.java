package com.playposse.interviewprep.leetcode.competition3;

import org.junit.Test;

/**
 * Created by thoma on 7/28/2018.
 */
public class StoneGame {

    @Test
    public void test() {
        new Solution().stoneGame(new int[]{7,7,12,16,41,48,41,48,11,9,34,2,44,30,27,12,11,39,31,8,23,11,47,25,15,23,4,17,11,50,16,50,38,34,48,27,16,24,22,48,50,10,26,27,9,43,13,42,46,24});
    }

    class Solution {
        public boolean stoneGame(int[] piles) {
            int winningScore = getWinningScore(piles);
            return tryAlexMove(0, 0, piles, 0, piles.length, winningScore);
        }

        private int getWinningScore(int[] piles) {
            int sum = 0;
            for (int pile : piles) {
                sum += pile;
            }
            return (int) Math.ceil(sum / 2.0);
        }

        private boolean tryAlexMove(int alexSum, int lizSum, int[] piles, int start, int end, int winningScore) {
            if (alexSum > winningScore) {
                return true;
            }

            if (lizSum > winningScore) {
                return false;
            }

            if (start >= end) {
                return alexSum > lizSum;
            }

            // Try start.
            boolean startOutcome = tryLizMove(alexSum + piles[start], lizSum, piles, start + 1, end, winningScore);
            if (startOutcome) {
                return true;
            }


            // Try end.
            return tryLizMove(alexSum + piles[end - 1], lizSum, piles, start, end - 1, winningScore);
        }

        private boolean tryLizMove(int alexSum, int lizSum, int[] piles, int start, int end, int winningScore) {
            if (alexSum > winningScore) {
                return true;
            }

            if (lizSum > winningScore) {
                return false;
            }

            if (start >= end) {
                return alexSum > lizSum;
            }

            // Try start.
            boolean startOutcome = tryAlexMove(alexSum, lizSum + piles[start], piles, start + 1, end, winningScore);

            // Try end.
            boolean endOutcome = tryAlexMove(alexSum, lizSum + piles[end - 1], piles, start, end - 1, winningScore);
            return startOutcome && endOutcome;
        }
    }
}
