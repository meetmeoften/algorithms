package com.algoexpert.linkedlist;

public class MyLinkedList {

	int size;
	ListNode dummyNode;

	public MyLinkedList() {
		dummyNode = new ListNode(-1);
		size = 0;
	}

	public int get(int index) {
		if (index >= size) {
			return -1;
		}
		ListNode currNode = dummyNode;
		for (int i = 0; i <= index; i++) {
			currNode = currNode.next;
		}
		return currNode.val;
	}

	public void addAtHead(int val) {
		ListNode node = new ListNode(val);
		node.next = dummyNode.next;
		dummyNode.next = node;
		size++;
	}

	public void addAtTail(int val) {
		ListNode node = new ListNode(val);
		ListNode currNode = dummyNode;

		for (int i = 0; i < size; i++) {
			currNode = currNode.next;
		}

		currNode.next = node;
		size++;
	}

	public void addAtIndex(int index, int val) {
		if (index > size) {
			return;
		}

		ListNode node = new ListNode(val);
		ListNode currNode = dummyNode;

		for (int i = 0; i < index; i++) {
			currNode = currNode.next;
		}

		node.next = currNode.next;
		currNode.next = node;
		size++;
	}

	public void deleteAtIndex(int index) {
		if (index >= size) {
			return;
		}

		ListNode currNode = dummyNode;

		for (int i = 0; i < index; i++) {
			currNode = currNode.next;
		}

		currNode.next = currNode.next.next;
		size--;
	}

	public static void main(String[] args) {
		MyLinkedList li = new MyLinkedList();

		li.addAtHead(1);
		li.addAtHead(2);
		li.addAtTail(4);
		li.addAtIndex(2, 3);
		li.get(2);
		li.deleteAtIndex(3);
	}

}
