package com.algoexpert.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class RemoveLoopLinkedList {

	public static void removeLoop(LinkedList head) {
		// code here
		// remove the loop without losing any nodes

		LinkedList prev = null;
		LinkedList curr = head;

		Set<LinkedList> set = new HashSet<>();

		while (curr != null) {

			if (set.contains(curr)) {
				prev.next = null;
				return;
			}

			set.add(curr);

			prev = curr;
			curr = curr.next;
		}
	}

	public static void removeCycle(LinkedList slow, LinkedList head) {
		// Do for each node of the linked list
		for (LinkedList curr = head; curr != null; curr = curr.next) {
			// start a pointer `ptr` from the `slow` node and
			// check if it meets the current node `curr`
			LinkedList ptr = slow;
			while (ptr.next != slow && ptr.next != curr) {
				ptr = ptr.next;
			}

			// If `ptr` meets `curr`, then that means there is a loop, and `curr`
			// points to the first node of the loop and `ptr` points to the last node
			if (ptr.next == curr) {
				// set next pointer of `ptr` to `null` to break the chain
				ptr.next = null;
				return;
			}
		}
	}

	static class LinkedList {
		int value;
		LinkedList next = null;

		public LinkedList(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		// total number of nodes in the linked list
		int n = 5;

		// construct a linked list
		LinkedList head = null;
		for (int i = n; i > 0; i--) {
			head = push(head, i);
		}

		// insert cycle
		head.next.next.next.next.next = head.next;

		removeLoop(head);
	}

	public static LinkedList push(LinkedList head, int data) {
		LinkedList node = new LinkedList(data);
		node.value = data;
		node.next = head;
		return node;
	}
}
