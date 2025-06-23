package com.algoexpert.linkedlist;

public class ReorderList {

    public static void reorderList(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }


        ListNode node2 = reverseList(slow.next);
        slow.next = null;
        ListNode node1 = head;

        while (node1 != null && node2 != null) {

            ListNode tmp1 = node1.next;
            ListNode tmp2 = node2.next;

            node1.next = node2;
            node2.next = tmp1;

            node1 = tmp1;
            node2 = tmp2;
        }
    }

    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = prev;
            prev = head;
            head = nextNode;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode node10 = new ListNode(1);
        ListNode node11 = new ListNode(2);
        ListNode node20 = new ListNode(3);
        ListNode node21 = new ListNode(4);
        ListNode node22 = new ListNode(5);

        node10.next = node11;
        node11.next = node20;
        node20.next = node21;
        node21.next = node22;
        reorderList(node10);
    }
}
