package com.algoexpert2.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeLinkedList {

	public static class LinkedList {
		int value;
		LinkedList next;

		LinkedList(int value) {
			this.value = value;
			this.next = null;
		}
	}

	public static LinkedList mergeLinkedLists(LinkedList headOne, LinkedList headTwo) {
		var dummy = new LinkedList(0);
		var tail = dummy;

		while (headOne != null && headTwo != null) {
			if (headOne.value < headTwo.value) {
				tail.next = headOne;
				headOne = headOne.next;
			} else {
				tail.next = headTwo;
				headTwo = headTwo.next;
			}
			tail = tail.next;
		}

		if (headOne != null) {
			tail.next = headOne;
		} else if (headTwo != null) {
			tail.next = headTwo;
		}
		return dummy.next;
	}

	public static LinkedList mergeLinkedLists2(LinkedList headOne, LinkedList headTwo) {
		if (headOne == null) {
			return headTwo;
		}
		if (headTwo == null) {
			return headOne;
		}

		if (headOne.value < headTwo.value) {
			headOne.next = mergeLinkedLists(headOne.next, headTwo);
			return headOne;
		} else {
			headTwo.next = mergeLinkedLists(headOne, headTwo.next);
			return headTwo;
		}
	}

	public static void main(String[] args) {
		LinkedList list1 = new LinkedList(2);
		addMany(list1, new ArrayList<Integer>(Arrays.asList(6, 7, 8)));
		LinkedList list2 = new LinkedList(1);
		addMany(list2, new ArrayList<Integer>(Arrays.asList(3, 4, 5, 9, 10)));
		LinkedList output = mergeLinkedLists(list1, list2);
		List<Integer> expectedNodes = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
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
