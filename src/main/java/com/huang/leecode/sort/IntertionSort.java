package com.huang.leecode.sort;

import com.alibaba.fastjson.JSON;

public class IntertionSort {

    public static void main(String[] args) {

        int[] arr = new int[]{9,8,7,6,5,4,3,2,1, 0};

        arr = IntertionSort.insertionSort(arr);

        System.out.println(JSON.toJSONString(arr));
    }

    public static int[] insertionSort(int[] arr) {
        for (int i=1; i<arr.length; i++) {
            for (int j=i; j >= 1; j--) {
                if (arr[j] < arr[j-1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                } else {
                    break;
                }
            }
        }

        return  arr;
    }
}
