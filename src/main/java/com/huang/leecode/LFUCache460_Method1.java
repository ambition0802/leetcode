package com.huang.leecode;

import com.alibaba.fastjson.JSON;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Least Frequently Cache
 * 最近最少被使用的缓存
 */
class LFUCache460_Method1 {

    //缓存
    private static HashMap<Integer, Node> cache = new HashMap<Integer, Node>();

    //缓存顺序
    private static PriorityQueue<Node> order = new PriorityQueue<Node>();

    private long time = 0;

    private int capacity = 0;

    private class Node implements Comparable {

        private int key;

        private int value;

        private int count = 1;

        private long date;

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Node) {
                Node compared = (Node) o;
                if (this.count > compared.count) {
                    return 1;
                } else if (this.count == compared.count) {
                    if (this.date > compared.date) {
                        return 1;
                    } else if (this.date == compared.date) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else {

                    return -1;
                }
            } else {
                //Node实例比非Node实例小
                System.out.println(this.key + " < Node实例比非Node实例小");

                return -1;
            }
        }
    }

    public LFUCache460_Method1(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            Node newNode = new Node();
            newNode.setKey(node.getKey());
            newNode.setValue(node.getValue());
            newNode.setCount(node.getCount() + 1);
            newNode.setDate(++time);
            cache.put(key, newNode);
            boolean b = order.remove(node);
            order.add(newNode);
            //System.out.println("AFTER GET +" + key + " : " + JSON.toJSONString(order));
            return node.getValue();
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            order.remove(node);
            node.setCount(node.getCount() + 1);
            node.setDate(++time);
            node.setValue(value);
            cache.put(key, node);
            order.add(node);
            //System.out.println("AFTER PUT +" + key + " : " + JSON.toJSONString(order));

        } else {
            if (cache.size() == capacity) {
                Node lfuNode = order.poll();
                cache.remove(lfuNode.getKey());
            }

            Node node = new Node();
            node.setKey(key);
            node.setValue(value);
            node.setCount(1);
            node.setDate(++time);
            cache.put(key, node);
            order.add(node);
           // System.out.println("AFTER PUT +" + key + " : " + JSON.toJSONString(order));

        }
    }

    /**
     * 初始化用来保存缓存的HashMap的大小
     *
     * @param cacheCapacity
     * @return
     */
    private int getHashMapCapacity(int cacheCapacity) {
        int hashMapCapacity = (int) (cacheCapacity / 0.75);
        if (cacheCapacity * 4 % 3 != 0) {
            hashMapCapacity ++;
        }

        int power = 0;
        int hashMapCapacityBak = hashMapCapacity;
        while ((hashMapCapacity = (hashMapCapacity / 2)) != 0) {
            power ++;
        }

        int hashMapPerfectCap = 1;
        for (int i = 0; i < power; i++) {
            hashMapPerfectCap = hashMapPerfectCap * 2;
        }

        if (hashMapPerfectCap != hashMapCapacityBak) {
            hashMapPerfectCap = hashMapPerfectCap * 2;
        } // else {hashMapMaxSize刚好是2的n次幂}

        return hashMapPerfectCap;
    }


}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
