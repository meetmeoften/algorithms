package com.algoexpert2.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShiftLinkedList {

	public static LinkedList shiftLinkedList(LinkedList head, int k) {
		LinkedList curr = head;
		// 0 1 2 3 4 5

		int length = 1;

		while(curr.next != null) {
			curr = curr.next;
			length++;
		}

		int offset = Math.abs(k) % length;

		if(offset == 0) {
			return head;
		}

		int tailPos = k > 0 ? length - offset : offset;

		LinkedList newNode = head;

		for(int i= 1; i < tailPos; i++) {
			newNode = newNode.next;
		}

		LinkedList newHead = newNode.next;
		newNode.next = null;
		curr.next = head;

		return newHead;
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

		var head = new LinkedList(0);
		head.next = new LinkedList(1);
		head.next.next = new LinkedList(2);
		head.next.next.next = new LinkedList(3);
		head.next.next.next.next = new LinkedList(4);
		head.next.next.next.next.next = new LinkedList(5);
		var result = shiftLinkedList(head, 2);
		var array = linkedListToArray(result);

		var expected = Arrays.asList(new Integer[] { 4, 5, 0, 1, 2, 3 });
		// Utils.assertTrue(expected.equals(array));
	}

	public static List<Integer> linkedListToArray(LinkedList head) {
		var array = new ArrayList<Integer>();
		var current = head;
		while (current != null) {
			array.add(current.value);
			current = current.next;
		}
		return array;
	}
}
