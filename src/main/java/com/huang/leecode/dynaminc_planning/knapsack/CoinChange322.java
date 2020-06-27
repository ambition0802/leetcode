package com.huang.leecode.dynaminc_planning.knapsack;

/**
 * 题目见
 * https://leetcode-cn.com/problems/coin-change/
 *
 * 是一个完全背包问题。
 *
 * 状态转移方程式为：
 * dp[i] = min(dp[i], dp[i-coin[j]])
 */
public class CoinChange322 {

    public static void main(String[] args) {
        System.out.println(new CoinChange322().coinChange2(new int[]{2}, 3));
    }

    /**
     * 暴力枚举出所有的可能情况，然后得到硬币个数最小的方案
     *
     * 采用 递归 + 备忘数组的方式
     *
     * @param coins
     * @param amount
     * @return
     */

    private  int[] dp;

    public int coinChange(int[] coins, int amount) {

        if (amount == 0) {
            return 0;
        }

        if (coins.length == 0) {
            return -1;
        }

        dp = new int[amount + 1];

        return getMin(coins, amount);

    }

    private int getMin(int[] coins, int amount) {

        if (dp[amount] != 0) {
            return dp[amount];
        }

        int min = 0;
        int tmpMin = 0;
        for (int coin : coins) {
            if (amount > coin) {
                tmpMin = getMin(coins, amount - coin) + 1;
            } else if (amount == coin) {
                tmpMin = 1;
            }

            if (tmpMin != 0) {
                if (min == 0) {
                    min = tmpMin;
                } else {
                    min = min < tmpMin ? min : tmpMin;
                }
            }
        }

        if (min == 0) {
            dp[amount] = -1;
            return -1;
        } else {
            dp[amount] = min;
            return min;
        }
    }

    /**
     * 使用动态规划，自底向上，完成动规数组
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {

        /**
         * 处理下边界情况
         */
        if (amount == 0) {
            return 0;
        }
        if (coins.length == 0) {
            return -1;
        }

        /**
         * dp[n]表示用指定的硬币集合coins凑出金额n时最小的硬币个数
         */
        int[] dp = new int[amount + 1];

        /**
         * 脑子里想象下那个自顶向下的那个递归树
         * 所以要先遍历金额再遍历硬币种类
         */
        for (int money = 1; money <= amount; money++) {
            for (int coin : coins) {
                if (coin > money) {
                    continue;
                }

                /**
                 * dp[money-coin]必须不为0（除了dp[0]之外），否则表示金额为money大小的时候，
                 * 先凑了一个硬币coin，剩下的钱用给的硬币集合就凑不出来了,这种情况无法凑出指定金额，要废弃。
                 */
                if (dp[money - coin] == 0 && money != coin) {
                    continue;
                }
                if (dp[money] <= 0) {
                    dp[money] = dp[money - coin] + 1;
                } else {
                    dp[money] = dp[money] < (dp[money - coin] + 1) ? dp[money] : (dp[money - coin] + 1);
                }

            }
        }

        return dp[amount] == 0 ? -1 : dp[amount];

    }


}
