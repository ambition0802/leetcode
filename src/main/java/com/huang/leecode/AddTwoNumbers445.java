package com.huang.leecode;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class AddTwoNumbers445 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(0);


        new AddTwoNumbers445().addTwoNumbers(l1, l2);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> l1Stack = turnListToStack(l1);
        Stack<Integer> l2Stack = turnListToStack(l2);

        int carry = 0;

        ListNode head = null;
        while(!l1Stack.isEmpty() || !l2Stack.isEmpty()  || carry != 0) {

            int value1 = l1Stack.isEmpty() ? 0 : l1Stack.pop();
            int value2 = l2Stack.isEmpty() ? 0 : l2Stack.pop();

            int sum = value1 + value2 + carry;

            ListNode newNode = new ListNode(sum % 10);
            newNode.next = head;
            head = newNode;

            carry = sum >= 10 ? 1 : 0;
        }

        return head;
    }

    private Stack<Integer> turnListToStack(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();

        if(listNode == null) {
            return stack;
        }

        do {
            stack.push(listNode.val);
            listNode = listNode.next;
        } while (listNode != null);

        return stack;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
}
