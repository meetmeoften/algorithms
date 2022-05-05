package com.algoexpert.linkedlist;

import com.algoexpert.linkedlist.MergeKList.ListNode;

public class RemoveNthNode {

	static class LinkedList {
		int value;
		LinkedList next = null;

		public LinkedList(int value) {
			this.value = value;
		}
	}

	public static void removeKthNodeFromEnd(LinkedList head, int k) {

		LinkedList first = head;
		LinkedList second = head;

		int counter = 0;

		while(counter < k) {
			second = second.next;
			counter++;
		}

		if(second == null) {
			head.value = head.next.value;
			head.next = head.next.next;
			return;
		}

		while(second.next != null) {
			first = first.next;
			second = second.next;
		}
		first.next = first.next.next;

	}

	public ListNode removeNthFromEnd3(ListNode head, int n) {
		ListNode start = new ListNode(0);
		ListNode slow = start, fast = start;
		slow.next = head;

		//Move fast in front so that the gap between slow and fast becomes n
		for(int i=1; i<=n+1; i++)   {
			fast = fast.next;
		}
		//Move fast to the end, maintaining the gap
		while(fast != null) {
			slow = slow.next;
			fast = fast.next;
		}
		//Skip the desired node
		slow.next = slow.next.next;
		return start.next;
	}

}
