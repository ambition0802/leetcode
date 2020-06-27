package com.huang.leecode.BFS;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 矩阵
 */
public class Matrix542 {

    public static void main(String[] args) {

        int[][] a = {{1,0,1,1,0,0,1,0,0,1},{0,1,1,0,1,0,1,0,1,1},{0,0,1,0,1,0,0,1,0,0},{1,0,1,0,1,1,1,1,1,1},{0,1,0,1,1,0,0,0,0,1},{0,0,1,0,1,1,1,0,1,0},{0,1,0,1,0,1,0,0,1,1},{1,0,0,0,1,1,1,1,0,1},{1,1,1,1,1,1,1,0,1,0},{1,1,1,1,0,1,0,0,1,1}};
        new Matrix542().updateMatrix(a);
        System.out.print(JSON.toJSONString(a));
    }


    public int[][] updateMatrix(int[][] matrix) {

        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j=0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = -1;
                }
                if (matrix[i][j] == 0) {
                    queue.add(new Point(i, j));
                }
            }
        }

        int[] xmove = {1, -1, 0, 0};
        int[] ymove = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int i=0; i < 4; i++) {
                if ((p.getX() + xmove[i]) >= 0 && (p.getX() + xmove[i]) < matrix.length
                        && (p.getY() + ymove[i]) >= 0 && (p.getY() + ymove[i]) < matrix[0].length
                        && matrix[p.getX() + xmove[i]][p.getY() + ymove[i]] == -1) {
                    matrix[p.getX() + xmove[i]][p.getY() + ymove[i]]
                            = matrix[p.getX()][p.getY()] + 1;
                    queue.offer(new Point(p.getX() + xmove[i], p.getY() + ymove[i]));
                }
            }
        }

        return matrix;

    }

    private class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
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
    }

}