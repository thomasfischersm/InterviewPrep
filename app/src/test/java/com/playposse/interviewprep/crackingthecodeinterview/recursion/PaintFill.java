package com.playposse.interviewprep.crackingthecodeinterview.recursion;

import org.junit.Test;

/**
 * Created by thoma on 7/31/2018.
 */
public class PaintFill {

    @Test
    public void test0() {
        int[][] bitmap = new int[5][5];
        print(bitmap);
        paint(bitmap, 1, 1, 1);
        print(bitmap);
    }

    @Test
    public void test1() {
        int[][] bitmap = new int[5][5];
        bitmap[0][1] = 2;
        bitmap[1][1] = 2;
        print(bitmap);

        paint(bitmap, 1, 1, 1);
        print(bitmap);
    }

    @Test
    public void test2() {
        int[][] bitmap = new int[5][5];
        bitmap[0][1] = 2;
        bitmap[1][1] = 2;
        print(bitmap);

        paint(bitmap, 0, 0, 1);
        print(bitmap);
    }

    @Test
    public void test3() {
        int[][] bitmap = new int[5][5];
        bitmap[0][1] = 2;
        bitmap[1][1] = 2;
        bitmap[1][0] = 2;
        print(bitmap);

        paint(bitmap, 0, 0, 1);
        print(bitmap);
    }

    @Test
    public void test4() {
        int[][] bitmap = new int[5][5];
        bitmap[0][1] = 2;
        bitmap[1][1] = 2;
        bitmap[1][0] = 2;
        bitmap[2][1] = 2;
        bitmap[3][1] = 2;
        bitmap[3][2] = 2;
        bitmap[3][3] = 2;
        bitmap[4][3] = 2;
        print(bitmap);

        paint(bitmap, 1, 0, 1);
        print(bitmap);
    }

    @Test
    public void test5() {
        int[][] bitmap = new int[5][5];
        bitmap[0][1] = 2;
        bitmap[1][1] = 2;
        bitmap[1][0] = 2;
        bitmap[2][1] = 2;
        bitmap[3][1] = 2;
        bitmap[3][2] = 2;
        bitmap[3][3] = 2;
        bitmap[4][3] = 2;
        print(bitmap);

        paint(bitmap, 2, 0, 1);
        print(bitmap);
    }

    private static void paint(int[][] bitmap, int x, int y, int paintColor) {
        paint(bitmap, x, y, bitmap[y][x], paintColor);
    }

    private static void paint(int[][] bitmap, int x, int y, int originColor, int paintColor) {
        if ((x < 0) || (x >= bitmap[0].length) || (y < 0) || (y >= bitmap.length)) {
            return;
        }
        if (bitmap[y][x] != originColor) {
            return;
        }

        bitmap[y][x] = paintColor;

        paint(bitmap, x - 1, y, originColor, paintColor);
        paint(bitmap, x, y - 1, originColor, paintColor);
        paint(bitmap, x + 1, y, originColor, paintColor);
        paint(bitmap, x, y + 1, originColor, paintColor);
    }

    private static void print(int[][] bitmap) {
        printLine(bitmap[0].length);

        for (int y = 0; y < bitmap.length; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < bitmap[0].length; x++) {
                sb.append(bitmap[y][x]);
            }
            System.out.println(sb.toString());
        }

        printLine(bitmap[0].length);
        System.out.println();
    }

    private static void printLine(int width) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < width; i++) {
            sb.append("-");
        }
        System.out.println(sb.toString());
    }
}
