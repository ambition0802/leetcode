package com.huang.leecode.tree;


import java.util.ArrayList;
import java.util.List;

public class LeastCommonAncestor {

    public static void main(String[] args) {

    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }



    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        int rootVal = root.val;
        int pVal = p.val;
        int qVal = q.val;

        if ((rootVal - pVal) * (rootVal - qVal) <= 0) {
            return root;
        }

        if (rootVal > pVal) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return lowestCommonAncestor(root.right, p, q);
        }

    }

    private void preorderTraversal(TreeNode node) {


    }
}
