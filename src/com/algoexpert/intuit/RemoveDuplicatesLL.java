package com.algoexpert.intuit;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesLL {

	public Node removeDuplicates(Node head) {
		// Your code here
		Set<Integer> set = new HashSet<>();
		Node temp = head, prev = null;
		while (temp != null) {
			if (set.contains(temp.data)) {
				prev.next = temp.next;
			} else {
				set.add(temp.data);
				prev = temp;
			}
			temp = temp.next;
		}
		return head;
	}

	class Node {
		int data;
		Node next;

		Node(int d) {
			data = d;
			next = null;
		}
	}

}
