package com.algoexpert.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestStringChain {

	public static List<String> longestStringChain(List<String> strings) {
		Map<String, StringChain> stringChains = new HashMap<>();

		for(String string: strings) {
			stringChains.put(string, new StringChain("", 1));
		}

		List<String> sortedStrings = new ArrayList<>(strings);
		sortedStrings.sort((a,b) -> a.length() - b.length());

		for(String string: sortedStrings) {
			findLongestStringChain(string, stringChains);
		}

		return buildLongestStringChain(strings, stringChains);  // backtracking
	}

	private static List<String> buildLongestStringChain(List<String> strings, Map<String, StringChain> stringChains) {
		int maxChainLength = 0;
		String chainStartingString = "";
		for(String string: strings) {
			if(stringChains.get(string).maxChainLength > maxChainLength) {
				maxChainLength = stringChains.get(string).maxChainLength;
				chainStartingString = string;
			}
		}
		List<String>  longestStringChain = new ArrayList<>();
		String currentString = chainStartingString;
		while(currentString != "") {
			longestStringChain.add(currentString);
			currentString = stringChains.get(currentString).nextString;
		}
		return longestStringChain.size() == 1? new ArrayList<String>(): longestStringChain;
	}

	public static void findLongestStringChain(String string, Map<String, StringChain> stringChains) {
		for(int i= 0; i < string.length(); i++) {
			String smallerString = getSmallerString(string, i);
			if(!stringChains.containsKey(smallerString)) {
				continue;
			}
			tryUpdateLongestStringChain(string, smallerString, stringChains);
		}
	}

	public static String getSmallerString(String string, int index) {
		return string.substring(0, index)+ string.substring(index+1);
	}

	public static void tryUpdateLongestStringChain(String currentString,
			String smallerString, Map<String, StringChain> stringChains) {

		int smallerStringChainLength = stringChains.get(smallerString).maxChainLength;
		int currentStringChainLength = stringChains.get(currentString).maxChainLength;

		if(smallerStringChainLength+1 > currentStringChainLength) {
			stringChains.get(currentString).maxChainLength = smallerStringChainLength+1;
			stringChains.get(currentString).nextString = smallerString;
		}
	}


	static class StringChain {
		String nextString;
		Integer maxChainLength;

		public StringChain(String nextString, Integer maxChainLength) {
			this.nextString = nextString;
			this.maxChainLength = maxChainLength;
		}
	}

}
