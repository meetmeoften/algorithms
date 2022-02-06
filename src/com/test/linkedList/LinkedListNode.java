package com.test.linkedList;

public class LinkedListNode {

	private Node head;

	private static class Node {
		private final int value;
		private Node next;

		Node(int value) {
			this.value = value;

		}
	}

	/**
	 * suga
	 *
	 */


	/**
	 * add to the end of the Node
	 *
	 * @param node
	 */

	public void addToTheLast(Node node) {
		if (head == null) {
			head = node;
		} else {
			Node temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}

			temp.next = node;
		}
	}

	public Node nthFromLastNode(Node head, int n) {
		Node firstPtr = head;
		Node secondPtr = head;

		for (int i = 0; i < n; i++) {
			firstPtr = firstPtr.next;

		}

		while (firstPtr != null) {
			firstPtr = firstPtr.next;
			secondPtr = secondPtr.next;
		}

		return secondPtr;
	}

	public void printList(Node head) {
		Node temp = head;
		while (temp != null) {
			System.out.format("%d ", temp.value);
			temp = temp.next;
		}
		System.out.println();
	}

	public static Node reverseLinkedList(Node currentNode) {
		Node previousNode = null;
		Node nextNode;
		while (currentNode != null) {
			nextNode = currentNode.next;
			currentNode.next = previousNode;
			previousNode = currentNode;
			currentNode = nextNode;
		}
		return previousNode;
	}

	public static Node reverseLinkedListRecursive(Node node) {
		if (node == null || node.next == null) {
			return node;
		}

		Node remaining = reverseLinkedListRecursive(node.next);
		node.next.next = node;
		node.next = null;
		return remaining;
	}

	public int lengthOfLinkedList() {
		Node temp = head;
		int count = 0;
		while (temp != null) {
			temp = temp.next;
			count++;
		}
		return count;
	}


	public Node startNodeOfLoop() {
		Node fastPtr = head;
		Node slowPtr = head;
		boolean loopExists = false;
		while (fastPtr != null && fastPtr.next != null) {
			fastPtr = fastPtr.next.next;
			slowPtr = slowPtr.next;
			System.out.println(slowPtr.value + "   " + fastPtr.value);
			if (slowPtr == fastPtr) {
				System.out.println(slowPtr.value + "   " + fastPtr.value);
				loopExists = true;
				break;
			}

		}
		if (loopExists) {
			slowPtr = head;
			while (slowPtr != fastPtr) {
				slowPtr = slowPtr.next;
				fastPtr = fastPtr.next;
			}

		} else {
			System.out.println("Loop does not exists");
			slowPtr = null;
		}
		return slowPtr;
	}

	public static Node findMiddleNode(Node head) {
		// step 1
		Node slowPointer, fastPointer;
		slowPointer = fastPointer = head;

		while (fastPointer != null) {
			fastPointer = fastPointer.next;
			if (fastPointer != null && fastPointer.next != null) {
				slowPointer = slowPointer.next;
				fastPointer = fastPointer.next;
			}
		}

		return slowPointer;
	}

	// Function to check if linked list is palindrome or not
	public static boolean checkPalindrome (Node head) {
		// Find middle node using slow and fast pointer
		Node middleNode = findMiddleNode(head);
		// we got head of second part
		Node secondHead = middleNode.next;
		// It is end of first part of linked list
		middleNode.next = null;
		// get reversed linked list for second part
		Node reverseSecondHead = reverseLinkedList(secondHead);

		while (head != null && reverseSecondHead != null) {
			if (head.value == reverseSecondHead.value) {
				head = head.next;
				reverseSecondHead = reverseSecondHead.next;
				continue;
			} else {
				return false;
			}
		}

		return true;
	}


	public void deleteNode(int position)
	{
		// If linked list is empty
		if (head == null) {
			return;
		}

		// Store head node
		Node temp = head;

		// If head needs to be removed
		if (position == 0)
		{
			head = temp.next;   // Change head
			return;
		}

		// Find previous node of the node to be deleted
		for (int i=0; temp!=null && i<position-1; i++) {
			temp = temp.next;
		}

		// If position is more than number of ndoes
		if (temp == null || temp.next == null) {
			return;
		}

		// Node temp->next is the node to be deleted
		// Store pointer to the next of node to be deleted
		Node next = temp.next.next;

		temp.next = next;  // Unlink the deleted node from list
	}


	public static Node reverseLinkedListInPairItr(Node head) {
		Node current = head;
		Node temp = null;
		Node newHead = null;
		while (current != null && current.next != null) {

			if (temp != null) {
				// This is important step
				temp.next.next = current.next;
			}
			temp = current.next;
			current.next = temp.next;
			temp.next = current;

			if (newHead == null) {
				newHead = temp;
			}
			current = current.next;

		}
		return newHead;
	}



	public static void main(String[] args) {
		LinkedListNode list = new LinkedListNode();
		// Creating a linked list
		//		Node head = new Node(1);
		//		list.addToTheLast(head);
		//		Node node2 = new Node(2);
		//		list.addToTheLast(node2);
		//		Node node3 = new Node(3);
		//		list.addToTheLast(node3);
		//		//		list.addToTheLast(new Node(4));
		//		//		list.addToTheLast(node2);
		//		//		list.printList(head);
		//		//		list.startNodeOfLoop();
		//		//		// Reversing LinkedList
		//		Node reverseHead = reverseLinkedListRecursive(head);
		//		System.out.println(reverseHead);
		//		//		System.out.println("After reversing");
		//		//		//		list.printList(reverseHead);
		//		//
		//		//		list.nthFromLastNode(head, 2);
		//
		//		//		System.out.println("Linked list palidrome: "+checkPalindrome(head));
		//		//		reverseLinkedListInPairItr(head);

		Node loopNode=new Node(4);
		list.addToTheLast(new Node(1));
		list.addToTheLast(new Node(2));
		list.addToTheLast(loopNode);
		list.addToTheLast(new Node(3));

		list.addToTheLast(loopNode);

		// creating a loop

		// Test if loop existed or not
		System.out.println("Loop existed-->" + list.startNodeOfLoop());

		//		list.deleteNode(3);
	}

}
