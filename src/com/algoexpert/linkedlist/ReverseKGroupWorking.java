package com.algoexpert.linkedlist;

public class ReverseKGroupWorking {

	public ListNode swapPairs(ListNode head) {
		ListNode node = reverseKGroup(head, 2);
		return node;
	}

	public ListNode reverseKGroup(ListNode head, int k) {
		// base case
		if (head == null) {
			return null;
		}

		ListNode curr = head;
		ListNode prev = null;
		int count = 0;

		while (curr != null && count++ < k) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		head.next = reverseKGroup(curr, k);
		return prev;
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
		int[] nums = { 1, 2, 3, 5 };
		ListNode node = ListNode.createListNode(nums);

		ReverseKGroupWorking r = new ReverseKGroupWorking();
		r.swapPairs(node);
		r.reverseBetween(node, 2, 4);
	}


}
