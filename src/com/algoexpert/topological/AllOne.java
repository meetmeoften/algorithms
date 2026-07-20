package com.algoexpert.topological;

import java.util.*;

class AllOne {

    class Node {
        int count;
        Set<String> keys;
        Node prev, next;

        Node(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }

    private Map<String, Node> map;
    private Node head, tail;

    public AllOne() {
        map = new HashMap<>();
        head = new Node(0); // dummy head
        tail = new Node(0); // dummy tail
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {

        if (!map.containsKey(key)) {
            if (head.next.count != 1) {
                addNodeAfter(new Node(1), head);
            }
            head.next.keys.add(key);
            map.put(key, head.next);
        } else {
            Node curr = map.get(key);
            Node next = curr.next;
            if (next == tail || next.count != curr.count + 1) {
                addNodeAfter(new Node(curr.count + 1), curr);
                next = curr.next;
            }
            next.keys.add(key);
            map.put(key, next);
            curr.keys.remove(key);
            if (curr.keys.isEmpty()) {
                removeNode(curr);
            }
        }
    }

    public void dec(String key) {
        Node curr = map.get(key);
        if (curr.count == 1) {
            map.remove(key);
        } else {
            Node prev = curr.prev;
            if (prev == head || prev.count != curr.count - 1) {
                addNodeAfter(new Node(curr.count - 1), prev);
                prev = curr.prev;
            }
            prev.keys.add(key);
            map.put(key, prev);
        }
        curr.keys.remove(key);
        if (curr.keys.isEmpty()) {
            removeNode(curr);
        }
    }

    public String getMaxKey() {
        if (tail.prev == head)
            return "";
        return tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        if (head.next == tail)
            return "";
        return head.next.keys.iterator().next();
    }


    private void addNodeAfter(Node node, Node prevNode) {
        Node nextNode = prevNode.next;
        prevNode.next = node;
        node.prev = prevNode;
        node.next = nextNode;
        nextNode.prev = node;
    }


    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String[] args) {

        AllOne ds = new AllOne();

        ds.inc("hello");
        ds.inc("hello");

        System.out.println(ds.getMaxKey()); // hello
        System.out.println(ds.getMinKey()); // hello

        ds.inc("leet");

        System.out.println(ds.getMaxKey()); // hello
        System.out.println(ds.getMinKey()); // leet

        ds.dec("hello");

        System.out.println(ds.getMaxKey()); // hello or leet
        System.out.println(ds.getMinKey()); // hello or leet

        ds.dec("hello");

        System.out.println(ds.getMaxKey()); // leet
        System.out.println(ds.getMinKey()); // leet
    }
}