package com.algoexpert.topological;

public class MyHashMap {

    class Node {
        int key;
        int value;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int SIZE = 1000;
    private Node[] buckets;

    public MyHashMap() {
        buckets = new Node[SIZE];
    }

    private int hash(int key) {
        return key % SIZE;
    }

    public void put(int key, int value) {

        int index = hash(key);

        if (buckets[index] == null) {
            buckets[index] = new Node(key, value);
            return;
        }

        Node curr = buckets[index];

        while (true) {
            if (curr.key == key) {
                curr.value = value;
                return;
            }

            if (curr.next == null)
                break;

            curr = curr.next;
        }

        curr.next = new Node(key, value);
    }

    public int get(int key) {

        int index = hash(key);

        Node curr = buckets[index];

        while (curr != null) {
            if (curr.key == key)
                return curr.value;

            curr = curr.next;
        }

        return -1;
    }

    public void remove(int key) {

        int index = hash(key);

        Node dummy = new Node(-1, -1);
        dummy.next = buckets[index];

        Node prev = dummy;
        Node curr = buckets[index];

        while (curr != null) {

            if (curr.key == key) {
                prev.next = curr.next;
                break;
            }

            prev = curr;
            curr = curr.next;
        }

        buckets[index] = dummy.next;
    }
}
