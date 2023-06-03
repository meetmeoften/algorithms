package com.algoexpert2.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CommonCharacters {


	public static String[] commonCharacters(String[] strings) {
		// Write your code here.
		HashMap<Character, Integer> map = new HashMap<>();

		for(String string: strings) {
			HashSet<Character> set = new HashSet<>();
			for(Character ch: string.toCharArray()) {
				set.add(ch);
			}

			for(Character ch : set) {
				map.put(ch, map.getOrDefault(ch, 0) + 1);
			}
		}

		List<String> matchingCharList = map.entrySet().stream()
				.filter( e-> e.getValue() == strings.length)
				.map(e -> e.getKey().toString())
				.collect(Collectors.toList());


		return matchingCharList.toArray(new String[matchingCharList.size()]);
	}

	public static void main(String[] args) {

	}


}
