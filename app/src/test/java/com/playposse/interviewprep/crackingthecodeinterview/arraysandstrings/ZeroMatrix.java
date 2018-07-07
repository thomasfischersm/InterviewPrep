package com.playposse.interviewprep.crackingthecodeinterview.arraysandstrings;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * A square matrix is given. Scan the matrix for 0 values. When a zero value is encountered, the
 * entire row and column should be zeroed.
 */
public class ZeroMatrix {

    @Test
    public void test() {
        test(new int[][]{{1}}, new int[][]{{1}});
        test(new int[][]{{0}}, new int[][]{{0}});
        test(new int[][]{{1, 2}, {3, 4}}, new int[][]{{1, 2}, {3, 4}});
        test(new int[][]{{0, 0}, {3, 0}}, new int[][]{{1, 0}, {3, 4}});
        test(new int[][]{{1, 2, 3}, {2, 3, 4}, {5, 6, 7}}, new int[][]{{1, 2, 3}, {2, 3, 4}, {5, 6, 7}});
        test(new int[][]{{1, 0, 3}, {0, 0, 0}, {5, 0, 7}}, new int[][]{{1, 2, 3}, {2, 0, 4}, {5, 6, 7}});
        test(new int[][]{{0, 0, 0}, {0, 3, 4}, {0, 6, 7}}, new int[][]{{0, 2, 3}, {2, 3, 4}, {5, 6, 7}});
        test(new int[][]{{1, 2, 0}, {2, 3, 0}, {0, 0, 0}}, new int[][]{{1, 2, 3}, {2, 3, 4}, {5, 6, 0}});
        test(new int[][]{{0, 0, 0}, {0, 3, 0}, {0, 0, 0}}, new int[][]{{0, 2, 3}, {2, 3, 4}, {5, 6, 0}});
        test(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, new int[][]{{0, 0, 0}, {2, 3, 4}, {5, 6, 7}});
    }

    private void test(int[][] expected, int[][] input) {
        zero(input);
        assertArrayEquals(expected, input);
    }

    private static void zero(int[][] matrix) {
        System.out.println("Before");
        dump(matrix);

        boolean isFirstRowZero = false;
        boolean isFirstColumnZero = false;

        // Scan for zero values and set the first row/column to zero for that.
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;

                    if (i == 0) {
                        isFirstRowZero = true;
                    }

                    if (j == 0) {
                        isFirstColumnZero = true;
                    }
                    break;
                }
            }
        }

        System.out.println("Got zeroes marked");
        dump(matrix);

        // Zero rows and columns. Start from the end to avoid overwriting our markers in the first
        // row and column.
        for (int i = matrix.length - 1; i > 0; i--) {
            if (matrix[i][0] == 0) {
                nullifyRow(matrix, i);
            }
        }

        System.out.println("Did first zero path");
        dump(matrix);

        for (int j = matrix.length - 1; j > 0; j--) {
            if (matrix[0][j] == 0) {
                nullifyColumn(matrix, j);
            }
        }

        if (isFirstRowZero) {
            nullifyRow(matrix, 0);
        }

        if (isFirstColumnZero) {
            nullifyColumn(matrix, 0);
        }

        System.out.println("After");
        dump(matrix);
    }

    private static void nullifyColumn(int[][] matrix, int j) {
        for (int i = matrix.length - 1; i > 0; i--) {
            matrix[i][j] = 0;
        }
    }

    private static void nullifyRow(int[][] matrix, int i) {
        for (int j = matrix.length - 1; j > 0; j--) {
            matrix[i][j] = 0;
        }
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
