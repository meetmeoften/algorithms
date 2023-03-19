package com.algoexpert.linkedlist;

public class LinkedListPalindrome {

	public boolean isPalindrome(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;

		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		ListNode reversed = reverse(slow);

		while(reversed != null && head != null) {
			if(head.val != reversed.val ) {
				return false;
			}
			head = head.next;
			reversed = reversed.next;
		}

		return true;

	}


	public ListNode reverse(ListNode head) {
		ListNode prev = null;
		while(head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}

		return prev;
	}

}
