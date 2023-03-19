package com.algoexpert.linkedlist;

public class ListNode {

	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
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
