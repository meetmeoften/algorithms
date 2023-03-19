package com.algoexpert.trie;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WordSearchIISolution2 {

	class Trie {
		Trie[] children;
		String word;

		public Trie() {
			this.children = new Trie[26];
			this.word = null;
		}
	}

	public void insertWord(Trie trie, String word) {
		char[] chars = word.toCharArray();
		for (char ch : chars) {
			if (trie.children[ch - 'a'] == null) {
				trie.children[ch - 'a'] = new Trie();
			}
			trie = trie.children[ch - 'a'];
		}
		trie.word = word;
	}

	private Trie trie;
	private Set<String> res;

	private void dfs(char[][] board, int i, int j, Trie trie, boolean[][] traversed) {
		if (trie.word != null) {
			res.add(trie.word);
		}
		if (i < 0 || j < 0 || i == board.length || j == board[0].length || traversed[i][j]) {
			return;
		}
		if (trie.children[board[i][j] - 'a'] == null) {
			return;
		}
		trie = trie.children[board[i][j] - 'a'];
		traversed[i][j] = true;
		dfs(board, i + 1, j, trie, traversed);
		dfs(board, i - 1, j, trie, traversed);
		dfs(board, i, j - 1, trie, traversed);
		dfs(board, i, j + 1, trie, traversed);
		traversed[i][j] = false;
	}

	public List<String> findWords(char[][] board, String[] words) {
		trie = new Trie();
		for (String word : words) {
			insertWord(trie, word);
		}
		res = new HashSet<>();
		int n = board.length, m = board[0].length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (trie.children[board[i][j] - 'a'] != null) {
					dfs(board, i, j, trie, new boolean[n][m]);
				}
			}
		}
		return res.stream().collect(Collectors.toList());
	}

	public static void main(String[] args) {
		char board[][] = {
				{ 'o', 'a', 'a', 'n' },
				{ 'e', 't', 'a', 'e' },
				{ 'i', 'h', 'k', 'r' },
				{ 'i', 'f', 'l', 'v' } };
		String words[] = { "oath", "pea", "eat", "rain" };

		// Output: ["eat","oath"]

		WordSearchIISolution2 sol = new WordSearchIISolution2();
		sol.findWords(board, words);
	}

}
