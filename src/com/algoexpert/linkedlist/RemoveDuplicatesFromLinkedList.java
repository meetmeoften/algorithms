package com.algoexpert.linkedlist;

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


	public static void main(String[] args) {
		RemoveDuplicatesFromLinkedList.LinkedList input = new RemoveDuplicatesFromLinkedList.LinkedList(1);
		addMany(input, new ArrayList<Integer>(Arrays.asList(1, 3, 4, 4, 4, 5, 6, 6)));
		List<Integer> expectedNodes = new ArrayList<Integer>(Arrays.asList(1, 3, 4, 5, 6));
		RemoveDuplicatesFromLinkedList.LinkedList output = new RemoveDuplicatesFromLinkedList().removeDuplicatesFromLinkedList(input);
	}

	public static RemoveDuplicatesFromLinkedList.LinkedList addMany(RemoveDuplicatesFromLinkedList.LinkedList ll, List<Integer> values) {
		RemoveDuplicatesFromLinkedList.LinkedList current = ll;
		while (current.next != null) {
			current = current.next;
		}
		for (int value : values) {
			current.next = new RemoveDuplicatesFromLinkedList.LinkedList(value);
			current = current.next;
		}
		return ll;
	}

	public static List<Integer> getNodesInArray(RemoveDuplicatesFromLinkedList.LinkedList ll) {
		List<Integer> nodes = new ArrayList<Integer>();
		RemoveDuplicatesFromLinkedList.LinkedList current = ll;
		while (current != null) {
			nodes.add(current.value);
			current = current.next;
		}
		return nodes;
	}
}
