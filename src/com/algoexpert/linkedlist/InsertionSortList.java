package com.algoexpert.linkedlist;

public class InsertionSortList {

	public static ListNode insertionSortList(ListNode head) {

		if (head == null) {
			return head;
		}

		ListNode helper = new ListNode(0); // new starter of the sorted list
		ListNode cur = head; // the node will be inserted
		ListNode pre = helper; // insert node between pre and pre.next

		while (cur != null) {
			ListNode next = cur.next;
			while (pre.next != null && pre.next.val < cur.val) {
				pre = pre.next;
			}
			cur.next = pre.next;
			pre.next = cur;
			pre = helper;
			cur = next;
		}
		return helper.next;

	}

	public static void main(String[] args) {
		int[] nums = { 4, 2, 1, 3 };
		ListNode node = ListNode.createListNode(nums);
		insertionSortList(node);
	}

}
