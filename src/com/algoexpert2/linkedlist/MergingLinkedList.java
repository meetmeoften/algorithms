package com.algoexpert2.linkedlist;

public class MergingLinkedList {

	public static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			this.next = null;
		}
	}

	public LinkedList mergingLinkedLists(LinkedList linkedListOne, LinkedList linkedListTwo) {
		// Write your code here.
		LinkedList curr = linkedListOne;
		LinkedList curr2 = linkedListTwo;

		while(curr != curr2) {
			if(curr == null) {
				curr = linkedListTwo;
			} else {
				curr = curr.next;
			}

			if(curr2 == null) {
				curr2 = linkedListOne;
			} else {
				curr2 = curr2.next;
			}
		}
		return curr;
	}

	public LinkedList mergingLinkedLists2(LinkedList list1, LinkedList list2) {

		LinkedList t = new LinkedList(-1);
		LinkedList dummy = t;

		while(list1 != null && list2 != null) {
			if(list1.value<= list2.value) {
				dummy.next = list1;
				list1 = list1.next;
			} else {
				dummy.next = list2;
				list2 = list2.next;
			}
			dummy = dummy.next;
		}

		if(list1 != null) {
			dummy.next = list1;
		}

		if(list2 != null) {
			dummy.next = list2;
		}

		return t.next;

	}


	public static void main(String[] args) {
		var l1 = new LinkedList(1);
		l1.next = new LinkedList(2);
		var l2 = new LinkedList(3);
		l2.next = l1.next;

		var expected = l1.next;
		var actual = new MergingLinkedList().mergingLinkedLists2(l1, l2);
		// Utils.assertTrue(expected == actual);
	}

}
