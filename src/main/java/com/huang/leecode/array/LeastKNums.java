package com.huang.leecode.array;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 */
public class LeastKNums {

    public static void main(String[] args) {


    }

    public int[] getLeastNumbers(int[] arr, int k) {

        if (arr == null || arr.length == 0 || k == 0) {
            return new int[0];
        }

        int[] sortedArr = new int[10001];
        for (int i : arr) {
            sortedArr[i]++;
        }

        int[] result = new int[k];
        int resultIndex = 0;

        for (int i = 0; i < sortedArr.length && resultIndex < k; i++) {
            int count = sortedArr[i];

            if (count == 0) {
                continue;
            }

            for (int j = 0; j < count && resultIndex < k; j++) {
                result[resultIndex++] = i;
            }

        }

        return result;

    }

}
