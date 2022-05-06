package com.algoexpert.linkedlist;

public class SwapInPairs {

	public static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			this.next = null;
		}
	}

	public LinkedList nodeSwap(LinkedList head) {
		// Write your code here.
		LinkedList tempNode = new LinkedList(0);
		tempNode.next = head;

		LinkedList prevNode = tempNode;
		while (prevNode.next != null && prevNode.next.next != null) {
			LinkedList firstNode = prevNode.next;
			LinkedList secondNode = prevNode.next.next;

			firstNode.next = secondNode.next;
			secondNode.next = firstNode;
			prevNode.next = secondNode;

			prevNode = firstNode;

		}
		return tempNode.next;
	}
}
