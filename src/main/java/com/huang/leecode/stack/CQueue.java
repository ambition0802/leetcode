package com.huang.leecode.stack;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 用两个栈实现队列
 *
 * 实现不考虑线程安全
 *
 * https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 */
public class CQueue {

    private Deque<Integer> trueStack = new LinkedList<>();
    private Deque<Integer> tmpStack = new LinkedList<>();

    public CQueue() {

    }

    public void appendTail(int value) {

       tmpStack.add(value);

    }

    public int deleteHead() {

        if (trueStack.isEmpty() && tmpStack.isEmpty()) {
            return -1;
        }

        if (trueStack.isEmpty()) {
            while (!tmpStack.isEmpty()) {
                trueStack.add(tmpStack.poll());
            }
        }
        return trueStack.pop();
    }

    public static int add(int x) {
        System.out.println(x);
        return x <= 0 ? 0 : x + add(x - 1);
    }


    public static void main(String[] args) {

        System.out.println(1134903170 + 1836311903);
        System.out.println(Integer.MAX_VALUE);
        CQueue c = new CQueue();
        System.out.println(c.fib(50));
        return;
    }
    private Map<Integer, Integer> dic = new HashMap<>();
    public int fib(int n) {

        if (n <= 1) { return n;}
        int first = 0;
        int second = 1;
        int sum = 0;
        for (int i=2; i<=n;i++) {
            sum = first + second;
            first = second;
            second = sum;
        }

        return sum;
    }


}
