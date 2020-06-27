package com.huang.leecode.sort;

import com.alibaba.fastjson.JSON;

public class SelectionSort {


    public static void main(String[] args) {
        int[] arr = new int[]{9,8,7,6,5,4,3,2,1,0};

        arr = SelectionSort.selectiontSort(arr);

        System.out.println(JSON.toJSONString(arr));
    }

    public static int[] selectiontSort(int[] arr) {

        for (int i=0; i < arr.length; i++) {
            int minIndex = 0;
            for (int j=i; j < arr.length; j++) {
                if (j == i) {
                    minIndex = j;
                } else {
                    if (arr[j] < arr[minIndex]) {
                        minIndex = j;
                    }
                }
            }
            int tmp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }
}
