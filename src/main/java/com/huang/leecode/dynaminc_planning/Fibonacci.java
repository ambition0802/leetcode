package com.huang.leecode.dynaminc_planning;

import java.util.HashMap;

public class Fibonacci {

    //DP-TABLE （备忘录）
    private HashMap<Integer, Integer> dpTable = new HashMap<>();

    public static void main(String[] args) {

        Fibonacci fibonacci = new Fibonacci();
        for (int i=1; i <= 20; i++) {
            System.out.print(fibonacci.fibonacci(i) + "  ");
        }

        System.out.println();
        System.out.println(fibonacci.fibonacci2(20));
    }

    public int fibonacci(int num) {
        if (num == 1 || num == 2) {
            return 1;
        }

        if (dpTable.containsKey(num)) {
            return dpTable.get(num);
        } else {
            int result = fibonacci(num -1) + fibonacci(num -2);
            dpTable.put(num, result);
            return result;
        }
    }

    public int fibonacci2(int num) {

        int[] dpTable = new int[num + 1];

        if (num <= 2) {
            return 1;
        }

        dpTable[1] = dpTable[2] = 1;

        for (int i=3; i<=num; i++) {
            dpTable[i] = dpTable[i -1] + dpTable[i-2];
        }
        return dpTable[num];
    }

}
