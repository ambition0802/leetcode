package com.huang.leecode.tree.create_tree_by_traverse_result;

import java.util.*;

/**
 * 根据多种遍历结果来还原树
 *
 * 中序遍历 + 后序遍历
 */
public class CreateTreeByInAndPostTraverseResult {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 通过中序、后序遍历还原二叉树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    private int[] inorder;
    private int[] postorder;
    private int rootIndexInPostorder = 1;
    private HashMap<Integer, Integer> inorderArrValueIndexMap = new HashMap<>();
    public TreeNode buildTreeByInAndPost(int[] inorder, int[] postorder) {

        if (inorder == null || inorder.length == 0) {
            return null;
        }

        if (postorder == null || postorder.length == 0) {
            return null;
        }

        this.inorder = inorder;
        this.postorder = postorder;
        for (int i=0; i<inorder.length; i++) {
            this.inorderArrValueIndexMap.put(inorder[i], i);
        }

        return buildChildTree(0, this.inorder.length - 1, this.postorder[this.postorder.length-this.rootIndexInPostorder++]);
    }

    private TreeNode buildChildTree(int begin, int end, int rootValue) {
        TreeNode root = new TreeNode(rootValue);

        int rootIndexInInorder = inorderArrValueIndexMap.get(rootValue);

        if (end >= rootIndexInInorder + 1) {
            root.right = buildChildTree(rootIndexInInorder + 1, end, postorder[this.postorder.length - this.rootIndexInPostorder++]);
        }
        if(rootIndexInInorder - 1 >= begin) {
            root.left = buildChildTree(begin, rootIndexInInorder - 1, postorder[this.postorder.length - this.rootIndexInPostorder++]);
        }

        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public Node connect(Node root) {

        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        List<Node> list = new ArrayList<>();
        while (!queue.isEmpty()) {

            list.clear();

            while (!queue.isEmpty()) {
                list.add(queue.poll());
            }

            int listLength = list.size();
            for (int i=0; i<listLength; i++) {

                if (list.get(i) == null) {
                    continue;
                }
                if (i < listLength - 1) {
                    list.get(i).next = list.get(i+1);
                } else {
                    list.get(i).next = null;
                }

                if (list.get(i).left != null) {
                    queue.add(list.get(i).left);
                }
                if (list.get(i).right != null) {
                    queue.add(list.get(i).right);
                }
            }

        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new CreateTreeByInAndPostTraverseResult()
                .deserialize2("1,2,3,null,null,4,5,null,null,null,null");

        String srt = new CreateTreeByInAndPostTraverseResult()
                .serialize2(treeNode);
        return;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String serialize = serializeTrue(root);
//        while (serialize.endsWith(",null")) {
//            serialize = serialize.substring(0, serialize.length() -5);
//        }

        return serialize;
    }
    private String serializeTrue(TreeNode root) {

       if (root == null) {
           return "null";
       }

       String serializeStr = String.valueOf(root.val);

       serializeStr += ("," + serializeTrue(root.left));

       serializeStr += ("," + serializeTrue(root.right));

       return serializeStr;
    }



    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if (data == null || data.length() == 0) {
            return null;
        }

        List<String> valueStrList = new ArrayList<>(Arrays.asList(data.split(",")));

        return deserialize(valueStrList);
    }

    private TreeNode deserialize(List<String> valueStrList) {


        if (valueStrList.get(0).equals("null")) {
            valueStrList.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(valueStrList.get(0)));
        valueStrList.remove(0);

 //       if (valueStrList.size() > 0) {
            root.left = deserialize(valueStrList);
       // }
//        if (valueStrList.size() > 0) {
            root.right = deserialize(valueStrList);
      //  }

        return root;

    }

    //TODO bfs的序列化和反序列化  规则制定好，leecode会搞正确的

    // Encodes a tree to a single string.
    public String serialize2(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        String serializeStr = "";
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                serializeStr = serializeStr + ",null";
            } else {
                serializeStr = serializeStr + "," + node.val;
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        if (serializeStr.length() > 0) {
            serializeStr = serializeStr.substring(1);
        }

        return serializeStr;
    }



    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {

        if (data == null || data.length() == 0) {
            return null;
        }

        List<String> valueStrList = new ArrayList<>(Arrays.asList(data.split(",")));

        TreeNode root = new TreeNode(Integer.valueOf(valueStrList.get(0)));
        valueStrList.remove(0);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty() && !valueStrList.isEmpty()) {
            TreeNode node = queue.poll();
            if (!valueStrList.isEmpty()) {
                if (valueStrList.get(0).equals("null")) {
                    valueStrList.remove(0);
                } else {
                    TreeNode left = new TreeNode(Integer.valueOf(valueStrList.get(0)));
                    valueStrList.remove(0);
                    node.left = left;
                    queue.add(left);
                }
            }

            if (!valueStrList.isEmpty()) {
                if (valueStrList.get(0).equals("null")) {
                    valueStrList.remove(0);
                } else {
                    TreeNode right = new TreeNode(Integer.valueOf(valueStrList.get(0)));
                    valueStrList.remove(0);
                    node.right = right;
                    queue.add(right);
                }
            }
        }

        return root;
    }


}
