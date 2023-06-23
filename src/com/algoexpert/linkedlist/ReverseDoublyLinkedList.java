package com.algoexpert.linkedlist;

public class ReverseDoublyLinkedList {

	public static DoublyLinkedListNode reverse(DoublyLinkedListNode head) {

		if (head == null) {
			return head;
		}

		DoublyLinkedListNode currentNode = head;
		DoublyLinkedListNode newHead = null;

		while (currentNode != null) {
			DoublyLinkedListNode prev = currentNode.prev;
			currentNode.prev = currentNode.next;
			currentNode.next = prev;
			newHead = currentNode;

			currentNode = currentNode.prev;

		}

		return newHead;
	}

	public static class DoublyLinkedListNode {
		int data;
		DoublyLinkedListNode next;
		DoublyLinkedListNode prev;

		DoublyLinkedListNode(int data) {
			this.data = data;
		}

		public int getData() {
			return data;
		}
		public void setData(int data) {
			this.data = data;
		}
		public DoublyLinkedListNode getNext() {
			return next;
		}
		public void setNext(DoublyLinkedListNode next) {
			this.next = next;
		}
		public DoublyLinkedListNode getPrev() {
			return prev;
		}
		public void setPrev(DoublyLinkedListNode prev) {
			this.prev = prev;
		}

	}


	public static void main(String[] args) {
		DoublyLinkedListNode head = new DoublyLinkedListNode(1);
		DoublyLinkedListNode node2 = new DoublyLinkedListNode(2);
		DoublyLinkedListNode node3= new DoublyLinkedListNode(3);
		DoublyLinkedListNode node4 = new DoublyLinkedListNode(4);
		head.setNext(node2);


		head.getNext().setNext(node3);
		head.getNext().setPrev(head);

		head.getNext().getNext().setNext(node4);
		head.getNext().getNext().setPrev(node2);

		head.getNext().getNext().getNext().setPrev(node3);

		DoublyLinkedListNode result = reverse(head);
		System.out.println(result);
	}
}
