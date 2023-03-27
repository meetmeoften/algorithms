package com.algoexpert2.linkedlist;

public class LinkedListPalindrome {

	public static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			this.next = null;
		}
	}

	public boolean linkedListPalindrome(LinkedList head) {
		// Write your code here.
		LinkedList slowNode = head;
		LinkedList fastNode = head;

		while (fastNode != null && fastNode.next != null) {
			slowNode = slowNode.next;
			fastNode = fastNode.next.next;
		}

		LinkedList reversedSecondHalfNode = reverseLinkedList(slowNode);
		LinkedList firstHalfNode = head;

		while (reversedSecondHalfNode != null) {
			if (reversedSecondHalfNode.value != firstHalfNode.value) {
				return false;
			}
			reversedSecondHalfNode = reversedSecondHalfNode.next;
			firstHalfNode = firstHalfNode.next;
		}
		return true;
	}

	public static LinkedList reverseLinkedList(LinkedList head) {
		LinkedList previousNode = null;
		LinkedList currentNode = head;
		while (currentNode != null) {
			LinkedList nextNode = currentNode.next;
			currentNode.next = previousNode;
			previousNode = currentNode;
			currentNode = nextNode;
		}
		return previousNode;
	}

	public static void main(String[] args) {
		var head = new LinkedList(0);
		head.next = new LinkedList(1);
		head.next.next = new LinkedList(2);
		head.next.next.next = new LinkedList(2);
		head.next.next.next.next = new LinkedList(1);
		head.next.next.next.next.next = new LinkedList(0);
		var expected = true;
		var actual = new LinkedListPalindrome().linkedListPalindrome(head);
		// Utils.assertTrue(expected == actual);
	}
}
