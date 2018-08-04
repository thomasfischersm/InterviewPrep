package com.playposse.interviewprep.crackingthecodeinterview.recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by thoma on 7/30/2018.
 */
public class PowerSet {

    @Test
    public void test() {
        printResult(getAllSubSets(toSet()));
        printResult(getAllSubSets(toSet(1)));
        printResult(getAllSubSets(toSet(1, 2)));
        printResult(getAllSubSets(toSet(1, 2, 3)));
        printResult(getAllSubSets(toSet(1, 2, 3, 4)));
        printResult(getAllSubSets(toSet(1, 2, 3, 4, 5)));
    }

    @Test
    public void testRecursive() {
        printResult(getAllSubSetsRecursive(toSet()));
        printResult(getAllSubSetsRecursive(toSet(1)));
        printResult(getAllSubSetsRecursive(toSet(1, 2)));
        printResult(getAllSubSetsRecursive(toSet(1, 2, 3)));
        printResult(getAllSubSetsRecursive(toSet(1, 2, 3, 4)));
        printResult(getAllSubSetsRecursive(toSet(1, 2, 3, 4, 5)));
    }

    private static List<Set<Integer>> getAllSubSets(Set<Integer> masterSet) {
        List<Integer> masterList = new ArrayList<>(masterSet);
        List<Integer> indices = new ArrayList<>();
        List<Set<Integer>> result = new ArrayList<>();

        for (int i = 0; i <= masterList.size(); i++) {

            shuffle:
            while (true) {
                // Add current set based on indices
                Set<Integer> currentSet = new HashSet<>();
                for (int j = 0; j < indices.size(); j++) {
                    currentSet.add(masterList.get(indices.get(j)));
                }
                result.add(currentSet);

                // Increment indices
                for (int j = indices.size() - 1; j >= 0; j--) {
                    int index = indices.get(j) + 1;
                    if (index >= masterList.size()) {
                        indices.set(j, -1); // 1 higher than the index to the left.
                    } else {
                        indices.set(j, index);

                        // Make sure that following indexes are higher.
                        for (int k = j + 1; k < indices.size(); k++) {
                            int resetIndex = indices.get(k - 1) + 1;
                            if (resetIndex >= masterList.size()) {
                                break shuffle;
                            }
                            indices.set(k, resetIndex);
                        }

                        continue shuffle;
                    }
                }
                break;
            }

            // Reset indices.
            for (int j = 0; j < indices.size(); j++) {
                indices.set(j, j);
            }

            indices.add(i);
        }
        return result;
    }

    private static Set<Integer> toSet(int... nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        System.out.println("\n\n---");
        printSet(set);
        System.out.println("---");

        return set;
    }

    private static void printResult(List<Set<Integer>> sets) {
        for (Set<Integer> set : sets) {
            printSet(set);
        }
    }

    private static void printSet(Set<Integer> set) {
        StringBuilder sb = new StringBuilder("{");
        for (int num : set) {
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(num);
        }
        sb.append("}");
        System.out.println(sb.toString());
    }

    private static List<Set<Integer>> getAllSubSetsRecursive(Set<Integer> masterSet) {
        ArrayList<Set<Integer>> partialResults = new ArrayList<>();
        partialResults.add(new HashSet<Integer>());

        return getAllSubSetsRecursive(
                new ArrayList<>(masterSet),
                partialResults,
                0);
    }

    private static List<Set<Integer>> getAllSubSetsRecursive(
            List<Integer> masterSet,
            List<Set<Integer>> partialResults,
            int elementIndex) {

        if (elementIndex >= masterSet.size()) {
            return partialResults;
        }

        List<Set<Integer>> newResults = new ArrayList<>();
        for (Set<Integer> set  : partialResults) {
            newResults.add(new HashSet<>(set));

            set.add(masterSet.get(elementIndex));
            newResults.add(set);
        }

        return getAllSubSetsRecursive(masterSet, newResults, elementIndex + 1);
    }
}
