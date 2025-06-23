package com.algoexpert.heap;

import java.util.PriorityQueue;

public class MergeKSortedLinkedLists {

	// https://leetcode.com/problems/merge-k-sorted-lists/discuss/429518/JAVA-SUMMARY-of-all-solutions-(B-F-minPQ-Divide-And-Conquer) - 44r45r54

	public ListNode mergeKLists(ListNode[] lists) {
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
			ListNode ln = pq.poll();

			if (ln.next != null) {
				pq.offer(ln.next);
			}

			curr.next = ln;
			curr = curr.next;
		}

		return dummy.next;
	}

	// Optimised Solution

	public ListNode mergeKLists2(ListNode[] lists) {
		int size = lists.length;
		int interval = 1;

		while (interval < size) {
			for (int i = 0; i < size - interval; i += 2 * interval) {
				lists[i] = merge(lists[i], lists[i + interval]);
			}

			interval *= 2;
		}

		return size > 0 ? lists[0] : null;
	}

	private ListNode merge(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode curr = dummy;

		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				curr.next = l1;
				l1 = l1.next;
			} else {
				curr.next = l2;
				l2 = l2.next;
			}

			curr = curr.next;
		}

		if (l1 != null) {
			curr.next = l1;
		} else {
			curr.next = l2;
		}

		return dummy.next;
	}

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
	}

}
