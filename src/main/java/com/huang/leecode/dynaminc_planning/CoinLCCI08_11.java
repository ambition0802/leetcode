package com.huang.leecode.dynaminc_planning;

public class CoinLCCI08_11 {

    public static void main(String[] args) {
        System.out.println(new CoinLCCI08_11().waysToChange(10));
    }

    int[] coins = {1, 5, 10, 25};

    public int waysToChange(int n) {

        int ways = 0;

        if (n == 0) {
            return 1;
        }

        for (int coin : coins) {
            if (n >= coin) {
                ways = ways + waysToChange(n - coin);
            }
        }

        return ways;
    }

}
