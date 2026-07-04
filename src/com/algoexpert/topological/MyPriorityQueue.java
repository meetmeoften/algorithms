package com.algoexpert.topological;

public class MyPriorityQueue {


    private int[] heap;
    private int size;

    public MyPriorityQueue(int capacity) {
        size = 0;
        heap = new int[capacity];
    }

    public void offer(int value) {
        if (size == heap.length) {
            throw new RuntimeException("Full");
        }
        heap[size] = value;
        heapifyUp(size);
        size++;

    }

    public int poll() {
        if (size == 0) {
            throw new RuntimeException("PriorityQueue is empty");
        }
        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return min;
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("PriorityQueue is empty");
        }
        return heap[0];
    }

    public void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap[parent] <= heap[index]) {
                break;
            }
            swap(parent, index);
            index = parent;
        }
    }

    public void heapifyDown(int index) {
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;
            if (left < size && heap[left] < heap[smallest]) {
                smallest = left;
            }
            if (right < size && heap[right] < heap[smallest]) {
                smallest = right;
            }
            if (smallest == index) {
                break;
            }
            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void main(String[] args) {
        MyPriorityQueue pq = new MyPriorityQueue(10);
        pq.offer(10);
        pq.offer(4);
        pq.offer(15);
        pq.offer(2);
        pq.offer(8);
        System.out.println(pq.peek()); // 2
        System.out.println(pq.poll()); // 2
        System.out.println(pq.poll()); // 4

    }
}
