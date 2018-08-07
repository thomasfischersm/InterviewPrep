package com.playposse.interviewprep.crackingthecodeinterview.sortandsearch;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 8/6/2018.
 */
public class Listy {

    @Test
    public void test() {
        assertEquals(-1, getIndex(create(), 1));
        assertEquals(-1, getIndex(create(2), 1));
        assertEquals(0, getIndex(create(2), 2));

        assertEquals(-1, getIndex(create(1, 2), 0));
        assertEquals(0, getIndex(create(1, 2), 1));
        assertEquals(1, getIndex(create(1, 2), 2));
        assertEquals(-1, getIndex(create(1, 2), 3));

        assertEquals(-1, getIndex(create(1, 2, 2), 0));
        assertEquals(0, getIndex(create(1, 2, 2), 1));
        assertThat(getIndex(create(1, 2, 2), 2), anyOf(is(1), is(2)));
        assertEquals(-1, getIndex(create(1, 2, 2), 3));
        assertEquals(-1, getIndex(create(1, 2, 2), 4));

        assertEquals(-1, getIndex(create(1, 2, 2, 3), 0));
        assertEquals(0, getIndex(create(1, 2, 2, 3), 1));
        assertThat(getIndex(create(1, 2, 2, 3), 2), anyOf(is(1), is(2)));
        assertEquals(3, getIndex(create(1, 2, 2, 3), 3));
        assertEquals(-1, getIndex(create(1, 2, 2, 3), 4));
    }

    private static StrangeList create(int... values) {
        StrangeList list = new StrangeList();
        for (int value : values) {
            list.add(value);
        }
        return list;
    }

    private int getIndex(StrangeList list, int value) {
        int lowerBound = 0;
        int upperBound = 1;

        int upperBoundValue = list.get(upperBound);
        while ((upperBoundValue != -1) && (upperBoundValue <= value)) {
            if (upperBoundValue == value) {
                return upperBound;
            }
            upperBound *= 2;
            upperBoundValue = list.get(upperBound);
        }

        lowerBound = upperBound / 2;

        while (lowerBound <= upperBound) {
            int midIndex = (lowerBound + upperBound) / 2;
            int midValue = list.get(midIndex);

            if (midValue == -1) {
                upperBound = midIndex - 1;
                continue;
            }

            if (midValue == value) {
                return midIndex;
            }

            if (midValue < value) {
                lowerBound = midIndex + 1;
            } else {
                upperBound = midIndex -1;
            }
        }

        return -1;
    }

    private static class StrangeList extends ArrayList<Integer> {

        @Override
        public Integer get(int index) {
            if (index < super.size()) {
                return super.get(index);
            } else {
                return -1;
            }
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException();
        }
    }
}
