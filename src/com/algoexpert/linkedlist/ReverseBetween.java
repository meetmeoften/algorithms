package com.algoexpert.linkedlist;

public class ReverseBetween {

	public ListNode reverseBetween2(ListNode head, int left, int right) {
		if (head == null) {
			return null;
		}

		ListNode preHead = new ListNode(0);
		preHead.next = head;

		ListNode start = preHead;

		for (int i = 0; i < left - 1; i++) {
			start = start.next;
		}

		ListNode curr = start.next;
		ListNode prev = null;
		for (int i = 0; i <= right - left; i++) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		start.next.next = curr;
		start.next = prev;
		return preHead.next;
	}

	public ListNode reverseBetween(ListNode head, int left, int right) {
		ListNode sentinalNode = new ListNode(0, head);
		var temp = sentinalNode;

		for (int i = 0; i < left - 1; i++) {
			temp = temp.next;
		}
		var start = temp.next;
		for (int i = 0; start != null && start.next != null && i < right - left; i++) {
			var next = start.next;
			start.next = start.next.next;
			next.next = temp.next;
			temp.next = next;
		}
		return sentinalNode.next;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5 };
		ListNode node = createListNode(nums);

		ReverseBetween r = new ReverseBetween();
		// r.reverseBetween(node, 2, 4);
		r.reverseBetween2(node, 2, 4);
	}

	public static ListNode createListNode(int[] nums) {
		ListNode node = null;
		for (int i = nums.length - 1; i >= 0; i--) {
			node = push(node, nums[i]);
		}
		return node;
	}

	public static ListNode push(ListNode head, int data) {
		ListNode node = new ListNode(data);
		node.val = data;
		node.next = head;
		return node;
	}

}
