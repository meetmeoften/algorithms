package com.algoexpert2.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveDuplicatesFromLinkedList {

	public static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			this.next = null;
		}
	}

	public LinkedList removeDuplicatesFromLinkedList(LinkedList linkedList) {
		// Write your code here.
		LinkedList currentNode = linkedList;

		while (currentNode != null) {
			LinkedList next = currentNode.next;
			while (next != null && currentNode.value == next.value) {
				next = next.next;
			}
			currentNode.next = next;
			currentNode = currentNode.next;
		}

		return linkedList;
	}

	public static void main(String[] args) {

		LinkedList input = new LinkedList(1);
		addMany(input, new ArrayList<Integer>(Arrays.asList(1, 3, 4, 4, 4, 5, 6, 6)));
		List<Integer> expectedNodes = new ArrayList<Integer>(Arrays.asList(1, 3, 4, 5, 6));
		LinkedList output = new RemoveDuplicatesFromLinkedList().removeDuplicatesFromLinkedList(input);
		// Utils.assertTrue(getNodesInArray(output).equals(expectedNodes));
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

	public List<Integer> getNodesInArray(LinkedList ll) {
		List<Integer> nodes = new ArrayList<Integer>();
		LinkedList current = ll;
		while (current != null) {
			nodes.add(current.value);
			current = current.next;
		}
		return nodes;
	}

}
