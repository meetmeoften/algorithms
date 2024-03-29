package com.algoexpert.linkedlist;

public class ReverseKGroup {

	public static LinkedList reverseKGroup(LinkedList head, int k) {
		if (head == null || head.next == null || k <= 1) {
			return head;
		}

		LinkedList dummy = new LinkedList(-1);
		dummy.next = head;
		LinkedList begin = dummy;
		int cnt = 0;

		while (head != null) {
			++cnt;

			if (cnt % k == 0) {
				begin = reverse(begin, head.next);
				head = begin.next;
			} else {
				head = head.next;
			}
		}

		return dummy.next;
	}

	private static LinkedList reverse(LinkedList begin, LinkedList end) {
		LinkedList prev = begin.next, curr = begin.next.next;

		while (curr != end) {
			prev.next = curr.next;
			curr.next = begin.next;
			begin.next = curr;
			curr = prev.next;
		}

		return prev;
	}

	private static LinkedList reverse2(LinkedList head) {
		LinkedList prev = null;

		while(head != null) {
			LinkedList next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}

	static LinkedList reverseRecursion(LinkedList head)
	{
		if (head == null || head.next == null) {
			return head;
		}

		/* reverse the rest list and put
        the first element at the end */
		LinkedList rest = reverseRecursion(head.next);
		head.next.next = head;

		/* tricky step -- see the diagram */
		head.next = null;

		/* fix the head pointer */
		return rest;
	}


	static class LinkedList {
		int value;
		LinkedList next = null;

		public LinkedList(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		LinkedList test = newLinkedList(new int[] {0, 1, 2, 3, 4});
		var result = reverseKGroup(test, 3);
		System.out.println(result);
	}

	public static LinkedList newLinkedList(int[] values) {
		LinkedList ll = new LinkedList(values[0]);
		LinkedList current = ll;
		for (int i = 1; i < values.length; i++) {
			current.next = new LinkedList(values[i]);
			current = current.next;
		}
		return ll;
	}

	public String longestCommonPrefix(String[] strs) {
		if(strs == null || strs.length == 0) {
			return "";
		}
		String pre = strs[0];
		int i = 1;
		while(i < strs.length){
			while(strs[i].indexOf(pre) != 0) {
				pre = pre.substring(0,pre.length()-1);
			}
			i++;
		}
		return pre;
	}
}
