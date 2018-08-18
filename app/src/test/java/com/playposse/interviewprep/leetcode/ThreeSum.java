package com.playposse.interviewprep.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 8/10/2018.
 */
public class ThreeSum {

    @Test
    public void test0() {
        List<List<Integer>> result = new Solution().threeSum(new int[]{1});
        assertEquals(0, result.size());
    }

    @Test
    public void test1() {
        List<List<Integer>> result = new Solution().threeSum(new int[]{1, 2});
        assertEquals(0, result.size());
    }

    @Test
    public void test2() {
        List<List<Integer>> result = new Solution().threeSum(new int[]{1, 2, 3});
        assertEquals(0, result.size());
    }

    @Test
    public void test3() {
        List<List<Integer>> result = new Solution().threeSum(new int[]{0, 0, 0});
        assertEquals(1, result.size());
        assertThat(result.get(0), is(Arrays.asList(0, 0, 0)));
    }

    @Test
    public void test4() {
        List<List<Integer>> result = new Solution().threeSum(new int[]{1, 0, -1});
        assertEquals(1, result.size());
        assertThat(result.get(0), is(Arrays.asList(-1, 0, 1)));
    }

    @Test
    public void test5() {
        List<List<Integer>> result = new Solution().threeSum(new int[]{5, 0, -10, 5, 3, 2, 1});
        assertEquals(1, result.size());
        assertThat(result.get(0), is(Arrays.asList(-10, 5, 5)));
    }

    @Test
    public void test6() {
        List<List<Integer>> result = new Solution().threeSum(new int[]{5, 0, -10, 5, 3, 2, 1, -3});
        assertEquals(3, result.size());
        assertThat(result.get(0), is(Arrays.asList(-10, 5, 5)));
        assertThat(result.get(1), is(Arrays.asList(-3, 0, 3)));
        assertThat(result.get(2), is(Arrays.asList(-3, 1, 2)));
    }


    @Test
    public void test7() {
        List<List<Integer>> result = new Solution().threeSum(new int[]{-1,0,1,2,-1,-4});
        assertEquals(2, result.size());
        assertThat(result.get(0), is(Arrays.asList(-1, -1, 2)));
        assertThat(result.get(1), is(Arrays.asList(-1, 0, 1)));
    }

    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums.length < 3) {
                return result;
            }

            Arrays.sort(nums);

            for (int i = 0; i < nums.length - 2; i++) {
                int j = i + 1;
                int k = nums.length - 1;

                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == 0) {
                        List<Integer> newResult = new ArrayList<>(3);
                        newResult.add(nums[i]);
                        newResult.add(nums[j]);
                        newResult.add(nums[k]);
                        if (!result.contains(newResult)) {
                            result.add(newResult);
                        }
                        j++;
                        k--;
                    } else if (sum < 0) {
                        j++;
                    } else {
                        k--;
                    }
                }
            }
            return result;
        }
    }
}
