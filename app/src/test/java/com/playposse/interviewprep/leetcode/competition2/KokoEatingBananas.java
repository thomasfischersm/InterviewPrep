package com.playposse.interviewprep.leetcode.competition2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/21/2018.
 */
public class KokoEatingBananas {

    @Test
    public void test() {
        assertEquals(4, new Solution().minEatingSpeed(new int[]{4}, 1));
        assertEquals(2, new Solution().minEatingSpeed(new int[]{4}, 2));
        assertEquals(4, new Solution().minEatingSpeed(new int[]{4, 1}, 2));
        assertEquals(2, new Solution().minEatingSpeed(new int[]{4, 1}, 3));

        assertEquals(4, new Solution().minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        assertEquals(30, new Solution().minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
        assertEquals(23, new Solution().minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));

        assertEquals(112677673, new Solution().minEatingSpeed(new int[]{332484035,
                524908576,
                855865114,
                632922376,
                222257295,
                690155293,
                112677673,
                679580077,
                337406589,
                290818316,
                877337160,
                901728858,
                679284947,
                688210097,
                692137887,
                718203285,
                629455728,
                941802184}, 823855818));

    }

    class Solution {
        public int minEatingSpeed(int[] piles, int H) {
            List<Pile> pileList = new ArrayList<>();
            for (int pile : piles) {
                pileList.add(new Pile(pile));
            }

            pileList.sort(new Comparator<Pile>() {
                @Override
                public int compare(Pile pile1, Pile pile2) {
                    return (int) (pile2.getCountPerHour() - pile1.getCountPerHour());
                }
            });

            for (int i = 0; i < H - piles.length; i++) {
                pileList.get(0).sessionCount++;

                int sessionCount = pileList.get(0).sessionCount;
                int moveIndex = 0;
                for (int j = 0; j < piles.length; j++) {
                    if (pileList.get(j).getCountPerHour() <= sessionCount) {
                        break;
                    }
                    moveIndex = j;
                }

                if (moveIndex > 0) {
                    pileList.add(moveIndex, pileList.get(0));
                    pileList.remove(0);
                }
            }

            double max = 0;
            for (Pile pile : pileList) {
                max = Math.max(max, pile.getCountPerHour());
            }

            return (int) Math.ceil(max);
        }


        private class Pile {

            private final int size;

            private int sessionCount = 1;

            public Pile(int size) {
                this.size = size;
            }

            private double getCountPerHour() {
                return ((double) size) / sessionCount;
            }
        }
    }

}


/*



[332484035
524908576
855865114
632922376
222257295
690155293
112677673
679580077
337406589
290818316
877337160
901728858
679284947
688210097
692137887
718203285
629455728
941802184]

823855818

 */