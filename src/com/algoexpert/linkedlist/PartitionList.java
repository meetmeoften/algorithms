package com.algoexpert.linkedlist;

public class PartitionList {

	public static ListNode partition(ListNode head, int x) {

		if (head == null) {
			return head;
		}

		ListNode less = new ListNode();
		ListNode greater = new ListNode();

		ListNode lessHead = less, greaterHead = greater;

		while (head != null) {
			if (head.val < x) {
				lessHead.next = head;
				lessHead = lessHead.next;
			} else {
				greaterHead.next = head;
				greaterHead = greaterHead.next;
			}
			head = head.next;
		}

		lessHead.next = greater.next;
		greaterHead.next = null;
		return less.next;

	}

	public static void main(String[] args) {
		int[] nums = { 1, 4, 3, 2, 5, 2 };
		ListNode node = ListNode.createListNode(nums);

		partition(node, 3);
	}

}
