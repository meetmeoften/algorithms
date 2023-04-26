package com.algoexpert.linkedlist;

import java.util.ArrayList;
import java.util.Collections;

import com.algoexpert.linkedlist.MergeKList.ListNode;

public class MaximumTwinSumOfLinkedList {

	public int pairSum(ListNode head) {
		ArrayList<Integer> l = new ArrayList<>();
		int mid = getLength(head) / 2;
		int i = 0;

		// adding only the first half elements in the arraylist
		while (i < mid) {
			l.add(head.val);
			head = head.next;
			i++;
		}
		// temp pointer has already reached to the next node(first element of second
		// half of the linkedlist),same goes with pointer i,so we decrement the i
		// pointer by 1;
		i = i - 1;

		while (head != null) {
			int sum = 0;
			sum = l.get(i) + head.val;
			l.set(i, sum);
			i--;
			head = head.next;
		}
		return Collections.max(l);
	}

	public int getLength(ListNode head) {
		int count = 0;
		while (head != null) {
			count++;
			head = head.next;
		}
		return count;
	}

	public static void main(String[] args) {
		ListNode node1 = new ListNode(5);
		ListNode node2 = new ListNode(4);
		ListNode node3 = new ListNode(2);
		ListNode node4 = new ListNode(1);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;

		MaximumTwinSumOfLinkedList twin = new MaximumTwinSumOfLinkedList();
		twin.pairSum(node1);

	}

}
