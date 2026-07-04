package com.algoexpert.topological;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head;
    Node tail;
    int capacity;
    Map<Integer, Node> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }


    public int get(int key) {
        Node node = cache.get(key);
        if(node == null) return -1;
        moveToFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            Node existing = cache.get(key);
            existing.value = value;
            moveToFront(existing);
            return;
        }
        if(cache.size() == capacity) {
            Node lru = tail.prev;
            removeNode(lru);
            cache.remove(lru.key);
        }

        Node newNode = new Node(key, value);
        addToFront(newNode);
        cache.put(key, newNode);

    }

    private void moveToFront(Node node) {
        removeNode(node);
        addToFront(node);
    }

    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.get(2);
    }
}
