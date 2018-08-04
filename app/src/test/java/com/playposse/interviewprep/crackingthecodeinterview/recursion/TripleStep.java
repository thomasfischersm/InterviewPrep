package com.playposse.interviewprep.crackingthecodeinterview.recursion;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/30/2018.
 */
public class TripleStep {

    @Test
    public void test() {
        assertEquals(1, countStepCombo(0));
        assertEquals(1, countStepCombo(1));
        assertEquals(2, countStepCombo(2));
        assertEquals(4, countStepCombo(3));
        assertEquals(7, countStepCombo(4));
        assertEquals(13, countStepCombo(5));
        assertEquals(53798080, countStepCombo(30));
    }

    private static int countStepCombo(int stepCount) {
        int[] cache = new int[stepCount + 1];
        Arrays.fill(cache, -1);
        return countStepCombo(stepCount, cache);
    }

    private static int countStepCombo(int stepCount, int[] cache) {
        if (stepCount == 0) {
            return 1;
        } else if (stepCount < 0) {
            return 0;
        } else if (cache[stepCount] != -1) {
            return cache[stepCount];
        }

        int pathCount = 0;
        pathCount += countStepCombo(stepCount - 1, cache);
        pathCount += countStepCombo(stepCount - 2, cache);
        pathCount += countStepCombo(stepCount - 3, cache);

        cache[stepCount] = pathCount;
        return pathCount;
    }
}
