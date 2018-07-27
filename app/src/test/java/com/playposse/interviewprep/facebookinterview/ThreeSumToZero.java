package com.playposse.interviewprep.facebookinterview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thoma on 7/24/2018.
 */
public class ThreeSumToZero {

    public int[] findZeroSum(int[] array) {
        // Exit early for two little values.
        if (array.length < 3) {
            return null;
        }

        // Separate array into sorted arrays by even/uneven and positive/negative numbers.
        SortedData sortedData = new SortedData(array);

        // Handle special case of three zeroes!
        if ((sortedData.getPositiveEven().size() >= 3)
                && (sortedData.getPositiveEven().get(0) == 0)
                && (sortedData.getPositiveEven().get(1) == 0)
                && (sortedData.getPositiveEven().get(2) == 0)) {
            return new int[]{0, 0, 0};
        }

        // Handle early terminations for zero size lists.
        int positiveEvenSize = sortedData.getPositiveEven().size();
        int positiveUnevenSize = sortedData.getPositiveUneven().size();
        int negativeEvenSize = sortedData.getNegativeEven().size();
        int negativeUnevenSize = sortedData.getNegativeUneven().size();
        if (((positiveEvenSize == 0) && (positiveUnevenSize == 0)) // Need at least 1 positive number.
                || ((negativeEvenSize == 0) && (negativeUnevenSize == 0)) // Need at least 1 negative number.
                || (positiveEvenSize == 0) && (negativeEvenSize == 0)) { // Need at least 1 even number.
            return null;
        }

        // We can be smart here. Ignoring the special case of (0, 0, 0), we know that at least
        // one number is negative and at least one number is positive. If two numbers are uneven,
        // the third number has to be even. If two numbers are even, the third number has to be
        // even as well. By focusing on the right type of number, we can cut down the checks by
        // roughly a quarter.
        search(sortedData.getNegativeEven(), sortedData.getPositiveEven());
        search(sortedData.getNegativeEven(), sortedData.getPositiveUneven());
        // Case where larger number is negative und uneven.

        search(sortedData.getPositiveEven(), sortedData.getPositiveEven());
        search(sortedData.getPositiveEven(), sortedData.getPositiveUneven());
        // Case where larger number is positive und uneven.


        return null; // remove
    }

    private int[] search(List<Integer> largeNumberList, List<Integer> twoNumberList) {
        // Note: large number refers to the large absolute value (e.g. abs(-10) is large to abs(1)).

        if ((largeNumberList.size() == 0) || (twoNumberList.size() == 0)) {
            return null;
        }

        int midPointIndex = 0; // The compiler isn't smart enough to figure out that all code paths
        // initialize this value before accessing.
        boolean isMidPointInitialized = false;
        for (int largeNumber : largeNumberList) {
            // The first time, we find the mid point with a modified binary search. On subsequent
            // loop iterations, we know that the next mid point should be near the old mid point.
            // So it should be faster to walk to it. E.g. if we had a 10,000 numbers, we might only
            // have to walk one value over. However doing a binary search would take more checks.
            int midPoint = largeNumber / 2;
            if (!isMidPointInitialized) {
                midPointIndex = modifiedBinarySearch(twoNumberList, midPoint);
                isMidPointInitialized = true;
            } else {
                while (twoNumberList.get(midPointIndex - 1) > midPoint) {
                    midPointIndex--;

                    // When there aren't at least two numbers have the expected sum left, it is no
                    // longer possible to form the sum. E.g., to get the sum of 4, at least the
                    // number 2 has to be present twice. If only 1 and 0 are present, there is no
                    // point of further checking.
                    if (midPointIndex < 0) {
                        return null;
                    }
                }
            }

            int upperIndex = midPointIndex;
            int lowerIndex = midPointIndex - 1;

            // The approach is to search from the midpoint out. We increase the upper index and the
            // lower index in such a balanced way to potentially have two values that lead to the
            // sum.
            //
            // The alternative approach would be to start searching from 0 and size -1 towards the
            // center. However imagine a case, where the midpoint is way to the left. Thus on the
            // right side of the list are a lot of very large values. By starting at the midpoint,
            // we'd quickly realize that for the smallest values, the rest of the large values are
            // too large. However if we started from the outside in, we'd have to traverse through
            // all of those numbers that are too large.
            while ((lowerIndex > 0) && (upperIndex < twoNumberList.size())) {
                Integer lowerNumber = twoNumberList.get(lowerIndex);
                Integer upperNumber = twoNumberList.get(upperIndex);
                int sum = largeNumber + lowerNumber + upperNumber;
                if (sum == 0) {
                    return new int[]{largeNumber, lowerNumber, upperNumber};
                }

                if (sum > 0) {
                    lowerIndex--;
                } else {
                    upperIndex++;
                }
            }
        }

        return null;
    }

    /**
     * A normal binary search returns the index of a found number. This binary search, returns the
     * index of the found number or the number that is the least larger.
     */
    private int modifiedBinarySearch(List<Integer> list, int midPoint) {
return -1;
    }

    private static class SortedData {

        // Optimization: LinkedList is faster for sorting because it avoids moving items around.
        // ArrayList is better for lookup later because of constant time lookups. We might also
        // want to attempt to do all this sorting in place inside of the array. We could have
        // indexes (pointers) that point to where one section starts and ends. If we do all of these
        // optimizations, the code will be more complicated and finely tuned. Yet the focus of the
        // question doesn't seem to be on sort optimizations.
        private List<Integer> negativeUneven = new ArrayList<>();
        private List<Integer> negativeEven = new ArrayList<>();
        private List<Integer> positiveUneven = new ArrayList<>();
        private List<Integer> positiveEven = new ArrayList<>();

        private SortedData(int[] array) {
            for (int num : array) {
                boolean isEven = (num & 1) == 0;
                boolean isPositive = (num >= 0);

                if (isEven && isPositive) {
                    add(positiveEven, num);
                } else if (!isEven && isPositive) {
                    add(positiveUneven, num);
                } else if (isEven) {
                    add(negativeEven, num);
                } else {
                    add(negativeUneven, num);
                }
            }
        }

        // Optimization: Bubble sort is the slowest. We could optimize this a lot.
        private void add(List<Integer> list, int num) {
            for (int i = 0; i < list.size(); i++) {
                if (num > list.get(i)) {
                    list.add(i, num);
                    return;
                }
            }
            list.add(num);
        }

        private List<Integer> getNegativeUneven() {
            return negativeUneven;
        }

        private List<Integer> getNegativeEven() {
            return negativeEven;
        }

        private List<Integer> getPositiveUneven() {
            return positiveUneven;
        }

        private List<Integer> getPositiveEven() {
            return positiveEven;
        }
    }
}
