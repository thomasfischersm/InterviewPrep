package com.playposse.interviewprep.crackingthecodeinterview.recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by thoma on 7/31/2018.
 */
public class StackBoxes {

    @Test
    public void test0() {
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(1, 1, 1));

        System.out.println(createHighestBoxStack(boxes));
    }

    @Test
    public void test1() {
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(1, 1, 1));
        boxes.add(new Box(2, 2, 2));

        System.out.println(createHighestBoxStack(boxes));
    }

    @Test
    public void test2() {
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(1, 1, 1));
        boxes.add(new Box(2, 2, 2));
        boxes.add(new Box(3, 3, 3));

        System.out.println(createHighestBoxStack(boxes));
    }

    private static Set<List<Box>> createHighestBoxStack(Set<Box> boxes) {
        List<Integer> sortedHeights = new ArrayList<>();
        Map<Integer, List<Box>> boxesByHeight = new HashMap<>();

        for (Box box : boxes) {
            int boxHeight = box.getHeight();

            final List<Box> boxList;
            if (!boxesByHeight.containsKey(boxHeight)) {
                boxList = new ArrayList<>();
                boxesByHeight.put(boxHeight, boxList);
                sortedHeights.add(boxHeight);
            } else {
                boxList = boxesByHeight.get(boxHeight);
            }
            boxList.add(box);
        }

        Collections.sort(sortedHeights, new Comparator<Integer>() {
            @Override
            public int compare(Integer i0, Integer i1) {
                return Integer.compare(i0, i1) * -1;
            }
        });

        HashSet<List<Box>> results = new HashSet<>();
        createHighestBoxStack(
                sortedHeights,
                boxesByHeight,
                0,
                new ArrayList<Box>(),
                results);

        return results;
    }

    private static void createHighestBoxStack(
            List<Integer> sortedHeights,
            Map<Integer, List<Box>> boxesByHeight,
            int heightIndex,
            List<Box> pickedBoxes,
            Set<List<Box>> results) {

        if (heightIndex >= sortedHeights.size()) {
            results.add(new ArrayList<>(pickedBoxes));
            return;
        }

        final Box lastBox;
        if (!pickedBoxes.isEmpty()) {
            lastBox = pickedBoxes.get(pickedBoxes.size() - 1);
        } else {
            lastBox = null;
        }

        List<Box> possibleBoxes = boxesByHeight.get(sortedHeights.get(heightIndex));
        for (int i = 0; i < possibleBoxes.size(); i++) {
            Box currentBox = possibleBoxes.get(i);
            if (currentBox.fitsOnTop(lastBox)) {
                pickedBoxes.add(currentBox);

                createHighestBoxStack(
                        sortedHeights,
                        boxesByHeight,
                        heightIndex + 1,
                        pickedBoxes,
                        results);

                pickedBoxes.remove(pickedBoxes.size() - 1);
            }
        }

        // Try next smaller box without placing one here.
        createHighestBoxStack(
                sortedHeights,
                boxesByHeight,
                heightIndex + 1,
                pickedBoxes,
                results);
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
