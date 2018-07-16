package com.playposse.interviewprep.leetcode.competition1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/14/2018.
 */
public class MinimumRefuelingStops {

    @Test
    public void test() {
        assertEquals(0, new Solution().minRefuelStops(1, 1, new int[0][0]));
        assertEquals(-1, new Solution().minRefuelStops(100, 1, new int[][]{{10, 100}}));
        assertEquals(2, new Solution().minRefuelStops(100, 10, new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}));
    }

    class Solution {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            if (target <= startFuel) {
                return 0;
            }

            List<Integer> stationsBySize = new ArrayList<>();

            int traveled = startFuel;
            int passedStationIndex = 0;
            int stops = 0;
            while (traveled < target) {
                for (int stationIndex = passedStationIndex; stationIndex < stations.length; stationIndex++) {
                    if (stations[stationIndex][0] > traveled) {
                        break;
                    }
                    stationsBySize.add(stations[stationIndex][1]);
                    passedStationIndex++;
                }
                Collections.sort(stationsBySize, Collections.<Integer>reverseOrder());

                if (stationsBySize.size() == 0) {
                    return -1;
                }
                traveled += stationsBySize.get(0);
                stationsBySize.remove(0);
                stops++;
            }
            return stops;
        }
    }
}
