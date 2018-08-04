package com.playposse.interviewprep.crackingthecodeinterview.recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by thoma on 7/31/2018.
 */
public class Coins {

    @Test
    public void test() {
        for (int i = 0; i < 31; i++) {
            test(i);
        }
    }

    private static void test(int amount) {
        System.out.println(" => " + amount);
        System.out.println(computePermutations(amount));
        System.out.println();
    }

    private static final int[] COIN_TYPES = new int[]{25, 10, 5, 1};

    private static Set<List<Integer>> computePermutations(int amount) {
        HashSet<List<Integer>> results = new HashSet<>();
        computePermutations(amount, 0, new LinkedList<Integer>(), results);
        return results;
    }

    private static void computePermutations(
            int amount,
            int nextCoinIndex,
            List<Integer> prefix,
            Set<List<Integer>> results) {

        if (amount == 0) {
            results.add(prefix);
            return;
        }

        if ((amount < 0) || (nextCoinIndex >= COIN_TYPES.length)) {
            return;
        }

        int currentCoin = COIN_TYPES[nextCoinIndex];
        for (int i = 0; i <= amount / currentCoin; i++) {
            ArrayList<Integer> nextPrefix = new ArrayList<>(prefix);
            for (int j = 0; j < i; j++) {
                nextPrefix.add(currentCoin);
            }

            int remainder = amount - (i * currentCoin);
            computePermutations(
                    remainder,
                    nextCoinIndex + 1,
                    nextPrefix,
                    results);
        }
    }
}
