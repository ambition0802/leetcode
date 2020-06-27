package com.huang.leecode.sort;

import com.alibaba.fastjson.JSON;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 快速排序
 *
 * 1.设置中轴数。
 * 2.将指定范围(下标)内的数字与中轴数比较大小，按照大小分别放到中轴数字的两侧 （双指针）
 * 3.对中轴数字的左右区间继续进行上述1，2步骤的操作
 *
 * PS:在给数组排序的时候，可以对原数组使用多个指针，来进行数组中数据的替换，
 *   从而避免创建临时（辅助）数组，节省内存空间。
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] arr = new int[]{9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423,9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423,9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423,9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423,9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423,9,8,7,6,5,4,3,2,1,0,100,6,324,2342,3534,654,746,2,432,4,34,654,1243,12346,1231234,23,4,23,423};


        LocalDateTime mergeSortStart = LocalDateTime.now();
        QuickSort.quickSort(arr, 0, arr.length - 1);
        System.out.println(JSON.toJSONString(arr));

    }

    public static void quickSort(int[] arr, int low, int high) {
        //基准(中轴)数
        int pivot = arr[low];
        //双指针
        int lp = low;
        int rp = high;
        //是否操作右指针，为true操作右指针，否则操作左指针
        boolean rpOpr = true;
        while (lp != rp) { //双指针相遇，表示该区间已经被遍历完了，然后将中轴数放到双指针相遇的位置
            if (rpOpr) {
                if (arr[rp] < pivot) {
                    arr[lp++] = arr[rp]; //将rp指向的值复制给lp（把小于中轴数的值移动到中轴数的左边）,然后lp往右移动一位
                    rpOpr = false; //接下来操作左指针
                } else {
                    rp--; //rp往左移动一位
                }
            } else {
                if (arr[lp] > pivot) {
                    arr[rp--] = arr[lp]; //将lp指向的值复制给rp（把小于中轴数的值移动到中轴数的左边）,然后rp往左移动一位
                    rpOpr = true; //接下来操作右指针
                } else {
                    lp++; //lp往右移动一位
                }
            }
        }
        //此时左右指针在同一个位置,左右两边分别为小于和大于pivot的值，将pivot保存到当前位置上
        arr[lp] = pivot;
        //递归排序pivot的左区间
        while (lp - 1 > low && arr[lp-1] == pivot) {
            //如果pivot的左边的值和他相等，则这个值不需要再进行快排，将lp左移，减小左子区间的范围
            lp--;
        }
        if (lp - 1 > low) {
            quickSort(arr, low, lp - 1);
        }
        //递归排序pivot的右区间
        while (rp + 1 < high && arr[rp+1] == pivot) {
            //如果pivot的右边的值和他相等，则这个值不需要再进行快排，将rp右移，减小右子区间的范围
            rp++;
        }
        if (rp + 1 < high) {
            quickSort(arr, rp + 1, high);
        }
    }

}
