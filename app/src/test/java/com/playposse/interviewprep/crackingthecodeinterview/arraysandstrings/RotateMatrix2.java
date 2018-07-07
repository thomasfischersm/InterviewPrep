package com.playposse.interviewprep.crackingthecodeinterview.arraysandstrings;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Starting over clean for {@link RotateMatrix}.
 */
public class RotateMatrix2 {
    @Test
    public void test() {
        test(new int[][]{{13, 9, 5, 1}, {14, 10, 6, 2}, {15, 11, 7, 3}, {16, 12, 8, 4}}, new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}});
        test(new int[][]{{3, 1}, {4, 2}}, new int[][]{{1, 2}, {3, 4}});
//        test(new int[][]{{1, 2}, {3, 4}}, new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }

    private void test(int[][] expectedMatrix, int[][] inputMatrix) {
        rotate(inputMatrix);
        assertArrayEquals(expectedMatrix, inputMatrix);
    }

    private static void rotate(int[][] matrix) {
        if ((matrix == null) || (matrix.length <= 1)) {
            return;
        }

        System.out.println("\nBefore");
        dump(matrix);

        for (int level = 0; level < matrix.length / 2; level++) {

            int start = 0 + level;
            int end = matrix.length - level - 1;

            for (int i = start; i < end; i++) {
                System.out.println("\nLevel " + level + " i " + i);
                // top -> swap
                int swap = matrix[i][level];
                System.out.println("(" + i + ", " + level + ") -> swap");

                // left -> top
                matrix[i][level] = matrix[matrix.length - 1 - level][i];
                System.out.println("(" + (matrix.length - 1 - level) + ", " + (i) + ")" + "->" + "(" + (i) + ", " + (level) + ")");
                dump(matrix);

                // bottom -> left
                matrix[matrix.length - 1 - level][i] = matrix[matrix.length - i - 1][matrix.length - 1 - level];
                System.out.println("(" + (matrix.length - i - 1) + ", " + (matrix.length - 1 - level) + ")" + "->" + "(" + (matrix.length - 1 - level) + ", " + (i) + ")");
                dump(matrix);

                // bottom -> right
                matrix[matrix.length - i - 1][matrix.length - 1 - level] = matrix[level][matrix.length - 1 - i];
                System.out.println("(" + (level) + ", " + (matrix.length - 1 - i) + ")" + "->" + "(" + (matrix.length - i - 1) + ", " + (matrix.length - 1 - level) + ")");
                dump(matrix);

                // swap -> right
                matrix[level][matrix.length - 1 - i] = swap;
                System.out.println("swap " + "->" + "(" + (level) + ", " + (matrix.length - 1 - i) + ")");
                dump(matrix);

                System.out.println("------------------------------------------");
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
