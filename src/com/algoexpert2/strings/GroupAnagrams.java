package com.algoexpert2.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

	public static List<List<String>> groupAnagrams(List<String> strs) {
		Map<String, List<String>> map = new HashMap<>();

		for (String word : strs) {
			char[] wordArray = word.toCharArray();
			Arrays.sort(wordArray);
			String newWord = new String(wordArray);

			if (map.containsKey(newWord)) {
				map.get(newWord).add(word);
			} else {
				List<String> list = new ArrayList<>();
				list.add(word);
				map.put(newWord, list);
			}
		}

		// Set<String> set = map.keySet();
		// Iterator<String> iterator = set.iterator();
		// List<List<String>> finalList = new ArrayList<>();
		// while(iterator.hasNext()){
		// String key = iterator.next();
		// List<String> list = map.get(key);
		// finalList.add(list);
		// }
		// return finalList;

		return new ArrayList<>(map.values());
	}

	public static void main(String[] args) {
		List<String> words =
				new ArrayList<String>(
						Arrays.asList("yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp"));
		List<List<String>> expected = new ArrayList<List<String>>();
		expected.add(new ArrayList<String>(Arrays.asList("yo", "oy")));
		expected.add(new ArrayList<String>(Arrays.asList("flop", "olfp")));
		expected.add(new ArrayList<String>(Arrays.asList("act", "tac", "cat")));
		expected.add(new ArrayList<String>(Arrays.asList("foo")));
		List<List<String>> output = groupAnagrams(words);
		for (List<String> innerList : output) {
			Collections.sort(innerList);
		}
	}

	public boolean compare(List<List<String>> expected, List<List<String>> output) {
		if (expected.size() != output.size()) {
			return false;
		}

		for (List<String> group : expected) {
			Collections.sort(group);
			if (!output.contains(group)) {
				return false;
			}
		}

		return true;
	}

}
