package com.algoexpert.linkedlist;

import java.util.ArrayList;
import java.util.HashSet;

public class Intersection {

	public static class Node {
		public int data;
		public Node next;

		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	public static Node findIntersection(Node head1, Node head2) {
		HashSet<Integer> set = new HashSet<>();
		ArrayList<Integer> list = new ArrayList<>();
		Node temp1 = head1;
		Node temp2 = head2;
		while (temp2 != null) {
			if (!set.contains(temp2.data)) {
				set.add(temp2.data);
			}
			temp2 = temp2.next;
		}
		while (temp1 != null) {
			if (set.contains(temp1.data)) {
				list.add(temp1.data);
			}
			temp1 = temp1.next;
		}

		Node main = null, dummy = null;
		for (int i = 0; i < list.size(); i++) {
			Node newNode = new Node(list.get(i));
			if (main == null) {
				main = newNode;
				dummy = main;
			} else {
				dummy.next = newNode;
				dummy = dummy.next;
			}

		}
		return main;
	}

	private static int length(Node node) {
		int length = 0;
		while (node != null) {
			node = node.next;
			length++;
		}
		return length;
	}

}
