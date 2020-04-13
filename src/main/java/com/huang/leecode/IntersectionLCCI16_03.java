package com.huang.leecode;

import java.math.BigDecimal;
import java.util.TreeSet;

public class IntersectionLCCI16_03 {

    public static void main(String[] args) {
        System.out.print(1/(double) 2);
    }

    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
        int start1X = start1[0];
        int start1Y = start1[1];
        int end1X = end1[0];
        int end1Y = end1[1];
        int start2X = start2[0];
        int start2Y = start2[1];
        int end2X = end2[0];
        int end2Y = end2[1];

        // 误差精度
        double epslion = 1e-6f;

        /**
         * 斜截式特殊情况 x = n
         */
        if (start1X != end1X && start2X != end2X) {
            //两条线段都不是 x = n的情况,可能存在线段重合的情况

        } else if(start1X != end1X && start2X == end2X) {
             //线段1不是，线段2是


        } else if (start1X == end1X && start2X != end2X) {
            //线段1是，线段2不是

        } else {
            //线段1，2都是,可能存在线段重合的情况

        }

        return new double[0];
    }

}
