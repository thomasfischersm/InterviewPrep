package com.playposse.interviewprep.crackingthecodeinterview.recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by thoma on 7/30/2018.
 */
public class RobotPath {

    @Test
    public void test0() {
        test(10, 10, new HashSet<Point>());
    }

    @Test
    public void test1() {
        HashSet<Point> offLimitPoints = new HashSet<>();
        offLimitPoints.add(new Point(8, 9));
        test(10, 10, offLimitPoints);
    }

    @Test
    public void test2() {
        HashSet<Point> offLimitPoints = new HashSet<>();
        offLimitPoints.add(new Point(8, 9));
        offLimitPoints.add(new Point(0, 2));
        test(10, 10, offLimitPoints);
    }

    @Test
    public void test3() {
        HashSet<Point> offLimitPoints = new HashSet<>();
        offLimitPoints.add(new Point(8, 9));
        offLimitPoints.add(new Point(0, 2));
        offLimitPoints.add(new Point(6, 8));
        test(10, 10, offLimitPoints);
    }

    private static void test(int width, int height, HashSet<Point> offLimitPoints) {
        List<Point> path = findPath(width, height, offLimitPoints);
        print(width, height, offLimitPoints, path);
    }


    private static List<Point> findPath(int width, int height, Set<Point> offLimitPoints) {
        int x = width - 1;
        int y = height -1;

        return tryPoint(x, y, offLimitPoints, new ArrayList<Point>(), new HashSet<Point>());
    }

    private static List<Point> tryPoint(
            int x,
            int y,
            Set<Point> offLimitPoints,
            List<Point> currentPath,
            Set<Point> badPoints) {

        Point thisPoint = new Point(x, y);
        currentPath.add(thisPoint);

        if ((x == 0) && (y == 0)) {
            return currentPath;
        }

        Point leftPoint = new Point(x - 1, y);
        if ((x > 0) && !offLimitPoints.contains(leftPoint) && !badPoints.contains(leftPoint)) {
            List<Point> path = tryPoint(x - 1, y, offLimitPoints, currentPath, badPoints);
            if (path != null) {
                return path;
            }
        }

        Point topPoint = new Point(x, y - 1);
        if ((y > 0) && !offLimitPoints.contains(topPoint) && !badPoints.contains(topPoint)) {
            List<Point> path = tryPoint(x, y - 1, offLimitPoints, currentPath, badPoints);
            if (path != null) {
                return path;
            }
        }

        currentPath.remove(currentPath.size() -1);
        badPoints.add(thisPoint);
        return null;
    }

    private static void print(int width, int height, Set<Point> offLimitPoints, List<Point> path) {
        printLine(width);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < height; x++) {
                Point point = new Point(x, y);
                if (offLimitPoints.contains(point)) {
                    System.out.print("X");
                } else if (path.contains(point)) {
                    System.out.print("O");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        printLine(width);
    }

    private static final void printLine(int width) {
        for (int i = 0; i < width; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    private static class Point {

        private final int x;
        private final int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
