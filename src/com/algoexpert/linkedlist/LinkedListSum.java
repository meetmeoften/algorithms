package com.algoexpert.linkedlist;

import java.util.ArrayList;

public class LinkedListSum {

	public static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			this.next = null;
		}
	}

	public LinkedList sumOfLinkedLists(LinkedList l1, LinkedList l2) {
		LinkedList dummy = new LinkedList(-1), head = dummy;
		int remainder = 0;

		while (l1 != null || l2 != null || remainder > 0) {
			int sum = remainder;

			if (l1 != null) {
				sum += l1.value;
				l1 = l1.next;
			}

			if (l2 != null) {
				sum += l2.value;
				l2 = l2.next;
			}

			remainder = sum / 10;

			head.next = new LinkedList(sum % 10);
			head = head.next;
		}

		return dummy.next;
	}

	public static void main(String[] args) {
		LinkedListSum.LinkedList ll1 = addMany(new LinkedListSum.LinkedList(2), new int[] {4, 7, 1});
		LinkedListSum.LinkedList ll2 = addMany(new LinkedListSum.LinkedList(9), new int[] {4, 5});
		LinkedListSum.LinkedList expected = addMany(new LinkedListSum.LinkedList(1), new int[] {9, 2, 2});
		var actual = new LinkedListSum().sumOfLinkedLists(ll1, ll2);
	}

	public static LinkedListSum.LinkedList addMany(LinkedListSum.LinkedList linkedList, int[] values) {
		var current = linkedList;
		while (current.next != null) {
			current = current.next;
		}
		for (var value : values) {
			current.next = new LinkedListSum.LinkedList(value);
			current = current.next;
		}
		return linkedList;
	}

	public ArrayList<Integer> getNodesInArray(LinkedListSum.LinkedList linkedList) {
		ArrayList<Integer> nodeValues = new ArrayList<Integer>();
		LinkedListSum.LinkedList current = linkedList;
		while (current != null) {
			nodeValues.add(current.value);
			current = current.next;
		}
		return nodeValues;
	}
}
