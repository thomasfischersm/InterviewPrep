package com.playposse.interviewprep.crackingthecodeinterview.recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by thoma on 7/31/2018.
 */
public class EightQueens {

    @Test
    public void test() {
        test(1);
        test(2);
        test(3);
        test(4);
        test(5);
        test(6);
        test(7);
        test(8);
    }

    private static void test(int maxQueenCount) {
        System.out.println(" => " + maxQueenCount);
        System.out.println(computePermutations(maxQueenCount));
        System.out.println();
    }

    private static Set<List<Integer>> computePermutations(int maxQueenCount) {
        HashSet<List<Integer>> results = new HashSet<>();
        computePermutations(0, maxQueenCount, new ArrayList<Integer>(), results);
        return results;
    }

    private static void computePermutations(
            int placedQueenCount,
            int maxQueenCount,
            List<Integer> placedQueens,
            Set<List<Integer>> results) {

        if (placedQueenCount == maxQueenCount) {
            results.add(new ArrayList<>(placedQueens));
            return;
        }

        Set<Integer> blockedColumns = new HashSet<>();
        for (int row = 0; row < placedQueens.size(); row++) {
            int column = placedQueens.get(row);

            // Left diagonal
            blockedColumns.add(column - (placedQueenCount - row));

            // below
            blockedColumns.add(column);

            // right diagonal
            blockedColumns.add(column + (placedQueenCount - row));
        }

        for (int column = 0; column < maxQueenCount; column++) {
            if (blockedColumns.contains(column)) {
                continue;
            }
            placedQueens.add(column);
            computePermutations(
                    placedQueenCount + 1,
                    maxQueenCount,
                    placedQueens,
                    results);
            placedQueens.remove(placedQueens.size() -1);
        }
    }
}
