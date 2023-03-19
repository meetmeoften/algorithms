package com.algoexpert.linkedlist;

public class MyCircularQueue {

	int front; // front pointer
	int rear; // rear pointer
	boolean noElePresent;
	int[] cQueue = null;

	public MyCircularQueue(int k) {
		this.cQueue = new int[k];
		this.noElePresent = true;
		this.front = 0;
		this.rear = 0;
	}

	public boolean enQueue(int value) {
		if (isFull()) {
			return false;
		}

		noElePresent = false;
		cQueue[rear] = value;
		rear = (rear + 1) % cQueue.length;
		return true;
	}

	public boolean deQueue() {
		if (isEmpty()) {
			return false;
		}

		front = (front + 1) % cQueue.length;
		if (rear == front) {
			noElePresent = true;
		}
		return true;
	}

	public int Front() {
		return isEmpty() ? -1 : cQueue[front];
	}

	public int Rear() {
		if (isEmpty()) {
			return -1;
		}
		return rear == 0 ? cQueue[cQueue.length - 1] : cQueue[rear - 1];
	}

	public boolean isEmpty() {
		return noElePresent;
	}

	public boolean isFull() {
		return rear == front && !noElePresent; // or !this.isEmpty()
	}

	public static void main(String[] args) {
		MyCircularQueue cir = new MyCircularQueue(3);
		System.out.println(cir.enQueue(1));
		System.out.println(cir.enQueue(2));
		System.out.println(cir.enQueue(3));
		System.out.println(cir.enQueue(4));
		System.out.println(cir.Rear());
		System.out.println(cir.isFull());
		System.out.println(cir.deQueue());
		System.out.println(cir.enQueue(4));
		System.out.println(cir.Rear());


	}

}
