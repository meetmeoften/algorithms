package com.algoexpert.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class WordSearchII {


	//	public List<String> findWords(char[][] board, String[] words) {
	//		// Create a root node of TrieNode data structure
	//		TrieNode root = new TrieNode();
	//
	//		// Add word to TrieNode datastructure
	//		for(String w: words) {
	//			root.addWord(w);
	//		}
	//
	//		int rows = board.length, cols = board[0].length;
	//		Set<String> result = new HashSet<>();
	//		String word = "";
	//		Set<Pair> visited = new HashSet<Pair>();
	//		for (int r = 0; r < rows; r++) {
	//			for(int c = 0; c < cols; c++) {
	//				dfs(rows, cols, r, c, root, result, visited, word, board);
	//			}
	//		}
	//
	//		return new ArrayList<String>(result);
	//	}
	//
	//	private static void dfs(int rows, int cols, int r, int c, TrieNode current, Set<String> result, Set<Pair> visited,
	//			String word, char[][] board) {
	//		if (r < 0 || c < 0 || r >= rows || c >= cols || visited.contains(new Pair(r, c)) ||
	//				!current.children.containsKey(board[r][c])) {
	//			return;
	//		}
	//		visited.add(new Pair(r,c));
	//		word = word + board[r][c];
	//		current = current.children.get(board[r][c]);
	//		// Add to result if current word is endOf word
	//		if (current.endOfWord) {
	//			result.add(word);
	//		}
	//		dfs(rows, cols, r, c + 1, current, result, visited, word, board);
	//		dfs(rows, cols, r, c - 1, current, result, visited, word, board);
	//		dfs(rows, cols, r + 1, c, current, result, visited, word, board);
	//		dfs(rows, cols, r - 1, c, current, result, visited, word, board);
	//		// backtracking
	//		visited.remove(new Pair(r, c));
	//	}

	class Trie {

		public HashMap<Character, Trie> children;
		public boolean isWord;
		public int refs = 0;

		public Trie() {
			children = new HashMap<>();
		}

		public void addWord(String word) {
			currentTrie = this;
			currentTrie.refs += 1;
			for (int i = 0; i < word.length(); i++) {
				char currentCharacter = word.charAt(i);
				if (!currentTrie.children.containsKey(currentCharacter)) {
					currentTrie.children.put(currentCharacter, new Trie());
				}
				currentTrie = currentTrie.children.get(currentCharacter);
				currentTrie.refs += 1;
			}
			currentTrie.isWord = true;
		}

		public void removeWord(String word) {
			currentTrie = this;
			currentTrie.refs -= 1;
			for (int i = 0; i < word.length(); i++) {
				char currentCharacter = word.charAt(i);
				if (currentTrie.children.containsKey(currentCharacter)) {
					currentTrie = currentTrie.children.get(currentCharacter);
					currentTrie.refs -= 1;
				}
			}
		}
	}


	private static int COLS;
	private static int ROWS;
	private Trie currentTrie;

	public List<String> findWords(char[][] board, String[] words) {
		Trie root = new Trie();
		for (String word : words) {
			root.addWord(word);
		}

		ROWS = board.length;
		COLS = board[0].length;
		HashSet<String> res = new HashSet<>();
		HashSet<String> visit = new HashSet<>();

		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				dfs(r, c, root, "", res, visit, board, root);
			}
		}
		return new ArrayList<>(res);
	}

	public void dfs(int r, int c, Trie node, String word, HashSet<String> res, HashSet<String> visit, char[][] board,
			Trie root) {
		if (r < 0 || c < 0 || r == ROWS || c == COLS || !node.children.containsKey(board[r][c])
				|| node.children.get(board[r][c]).refs < 1 || visit.contains(r + "-" + c)) {
			return;
		}

		visit.add(r + "-" + c);
		node = node.children.get(board[r][c]);
		word += board[r][c];
		if (node.isWord) {
			node.isWord = false;
			res.add(word);
			root.removeWord(word);
		}

		dfs(r + 1, c, node, word, res, visit, board, root);
		dfs(r - 1, c, node, word, res, visit, board, root);
		dfs(r, c + 1, node, word, res, visit, board, root);
		dfs(r, c - 1, node, word, res, visit, board, root);
		visit.remove(r + "-" + c);
	}

}
