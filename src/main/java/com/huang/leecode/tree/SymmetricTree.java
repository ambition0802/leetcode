package com.huang.leecode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断是否对称树
 */
public class SymmetricTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 判断树是否左右对称的迭代版本
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();

        if (root == null) {
            return false;
        }

        queue.add(root.left);
        queue.add(root.right);

        while (queue.size() >= 2) {

           TreeNode node1 = queue.poll();
           TreeNode node2 = queue.poll();

           if (node1 == null && node2 == null) {
               continue;
           }

           if (node1 == null || node2 == null) {
               return false;
           }

           if (node1.val != node2.val) {
               return false;
           } else {
                queue.add(node1.left);
                queue.add(node2.right);
                queue.add(node1.right);
                queue.add(node2.left);
           }

        }

        return true;
    }

    /**
     * 判断是否是镜像二叉数的递归版本(自顶向下)
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {

        return recursion(root, root);

    }

    private boolean recursion(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.val != root2.val) {
            return false;
        }

        return recursion(root1.left,root2.right) && recursion(root1.right, root2.left);
    }
}
