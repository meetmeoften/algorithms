package com.algoexpert2.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZipLinkedList {

	public static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			this.next = null;
		}
	}

	public LinkedList zipLinkedList(LinkedList linkedList) {
		// Write your code here.
		if ((linkedList.next == null) || (linkedList.next.next == null)) {
			return linkedList;
		}
		LinkedList firstHalfHead = linkedList;
		LinkedList secondHalfHead = splitLinkedList(linkedList);

		LinkedList reversedSecondHalfHead = reverseLinkedList(secondHalfHead);
		return interweaveLinkedLists(firstHalfHead, reversedSecondHalfHead);
	}

	public LinkedList interweaveLinkedLists(LinkedList l1, LinkedList l2) {
		LinkedList l1Iter = l1;
		LinkedList l2Iter = l2;

		while (l1Iter != null && l2Iter != null) {
			LinkedList f1IterNext = l1Iter.next;
			LinkedList f2IterNext = l2Iter.next;

			l1Iter.next = l2Iter;
			l2Iter.next = f1IterNext;

			l1Iter = f1IterNext;
			l2Iter = f2IterNext;
		}
		return l1;
	}

	public LinkedList splitLinkedList(LinkedList linkedList) {
		LinkedList slow = linkedList;
		LinkedList fast = linkedList;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		LinkedList secondHalfHead = slow.next;
		slow.next = null;
		return secondHalfHead;
	}

	public LinkedList reverseLinkedList(LinkedList linkedList) {
		LinkedList previousNode = null;
		LinkedList currentNode = linkedList;
		while (currentNode != null) {
			LinkedList nextNode = currentNode.next;
			currentNode.next = previousNode;
			previousNode = currentNode;
			currentNode = nextNode;
		}
		return previousNode;
	}

	public static void main(String[] args) {
		TestLinkedList head = new TestLinkedList(1);
		head.addMany(new int[] { 2, 3, 4, 5, 6 });
		List<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 6, 2, 5, 3, 4));
		var actual = getNodesInArray(new ZipLinkedList().zipLinkedList(head));
		// Utils.assertTrue(expected.equals(actual));
	}

	public static List<Integer> getNodesInArray(LinkedList linkedList) {
		var nodes = new ArrayList<Integer>();
		var current = linkedList;
		while (current != null) {
			nodes.add(current.value);
			current = current.next;
		}
		return nodes;
	}

	static class TestLinkedList extends LinkedList {
		public TestLinkedList(int value) {
			super(value);
		}

		public void addMany(int[] values) {
			LinkedList current = this;
			while (current.next != null) {
				current = current.next;
			}
			for (int value : values) {
				current.next = new LinkedList(value);
				current = current.next;
			}
		}
	}

}
