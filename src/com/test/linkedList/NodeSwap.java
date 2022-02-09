package com.test.linkedList;

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
		if(head == null || head.next == null) {
			return head;
		}
		LinkedList nextNode = head.next;
		head.next = nodeSwap(head.next.next);
		nextNode.next = head;
		return nextNode;
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
