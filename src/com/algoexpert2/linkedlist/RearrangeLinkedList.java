package com.algoexpert2.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RearrangeLinkedList {

	public static LinkedList rearrangeLinkedList(LinkedList head, int k) {
		// Write your code here.
		LinkedList smallerListHead = null;
		LinkedList smallerListTail = null;
		LinkedList equalListHead = null;
		LinkedList equalListTail = null;
		LinkedList greaterListHead = null;
		LinkedList greaterListTail = null;

		LinkedList node = head;
		while (node != null) {
			if (node.value < k) {
				LinkedListPair smallerList = growLinkedList(smallerListHead, smallerListTail, node);
				smallerListHead = smallerList.head;
				smallerListTail = smallerList.tail;
			} else if (node.value > k) {
				LinkedListPair greaterList = growLinkedList(greaterListHead, greaterListTail, node);
				greaterListHead = greaterList.head;
				greaterListTail = greaterList.tail;
			} else {
				LinkedListPair equalList = growLinkedList(equalListHead, equalListTail, node);
				equalListHead = equalList.head;
				equalListTail = equalList.tail;
			}

			LinkedList prevNode = node;
			node = node.next;
			prevNode.next = null;

		}

		LinkedListPair firstPair = connectLinkedList(smallerListHead, smallerListTail, equalListHead, equalListTail);
		LinkedListPair finalPair = connectLinkedList(firstPair.head, firstPair.tail, greaterListHead, greaterListTail);

		return finalPair.head;
	}

	public static LinkedListPair connectLinkedList(LinkedList headOne, LinkedList tailOne, LinkedList headTwo,
			LinkedList tailTwo) {
		LinkedList newHead = headOne == null ? headTwo : headOne;
		LinkedList newTail = tailTwo == null ? tailOne : tailTwo;

		if (tailOne != null) {
			tailOne.next = headTwo;
		}
		return new LinkedListPair(newHead, newTail);

	}

	public static LinkedListPair growLinkedList(LinkedList head, LinkedList tail, LinkedList node) {
		LinkedList newHead = head;
		LinkedList newTail = node;

		if (newHead == null) {
			newHead = node;
		}
		if (tail != null) {
			tail.next = node;
		}
		return new LinkedListPair(newHead, newTail);
	}

	static class LinkedListPair {
		public LinkedList head;
		public LinkedList tail;

		public LinkedListPair(LinkedList head, LinkedList tail) {
			this.head = head;
			this.tail = tail;
		}
	}

	static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			next = null;
		}
	}

	public static void main(String[] args) {
		LinkedList list1 = new LinkedList(3);
		addMany(list1, new ArrayList<Integer>(Arrays.asList(0, 5, 2, 1, 4)));
		rearrangeLinkedList(list1, 3);
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
