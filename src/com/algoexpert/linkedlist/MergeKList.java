package com.algoexpert.linkedlist;

import java.util.PriorityQueue;

public class MergeKList {

	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}

		ListNode dummy = new ListNode(-1);
		ListNode curr = dummy;

		PriorityQueue<ListNode> pq = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);

		for (ListNode ln : lists) {
			if (ln != null) {
				pq.offer(ln);
			}
		}

		while (!pq.isEmpty()) {
			ListNode l = pq.poll();
			System.out.println(l.val);
			if (l.next != null) {
				pq.offer(l.next);
			}

			curr.next = l;
			curr = curr.next;
		}

		return dummy.next;
	}

	public static class ListNode {
		int val;
		ListNode next;

		public ListNode() {
		}

		public ListNode(int val) {
			this.val = val;
		}

		public ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public static void main(String[] args) {
		ListNode node10 = new ListNode(1);
		ListNode node11 = new ListNode(4);
		ListNode node12 = new ListNode(5);
		node10.next = node11;
		node11.next = node12;

		ListNode node20 = new ListNode(1);
		ListNode node21 = new ListNode(3);
		ListNode node22 = new ListNode(4);
		node20.next = node21;
		node21.next = node22;

		ListNode[] lists = new ListNode[] {node10, node20};
		mergeKLists(lists);


	}

}
