package com.algoexpert.linkedlist;

public class IntersectionYElement {

	public static int intersectPoint(Node headA, Node headB) {
		if (headA == null || headB == null) {
			return -1;
		}

		Node a = headA;
		Node b = headB;

		// if a & b have different len, then we will stop the loop after second
		// iteration
		while (a != b) {
			// for the end of first iteration, we just reset the pointer to the head of
			// another linkedlist
			//			a = a == null ? headB : a.next;
			//			b = b == null ? headA : b.next;

			if (a == null) {
				a = headB;
			}
			else {
				a = a.next;
			}

			// When the second list reaches its end, redirect it to the
			// head of the first list
			if (b == null) {
				b = headA;
			}
			else {
				b = b.next;
			}
		}

		return a.data;

	}

	public static class Node {
		public int data;
		public Node next;

		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	public static void main(String[] args) {
		// construct the first linked list (1 —> 2 —> 3 —> 4 —> 5 —> null)
		Node first = null;
		for (int i = 5; i > 0; i--) {
			first = push(first, i);
		}

		// construct the second linked list (1 —> 2 —> 3 —> null)
		Node second = null;
		for (int i = 3; i > 0; i--) {
			second = push(second, i);
		}

		// link tail of the second list to the fourth node of the first list
		//second.next.next.next = first.next.next.next;

		Integer addr = intersectPoint(first, second);
		if (addr != null) {
			System.out.println("The intersection point is " + addr);
		}
	}

	public static Node push(Node head, int data) {
		Node node = new Node(data);
		node.data = data;
		node.next = head;
		return node;
	}

}
