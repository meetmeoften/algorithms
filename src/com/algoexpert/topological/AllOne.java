package com.algoexpert.topological;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllOne {

    class Node {
        int count;
        Set<String> keys;
        Node prev, next;

        Node(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }

    Node head;
    Node tail;
    Map<String, Node> map;

    public AllOne() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }

    public void decr(String key) {
        Node curr = map.get(key);
        if (curr.count == 1) {
            curr.keys.remove(key);
            map.remove(key);
            if (curr.keys.isEmpty()) {
                remove(curr);
            }
        } else {
            Node prev = curr.prev;
            if (prev == head || prev.count != curr.count - 1) {
                Node newNode = new Node(curr.count - 1);
                addAfter(prev, newNode);
                prev = newNode;
            }
            prev.keys.add(key);
            map.put(key, prev);

            curr.keys.remove(key);
            if (curr.keys.isEmpty()) {
                remove(curr);
            }
        }
    }

    public void incr(String key) {
        if (map.containsKey(key)) {
            Node curr = map.get(key);
            Node next = curr.next;

            if (next == tail || next.count != curr.count + 1) {
                Node newNode = new Node(curr.count + 1);
                addAfter(curr, newNode);
                next = newNode;
            }
            next.keys.add(key);
            map.put(key, next);
            curr.keys.remove(key);
            if (curr.keys.isEmpty()) {
                remove(curr);
            }
        } else {
            Node first = head.next;
            if (first == tail || first.count != 1) {
                Node newNode = new Node(1);
                addAfter(head, newNode);
                first = newNode;
            }
            first.keys.add(key);
            map.put(key, first);
        }
    }

    public String getMaxKey() {
        if (tail.prev == head) {
            return "";
        }
        return tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        if (head.next == tail) {
            return "";
        }
        return head.next.keys.iterator().next();
    }


    private void addAfter(Node prevNode, Node newNode) {
        Node nextNode = prevNode.next;
        prevNode.next = newNode;
        newNode.prev = prevNode;

        newNode.next = nextNode;
        nextNode.prev = newNode;
    }

    private void remove(Node node) {
        node.next.prev = node.next;
        node.prev.next = node.prev;
    }
}

