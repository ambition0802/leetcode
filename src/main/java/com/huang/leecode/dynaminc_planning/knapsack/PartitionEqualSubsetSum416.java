package com.huang.leecode.dynaminc_planning.knapsack;

/**
 * 01背包问题
 */
public class PartitionEqualSubsetSum416 {


    public static void main(String[] args) {
        new PartitionEqualSubsetSum416().canPartition(new int[]{1,5,11,5});
    }

    public boolean canPartition(int[] nums) {

        //数组中所有成员的总大小
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        //因为数组会被拆分成两个数组，且这两个数组的成员之和相等
        //所以背包的总容量大小就为数组的“总容量”大小的一半
        if (sum % 2 != 0) {
            return false;
        }

        int knapsackCap = sum / 2;

        //f[i][j]这个二维数组表示,只从nums的前i个内取成员，每个成员只能取一次，成员之和的上限为j时的最大成员之和
        int[][] f = new int[nums.length + 1][knapsackCap + 1];
        for (int i= 1; i<nums.length + 1; i++) {
            for (int j= 1; j < knapsackCap + 1; j++) {
                if (nums[i - 1] > j) {
                    f[i][j] = f[i-1][j];
                } else {
                    int get = f[i -1][j-nums[i - 1]] + nums[i - 1];
                    int notGet = f[i - 1][j];
                    f[i][j] = get >= notGet ? get : notGet;
                }
            }
        }

        for (int i = 1; i < nums.length; i++) {
            if (f[i][knapsackCap] == knapsackCap) {
                return true;
            }
        }

        return false;
    }


}
