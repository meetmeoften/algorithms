package com.algoexpert.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

	public static List<List<String>> groupAnagrams(List<String> strs) {
		Map<String, List<String>> map = new HashMap<>();

		for(String word: strs) {
			char[] wordArray = word.toCharArray();
			Arrays.sort(wordArray);
			String newWord = new String(wordArray);

			if(map.containsKey(newWord)) {
				map.get(newWord).add(word);
			} else {
				List<String> list = new ArrayList<>();
				list.add(word);
				map.put(newWord, list);
			}
		}

		//		Set<String> set = map.keySet();
		//		Iterator<String> iterator = set.iterator();
		//		List<List<String>> finalList = new ArrayList<>();
		//		while(iterator.hasNext()){
		//			String key = iterator.next();
		//			List<String> list = map.get(key);
		//			finalList.add(list);
		//		}
		//		return finalList;

		return new ArrayList<>(map.values());
	}

}
