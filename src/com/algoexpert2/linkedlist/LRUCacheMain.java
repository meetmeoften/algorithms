package com.algoexpert2.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheMain {

	static class LRUCache {

		int maxSize;
		Map<String, Node> map = new HashMap<>();

		Node head = null;
		Node tail = null;

		public LRUCache(int maxSize) {
			this.maxSize = maxSize > 1 ? maxSize : 1;
		}

		public void insertKeyValuePair(String key, int value) {
			if (map.containsKey(key)) {
				Node old = map.get(key);
				old.value = value;
				delete(old);
				setHead(old);
			} else {
				Node newNode = new Node(key, value);
				if (map.size() >= maxSize) {
					map.remove(tail.key);
					delete(tail);
					setHead(newNode);
				} else {
					setHead(newNode);
				}
				map.put(key, newNode);
			}
		}

		public LRUResult getValueFromKey(String key) {
			if (!map.containsKey(key)) {
				return new LRUResult(false, -1);
			}
			Node n = map.get(key);
			delete(n);
			setHead(n);
			return new LRUResult(true, map.get(key).value);
		}

		public String getMostRecentKey() {
			if (head == null) {
				return "";
			}
			return head.key;
		}

		// ----------
		public void setHead(Node node) {
			node.next = head;
			node.prev = null;

			if (head != null) {
				head.prev = node;
			}
			head = node;
			if (tail == null) {
				tail = head;
			}
		}

		public void delete(Node node) {
			if (node.prev != null) {
				node.prev.next = node.next;
			} else {
				head = node.next;
			}

			if (node.next != null) {
				node.next.prev = node.prev;
			} else {
				tail = node.prev;
			}
		}

		// ----------

	}

	static class Node {
		String key;
		int value;
		Node prev;
		Node next;

		public Node(String key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	static class LRUResult {
		boolean found;
		int value;

		public LRUResult(boolean found, int value) {
			this.found = found;
			this.value = value;
		}
	}

	public static void main(String[] args) {
		LRUCache lruCache = new LRUCache(3);
		lruCache.insertKeyValuePair("b", 2);
		lruCache.insertKeyValuePair("a", 1);
		lruCache.insertKeyValuePair("c", 3);
		lruCache.getMostRecentKey();
		lruCache.getValueFromKey("a");
		lruCache.getMostRecentKey();
		lruCache.insertKeyValuePair("d", 4);
		var evictedValue = lruCache.getValueFromKey("b");
		lruCache.insertKeyValuePair("a", 5);
		lruCache.getValueFromKey("a");
	}

}
