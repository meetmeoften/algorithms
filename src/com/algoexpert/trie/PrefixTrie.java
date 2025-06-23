package com.algoexpert.trie;

import java.util.ArrayList;
import java.util.List;

public class PrefixTrie {


	class TrieNode {
		char value;
		TrieNode[] children;
		boolean isEndWord;

		TrieNode(char value) {
			this.value = value;
			children = new TrieNode[26];
			isEndWord = false;

		}
	}


	private TrieNode root;

	public PrefixTrie() {
		root = new TrieNode('\0');
	}

	public void insert(String word) {
		TrieNode runner = root;
		for(int i= 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if(runner.children[ch - 'a'] == null) {
				runner.children[ch - 'a'] = new TrieNode(ch);
			}
			runner = runner.children[ch - 'a'];
		}

		runner.isEndWord = true;
	}

	public boolean search(String word) {
		TrieNode runner = root;
		for(char ch: word.toCharArray()) {

			if(runner.children[ch - 'a'] == null) {
				return false;
			}
			runner = runner.children[ch - 'a'];
		}
		return runner.isEndWord ;
	}

	public boolean search2(String word) {
		return helper(word, 0, root);
	}


	public boolean wordSearch(String word) {
		return helper2(word, 0, root);
	}

	public boolean helper(String word, int idx, TrieNode t) {
		if(idx >= word.length()) {
			return t.isEndWord;
		}
		char ch = word.charAt(idx);
		return t.children[ch -'a'] != null && helper(word, idx+1, t.children[ch - 'a']);
	}

	public boolean helper2(String word, int idx, TrieNode t) {
		if(idx >= word.length()) {
			return t.isEndWord;
		}
		char ch = word.charAt(idx);
		if(ch == '.') {
			for(int i= 0; i < 26; i++) {
				if(t.children[i] != null && helper2(word, idx+1, t.children[i])) {
					return true;
				}
			}
			return false;
		}
		return t.children[ch - 'a'] != null && helper2(word, idx+1, t.children[ch - 'a']);
	}


	public boolean startsWith(String word) {
		TrieNode runner = root;
		for(char ch: word.toCharArray()) {

			if(runner.children[ch - 'a'] == null) {
				return false;
			}
			runner = runner.children[ch - 'a'];
		}
		return true;
	}


	// --------------------

	public void suggestHelper(TrieNode root, List<String> list, StringBuffer curr) {
		if (root.isEndWord) {
			list.add(curr.toString());
		}

		if (root.children == null || root.children.length == 0) {
			return;
		}

		for(int i= 0; i < root.children.length; i++) {
			TrieNode child = root.children[i];
			if(child == null) {
				continue;
			}
			suggestHelper(child, list, curr.append(child.value));
			curr.setLength(curr.length() - 1);
		}
	}

	public List<String> suggest(String prefix) {
		List<String> list = new ArrayList<>();
		TrieNode lastNode = root;
		StringBuffer curr = new StringBuffer();

		for(int i= 0; i < prefix.toCharArray().length; i++) {
			char ch = prefix.charAt(i);
			lastNode = lastNode.children[ch - 'a'];
			if (lastNode == null) {
				return list;
			}
			curr.append(ch);
		}
		suggestHelper(lastNode, list, curr);
		return list;
	}

	public static void main(String[] args) {
		PrefixTrie prefixTrie = new PrefixTrie();
		prefixTrie.insert("apple");
		prefixTrie.insert("appes");
		System.out.println(prefixTrie.search("apple"));
		System.out.println(prefixTrie.wordSearch("ap.l."));
		System.out.println(prefixTrie.startsWith("app"));


		List<String> list = new ArrayList<>();
		list.add("apple");
		list.add("appes");

		prefixTrie.suggest("appl");
	}
}
