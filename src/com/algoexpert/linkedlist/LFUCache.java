package com.algoexpert.linkedlist;

import java.util.HashMap;
import java.util.PriorityQueue;

public class LFUCache {

	HashMap<Integer, Node> hash;
	PriorityQueue<Node> pq;
	int capacity;
	int counter;

	class Node {
		int key;
		int val;
		int frequency;
		int date;
	}

	public LFUCache(int capacity) {
		hash = new HashMap<>();
		this.capacity = capacity;

		pq = new PriorityQueue<Node>((a, b) -> {
			if (a.frequency == b.frequency) {
				if (a.date < b.date) {
					return -1;
				} else if (a.date == b.date) {
					return 0;
				} else {
					return 1;
				}

			}
			return a.frequency - b.frequency;
		});
	}

	public int get(int key) {
		counter++;
		Node node = hash.get(key);
		int result = -1;
		if (node != null) {
			pq.remove(node);
			node.frequency += 1;
			node.date = counter;
			result = node.val;
			pq.add(node);
		}
		return result;
	}

	public void put(int key, int value) {
		counter++;
		if (capacity == 0) {
			return;
		}
		Node node = hash.get(key);
		if (node != null) {
			node.frequency += 1;
			node.val = value;
			node.date = counter;
			pq.remove(node);
			pq.add(node);
		} else {
			if (capacity == hash.size()) {
				hash.remove(pq.poll().key);
			}
			node = new Node();
			node.val = value;
			node.key = key;
			node.frequency = 1;
			node.date = counter;
			hash.put(key, node);
			pq.add(node);
		}
	}

	public static void main(String[] args) {

	}

}
