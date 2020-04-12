package com.huang.leecode;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实现最近最少使用缓存的第二种方式
 * 通过双端列表和,每个节点保存自己在列表中的节点，来实现时间复杂度O(1)的get、put操作
 */
public class LFUCache460_Method2 {

    public static void main(String[] args) {
        LFUCache460_Method2 lfuCache460_method2 = new LFUCache460_Method2(10);
        lfuCache460_method2.put(10, 13);
        lfuCache460_method2.put(3, 17);
        lfuCache460_method2.put(6, 11);
        lfuCache460_method2.put(10,5);
        lfuCache460_method2.put(9,10);
        lfuCache460_method2.get(13);
        lfuCache460_method2.put(2,19);
        lfuCache460_method2.get(2);
        lfuCache460_method2.get(3);

        lfuCache460_method2.put(5,25);
        lfuCache460_method2.get(8);
        lfuCache460_method2.put(9,22);
        lfuCache460_method2.put(5, 5);
        lfuCache460_method2.put(1, 30);
        lfuCache460_method2.get(11);
        lfuCache460_method2.put(9, 12);
        lfuCache460_method2.get(7);
        lfuCache460_method2.get(5);
        lfuCache460_method2.get(8);
        lfuCache460_method2.get(9);
        lfuCache460_method2.put(4, 30);
        lfuCache460_method2.put(9, 3);
        lfuCache460_method2.get(9);
        lfuCache460_method2.get(10);
        lfuCache460_method2.get(10);
        lfuCache460_method2.put(6, 14);
        lfuCache460_method2.put(3, 1);
        lfuCache460_method2.get(3);
        lfuCache460_method2.put(10, 11);
        lfuCache460_method2.get(8);
        lfuCache460_method2.put(2, 14);
        lfuCache460_method2.get(1);
        lfuCache460_method2.get(5);
        lfuCache460_method2.get(4);



        return;

    }

    private int date = 0;

    //缓存容量大小
    private int capacity = 0;

    /**
     * 保存缓存
     */
    private ConcurrentHashMap<Integer, Node> cacheMap = null;

    /**
     * 将缓存按照使用频次（count）分别放到不同的双端列表中
     */
    private ConcurrentHashMap<Integer, DuplexingList> freqMap = null;

    /**
     * 保存最小的使用频次（count）
     */
    private int minFreq = 0;

    public LFUCache460_Method2(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new ConcurrentHashMap<Integer, Node>(getHashMapCapacity(capacity));
        this.freqMap = new ConcurrentHashMap<Integer, DuplexingList>(getHashMapCapacity(capacity));
    }

    public int get(int key) {

        if(capacity <= 0) {
            return -1;
        }

        if (cacheMap.containsKey(key)) {
            Node cache = cacheMap.get(key);

            //从原先freq链表中删除
            DuplexingList duplexingList = freqMap.get(cache.getCount());
            duplexingList.removeNode(cache);
            if (duplexingList.length == 0 && cache.getCount() == minFreq) {
                minFreq++;
            }

            cache.setCount(cache.getCount() + 1);
            cache.setDate(++date);

            duplexingList = freqMap.get(cache.getCount());
            if (duplexingList == null) {
                duplexingList = new DuplexingList();
                freqMap.put(cache.getCount(), duplexingList);
            }
            duplexingList.addToTail(cache);

            return cache.getValue();

        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        if (cacheMap.containsKey(key)) {
            Node cache = cacheMap.get(key);
            cache.setValue(value);

            DuplexingList duplexingList = freqMap.get(cache.getCount());
            duplexingList.removeNode(cache);
            if (duplexingList.length == 0 && cache.getCount() == minFreq) {
                minFreq++;
            }

            cache.setCount(cache.getCount() + 1);
            cache.setDate(++date);

            duplexingList = freqMap.get(cache.getCount());
            if (duplexingList == null) {
                duplexingList = new DuplexingList();
                freqMap.put(cache.getCount(), duplexingList);
            }
            duplexingList.addToTail(cache);
        } else {
            if (cacheMap.size() == capacity) {
                DuplexingList duplexingList = freqMap.get(minFreq);
                Node oldest = duplexingList.removeOldest();
                if (duplexingList.length == 0) {
                    minFreq++;
                }
                cacheMap.remove(oldest.getKey());
            }

            Node newNode = new Node();
            newNode.setKey(key);
            newNode.setValue(value);
            newNode.setDate(++date);
            cacheMap.put(key, newNode);

            DuplexingList duplexingList = freqMap.get(newNode.getCount());
            if (duplexingList == null) {
                duplexingList = new DuplexingList();
                freqMap.put(newNode.getCount(), duplexingList);
            }
            duplexingList.addToTail(newNode);

            minFreq = newNode.getCount();// =1

        }
    }

    /**
     * 存放同一频次（count）的缓存的双端列表
     * 后加进来（最新）的缓存放到尾部
     */
    private class DuplexingList {
        private Node head = null;
        private Node tail = null;;

        int length = 0;

        public DuplexingList () {
            this.head = new Node();
            this.tail = new Node();
            this.head.next = this.tail;
            this.tail.former = this.head;
        }

        //NOTE 为什么有默认的head和tail
        public void addToTail(Node node) {
            this.tail.former.next = node;
            node.former = this.tail.former;
            node.next = this.tail;
            this.tail.former = node;
            length ++;
        }

        public void removeNode(Node node) {
            node.former.next = node.next;
            node.next.former = node.former;
            node.former = null;
            node.next = null;
            length--;
        }

        public Node removeOldest() {
            if (length <= 0) {
                throw new RuntimeException("removeOldest error : length = " + length);
            }
            Node oldest = this.head.next;
            this.head.next = oldest.next;
            oldest.next.former = oldest.former;
            oldest.former = null;
            oldest.next = null;
            length--;
            return  oldest;
        }
    }

    private class Node implements Comparable {

        private int key;

        private int value;

        private int count = 1;

        private int date;

        private Node next;

        private Node former;

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

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Node) {
                Node compared = (Node) o;
                return this.count == compared.getCount()
                        ? this.date - compared.date : this.count - compared.count;
            } else {
                //Node实例比非Node实例小
                return -1;
            }
        }
    }

    /**
     * 初始化用来保存缓存的HashMap的大小
     *
     * @param cacheCapacity
     * @return
     */
    private int getHashMapCapacity(int cacheCapacity) {
        if (cacheCapacity == 0) {
            return 0;
        }
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
