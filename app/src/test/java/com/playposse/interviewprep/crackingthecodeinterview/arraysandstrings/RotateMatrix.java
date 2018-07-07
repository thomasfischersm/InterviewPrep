package com.playposse.interviewprep.crackingthecodeinterview.arraysandstrings;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by thoma on 7/6/2018.
 */
public class RotateMatrix {

    @Test
    public void test() {
        test(new int[][]{{1, 2}, {3, 4}}, new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}});
        test(new int[][]{{1, 2}, {3, 4}}, new int[][]{{1, 2}, {3, 4}});
        test(new int[][]{{1, 2}, {3, 4}}, new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }

    private void test(int[][] expectedMatrix, int[][] inputMatrix) {
        rotate(inputMatrix);
        assertArrayEquals(expectedMatrix, inputMatrix);
    }

    private static void rotate(int[][] matrix) {
        System.out.println("\nBefore");
        dump(matrix);

        int length = matrix.length;
        for (int j = 0; j < length / 2; j++) {
            for (int i = j; i < length - j - 1; i++) {
                int swap = matrix[i][j]; // top
                matrix[i][j] = matrix[length - 1 - j][i]; // right
                matrix[length - 1 - j][length - 1 - i] = matrix[i][length - 1 - j]; // bottom
                matrix[i][length - 1 - j] = matrix[j][length - 1 - i]; // left
                matrix[j][length - 1 - i] = swap; // top
            }
        }

        System.out.println("\nAfter");
        dump(matrix);
    }

    private static void dump(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(String.format("%2d", matrix[i][j]));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
