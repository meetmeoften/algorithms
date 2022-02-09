package com.algoexpert.hard;

public class HardProblemsLinkedList {

	public static LinkedList reverseLinkedList(LinkedList head) {
		// Write your code here.
		LinkedList current = head;
		LinkedList prev = null;

		while(current != null) {
			LinkedList next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		return prev;
	}

	public static LinkedList findLoop(LinkedList head) {
		// Write your code here.
		LinkedList fast = head;
		LinkedList slow = head;

		boolean loop = false;
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if(slow == fast) {
				loop = true;
				break;
			}
		}

		if(loop) {
			slow = head;
			while(slow != fast) {
				slow = slow.next;
				fast = fast.next;
			}
		} else {
			return null;
		}
		return slow;
	}

	// MergeLinkedList
	public static LinkedList mergeLinkedLists(LinkedList headOne, LinkedList headTwo) {
		var dummy = new LinkedList(0);
		var tail = dummy;
		while(headOne != null && headTwo != null) {
			if(headOne.value < headTwo.value) {
				tail.next = headOne;
				headOne = headOne.next;
			} else {
				tail.next = headTwo;
				headTwo = headTwo.next;
			}
			tail = tail.next;
		}

		if(headOne != null) {
			tail.next = headOne;
		} else if(headTwo != null) {
			tail.next = headTwo;
		}
		return dummy.next;
	}

	// ShiftedLinkedList
	public static LinkedList shiftLinkedList(LinkedList head, int k) {
		LinkedList node = head;

		int length = 1;
		while(node.next != null) {
			node = node.next;
			length++;
		}

		int offset = Math.abs(k) % length;
		if(offset == 0) {
			return head;
		}
		int tailPosition = k > 0 ? length-offset : offset;

		LinkedList newNode = head;
		for(int i= 1; i < tailPosition; i++) {
			newNode = newNode.next;
		}

		LinkedList newHead = newNode.next;
		newNode.next = null;
		node.next = newNode;
		return newHead;
	}


	static class LinkedList {
		int value;
		LinkedList next = null;

		public LinkedList(int value) {
			this.value = value;
		}
	}
}
