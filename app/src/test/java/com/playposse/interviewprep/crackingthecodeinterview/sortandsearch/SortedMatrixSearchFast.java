package com.playposse.interviewprep.crackingthecodeinterview.sortandsearch;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by thoma on 8/7/2018.
 */
public class SortedMatrixSearchFast {

    @Test
    public void test0() {
        int[][] matrix = new int[][]{};
        assertNull(findElement(matrix, 5));
    }

    @Test
    public void test1() {
        int[][] matrix = new int[][]{{1}};
        assertEquals(new Coordinate(0, 0), findElement(matrix, 1));
    }

    @Test
    public void test2() {
        int[][] matrix = new int[][]{{1}};
        assertNull(findElement(matrix, 2));
    }

    @Test
    public void test3() {
        int[][] matrix = new int[][]{{1, 2}, {3, 4}};
        assertNull(findElement(matrix, 0));
        assertEquals(new Coordinate(0, 0), findElement(matrix, 1));
        assertEquals(new Coordinate(0, 1), findElement(matrix, 2));
        assertEquals(new Coordinate(1, 0), findElement(matrix, 3));
        assertEquals(new Coordinate(1, 1), findElement(matrix, 4));
        assertNull(findElement(matrix, 5));
    }

    @Test
    public void test4() {
        int[][] matrix = new int[][]{{1, 3, 5}, {2, 4, 6}, {7, 8, 9}};
        assertNull(findElement(matrix, 0));
        assertEquals(new Coordinate(0, 0), findElement(matrix, 1));
        assertEquals(new Coordinate(1, 0), findElement(matrix, 2));
        assertEquals(new Coordinate(0, 1), findElement(matrix, 3));
        assertEquals(new Coordinate(1, 1), findElement(matrix, 4));
        assertEquals(new Coordinate(0, 2), findElement(matrix, 5));
        assertEquals(new Coordinate(1, 2), findElement(matrix, 6));
        assertEquals(new Coordinate(2, 0), findElement(matrix, 7));
        assertEquals(new Coordinate(2, 1), findElement(matrix, 8));
        assertEquals(new Coordinate(2, 2), findElement(matrix, 9));
        assertNull(findElement(matrix, 10));
    }

    @Test
    public void test5() {
        int[][] matrix = new int[][]{
                {1, 3, 5, 6},
                {2, 4, 7, 8},
                {9, 10, 11, 12},
                {100, 200, 300, 400}};
        assertNull(findElement(matrix, 0));
        assertEquals(new Coordinate(0, 0), findElement(matrix, 1));
        assertEquals(new Coordinate(1, 0), findElement(matrix, 2));
        assertEquals(new Coordinate(0, 1), findElement(matrix, 3));
        assertEquals(new Coordinate(1, 1), findElement(matrix, 4));
        assertEquals(new Coordinate(0, 2), findElement(matrix, 5));
        assertEquals(new Coordinate(0, 3), findElement(matrix, 6));
        assertEquals(new Coordinate(1, 2), findElement(matrix, 7));
        assertEquals(new Coordinate(1, 3), findElement(matrix, 8));
        assertEquals(new Coordinate(2, 0), findElement(matrix, 9));
        assertEquals(new Coordinate(2, 1), findElement(matrix, 10));
        assertEquals(new Coordinate(2, 2), findElement(matrix, 11));
        assertEquals(new Coordinate(2, 3), findElement(matrix, 12));
        assertEquals(new Coordinate(3, 0), findElement(matrix, 100));
        assertEquals(new Coordinate(3, 1), findElement(matrix, 200));
        assertEquals(new Coordinate(3, 2), findElement(matrix, 300));
        assertEquals(new Coordinate(3, 3), findElement(matrix, 400));
        assertNull(findElement(matrix, 13));
        assertNull(findElement(matrix, 99));
        assertNull(findElement(matrix, 101));
        assertNull(findElement(matrix, 401));
        assertNull(findElement(matrix, 500));
    }

    @Test
    public void test6() {
        int[][] matrix = new int[][]{{1, 3, 5}};
        assertNull(findElement(matrix, 0));
        assertEquals(new Coordinate(0, 0), findElement(matrix, 1));
        assertEquals(new Coordinate(0, 1), findElement(matrix, 3));
        assertEquals(new Coordinate(0, 2), findElement(matrix, 5));
        assertNull(findElement(matrix, 10));
    }

    @Test
    public void test7() {
        int[][] matrix = new int[][]{{1}, {3}, {5}};
        assertNull(findElement(matrix, 0));
        assertEquals(new Coordinate(0, 0), findElement(matrix, 1));
        assertEquals(new Coordinate(1, 0), findElement(matrix, 3));
        assertEquals(new Coordinate(2, 0), findElement(matrix, 5));
        assertNull(findElement(matrix, 10));
    }

    private static Coordinate findElement(int[][] matrix, int x) {
        System.out.println("Searching for " + x + " in grid: ");
        printMatrix(matrix);

        if ((matrix == null) || (matrix.length == 0)) {
            return null;
        }

        Coordinate result = find(
                matrix,
                x,
                new Coordinate(0, 0),
                new Coordinate(matrix.length - 1, matrix[0].length - 1));
        System.out.println("=> Finished with result " + result + "\n\n");
        return result;
    }

    private static Coordinate find(int[][] matrix, int x, Coordinate start, Coordinate end) {
        System.out.println("Starting binary search from (" + start.column + ", " + start.row
                + " to (" + end.column + ", " + end.row + ").");

        if (!start.isInBounds(matrix) || !end.isInBounds(matrix)) {
            System.out.println("-> out of bounds!");
            return null;
        }

        if (matrix[start.row][start.column] == x) {
            System.out.println("-> found target!");
            return start;
        }

        if (!start.isBefore(end)) {
            System.out.println("-> start after end!");
            return null;
        }

        int minDiff = Math.min(end.row - start.row, end.column - start.column);
        Coordinate lowerBound = start.copy();
        Coordinate upperBound = new Coordinate(
                Math.min(end.row, start.row + minDiff),
                Math.min(end.column, start.column + minDiff));

        while (lowerBound.isBefore(upperBound)) {
            Coordinate midCoordinate = new Coordinate(
                    (upperBound.row + lowerBound.row) / 2,
                    (upperBound.column + lowerBound.column) / 2);
            int midValue = matrix[midCoordinate.row][midCoordinate.column];

            System.out.println("- Trying mid point ("
                    + midCoordinate.getColumn() + ", " + midCoordinate.getRow() + ") with value "
                    + midValue);

            if (midValue == x) {
                return midCoordinate;
            } else if (x < midValue) {
                upperBound = new Coordinate(
                        midCoordinate.getRow() - 1,
                        midCoordinate.getColumn() - 1);
                System.out.println("-- Set upper bound to ("
                        + upperBound.getColumn() + ", " + upperBound.getRow() + ")");
            } else {
                lowerBound = new Coordinate(
                        midCoordinate.getRow() + 1,
                        midCoordinate.getColumn() + 1);
                System.out.println("-- Set lower bound to ("
                        + lowerBound.getColumn() + ", " + lowerBound.getRow() + ")");
            }
        }

        System.out.println("Found pivot point at ("
                + lowerBound.getColumn() + ", " + lowerBound.getRow() + ")");

        return partition(matrix, start, end, x, lowerBound);
    }

    private static Coordinate partition(
            int[][] matrix,
            Coordinate start,
            Coordinate end,
            int x,
            Coordinate lowerBound) {

        Coordinate lowerLeft = find(
                matrix,
                x,
                new Coordinate(lowerBound.getRow(), start.getColumn()),
                new Coordinate(end.getRow(), lowerBound.getColumn() - 1));
        if (lowerLeft != null) {
            return lowerLeft;
        } else {
            return find(
                    matrix,
                    x,
                    new Coordinate(start.getRow(), lowerBound.getColumn()),
                    new Coordinate(lowerBound.getRow() - 1, end.getColumn()));
        }
    }

    private static void printMatrix(int[][] matrix) {
        if ((matrix == null) || (matrix.length == 0)) {
            System.out.println("(empty grid");
            return;
        }

        for (int row = 0; row < matrix.length; row++) {
            StringBuilder sb = new StringBuilder();
            for (int column = 0; column < matrix[row].length; column++) {
                sb.append(String.format("%1$2d ", matrix[row][column]));
            }
            System.out.println(sb.toString());
        }
        System.out.println();
    }

    private static class Coordinate {

        private int row;
        private int column;

        private Coordinate(int row, int column) {
            this.row = row;
            this.column = column;
        }

        private int getRow() {
            return row;
        }

        private int getColumn() {
            return column;
        }

        private void setRow(int row) {
            this.row = row;
        }

        private void setColumn(int column) {
            this.column = column;
        }

        private boolean isBefore(Coordinate other) {
            return (row <= other.row) && (column <= other.column);
        }

        private boolean isInBounds(int[][] matrix) {
            return (row >= 0) && (row < matrix.length)
                    && (column >= 0) && (column < matrix[0].length);
        }

        private Coordinate copy() {
            return new Coordinate(row, column);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return row == that.row &&
                    column == that.column;
        }

        @Override
        public int hashCode() {

            return Objects.hash(row, column);
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "row=" + row +
                    ", column=" + column +
                    '}';
        }
    }
}
