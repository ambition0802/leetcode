package com.huang.leecode.tree;

/**
 * 求树的最大深度
 */
public class TreeMaxDepth {

    public static void main(String[] args) {

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 自底向上递归
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }

    /**
     * 自顶向下递归
     */
    int maxDepth = 0;
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        getMaxDepth(root, 0);

        return maxDepth;
    }

    private void getMaxDepth(TreeNode root, int depth) {
        if (root == null) {
            maxDepth = Math.max(maxDepth, depth);
            return;
        }

        getMaxDepth(root.right, depth +1);
        getMaxDepth(root.left, depth +1);

    }

}
