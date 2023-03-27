package com.algoexpert2.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NodeSwap {

	public static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			this.next = null;
		}
	}

	public static LinkedList nodeSwap(LinkedList head) {
		// Write your code here.
		if (head == null || head.next == null) {
			return head;
		}
		LinkedList nextNode = head.next;
		head.next = nodeSwap(head.next.next);
		nextNode.next = head;
		return nextNode;
	}

	public LinkedList nodeSwap2(LinkedList head) {
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

	public LinkedList nodeSwap3(LinkedList head) {
		return reverseKGroup(head, 2);
	}

	public LinkedList reverseKGroup(LinkedList head, int k) {
		if (head == null) {
			return null;
		}

		LinkedList curr = head;
		LinkedList prev = null;

		int count = 0;

		while (curr != null && count++ < k) {
			LinkedList next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		head.next = reverseKGroup(curr, k);
		return prev;
	}

	public static void main(String[] args) {
		LinkedList list1 = new LinkedList(0);
		addMany(list1, new ArrayList<Integer>(Arrays.asList(1)));
		nodeSwap(list1);
	}

	public static LinkedList addMany(LinkedList ll, List<Integer> values) {
		LinkedList current = ll;
		while (current.next != null) {
			current = current.next;
		}
		for (int value : values) {
			current.next = new LinkedList(value);
			current = current.next;
		}
		return ll;
	}

	public static List<Integer> getNodesInArray(LinkedList ll) {
		List<Integer> nodes = new ArrayList<Integer>();
		LinkedList current = ll;
		while (current != null) {
			nodes.add(current.value);
			current = current.next;
		}
		return nodes;
	}

}
