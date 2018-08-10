package com.playposse.interviewprep.crackingthecodeinterview.moderate;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 8/7/2018.
 */
public class TicTocWin {

    @Test
    public void test() {
        assertEquals(0, determineWinner(null));
        assertEquals(0, determineWinner(new int[][]{}));

        assertEquals(0, determineWinner(new int[][]{{0}}));
        assertEquals(1, determineWinner(new int[][]{{1}}));
        assertEquals(2, determineWinner(new int[][]{{2}}));

        assertEquals(0, determineWinner(new int[][]{{0, 0}, {0, 0}}));

        assertEquals(0, determineWinner(new int[][]{{1, 0}, {0, 0}}));
        assertEquals(0, determineWinner(new int[][]{{0, 1}, {0, 0}}));
        assertEquals(0, determineWinner(new int[][]{{0, 0}, {1, 0}}));
        assertEquals(0, determineWinner(new int[][]{{0, 0}, {0, 1}}));

        assertEquals(1, determineWinner(new int[][]{{1, 1}, {0, 0}}));
        assertEquals(1, determineWinner(new int[][]{{0, 0}, {1, 1}}));
        assertEquals(1, determineWinner(new int[][]{{1, 0}, {1, 0}}));
        assertEquals(1, determineWinner(new int[][]{{0, 1}, {0, 1}}));
        assertEquals(1, determineWinner(new int[][]{{1, 0}, {0, 1}}));
        assertEquals(1, determineWinner(new int[][]{{0, 1}, {1, 0}}));

        assertEquals(2, determineWinner(new int[][]{{1, 2}, {0, 2}}));
        assertEquals(2, determineWinner(new int[][]{{2, 2}, {1, 0}}));

        assertEquals(0, determineWinner(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
        assertEquals(1, determineWinner(new int[][]{{1, 2, 1}, {0, 1, 0}, {0, 2, 1}}));
        assertEquals(2, determineWinner(new int[][]{{1, 2, 0}, {0, 2, 1}, {1, 2, 0}}));
    }

    private static int determineWinner(int[][] board) {
        // 0 : empty field
        // 1 : player 1
        // 2 : player 2

        if ((board == null) || (board.length == 0)) {
            return 0;
        }

        if (board.length == 1) {
            return board[0][0];
        }

        int n = board.length;

        // Find horizontal winning combinations.
        outer:
        for (int rows = 0; rows < n; rows++) {
            int firstField = board[rows][0];
            if (firstField != 0) {
                for (int columns = 1; columns < n; columns++) {
                    if (firstField != board[rows][columns]) {
                        continue outer;
                    }
                }
                return firstField;
            }
        }

        // Find vertical winning combinations.
        outer:
        for (int columns = 0; columns < n; columns++) {
            int firstField = board[0][columns];
            if (firstField != 0) {
                for (int rows = 1; rows < n; rows++) {
                    if (firstField != board[rows][columns]) {
                        continue outer;
                    }
                }
                return firstField;
            }
        }

        // Find left top diagonal.
        int firstField = board[0][0];
        if (firstField != 0) {
            for (int i = 1; i < n; i++) {
                if (board[i][i] != firstField) {
                    firstField = -1;
                    break;
                }
            }
            if (firstField != -1) {
                return firstField;
            }
        }

        // Find right top diagonal
        firstField = board[0][n - 1];
        if (firstField != 0) {
            for (int i = 1; i < n; i++) {
                if (board[i][n - i - 1] != firstField) {
                    firstField = -1;
                    break;
                }
            }
            if (firstField != -1) {
                return firstField;
            }
        }

        return 0;
    }
}
