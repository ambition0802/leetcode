package com.huang.leecode;

import java.util.*;

/**
 * BFS算法
 */
public class AsFarFromLandAsPossible1162 {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};

        System.out.println(new AsFarFromLandAsPossible1162().maxDistance1(grid));

        System.out.println(new AsFarFromLandAsPossible1162().maxDistance2(grid));

    }

    //解法1：暴力BFS
    public int maxDistance1(int[][] grid) {
        int maxDistance = -1;

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == 0) {
                    //海洋
                    int minDistanceFromLand = getMinDistanceFromLand(x, y, grid);
                    if (maxDistance < minDistanceFromLand) {
                        maxDistance = minDistanceFromLand;
                    }
                } // else {陆地}
            }
        }

        return maxDistance;
    }

    private int getMinDistanceFromLand(int x, int y, int[][] grid) {

        //走过哪些点的记录
        int[][] mark = new int[grid.length][grid[0].length];

        ArrayDeque<int[]> queue = new ArrayDeque<int[]>();

        int[] firstCoordinate = new int[2];
        firstCoordinate[0] = x;
        firstCoordinate[1] = y;
        queue.add(firstCoordinate);

        while (!queue.isEmpty()) {
            int[] coordinate = queue.pop();
            int xCoordinate = coordinate[0];
            int yCoordinate = coordinate[1];

            //标记点已经走过了
            mark[xCoordinate][yCoordinate] = 1;

            if (grid[xCoordinate][yCoordinate] == 1) {
                //广度优先算法，最早找到的一定是最短的。反证法可以证明
                return Math.abs(x - xCoordinate) + Math.abs(y - yCoordinate);
            } else {
                //找到这个点周围的点
                if (xCoordinate - 1 >= 0) {
                    //左边的点入队
                    if (mark[xCoordinate - 1][yCoordinate] == 0) {
                        queue.add(new int[]{xCoordinate - 1, yCoordinate});
                    }
                }
                if (xCoordinate + 1 < grid.length) {
                    //右边的点入队
                    if (mark[xCoordinate + 1][yCoordinate] == 0) {
                        queue.add(new int[]{xCoordinate + 1, yCoordinate});
                    }
                }
                if (yCoordinate + 1 < grid[xCoordinate].length) {
                    //上面的点
                    if (mark[xCoordinate][yCoordinate + 1] == 0) {
                        queue.add(new int[]{xCoordinate, yCoordinate + 1});
                    }
                }
                if (yCoordinate - 1 >= 0) {
                    //下面的点入队
                    if (mark[xCoordinate][yCoordinate - 1] == 0) {
                        queue.add(new int[]{xCoordinate, yCoordinate - 1});
                    }
                }

            }
        }

        return -1;

    }


    //解法2
    public int maxDistance2(int[][] grid) {

        Queue<int[]> queue = new ArrayDeque<int[]>(grid.length * grid.length);

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == 1) {
                    queue.offer(new int[]{x, y});
                } // else {海洋}
            }
        }


        int[] xmove = new int[]{0, 0, 1, -1};
        int[] ymove = new int[]{1, -1, 0, 0};

        int[] lastOcean = null;

        //遍历队列,找到最后一个被陆地访问到的海洋区域，这个海洋区域就是最远的一个海洋区域
        while (!queue.isEmpty()) {

            int[] point = queue.poll();

            //遍历point上下左右的点，陆地就直接忽略，海洋才入队
            for (int i = 0; i < 4; i++) {
                int[] newPoint = new int[]{point[0] + xmove[i], point[1] + ymove[i]};
                if (newPoint[0] < 0 || newPoint[0] >= grid.length
                        || newPoint[1] < 0 || newPoint[1] >= grid[0].length
                        || grid[newPoint[0]][newPoint[1]] > 0) {
                    continue;
                } else {
                    //在地图范围内，且没有被走过的海洋
                    //将海洋标记已经走过了
                    grid[newPoint[0]][newPoint[1]] = grid[point[0]][point[1]] + 1;

                    lastOcean = newPoint;

                    //记录这个最迟找到的海洋
                    //海洋入队
                    queue.offer(newPoint);
                }
            }
        }

        return lastOcean == null ? -1 : grid[lastOcean[0]][lastOcean[1]] - 1;

    }
}
