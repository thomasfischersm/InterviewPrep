package com.playposse.interviewprep.leetcode.competition2;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by thoma on 7/21/2018.
 */
public class RobotSim {

    @Test
    public void test() {
        assertEquals(16, (new Solution().robotSim(
                new int[]{4},
                new int[][]{new int[]{10, 10}})));

        assertEquals(65, (new Solution().robotSim(
                new int[]{4, -1, 4, -2, 4},
                new int[][]{new int[]{2, 4}})));

        assertEquals(25, (new Solution().robotSim(
                new int[]{-1, -1, -1, -1, -1, -2, 5},
                new int[][]{new int[]{2, 4}})));

        assertEquals(9, (new Solution().robotSim(
                new int[]{-1, -1, 6},
                new int[][]{new int[]{0, -4}})));

        assertEquals(18, (new Solution().robotSim(
                new int[]{-1, -1, 6, -1, 3},
                new int[][]{new int[]{0, -4}})));

        assertEquals(9, (new Solution().robotSim(
                new int[]{-1, -1, 6, 5, 6, -2, -2, 3},
                new int[][]{new int[]{0, -4}})));

        assertEquals(0, (new Solution().robotSim(
                new int[]{-2, -1, 8, 9, 6},
                new int[][]{
                        new int[]{-1, 3},
                        new int[]{0, 1},
                        new int[]{-1, 5},
                        new int[]{-2, -4},
                        new int[]{5, 4},
                        new int[]{-2, -3},
                        new int[]{5, -1},
                        new int[]{1, -1},
                        new int[]{5, 5},
                        new int[]{5, 2}})));
    }

    class Solution {

        public int robotSim(int[] commands, int[][] obstacles) {
            Set<Obstacle> obstacleSet = new HashSet<>();
            for (int i = 0; i < obstacles.length; i++) {
                int obstacleX = obstacles[i][0];
                int obstacleY = obstacles[i][1];
                obstacleSet.add(new Obstacle(obstacleX, obstacleY));
            }

            int x = 0;
            int y = 0;
            int direction = 0;

            int result = 0;

            for (int i = 0; i < commands.length; i++) {
                switch (commands[i]) {
                    case -2:
                        direction -= 90;
                        if (direction < 0) {
                            direction += 360;
                        }
                        break;
                    case -1:
                        direction += 90;
                        if (direction >= 360) {
                            direction -= 360;
                        }
                        break;
                    default:
                        int distance = commands[i];

                        for (int j = 0; j < distance; j++) {
                            int possibleX = x;
                            int possibleY = y;
                            switch (direction) {
                                case 0:
                                    possibleY++;
                                    break;
                                case 90:
                                    possibleX++;
                                    break;
                                case 180:
                                    possibleY--;
                                    break;
                                case 270:
                                    possibleX--;
                            }

//                            if (isObstacle(possibleX, possibleY, obstacles)) {
                            if (obstacleSet.contains(new Obstacle(possibleX, possibleY))) {
                                break;
                            } else {
                                x = possibleX;
                                y = possibleY;
                            }
                        }

                        break;
                }

                result = Math.max(result, x * x + y * y);
            }

            return result;
        }

        private boolean isObstacle(int x, int y, int[][] obstacles) {
            for (int i = 0; i < obstacles.length; i++) {
                int obstacleX = obstacles[i][0];
                int obstacleY = obstacles[i][1];

                if ((x == obstacleX) && (y == obstacleY)) {
                    return true;
                }
            }

            return false;
        }

        private class Obstacle {
            private final int x;
            private final int y;

            public Obstacle(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public int hashCode() {
                return Arrays.hashCode(new int[]{x, y});
            }

            @Override
            public boolean equals(Object obj) {
                if (!(obj instanceof Obstacle)) {
                    return false;
                }

                Obstacle other = (Obstacle) obj;
                return (x == other.x) && (y == other.y);
            }
        }
    }
}


/*
Expect: 1954

[2,5,2,5,-1,3,4,2,4,-2,5,8,-1,-2,-2,-1,8,-1,-2,-2,-1,-1,5,-1,-1,1,5,9,1,-1,-2,-1,-2,3,7,2,3,5,9,-2,5,2,1,4,6,5,9,1,9,6,3,-1,-1,9,9,-1,1,6,3,-2,2,2,5,-1,-1,-2,9,6,5,5,9,2,5,-2,-2,5,7,-1,-2,-1,2,-1,1,-1,-1,2,8,-1,8,3,-2,3,-1,-2,4,7,3,8,-1,2]
[[-5,-77],[35,40],[58,-30],[31,-96],[40,14],[-25,50],[37,-38],[-54,-31],[64,-41],[72,53],[83,-95],[-31,-61],[68,32],[-56,16],[88,-81],[-48,-31],[56,-57],[-74,24],[-42,-59],[72,-86],[40,34],[-85,-45],[22,-35],[-95,56],[-66,42],[-67,94],[46,10],[35,27],[-9,-6],[-84,-97],[38,-22],[3,-39],[71,-97],[-40,-86],[-45,56],[-91,59],[24,-11],[91,100],[-68,43],[34,27],[-60,32],[-20,34],[-34,34],[-31,-53],[52,-98],[-70,-15],[73,-41],[-94,95],[-78,-42],[-7,-11],[-37,-94],[26,-74],[-53,68],[72,2],[-80,-58],[-94,48],[-80,-57],[-35,69],[17,-45],[-72,-76],[21,99],[-25,-19],[-48,86],[86,-47],[59,-66],[-38,-16],[47,-44],[28,96],[92,-64],[-62,-35],[-63,-87],[-83,95],[25,-89],[30,-40],[-75,93],[47,-21],[12,-93],[70,-22],[-64,-43],[-17,-86],[-33,93],[-74,-7],[-78,5],[-37,-11],[-84,-29],[-29,-11],[17,9],[-64,10],[25,29],[14,25],[19,42],[71,52],[30,-76],[19,66],[40,99],[-61,-95],[-17,40],[6,-21],[7,28],[-4,85],[71,99],[50,99],[-53,-95],[-8,8],[63,-79],[88,-35],[50,-38],[-60,-31],[-2,-8],[-8,91],[-14,50],[-25,-26],[1,71],[-91,-64],[-40,46],[30,-97],[9,-55],[-36,-75],[71,99],[90,-53],[-68,-20],[-73,89],[-14,74],[-8,72],[82,48],[45,2],[-42,12],[77,22],[87,56],[73,-21],[78,15],[-6,-75],[24,46],[-11,-70],[-90,-77],[57,43],[-66,10],[-30,-47],[91,-37],[-4,-67],[-88,19],[66,29],[86,97],[-4,67],[54,-92],[-54,71],[-42,-17],[57,-91],[-9,-15],[-26,56],[-57,-58],[-72,91],[-55,35],[-20,29],[51,70],[-61,88],[-62,99],[52,17],[-75,-32],[91,-22],[54,33],[-45,-59],[47,-48],[53,-98],[-91,83],[81,12],[-34,-90],[-79,-82],[-15,-86],[-24,66],[-35,35],[3,31],[87,93],[2,-19],[87,-93],[24,-10],[84,-53],[86,87],[-88,-18],[-51,89],[96,66],[-77,-94],[-39,-1],[89,51],[-23,-72],[27,24],[53,-80],[52,-33],[32,4],[78,-55],[-25,18],[-23,47],[79,-5],[-23,-22],[14,-25],[-11,69],[63,36],[35,-99],[-24,82],[-29,-98]]
 */
