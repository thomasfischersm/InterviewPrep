package com.playposse.interviewprep.facebookinterview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * A celebrity is defined as someone who is known by everyone but knows nobody. A method is
 * provided, that can query if person A knows person B. Try to minimize calls to that method.
 */
public class Celebrity {

    @Test
    public void test0() {
        TestCallback callback = new TestCallback(1);
        assertEquals(1, findCelebrity(10, callback));
    }

    @Test
    public void test1() {
        TestCallback callback = new TestCallback(1);
        callback.addKnows(0, 2);
        callback.addKnows(0, 3);
        callback.addKnows(0, 4);
        callback.addKnows(0, 5);
        callback.addKnows(0, 6);
        assertEquals(1, findCelebrity(10, callback));
    }

    @Test
    public void test2() {
        TestCallback callback = new TestCallback(1);
        callback.addKnows(0, 2);
        callback.addKnows(0, 3);
        callback.addKnows(0, 4);
        callback.addKnows(0, 5);
        callback.addKnows(0, 6);
        callback.addKnows(2, 0);
        callback.addKnows(2, 3);
        callback.addKnows(2, 7);
        assertEquals(1, findCelebrity(10, callback));
    }

    /**
     * Returns the id of the celebrity. The parameter specifies how many people there are.
     */
    public static int findCelebrity(int n, Callback callback) {
        LinkedList<Integer> possibleCelebrity = new LinkedList<>();

        for (int i = 1; i < n; i++) {
            if (callback.knows(0, i)) {
                possibleCelebrity.add(i);
            }
        }

        if (possibleCelebrity.size() == 0) {
            return 1;
        }

        while (possibleCelebrity.size() > 1) {
            int first = possibleCelebrity.getFirst();
            int last = possibleCelebrity.getLast();
            if (callback.knows(first, last)) {
                possibleCelebrity.removeFirst();
            } else {
                possibleCelebrity.removeLast();
            }
        }

        return possibleCelebrity.getFirst();
    }

    private static interface Callback {

        boolean knows(int personA, int personB);
    }

    private static class TestCallback implements Callback {

        private final int celebrity;
        private final Map<Integer, List<Integer>> keyKnowsValue = new HashMap<>();

        private TestCallback(int celebrity) {
            this.celebrity = celebrity;
        }

        private void addKnows(int personA, int personB) {
            List<Integer> knownPeople = keyKnowsValue.get(personA);
            if (knownPeople == null) {
                knownPeople = new ArrayList<>();
            }
            knownPeople.add(personB);
        }

        @Override
        public boolean knows(int personA, int personB) {
            if (personB == celebrity) {
                return true;
            } else {
                List<Integer> knownPeople = keyKnowsValue.get(personA);
                return (knownPeople != null) && (knownPeople.contains(personB));
            }
        }
    }
}
