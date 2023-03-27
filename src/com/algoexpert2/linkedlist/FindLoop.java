package com.algoexpert2.linkedlist;

public class FindLoop {

	public static LinkedList findLoop(LinkedList head) {
		// Write your code here.
		LinkedList fast = head;
		LinkedList slow = head;

		boolean loop = false;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast) {
				loop = true;
				break;
			}
		}

		if (loop) {
			slow = head;
			while (slow != fast) {
				slow = slow.next;
				fast = fast.next;
			}
		} else {
			return null;
		}

		return slow;
	}

	static class LinkedList {
		int value;
		LinkedList next = null;

		public LinkedList(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		TestLinkedList test = new TestLinkedList(0);
		test.addMany(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
		test.getNthNode(10).next = test.getNthNode(5);
		// Utils.assertTrue(findLoop(test) == test.getNthNode(5));
		findLoop(test);
	}

	static class TestLinkedList extends LinkedList {
		public TestLinkedList(int value) {
			super(value);
		}

		public void addMany(int[] values) {
			LinkedList current = this;
			while (current.next != null) {
				current = current.next;
			}
			for (int value : values) {
				current.next = new LinkedList(value);
				current = current.next;
			}
		}

		public LinkedList getNthNode(int n) {
			int counter = 1;
			LinkedList current = this;
			while (counter < n) {
				current = current.next;
				counter++;
			}
			return current;
		}
	}

}
