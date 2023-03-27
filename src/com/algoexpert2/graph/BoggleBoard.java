package com.algoexpert2.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BoggleBoard {

	public static List<String> boggleBoard(char[][] board, String[] words) {
		// Write your code here.

		Trie trie = new Trie();
		for(String word: words) {
			trie.add(word);
		}

		Set<String> finalWords = new HashSet<>();
		boolean[][] visited = new boolean[board.length][board[0].length];

		for(int i= 0; i< board.length; i++) {
			for(int j= 0; j < board[0].length; j++) {
				explore(i, j, board, trie.root, visited, finalWords);
			}
		}

		List<String> finalWordsList = new ArrayList<>();
		finalWordsList.addAll(finalWords);
		return finalWordsList;
	}

	private static void explore(int i, int j, char[][] board, TrieNode trie, boolean[][] visited,
			Set<String> finalWords) {

		if(visited[i][j]) {
			return;
		}

		char letter = board[i][j];
		if(!trie.children.containsKey(letter)) {
			return;
		}

		visited[i][j] = true;
		trie = trie.children.get(letter);
		if(trie.children.containsKey('*')) {
			finalWords.add(trie.word);
		}

		List<Integer[]> neighbours = getNeighbours(i, j, board);
		for(Integer[] neighbour : neighbours) {
			explore(neighbour[0], neighbour[1], board, trie, visited, finalWords);
		}

		visited[i][j] = false;

	}

	private static List<Integer[]> getNeighbours(int row, int col, char[][] board) {
		ArrayList<Integer[]> list = new ArrayList<>();

		if (row > 0) {
			list.add(new Integer[] { row - 1, col });
		}

		if (row < board.length - 1) {
			list.add(new Integer[] { row + 1, col });
		}

		if (col > 0) {
			list.add(new Integer[] { row, col - 1 });
		}

		if (col < board[0].length - 1) {
			list.add(new Integer[] { row, col + 1 });
		}

		if(row > 0 && col > 0) {
			list.add(new Integer[] { row - 1, col -1});
		}

		if(row > 0 && col < board[0].length - 1) {
			list.add(new Integer[] { row - 1, col + 1});
		}

		if(row < board.length - 1 && col < board[0].length - 1) {
			list.add(new Integer[] { row + 1, col + 1});
		}

		if(row < board.length - 1 && col > 0) {
			list.add(new Integer[] { row + 1, col - 1});
		}

		return list;
	}

	static class TrieNode {
		Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
		String word;
	}

	static class Trie {
		TrieNode root;
		char endSymbol = '*';

		public Trie() {
			root = new TrieNode();
			endSymbol = '*';
		}

		public void add(String str) {
			TrieNode node = root;
			for(int i = 0; i < str.length(); i++) {
				char letter = str.charAt(i);
				if(!node.children.containsKey(letter)) {
					TrieNode newNode = new TrieNode();
					node.children.put(letter, newNode);
				}
				node = node.children.get(letter);
			}
			node.children.put(endSymbol, null);
			node.word = str;
		}

		public boolean contains(String str) {
			TrieNode node = root;
			for(int i=0; i< str.length(); i++) {
				char letter = str.charAt(i);
				if(!node.children.containsKey(letter)) {
					return false;
				}
				node = node.children.get(letter);
			}
			return node.children.containsKey(endSymbol);
		}
	}


	public static void main(String[] args) {
		char[][] board = {
				{'t', 'h', 'i', 's', 'i', 's', 'a'},
				{'s', 'i', 'm', 'p', 'l', 'e', 'x'},
				{'b', 'x', 'x', 'x', 'x', 'e', 'b'},
				{'x', 'o', 'g', 'g', 'l', 'x', 'o'},
				{'x', 'x', 'x', 'D', 'T', 'r', 'a'},
				{'R', 'E', 'P', 'E', 'A', 'd', 'x'},
				{'x', 'x', 'x', 'x', 'x', 'x', 'x'},
				{'N', 'O', 'T', 'R', 'E', '-', 'P'},
				{'x', 'x', 'D', 'E', 'T', 'A', 'E'},
		};
		String[] words = {
				"this", "is", "not", "a", "simple", "boggle", "board", "test", "REPEATED", "NOTRE-PEATED"
		};
		String[] expected = {"this", "is", "a", "simple", "boggle", "board", "NOTRE-PEATED"};
		List<String> actual = boggleBoard(board, words);
		//Utils.assertTrue(actual.size() == expected.length);
		//		for (String word : actual) {
		//			Utils.assertTrue(contains(expected, word));
		//		}
	}

	public static boolean contains(String[] wordArray, String targetWord) {
		for (String word : wordArray) {
			if (targetWord.equals(word)) {
				return true;
			}
		}
		return false;
	}
}
