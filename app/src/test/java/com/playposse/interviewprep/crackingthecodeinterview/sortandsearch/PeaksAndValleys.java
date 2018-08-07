package com.playposse.interviewprep.crackingthecodeinterview.sortandsearch;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by thoma on 8/7/2018.
 */
public class PeaksAndValleys {

    @Test
    public void test() {
        test(new int[]{});
        test(new int[]{1});
        test(new int[]{1, 2});
        test(new int[]{2,1});
        test(new int[]{1, 2, 3, 4, 5, 6, 7});
        test(new int[]{8, 2, 3, -1, 5, 6, 7, 10, 5, 4, 3, 11});
    }

    private static void test(int[] array) {
        System.out.print("   ");
        print(array);
        convert(array);
        System.out.print("-> ");
        print(array);
        assertTrue(verify(array));
    }

    private static void convert(int[] array) {
        for (int i = 1; i < array.length - 1; i += 2) {
            if ((array[i - 1] > array[i]) || (array[i + 1] > array[i])) {
                if (array[i - 1] < array[i + 1]) {
                    swap(array, i, i + 1);
                } else {
                    swap(array, i, i - 1);
                }
            }
        }
    }

    private static void swap(int[] array, int index0, int index1) {
        int tmp = array[index0];
        array[index0] = array[index1];
        array[index1] = tmp;
    }

    private static boolean verify(int[] array) {
        if (array.length <= 2) {
            return true;
        }

        boolean isPeak = array[0] < array[1];

        for (int i = 1; i < array.length - 1; i++) {
            if (isPeak) {
                if ((array[i - 1] > array[i]) || (array[i + 1] > array[i])) {
                    return  false;
                }
            } else {
                if ((array[i - 1] < array[i]) || (array[i + 1] < array[i])) {
                    return  false;
                }
            }
            isPeak = !isPeak;
        }

        if (isPeak) {
            if (array[array.length - 2] > array[array.length - 1]) {
                return  false;
            }
        } else {
            if (array[array.length - 2] < array[array.length - 1]) {
                return  false;
            }
        }

        return true;
    }

    private static void print(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int num : array) {
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(num);
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
