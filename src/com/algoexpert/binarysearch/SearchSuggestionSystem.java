package com.algoexpert.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class SearchSuggestionSystem {

	// https://leetcode.com/problems/search-suggestions-system/solutions/1894633/java-binary-search-very-easy-with-explanation/

	public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
		List<List<String>> suggestionsList = new ArrayList<>();

		// sort products lexicographically
		Arrays.sort(products);

		for (int i = 0; i < searchWord.length(); i++) {
			String prefix = searchWord.substring(0, i + 1);

			// Note: binary search returns the index of the first element if it is present,
			// otherwise returns (-)insertion point for the element.
			int index = Arrays.binarySearch(products, prefix);

			// no prefix in products
			// take the next word greater than the prefix.
			if (index < 0) {
				index = -index - 1;
			}

			List<String> suggestions = new ArrayList<>();
			int max = index + 3;
			for (int j = index; j < products.length; j++) {
				if (j == max) { // if we have reached the max number of suggestions
					break;
				}

				if (products[j].startsWith(prefix)) { // if the product starts with the prefix
					suggestions.add(products[j]);
				}
			}

			suggestionsList.add(suggestions);
		}

		return suggestionsList;
	}

	private static class TrieNode {
		public TrieNode[] children;
		public PriorityQueue<String> pq;

		public TrieNode() {
			children = new TrieNode[26];
			pq = new PriorityQueue<>((a, b) -> b.compareTo(a));
		}

		public void addToPQ(String word) {
			pq.add(word);
			if (pq.size() > 3) {
				pq.poll();
			}
		}

		public List<String> getTopThree() {
			List<String> topThree = new ArrayList<>();
			while (!pq.isEmpty()) {
				topThree.add(pq.poll());
			}
			Collections.reverse(topThree);
			return topThree;
		}
	}

	public static List<List<String>> suggestedProducts2(String[] products, String searchWord) {
		TrieNode root = new TrieNode();
		for (String product : products) {
			insert(root, product);
		}

		List<List<String>> results = new ArrayList<>();
		for (char c : searchWord.toCharArray()) {
			if ((root = root.children[c - 'a']) == null) {
				break;
			}
			results.add(root.getTopThree());
		}

		while (results.size() < searchWord.length()) {
			results.add(new ArrayList<>());
		}
		return results;
	}

	private static void insert(TrieNode root, String word) {
		for (char c : word.toCharArray()) {
			if (root.children[c - 'a'] == null) {
				root.children[c - 'a'] = new TrieNode();
			}
			root = root.children[c - 'a'];
			root.addToPQ(word);
		}
	}

	public static void main(String[] args) {
		String[] products = { "mobile", "mou", "mouse", "moneypot", "monitor", "mousepad" };
		suggestedProducts(products, "mouse");
		suggestedProducts2(products, "mouse");

	}

}
