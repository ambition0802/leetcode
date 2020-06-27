package com.huang.leecode.sort;

import com.alibaba.fastjson.JSON;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 归并排序
 *
 */
public class MergeSort {


    public static void main(String[] args) {
        int[] arr = new int[]{9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423,9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423,9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423,9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423,9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423,9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423};


        LocalDateTime mergeSortStart = LocalDateTime.now();
        arr = MergeSort.mergeSort(arr);
        LocalDateTime mergeSortEnd = LocalDateTime.now();
        Duration duration = Duration.between(mergeSortStart, mergeSortEnd);


        System.out.println("mergeSort1 result = " + JSON.toJSONString(arr) + "\n costs = " + duration.toNanos());

        arr = new int[]{9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423,9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423,9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423,9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423,9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423,9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423};
        LocalDateTime merge2start = LocalDateTime.now();
        MergeSort.mergeSort(arr, 0, arr.length - 1);
        LocalDateTime merge2end = LocalDateTime.now();
        Duration duration2 = Duration.between(merge2start, merge2end);

        System.out.println("mergeSort2 result = " + JSON.toJSONString(arr) + "\n costs = " + duration2.toNanos());



    }

    public static void mergeSort(int[] arr, int low, int high) {

        if (low == high) {
            return;
        }

        int mid = (low + high) / 2;

        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);

        mergeLeftAndRight(arr, low, mid, high);

    }

    private static void mergeLeftAndRight(int[] arr, int low, int middle, int high) {

        int leftIndex = low;
        int rightIndex = middle + 1;

        int[] tmp = new int[high -low + 1];

        int tmpIndex = 0;

       while (leftIndex <= middle && rightIndex <= high) {
           if (arr[leftIndex] < arr[rightIndex]) {
                tmp[tmpIndex++] = arr[leftIndex++];
           } else {
               tmp[tmpIndex++] = arr[rightIndex++];
           }
       }

       while (leftIndex <= middle) {
           tmp[tmpIndex++] = arr[leftIndex++];
       }

       while (rightIndex <= high) {
           tmp[tmpIndex++] = arr[rightIndex++];
       }

       for (int i=0; i<tmp.length; i++) {
           arr[low+i] = tmp[i];
       }
    }

    public static int[] mergeSort(int[] arr) {

        //递归出口
        if (arr.length == 1) {
            return arr;
        }

        if (arr.length == 2) {
            if (arr[0] > arr[1]) {
                int tmp = arr[0];
                arr[0] = arr[1];
                arr[1] = tmp;
            }
            return arr;
        }

        int length = arr.length;
        int firstArrLength = length / 2;

        int[] arrFisrt = new int[firstArrLength];
        int[] arrSecond = new int[length - firstArrLength];

        System.arraycopy(arr, 0, arrFisrt, 0, arrFisrt.length);
        System.arraycopy(arr, firstArrLength, arrSecond,0, arrSecond.length);

        arrFisrt = mergeSort(arrFisrt);
        arrSecond = mergeSort(arrSecond);

        int firstIndex = 0;
        int secondIndex = 0;

        int mergeIndex = 0;

        while (firstIndex < arrFisrt.length && secondIndex < arrSecond.length) {
            if (arrFisrt[firstIndex] < arrSecond[secondIndex]) {
                arr[mergeIndex] = arrFisrt[firstIndex];
                firstIndex++;
            } else {
                arr[mergeIndex] = arrSecond[secondIndex];
                secondIndex++;
            }
            mergeIndex++;
        }

        if (firstIndex == arrFisrt.length) {
            while (secondIndex < arrSecond.length) {
                arr[mergeIndex] = arrSecond[secondIndex];
                mergeIndex++;
                secondIndex++;
            }
        } else {
            while (firstIndex < arrFisrt.length) {
                arr[mergeIndex] = arrFisrt[firstIndex];
                mergeIndex++;
                firstIndex++;
            }
        }

        return arr;

    }

}
