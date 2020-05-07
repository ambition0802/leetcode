package com.huang.leecode.dynaminc_planning;

public class CoinLCCI08_11 {

    public static void main(String[] args) {
        System.out.println(new CoinLCCI08_11().waysToChange(900750));
        System.out.println(new CoinLCCI08_11().waysToChange2(900750));

    }

    int[] coins = {1, 5, 10, 25};

    public int waysToChange(int n) {
        if (n == 0) {
            return 1;
        }

        int[][] ways = new int[coins.length+1][n+1];
        ways[0][0] = 1;

        for (int i=1; i<= coins.length; i++) {
            for (int cap = 0; cap <= n; cap++) {
                 if(cap == 0) {
                    ways[i][cap] = 1;
                    continue;
                }
                 if (cap >= coins[i - 1]) {
                     ways[i][cap] = (ways[i - 1][cap] + ways[i][cap - coins[i -1]]) % 1000000007;
                 } else {
                     ways[i][cap] = ways[i - 1][cap] % 1000000007;
                 }
            }
        }

        return ways[ways.length - 1][ways[0].length - 1];
    }

    public int waysToChange2(int n) {
        if (n == 0) {
            return 1;
        }

        int[] ways = new int[n+1];
        ways[0] = 1;

        for (int i=0; i< coins.length; i++) {
            for (int cap = 1; cap <= n; cap++) {
                if (cap >= coins[i]) {
                    ways[cap] = (ways[cap] + ways[cap - coins[i]]) % 1000000007;
                }
            }
        }

        return ways[ways.length - 1];
    }


}
