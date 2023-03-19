package com.algoexpert.linkedlist;

public class RotateLinkedList {

	public static ListNode rotateRight(ListNode head, int k) {

		if (head == null || head.next == null || k == 0) {
			return head;
		}

		ListNode cur = head;
		int len = 1;
		while (cur.next != null) {
			cur = cur.next;
			len++;
		}

		cur.next = head;
		k = k % len;
		k = len - k;
		while (k != 0) {
			cur = cur.next;
			k--;
		}

		head = cur.next;
		cur.next = null;
		return head;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5 };
		ListNode node = ListNode.createListNode(nums);

		rotateRight(node, 2);
	}

}
