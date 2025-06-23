package com.algoexpert.trie;

import java.util.ArrayList;
import java.util.List;

public class TrieAutoComplete {

	private static List<String> solve(String[] strs, String s) {
		TrieNode root = new TrieNode();
		for (String str : strs) {
			TrieNode ref = root;
			for (char c : str.toCharArray()) {
				if (ref.next[c - 'a'] == null) {
					ref.next[c - 'a'] = new TrieNode();
				}
				ref.lst.add(str);
				ref = ref.next[c - 'a'];
			}
			ref.lst.add(str);
		}

		TrieNode ref = root;
		for (char c : s.toCharArray()) {
			if (ref.next[c - 'a'] == null) {
				return new ArrayList<>();
			}
			ref = ref.next[c - 'a'];
		}
		return ref.lst;
	}

	static class TrieNode {
		TrieNode[] next;
		List<String> lst;

		public TrieNode() {
			next = new TrieNode[26];
			lst = new ArrayList<>();
		}
	}

	public static void main(String[] args) {
		String[] strs = { "car", "cart", "carpool", "bus", "apple", "cargo" };
		String s = "car";
		System.out.println(solve(strs, s));
	}
}
