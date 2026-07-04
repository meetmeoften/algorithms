package com.algoexpert.topological;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MaxStack {

    private TreeMap<Integer, List<Node>> map;
    private Node head;
    private Node tail;

    public MaxStack() {
        map = new TreeMap<>();

        head = new Node(0);
        tail = new Node(0);

        head.next = tail;
        tail.prev = head;
    }

    public void push(int x) {
        Node node = new Node(x);
        addToTop(node);

        map.computeIfAbsent(x, key -> new ArrayList<>()).add(node);
    }

    public int pop() {
        Node node = tail.prev;
        removeNode(node);

        List<Node> list = map.get(node.val);
        list.remove(list.size() - 1);

        if (list.isEmpty()) {
            map.remove(node.val);
        }

        return node.val;
    }

    public int top() {
        return tail.prev.val;
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = map.lastKey();

        List<Node> list = map.get(max);
        Node node = list.remove(list.size() - 1);

        if (list.isEmpty()) {
            map.remove(max);
        }

        removeNode(node);

        return max;
    }

    private void addToTop(Node node) {
        Node prev = tail.prev;

        prev.next = node;
        node.prev = prev;

        node.next = tail;
        tail.prev = node;
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    static class Node {
        int val;
        Node prev;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        MaxStack stack = new MaxStack();

        stack.push(5);
        stack.push(1);
        stack.push(5);

        System.out.println(stack.top());      // 5
        System.out.println(stack.popMax());   // 5
        System.out.println(stack.top());      // 1
        System.out.println(stack.peekMax());  // 5
        System.out.println(stack.pop());      // 1
        System.out.println(stack.top());      // 5
    }
}
