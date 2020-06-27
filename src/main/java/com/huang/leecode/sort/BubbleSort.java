package com.huang.leecode.sort;

import com.alibaba.fastjson.JSON;

/**
 * 冒泡排序算法
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] arr = new int[]{0,1,2,3,4,5,6,7,8,9};

        arr = BubbleSort.bubbleSort(arr);

        System.out.println(JSON.toJSONString(arr));

    }

    public static int[] bubbleSort(int[] arr) {

        if (arr == null || arr.length <= 0) {
            return arr;
        }

        boolean sorted = false;

        for (int i=0; i<arr.length; i++) {
            sorted = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j+1] = tmp;
                    sorted = false;
                }
                System.out.println("遍历一次");
            }

            if (sorted) {
                break;
            }
        }

        return arr;

    }
}
