package com.huang.leecode.tree;

import com.huang.leecode.Test;

import java.util.*;

/**
 * 树的四种遍历方式 ：
 *      1.前序遍历
 *      2.中序遍历
 *      3.后续遍历
 *      4.层级遍历
 *
 *      前三种遍历方式都是采用递归实现的，TODO 用迭代的方式实现前三种遍历
 *
 */
public class TreeTraverse {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new  TreeNode(3);

        List<Integer> list = new TreeTraverse().inorderTraversal(root);
        return;
    }


    /***********************************前序遍历***********************************/
    //保存前序遍历的解结果
    private List<Integer> preoderList = new ArrayList<>();
    //前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        preTraverse(root);
        return preoderList;
    }
    //前序遍历递归
    private void preTraverse(TreeNode node) {
        if (node == null) {
            return;
        }
        preoderList.add(node.val);
        preTraverse(node.left);
        preTraverse(node.right);
    }


    /***********************************中序遍历***********************************/
    //保存中序遍历的解结果
    private List<Integer> inoderList = new ArrayList<>();
    //中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return inoderList;
        }

        inTraverse(root);

        return inoderList;
    }
    //中序遍历递归
    private void inTraverse(TreeNode node) {
        if (node.left == null && node.right == null) {
            inoderList.add(node.val);
            return;
        }

        if (node.left != null) {
            inTraverse(node.left);
        }

        inoderList.add(node.val);

        if (node.right != null) {
            inTraverse(node.right);
        }
    }


    /***********************************后序遍历 (递归)***********************************/
    //保存后序遍历的解结果
    private List<Integer> postoderList = new ArrayList<>();
    //后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return postoderList;
        }

        postTraverse(root);

        return postoderList;
    }
    //后序遍历递归
    private void postTraverse(TreeNode node) {
        if (node.left != null) {
            postTraverse(node.left);
        }

        if (node.right != null) {
            postTraverse(node.right);
        }

        postoderList.add(node.val);

    }

    /***********************************后序遍历 (迭代)***********************************/
    // 方法1，按照后续遍历的顺序递归
    private List<Integer> postorderTraversalByIteration(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        root = root.left;

        TreeNode preNode = null;

        while (!stack.isEmpty()) {

            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.peek();
            if (root.right != null && root.right != preNode) {
                root = root.right;
            } else {
                result.add(root.val);
                preNode = root;
                root = null;

                stack.pop();
            }

        }

        return result;
    }

    // 方法2，按照“前序“遍历(根右左)递归，再反转结果链表(根右左--->左右根)，就是后续遍历的结果了。
    public static List<Integer> postorderTraversalByIteration2(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            root = stack.pop();
            result.add(root.val);

            if (root.left != null) {
                stack.push(root.left);
            }

            if (root.right != null) {
                stack.push(root.right);
            }
        }

        Collections.reverse(result);
        return result;

    }

    /***********************************BFS遍历(层级遍历)***********************************/
    //层级遍历
    public List<List<Integer>>  levelOrder(TreeNode root) {

        //层级遍历的解结果
        List<List<Integer>> leveloderList = new ArrayList<>();

        if (root == null) {
            return leveloderList;
        }

        //通过队列来实现BFS
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        Queue<TreeNode> currentLevelQueue = new LinkedList<>();
        treeNodeQueue.add(root);

        while (!treeNodeQueue.isEmpty()) {
            while (!treeNodeQueue.isEmpty()) {
                currentLevelQueue.offer(treeNodeQueue.poll());
            }

            List<Integer> currentLevelList = new ArrayList<>();

            while (!currentLevelQueue.isEmpty()) {
                TreeNode node = currentLevelQueue.poll();
                currentLevelList.add(node.val);

                if (node.left != null) {
                    treeNodeQueue.offer(node.left);
                }
                if (node.right != null) {
                    treeNodeQueue.offer(node.right);
                }
            }

            leveloderList.add(currentLevelList);
        }

        return leveloderList;
    }


}
