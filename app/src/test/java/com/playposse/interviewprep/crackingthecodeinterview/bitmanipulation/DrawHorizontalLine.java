package com.playposse.interviewprep.crackingthecodeinterview.bitmanipulation;

import org.junit.Test;

/**
 * Created by thoma on 7/30/2018.
 */
public class DrawHorizontalLine {

    @Test
    public void test0() {
        System.out.println("Empty");
        print(new byte[8 * 8], 8 * 8);
    }

    @Test
    public void test1() {
        System.out.println("Max positive");
        byte[] bitmap = new byte[8 * 8];
        bitmap[0] = Byte.MAX_VALUE;
        print(bitmap, 8 * 8);
    }

    @Test
    public void test2() {
        System.out.println("Min negative");
        byte[] bitmap = new byte[8 * 8];
        bitmap[0] = Byte.MIN_VALUE;
        print(bitmap, 8 * 8);
    }

    @Test
    public void test3() {
        System.out.println("-1");
        byte[] bitmap = new byte[8 * 8];
        bitmap[0] = -1;
        print(bitmap, 8 * 8);
    }

    @Test
    public void test4() {
        System.out.println("First bit line");
        byte[] bitmap = new byte[8 * 8];
        drawLine(bitmap, 8 * 8, 0, 0, 0);
        print(bitmap, 8 * 8);
    }


    @Test
    public void test5() {
        System.out.println("Two bit line");
        byte[] bitmap = new byte[8 * 8];
        drawLine(bitmap, 8 * 8, 0, 1, 0);
        print(bitmap, 8 * 8);
    }

    @Test
    public void test6() {
        System.out.println("Three bit line");
        byte[] bitmap = new byte[8 * 8];
        drawLine(bitmap, 8 * 8, 0, 2, 0);
        print(bitmap, 8 * 8);
    }

    @Test
    public void test7() {
        System.out.println("First byte line");
        byte[] bitmap = new byte[8 * 8];
        drawLine(bitmap, 8 * 8, 0, 7, 0);
        print(bitmap, 8 * 8);
    }

    @Test
    public void test8() {
        System.out.println("First byte plus 1 bit line");
        byte[] bitmap = new byte[8 * 8];
        drawLine(bitmap, 8 * 8, 0, 8, 0);
        print(bitmap, 8 * 8);
    }

    @Test
    public void test9() {
        System.out.println("First two byte line");
        byte[] bitmap = new byte[8 * 8];
        drawLine(bitmap, 8 * 8, 0, 15, 0);
        print(bitmap, 8 * 8);
    }

    @Test
    public void test10() {
        System.out.println("First two byte on second line");
        byte[] bitmap = new byte[8 * 8];
        drawLine(bitmap, 8 * 8, 0 + 8 * 8, 15 + 8 * 8, 0);
        print(bitmap, 8 * 8);
    }

    @Test
    public void test11() {
        System.out.println("First two byte on second line + indend by 1");
        byte[] bitmap = new byte[8 * 8];
        drawLine(bitmap, 8 * 8, 1 + 8 * 8, 16 + 8 * 8, 0);
        print(bitmap, 8 * 8);
    }

    @Test
    public void test12() {
        System.out.println("First row");
        byte[] bitmap = new byte[8 * 8];
        drawLine(bitmap, 8 * 8, 0, 8 * 8 - 1, 0);
        print(bitmap, 8 * 8);
    }

    @Test
    public void test13() {
        System.out.println("First row inset by 1");
        byte[] bitmap = new byte[8 * 8];
        drawLine(bitmap, 8 * 8, 1, 8 * 8 - 2, 0);
        print(bitmap, 8 * 8);
    }

    private static void drawLine(byte[] bitmap, int width, int x0, int x1, int y) {
        // Make sure that x0 is smaller.
        if (x0 > x1) {
            int swap = x0;
            x0 = x1;
            x1 = swap;
        }

        int yOffset = y * width;
        int xByte0 = yOffset + (x0 / 8);
        int xBit0 = x0 % 8;
        int xByte1 = yOffset + (x1 / 8);
        int xBit1 = x1 % 8;

        if (xByte0 < xByte1) {
            // Get first partial
            if (xBit0 < 7) {
                bitmap[xByte0] |= ~(1 << (xBit0 - 1));
            }

            if (xBit1 >= 0) {
                bitmap[xByte1] |= ((1 << (xBit1 + 1)) - 1);
            }
        } else {
            // Line starts and ends in the first byte.
            int mask = ~(1 << (xBit0 - 1)) & ((1 << (xBit1 + 1)) - 1);
            bitmap[xByte0] |= mask;
            return;
        }

        if (xByte0 < xByte1 - 1) {
            for (int i = xByte0 + 1; i < xByte1; i++) {
                bitmap[i] = -1;
            }
        }
    }

    private static void print(byte[] bitmap, int width) {
        printLine(width);

        for (int i = 0; i < bitmap.length; i++) {
            for (int j = 0; j < 8; j++) {
                if ((bitmap[i] & (1 << j)) > 0) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }

            if ((i + 1) % (width / 8) == 0) {
                System.out.println("|");
            }
        }

        printLine(width);
    }

    private static void printLine(int width) {
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
