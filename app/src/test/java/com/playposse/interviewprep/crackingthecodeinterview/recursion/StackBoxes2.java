package com.playposse.interviewprep.crackingthecodeinterview.recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/31/2018.
 */
public class StackBoxes2 {

    @Test
    public void test0() {
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(1, 1, 1));

        assertEquals(1, getHighestStack(boxes));
    }

    @Test
    public void test1() {
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(1, 1, 1));
        boxes.add(new Box(2, 2, 2));

        assertEquals(3, getHighestStack(boxes));
    }

    @Test
    public void test2() {
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(2, 1, 1));
        boxes.add(new Box(2, 2, 2));

        assertEquals(2, getHighestStack(boxes));
    }

    @Test
    public void test3() {
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(1, 1, 1));
        boxes.add(new Box(2, 2, 2));
        boxes.add(new Box(3, 3, 3));

        assertEquals(6, getHighestStack(boxes));
    }

    @Test
    public void test4() {
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(2, 1, 1));
        boxes.add(new Box(2, 3, 2));
        boxes.add(new Box(5, 3, 3));

        assertEquals(4, getHighestStack(boxes));
    }

    @Test
    public void test5() {
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(1, 1, 1));
        boxes.add(new Box(2, 2, 2));
        boxes.add(new Box(3, 3, 3));
        boxes.add(new Box(4, 4, 4));
        boxes.add(new Box(5, 5, 5));

        assertEquals(15, getHighestStack(boxes));
    }

    @Test
    public void test6() {
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(1, 1, 1));
        boxes.add(new Box(2, 2, 2));
        boxes.add(new Box(3, 3, 3));
        boxes.add(new Box(4, 4, 4));
        boxes.add(new Box(2, 5, 5));

        assertEquals(10, getHighestStack(boxes));
    }

    private static int getHighestStack(Set<Box> boxes) {
        List<Box> boxesByHeight = new ArrayList<>(boxes);
        Collections.sort(boxesByHeight, new Comparator<Box>() {
            @Override
            public int compare(Box box0, Box box1) {
                return Integer.compare(box0.getHeight(), box1.getHeight()) * -1;
            }
        });

        int[] cache = new int[boxes.size()];

        int maxHeight = 0;
        for (int i = 0; i < boxesByHeight.size(); i++) {
            maxHeight = Math.max(maxHeight, getHighestStack(boxesByHeight, i, cache));
        }
        return maxHeight;
    }

    private static int getHighestStack(List<Box> boxes, int boxIndex, int[] cache) {
        if (cache[boxIndex] > 0) {
            return cache[boxIndex];
        }

        Box currentBox = boxes.get(boxIndex);
        int maxHeight = 0;
        for (int i = boxIndex + 1; i < boxes.size(); i++) {
            Box nextBox = boxes.get(i);
            if (nextBox.fitsOnTop(currentBox)) {
                maxHeight = Math.max(maxHeight, getHighestStack(boxes, i, cache));
            }
        }

        maxHeight += currentBox.getHeight();
        cache[boxIndex] = maxHeight;
        return maxHeight;
    }

    private static class Box {

        private final int width;
        private final int length;
        private final int height;

        private Box(int width, int length, int height) {
            this.width = width;
            this.length = length;
            this.height = height;
        }

        private int getWidth() {
            return width;
        }

        private int getLength() {
            return length;
        }

        private int getHeight() {
            return height;
        }

        private boolean fitsOnTop(Box lastBox) {
            if (lastBox == null) {
                return true;
            } else {
                return (width < lastBox.getWidth())
                        && (length < lastBox.getLength())
                        && (height < lastBox.getHeight());
            }
        }

        @Override
        public String toString() {
            return "(width: " + width + ", length: " + length + ", height: " + height + ")";
        }
    }
}
