package com.huang.leecode.BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class OpenTheLock752 {

    public static void main(String[] args) {
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";

        System.out.println(new OpenTheLock752().openLock(deadends, target));
    }

    public int openLock(String[] deadends, String target) {

        Set<String> deadendsSet = new HashSet<>();
        for (String str : deadends) {
            deadendsSet.add(str);
        }

        if (deadendsSet.contains("0000")) {
            return -1;
        }

        //标记已经使用过的密码，防止走回头路
        Set<String> used = new HashSet<>();

        HashSet<String> set1 = new HashSet<>();
        set1.add("0000");

        HashSet<String> set2 = new HashSet<>();
        set2.add(target);

        int times = 0;

        while (!set1.isEmpty() && !set2.isEmpty()) {

            HashSet<String> tmp = new HashSet<>();
            for (String str : set1) {
                if (deadendsSet.contains(str)) {
                    continue;
                }

                if (set2.contains(str)) {
                    return times;
                }

                used.add(str);

                for (int i=0; i<4; i++) {

                    String pullUpResult = pullUp(str, i);
                    String pullDownResult = pullDown(str, i);

                    if (!deadendsSet.contains(pullUpResult) && !used.contains(pullUpResult)) {
                        tmp.add(pullUpResult);
                    }
                    if (!deadendsSet.contains(pullDownResult) && !used.contains(pullDownResult)) {
                        tmp.add(pullDownResult);
                    }
                }
            }

            times++;
            if (set2.size() > tmp.size()) {
                set1 = tmp;
            } else {
                set1 = set2;
                set2 = tmp;
            }
        }

        return -1;
    }

    private String pullUp(String str, int index) {
        char[] strArr =str.toCharArray();
        if (strArr[index] == '9') {
            strArr[index] = '0';
        } else {
            strArr[index] = (char) (strArr[index] + 1);
        }

        return new String(strArr);
    }

    private String pullDown(String str, int index) {
        char[] strArr =str.toCharArray();
        if (strArr[index] == '0') {
            strArr[index] = '9';
        } else {
            strArr[index] = (char) (strArr[index] - 1);
        }

        return new String(strArr);
    }
}
