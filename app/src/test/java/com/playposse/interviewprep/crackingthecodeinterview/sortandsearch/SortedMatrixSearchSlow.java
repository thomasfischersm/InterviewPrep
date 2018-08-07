package com.playposse.interviewprep.crackingthecodeinterview.sortandsearch;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by thoma on 8/7/2018.
 */
public class SortedMatrixSearchSlow {

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

    private static Coordinate findElement(int[][] matrix, int x) {
        if (matrix.length == 0) {
            return null;
        }

        int row = 0;
        int column = matrix[0].length - 1;
        while ((row < matrix.length) && (column >= 0)) {
            if (matrix[row][column] == x) {
                return new Coordinate(row, column);
            }

            if (matrix[row][column] < x) {
                row++;
            } else {
                column--;
            }
        }
        return null;
    }

    private static class Coordinate {

        private final int row;
        private final int column;

        public Coordinate(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
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
