package com.huang.leecode;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 矩阵
 */
public class Matrix542 {

    public static void main(String[] args) {
        int[][] a = {{1,0,1,1,0,0,1,0,0,1},{0,1,1,0,1,0,1,0,1,1},{0,0,1,0,1,0,0,1,0,0},{1,0,1,0,1,1,1,1,1,1},{0,1,0,1,1,0,0,0,0,1},{0,0,1,0,1,1,1,0,1,0},{0,1,0,1,0,1,0,0,1,1},{1,0,0,0,1,1,1,1,0,1},{1,1,1,1,1,1,1,0,1,0},{1,1,1,1,0,1,0,0,1,1}};
        new Matrix542().updateMatrix(a);
        System.out.print(JSON.toJSONString(a));
    }

    private static int[][] calculated = null;

    public int[][] updateMatrix(int[][] matrix) {

        calculated = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j=0; j < matrix[0].length; j++) {
                int num = matrix[i][j];
                if (matrix[i][j] == 0 || calculated[i][j] != 0) {
                    //0、已经计算出距离的点
                    continue;
                }

                Point start = new Point(i, j);

                getMinDistanceFromStartTo0(matrix, start);
            }
        }

        return matrix;

    }

    private void getMinDistanceFromStartTo0(int[][] matrix, Point startPoint) {
        Queue<Point> queue = new ArrayDeque<>();

        int[] xmove = new int[]{1, -1, 0, 0};
        int[] ymove = new int[]{0, 0, -1, 1};

        queue.offer(startPoint);

        calculate : while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (matrix[p.x][p.y] == 0) {
                //找到距离startPoint最近的0
                int distanceFrom0 = 0;
                while (p.former != null) {
                    distanceFrom0 ++;
                    Point former = p.former;
                    p = former;
                }

                matrix[startPoint.getX()][startPoint.getY()] = distanceFrom0;
                calculated[startPoint.getX()][startPoint.getY()] = 1;
                break;
            } else {
                //当前Point不是0,且还没有计算出到0的最短距离
                List<Point> nextPointList = new ArrayList<>();
                for (int i=0; i < 4; i++) {
                    Point nextPoint = new Point(p.getX() + xmove[i], p.getY() + ymove[i], p);

                    if (nextPoint.getX() < 0 || nextPoint.getX() >= matrix.length
                            || nextPoint.getY() < 0 || nextPoint.getY() >= matrix[0].length) {
                        continue;
                    }
                    if (matrix[nextPoint.getX()][nextPoint.getY()] == 0) {
                        int distanceFrom0 = 0;
                        while (nextPoint.former != null) {
                            distanceFrom0 ++;
                            Point former = nextPoint.former;
                            nextPoint = former;
                        }

                        matrix[startPoint.getX()][startPoint.getY()] = distanceFrom0;
                        calculated[startPoint.getX()][startPoint.getY()] = 1;
                        break calculate;
                    }
                    nextPointList.add(nextPoint);
                }

                queue.addAll(nextPointList);
            }
        }

    }

    private class Point {
        private int x;
        private int y;
        private Point former; //上一个节点，起始节点是第一个节点
        private int distance; //距离最近的0的最短的距离

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, Point former) {
            this.x = x;
            this.y = y;
            this.former = former;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Point getFormer() {
            return former;
        }

        public void setFormer(Point former) {
            this.former = former;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }

}
