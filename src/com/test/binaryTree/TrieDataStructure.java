package com.test.binaryTree;

import java.util.Iterator;

public class TrieDataStructure {
	private final TrieNode root;

	/* Constructor */
	public TrieDataStructure() {
		root = new TrieNode(' ');
	}

	/* This function is used to insert a word in trie */
	public void insert(String word) {
		if (search(word) == true) {
			return;
		}
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			TrieNode child = current.getChild(ch);
			if (child != null) {
				current = child;
			} else {
				// If child not present, adding it io the list
				current.childList.add(new TrieNode(ch));
				current = current.getChild(ch);
			}
			current.count++;
		}
		current.isEnd = true;
	}

	/* This function is used to search a word in trie */
	public boolean search(String word) {
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			if (current.getChild(ch) == null) {
				return false;
			} else {
				current = current.getChild(ch);
			}
		}
		if (current.isEnd == true) {
			return true;
		}
		return false;
	}

	/* This function is used to remove function from trie */
	public void remove(String word) {
		if (search(word) == false) {
			System.out.println(word + " does not present in trien");
			return;
		}
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			System.out.println("CURRENT " + current.data + " ");
			TrieNode child = current.getChild(ch);
			System.out.println("CHILD " + child.data);
			System.out.println(child);
			if (child.count == 1) {
				current.childList.remove(child);
				return;
			} else {
				child.count--;
				current = child;
			}
		}
		System.out.println("FINALCURRENT " + current.data + " ");
		current.isEnd = false;
	}

	public static void printAllWordsInTrie(TrieNode root, String s) {
		TrieNode current = root;
		if (root.childList == null || root.childList.size() == 0) {
			return;
		}
		//		for(TrieNode child : root.childList) {
		//			System.out.println(child.data + "  " + child.count);
		//		}
		Iterator<TrieNode> iter = current.childList.iterator();
		while (iter.hasNext()) {
			TrieNode node = iter.next();
			s += node.data;
			printAllWordsInTrie(node, s);
			System.out.println("ROOT " + current.data);
			if (node.isEnd == true) {
				System.out.print(" " + s);
				s = s.substring(0, s.length() - 1);
			} else {
				s = s.substring(0, s.length() - 1);
			}
		}

	}

	public static void main(String[] args) {
		TrieDataStructure t = new TrieDataStructure();
		t.insert("do");
		t.insert("de");
		t.insert("dear");
		//		t.insert("deoal");
		//		t.insert("he");
		//		t.insert("hen");
		//		t.insert("heat");


		//		System.out.println("hen present in trie : " + t.search("hen"));
		//		System.out.println("hear present in trie : " + t.search("hear"));
		//		System.out.println("deal present in trie : " + t.search("deal"));
		//		System.out.println("========================");
		System.out.println("Printing all word present in trie : ");
		printAllWordsInTrie(t.root, "");
		t.remove("de");
		System.out.println("========================");
		System.out.println(" \n Printing all word present in trie : ");
		printAllWordsInTrie(t.root, "");
	}
}
