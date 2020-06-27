package com.huang.leecode.BFS;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumDepthofBinaryTree111 {

    public static void main(String[] args) {
       TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.left.left = null;
        node.left.right = null;

        System.out.print(new MinimumDepthofBinaryTree111().minDepth(node));
    }

    public int minDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 0;

        while (!queue.isEmpty()) {

            depth++;

            int queueSize = queue.size();
            for (int i=0; i< queueSize; i++) {
                TreeNode node = queue.poll();
                TreeNode left = node.left;
                TreeNode right = node.right;
                if (left == null && right == null) {
                    return depth;
                }

                if (left != null) {
                    queue.offer(left);
                }

                if (right != null) {
                    queue.offer(right);
                }
            }
        }

        return  depth;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


}
