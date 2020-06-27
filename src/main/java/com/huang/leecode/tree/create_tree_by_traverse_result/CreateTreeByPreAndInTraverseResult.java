package com.huang.leecode.tree.create_tree_by_traverse_result;

import java.util.HashMap;

/**
 * 根据多种遍历结果来还原树
 *
 * 前序遍历 + 中序遍历
 */
public class CreateTreeByPreAndInTraverseResult {

    public static void main(String[] args) {
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 通过前序、中序遍历还原二叉树
     *
     * @return
     */
    private int[] preorder;
    private int[] inorder;
    int preorderRootIndex = 0;
    private HashMap<Integer, Integer> valueAndIndexMapOfInorderArr = new HashMap<>();
    public TreeNode buildTreeByPreAndin(int[] preorder, int[] inorder) {

        if (preorder == null || preorder.length == 0) {
            return null;
        }

        if (inorder == null || inorder.length == 0) {
            return null;
        }

        this.preorder = preorder;
        this.inorder = inorder;

        for (int i=0; i<inorder.length; i++) {
            valueAndIndexMapOfInorderArr.put(inorder[i], i);
        }

        return buildChildTree(0, this.inorder.length - 1, preorderRootIndex++);
    }

    private TreeNode buildChildTree(int begin, int end, int rootValueIndexInPreoderArr) {

        TreeNode root = new TreeNode(this.preorder[rootValueIndexInPreoderArr]);

        int rootValueIndexInInorder = this.valueAndIndexMapOfInorderArr.get(this.preorder[rootValueIndexInPreoderArr]);

        if (rootValueIndexInInorder - 1 >= begin) {
            root.left = buildChildTree(begin, rootValueIndexInInorder - 1, preorderRootIndex++);
        }
        if (end >= rootValueIndexInInorder + 1) {
            root.right = buildChildTree(rootValueIndexInInorder + 1, end, preorderRootIndex++);
        }

        return root;
    }



}
