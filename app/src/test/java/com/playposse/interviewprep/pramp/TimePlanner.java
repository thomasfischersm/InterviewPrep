package com.playposse.interviewprep.pramp;

/**
 * Created by thoma on 7/17/2018.
 */
public class TimePlanner {

    static class Solution {

        int[] meetingPlanner(int[][] slotsA, int[][] slotsB, int dur) {
            // your code goes here
            int indexA = 0;
            int indexB = 0;

            while ((indexA < slotsA.length) && (indexB < slotsB.length)) {
                int start = Math.max(slotsA[indexA][0], slotsB[indexB][0]);
                int end = Math.min(slotsA[indexA][1], slotsB[indexB][1]);

                if (end - start >= dur) {
                    return new int[]{start, dur};
                }

                if (slotsA[indexA][1] < slotsB[indexB][1]) {
                    indexA++;
                } else {
                    indexB++;
                }
            }

            return new int[0];
        }

        public void main(String[] args) {

        }

    }
}
