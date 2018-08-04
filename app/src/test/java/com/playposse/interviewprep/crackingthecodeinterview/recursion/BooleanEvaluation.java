package com.playposse.interviewprep.crackingthecodeinterview.recursion;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 8/1/2018.
 */
public class BooleanEvaluation {

    @Test
    public void test() {
        assertEquals(2, countTheWays("1^0|0|1", false));
        assertEquals(10, countTheWays("0&0&0&1^1|0", true));

        assertEquals(0, countTheWays("1", false));
        assertEquals(1, countTheWays("1", true));

        assertEquals(0, countTheWays("1|1", false));
        assertEquals(1, countTheWays("1|1", true));

        assertEquals(0, countTheWays("1|0&1", false));
        assertEquals(2, countTheWays("1|0&1", true));

        assertEquals(2, countTheWays("1&0&1", false));
        assertEquals(0, countTheWays("1&0&1", true));

        assertEquals(2, countTheWays("1^0^1", false));
        assertEquals(0, countTheWays("1^0^1", true));

        assertEquals(5, countTheWays("1^0^1^0", false));
        assertEquals(0, countTheWays("1^0^1^0", true));

        assertEquals(2, countTheWays("1|0^1^0", false));
        assertEquals(3, countTheWays("1|0^1^0", true));
    }

    private static int countTheWays(String str, boolean expectedResult) {
        return countTheWays(str, expectedResult, new HashMap<String, Integer>());
    }

    private static int countTheWays(String str, boolean expectedResult, Map<String, Integer> cache) {
        if (cache.containsKey(expectedResult + str)) {
            return cache.get(expectedResult + str);
        }

        if (str.length() == 0) {
            return 0;
        }

        if (str.length() == 1) {
            boolean actualResult = (str.charAt(0) == '1');
            return (actualResult == expectedResult) ? 1 : 0;
        }

        int totalWays = 0;
        for (int i = 1; i < str.length(); i += 2) {
            String left = str.substring(0, i);
            String right = str.substring(i + 1);

            int leftTrue = countTheWays(left, true, cache);
            int leftFalse = countTheWays(left, false, cache);
            int rightTrue = countTheWays(right, true, cache);
            int rightFalse = countTheWays(right, false, cache);

            int maxWays = (leftTrue + leftFalse) * (rightTrue + rightFalse);

            int ways = 0;
            switch (str.charAt(i)) {
                case '|':
                    ways = (leftTrue * rightTrue)
                            + (leftTrue * rightFalse)
                            + (leftFalse * rightTrue);
                    break;
                case '&':
                    ways = leftTrue * rightTrue;
                    break;
                case '^':
                    ways = (leftTrue * rightFalse)
                            + (leftFalse * rightTrue);
                    break;
            }

            if (!expectedResult) {
                ways = maxWays - ways;
            }
            totalWays += ways;
        }

        cache.put(expectedResult + str, totalWays);
        return totalWays;
    }
}
