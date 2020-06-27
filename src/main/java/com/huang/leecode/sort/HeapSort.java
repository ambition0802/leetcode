package com.huang.leecode.sort;

/**
 * 堆排序
 */
public class HeapSort {

    public static void main(String[] args) {

    }

    public static void heapSort(int[] arr) {

        buildHeap(arr, arr.length - 1);

        for (int i = arr.length - 1; i>0; i--) {
            swap(arr, i, 0);
            //因为整个堆已经创建过一次，所以在swap之后，除了根节点所在的小子树，之外，其他子树都是父节点最大
            //所以不需要再次的创建整个堆（开销大，某些已经堆化的子树又反复进行堆化），
            //buildHeap(arr, i - 1); //TODO 每次获取最大值之后，都重新创建堆简直智障，浪费时间，会超时的。
            //只需要从根节点开始重新堆化即可，因为整个堆，除了根节点的小子树之外其他节点都符合堆的规范。只需要让根节点从堆中下沉到合适的位置即可。
            heapify(arr, 0, i - 1);
        }
    }

    /**
     * 将数组创建成最大堆
     *
     * @param arr
     */
    public static void buildHeap(int[] arr, int high) {

        int descFirstParentNode = (high -1) >> 1;
        for (int i=descFirstParentNode; i>=0; i--) {
            heapify(arr, i, high);
        }

    }

    public static void heapify(int[] arr, int index, int high) {

        int leftNode = index * 2 + 1;
        int rightNode = index * 2 + 2;

        int maxIndex = index;
        if (leftNode <= high && arr[leftNode] > arr[maxIndex]) {
            maxIndex = leftNode;
        }
        if (rightNode <= high && arr[rightNode] > arr[maxIndex]) {
            maxIndex = rightNode;
        }

        if (maxIndex != index) {
            swap(arr, maxIndex, index);
            //以maxIndex为parent的子树改变了,通过递归重新堆化
            //以index为子节点的子树也改变了，通过外层的迭代，迭代到父节点进行堆化，从而整个堆的创建
            heapify(arr, maxIndex, high);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
