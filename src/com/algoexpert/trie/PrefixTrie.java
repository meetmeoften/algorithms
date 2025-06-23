package com.algoexpert.trie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

	public List<String> autoComplete(String prefix) {
		List<String> results = new ArrayList<>();

		TrieNode node = root;
		for(int i=0; i < prefix.length(); i++) {
			char ch = prefix.charAt(i);
			if(node.children[ch - 'a'] == null) {
				return results;
			}
			node = node.children[ch - 'a'];
		}
		dfs(node, new StringBuilder(), results);
		return results;
	}

	private void dfs(TrieNode node, StringBuilder stringBuilder, List<String> results) {
		if(node.isEndWord) {
			results.add(stringBuilder.toString());
		}

		for(char ch ='a'; ch <= 'z'; ch++) {
			TrieNode child = node.children[ch - 'a'];
			if(child != null) {
				stringBuilder.append(ch);
				dfs(child, stringBuilder, results);
				stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			}
		}
 	}

	private void bfs(String prefix, TrieNode node, List<String> results) {
		Queue<Pair> queue = new LinkedList<>();
		queue.offer(new Pair(node, new StringBuilder(prefix)));

		while (!queue.isEmpty()) {
			Pair current = queue.poll();
			TrieNode currNode = current.node;
			StringBuilder path = current.path;

			if (currNode.isEndWord) {
				results.add(path.toString());
			}

			for (char ch = 'a'; ch <= 'z'; ch++) {
				TrieNode child = currNode.children[ch - 'a'];
				if (child != null) {
					queue.offer(new Pair(child, new StringBuilder(path).append(ch)));
				}
			}
		}
	}

	static class Pair {
		TrieNode node;
		StringBuilder path;

		Pair(TrieNode node, StringBuilder path) {
			this.node = node;
			this.path = path;
		}
	}


	public static void main(String[] args) {
		PrefixTrie prefixTrie = new PrefixTrie();
		prefixTrie.insert("apple");
		System.out.println(prefixTrie.search("apple"));
		System.out.println(prefixTrie.wordSearch("ap.l."));
		System.out.println(prefixTrie.startsWith("app"));
	}

}
