package com.test.linkedList;

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
		// Write your code here.
		LinkedList p1 = headOne;
		LinkedList p2 = headTwo;
		LinkedList p1Prev = null;
		while(p1 != null && p2 != null) {
			if(p1.value < p2.value) {
				p1Prev = p1;
				p1 = p1.next;
			} else {
				if(p1Prev != null) {
					p1Prev.next = p2;
				}
				p1Prev = p2;
				p2 = p2.next;
				p1Prev.next = p1;
			}
		}
		if(p1 == null) {
			p1Prev.next = p2;
		}
		return headOne.value  < headTwo.value ? headOne : headTwo;
	}

	public static void main(String[] args) {
		MergeLinkedList.LinkedList list1 = new MergeLinkedList.LinkedList(2);
		addMany(list1, new ArrayList<Integer>(Arrays.asList(6, 7, 8)));
		MergeLinkedList.LinkedList list2 = new MergeLinkedList.LinkedList(1);
		addMany(list2, new ArrayList<Integer>(Arrays.asList(3, 4, 5, 9, 10)));
		LinkedList output = mergeLinkedLists(list1, list2);
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
