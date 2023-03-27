package com.algoexpert2.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class ReverseLinkedList {

	public static LinkedList reverseLinkedList(LinkedList head) {
		// Write your code here.

		LinkedList prevNode = null;

		while (head != null) {
			LinkedList next = head.next;
			head.next = prevNode;
			prevNode = head;
			head = next;
		}

		return prevNode;
	}

	static class LinkedList {
		int value;
		LinkedList next = null;

		public LinkedList(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		LinkedList test = newLinkedList(new int[] { 0, 1, 2, 3, 4, 5 });
		List<Integer> result = toArrayList(reverseLinkedList(test));
		int[] expected = new int[] { 5, 4, 3, 2, 1, 0 };
		// Utils.assertTrue(arraysEqual(result, expected));
	}

	public static LinkedList newLinkedList(int[] values) {
		LinkedList ll = new LinkedList(values[0]);
		LinkedList current = ll;
		for (int i = 1; i < values.length; i++) {
			current.next = new LinkedList(values[i]);
			current = current.next;
		}
		return ll;
	}

	public static List<Integer> toArrayList(LinkedList ll) {
		List<Integer> arr = new ArrayList<Integer>();
		LinkedList current = ll;
		while (current != null) {
			arr.add(current.value);
			current = current.next;
		}
		return arr;
	}

	public boolean arraysEqual(List<Integer> arr1, int[] arr2) {
		if (arr1.size() != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.size(); i++) {
			if (arr1.get(i) != arr2[i]) {
				return false;
			}
		}
		return true;
	}

}
