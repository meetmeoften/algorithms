package com.test.linkedList;

public class AddTwoNumberLinkedList{
	/*
	 * @Author: Arpit Mandliya
	 * */
	private static Node head;

	private static class Node {
		private int value;
		private Node next;

		Node(int value) {
			this.value = value;

		}
	}

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

	public void printList(Node printNode) {
		Node temp = printNode;
		while (temp != null) {
			System.out.format("%d ", temp.value);
			temp = temp.next;
		}
		System.out.println();
	}

	public static Node reverseLinkedList(Node node) {
		if (node == null || node.next == null) {
			return node;
		}

		Node remaining = reverseLinkedList(node.next);
		node.next.next = node;
		node.next = null;
		return remaining;
	}

	// This function will do sum of numbers represented by linked list
	public Node findSumOfNumbers(Node l1, Node l2) {
		int carry =0;

		Node newHead = null;
		Node tempNodeForIteration=null;
		int sum=0;

		int firstIter=0;
		while(l1!=null || l2!=null)
		{
			firstIter++;
			sum=carry;
			if(l1!=null)
			{
				sum=sum+l1.value;
				l1=l1.next;
			}

			if(l2!=null)
			{
				sum=sum+l2.value;
				l2=l2.next;
			}

			carry=sum/10;
			sum=sum%10;
			// Check if it first node for the result
			if(firstIter==1)
			{
				tempNodeForIteration = new Node(sum);
				newHead=tempNodeForIteration;
			}
			else
			{
				Node tempSumNode=new Node(sum);
				tempNodeForIteration.next=tempSumNode;
				tempNodeForIteration=tempNodeForIteration.next;
			}
			if(carry!=0)
			{
				Node tempNode=new Node(carry);
				tempNodeForIteration.next=tempNode;
			}

		}

		return newHead;
	}

	public static Node shiftLinkedList(Node head, int k) {
		// Write your code here.
		int length = 1;
		Node listTail = head;
		while(listTail.next != null) {
			listTail = listTail.next;
			length++;
		}


		int offset = Math.abs(k) % length;
		System.out.println("offset " + offset);
		if(offset == 0) {
			return head;
		}
		int tailPosition = k > 0 ? length - offset : offset;
		Node newTail=head;

		System.out.println("tailPosition " + tailPosition);
		for(int i= 1; i<tailPosition; i++) {
			newTail = newTail.next;

		}
		System.out.println("NewTailValue " + newTail.value);
		System.out.println("ListTailValue " + listTail.value);
		System.out.println("ListTailNext " + listTail.next);
		Node newHead = newTail.next;
		newTail.next = null;
		listTail.next = head;
		return newHead;
	}

	public Node sumOfLinkedLists(Node linkedListOne, Node linkedListTwo) {
		// Write your code here.

		int carry = 0;
		Node newLinkedListHeadPointer = new Node(0);
		Node currentNode = newLinkedListHeadPointer;
		Node temp = linkedListOne;

		while(temp != null) {
			System.out.println(temp.value);
			temp = temp.next;
		}

		Node nodeOne = linkedListOne;
		Node nodeTwo = linkedListTwo;

		while(nodeOne != null || nodeTwo != null || carry != 0) {

			int valueOne = (nodeOne != null) ? nodeOne.value : 0;
			int valueTwo = (nodeTwo != null) ? nodeTwo.value : 0;
			int sumOfValues = valueOne + valueTwo + carry;

			int newValue = sumOfValues%10;
			Node newNode = new Node(newValue);
			currentNode.next = newNode;
			currentNode = newNode;

			carry = sumOfValues/10;
			nodeOne = (nodeOne != null) ? nodeOne.next : null;
			nodeTwo = (nodeTwo != null) ? nodeTwo.next : null;
		}

		return newLinkedListHeadPointer.next;
	}

	public static void main(String[] args) {
		AddTwoNumberLinkedList list = new AddTwoNumberLinkedList();
		// Creating a linked list
		Node head1=new Node(5);
		list.addToTheLast(head1);
		list.addToTheLast(new Node(5));
		list.addToTheLast(new Node(8));
		list.addToTheLast(new Node(6));
		System.out.print("Number 1:  ");
		list.printList(head1);
		head=null;
		Node head2=new Node(1);
		list.addToTheLast(head2);
		list.addToTheLast(new Node(2));
		list.addToTheLast(new Node(3));
		list.addToTheLast(new Node(4));
		list.addToTheLast(new Node(5));
		list.addToTheLast(new Node(6));

		System.out.print("Number 2:  ");
		Node result= shiftLinkedList(head2, 2);
		//		list.printList(head2);
		//		//		Node result= list.sumOfLinkedLists(head1,head2);
		//		//		list.printList(result);
		//
		//		//		Reversing first linkedList
		//		head1=reverseLinkedList(head1);
		//
		//		//Reversing second linkedList
		//		head2=reverseLinkedList(head2);
		//
		//		// function to find sum of two linkedlist represent by number
		//		Node result= list.findSumOfNumbers(head1,head2);
		//		// Reverse the above linkedlist to get actual sum
		//		result=reverseLinkedList(result);
		//		System.out.print("Sum:  ");
		list.printList(result);
	}
}
