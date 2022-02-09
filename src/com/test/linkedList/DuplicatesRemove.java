package com.test.linkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DuplicatesRemove {

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

		while(currentNode != null) {
			LinkedList next = currentNode.next;
			while(next != null && currentNode.value == next.value) {
				next = next.next;
			}
			currentNode.next = next;
			currentNode = next;
		}

		return linkedList;
	}

	public static DuplicatesRemove.LinkedList addMany(DuplicatesRemove.LinkedList ll, List<Integer> values) {
		DuplicatesRemove.LinkedList current = ll;
		while (current.next != null) {
			current = current.next;
		}
		for (int value : values) {
			current.next = new DuplicatesRemove.LinkedList(value);
			current = current.next;
		}
		return ll;
	}

	public static List<Integer> getNodesInArray(DuplicatesRemove.LinkedList ll) {
		List<Integer> nodes = new ArrayList<Integer>();
		DuplicatesRemove.LinkedList current = ll;
		while (current != null) {
			nodes.add(current.value);
			current = current.next;
		}
		return nodes;
	}

	public static void main(String[] args) {
		DuplicatesRemove.LinkedList input = new DuplicatesRemove.LinkedList(1);
		addMany(input, new ArrayList<Integer>(Arrays.asList(1, 3, 4, 4, 4, 5, 6, 6)));
		List<Integer> expectedNodes = new ArrayList<Integer>(Arrays.asList(1, 3, 4, 5, 6));
		DuplicatesRemove.LinkedList output = new DuplicatesRemove().removeDuplicatesFromLinkedList(input);
	}
}
