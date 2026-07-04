package com.algoexpert.topological;


/**
 * Core Data Structures for Interviews
 * Data Structure	Important Topics
 * Array	Prefix sum, sliding window, two pointers
 * String	Pattern matching, hashing
 * Linked List	Reverse, cycle detection
 * Stack	Monotonic stack, expression evaluation
 * Queue	Circular queue, deque
 * HashMap / HashSet	Frequency counting
 * Heap / Priority Queue	Top K elements
 * Tree	Traversals, BST
 * Binary Search Tree	Insert/search/delete
 * Trie	Prefix searching
 * Segment Tree	Range queries
 * Fenwick Tree (BIT)	Prefix sums
 * Graph	BFS, DFS, shortest path
 * Disjoint Set (Union Find)	Connected components
 * LRU Cache	HashMap + Doubly Linked List
 */

public class DoublyLinkedList {

    class Node {
        int data;
        Node prev;
        Node next;

        Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    Node head;
    // Insert at end
    void insertAtEnd(int data) {
        Node newNode = new Node(data);

        // If list is empty
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        // Move to last node
        while (temp.next != null) {
            temp = temp.next;
        }
        // Link nodes
        temp.next = newNode;
        newNode.prev = temp;
    }

    // Display forward
    void displayForward() {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }

        System.out.println("NULL");
    }

    // Display backward
    void displayBackward() {
        Node temp = head;

        // Move to last node
        while (temp.next != null) {
            temp = temp.next;
        }

        // Traverse backward
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.prev;
        }

        System.out.println("NULL");
    }

    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();

        dll.insertAtEnd(10);
        dll.insertAtEnd(20);
        dll.insertAtEnd(30);

        System.out.println("Forward Traversal:");
        dll.displayForward();

        System.out.println("Backward Traversal:");
        dll.displayBackward();
    }
}


