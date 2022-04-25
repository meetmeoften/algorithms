package com.algoexpert.trie;

public class WordDictionary {

	class TrieNode {
		private TrieNode[] children;
		private boolean isWord;

		public TrieNode() {
			children = new TrieNode[26];
			isWord = false;
		}
	}

	private TrieNode root;

	public WordDictionary() {
		root = new TrieNode();
	}

	public void addWord(String word) {
		TrieNode curr = root;

		for (char c : word.toCharArray()) {
			if (curr.children[c - 'a'] == null) {
				curr.children[c - 'a'] = new TrieNode();
			}
			curr = curr.children[c - 'a'];
		}

		curr.isWord = true;
	}

	public boolean search(String word) {
		return helper(word, 0, root);
	}

	private boolean helper(String word, int idx, TrieNode t) {
		if (idx >= word.length()) {
			return t.isWord;
		}

		char c = word.charAt(idx);

		if (c == '.') {
			for (int i = 0; i < 26; i++) {
				if (t.children[i] != null && helper(word, idx + 1, t.children[i])) {
					return true;
				}
			}

			return false;
		}

		return t.children[c - 'a'] != null && helper(word, idx + 1, t.children[c - 'a']);
	}

	public static void main(String[] args) {
		WordDictionary wordDictionary = new WordDictionary();
		wordDictionary.addWord("bad");
		wordDictionary.addWord("dad");
		wordDictionary.addWord("mad");
		wordDictionary.search("pad"); // return False
		wordDictionary.search("bad"); // return True
		wordDictionary.search(".ad"); // return True
		wordDictionary.search("b.."); // return True//
	}

}


