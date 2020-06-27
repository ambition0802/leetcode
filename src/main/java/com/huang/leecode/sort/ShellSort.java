package com.huang.leecode.sort;

import com.alibaba.fastjson.JSON;

/**
 * 希尔排序
 *
 * 插入排序的升级版--->多组多次的插入排序
 *
 * 希尔排序的核心在于间隔序列的设定。
 * 既可以提前设定好间隔序列，也可以动态的定义间隔序列。
 * 动态定义间隔序列的算法是《算法（第4版）》的合著者Robert Sedgewick提出的。
 */
public class ShellSort {

    public static void main(String[] args) {

        int[] arr = new int[]{9,8,7,6,5,4,3,2,1};

        arr = ShellSort.shellSort(arr);

        System.out.println("shellsort1 result = " + JSON.toJSONString(arr));

        arr = new int[]{9,8,7,6,5,4,3,2,1};

        arr = ShellSort.shellSort2(arr);

        System.out.println("shellsort2 result = " + JSON.toJSONString(arr));

    }

    private static final int[] distance = {5, 2, 1};

    public static int[] shellSort(int[] arr) {

        int count = 0;

        for (int gap= arr.length / 2; gap > 0; gap = gap/2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j >= gap && arr[j] < arr[j-gap]; j = j - gap) {

                    count++;
                    int tmp = arr[j];
                    arr[j] = arr[j-gap];
                    arr[j-gap] = tmp;

                }
            }
        }

        System.out.println("shellsort1 count = " + count);
        return arr;
    }


    // 修改于 2019-03-06
    public static int[] shellSort2(int[] arr) {
        int len = arr.length;

        int count = 0;

        for(int gap = len/2; gap > 0; gap = gap / 2) {
            // 注意：这里和动图演示的不一样，动图是分组执行，实际操作是多个分组交替执行
            for(int i = gap; i < len; i++) {
                int j = i;
                int current = arr[i];
                while(j - gap >= 0 && current < arr[j - gap]) {
                    arr[j] = arr[j - gap];
                    j = j - gap;

                    count++;
                }
                arr[j] = current;
            }
        }

        System.out.println("shellsort2 count = " + count);
        return arr;
    }
}
