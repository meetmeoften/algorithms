package com.algoexpert2.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Semordnilap {

	public ArrayList<ArrayList<String>> semordnilap(String[] words) {
		// Write your code here.
		HashSet<String> wordSet = new HashSet<>(Arrays.asList(words));
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

		for (String word : words) {
			String reverse = new StringBuilder(word).reverse().toString();

			if (wordSet.contains(reverse) && !reverse.equals(word)) {
				ArrayList<String> subList = new ArrayList<String>();
				subList.add(word);
				subList.add(reverse);
				list.add(subList);
				wordSet.remove(word);
				wordSet.remove(reverse);
			}
		}

		return list;
	}

	public static void main(String[] args) {
		var input = new String[] { "desserts", "stressed", "hello" };
		ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
		ArrayList<String> pair = new ArrayList<String>();
		pair.add("desserts");
		pair.add("stressed");
		expected.add(pair);
		var actual = new Semordnilap().semordnilap(input);
		// Utils.assertTrue(expected.equals(actual));
	}
}
