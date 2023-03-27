package com.algoexpert2.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MinimumCharactersForWords {

	public char[] minimumCharactersForWords(String[] words) {
		HashMap<Character, Integer> maxCharFreq = new HashMap<>();
		for (String word : words) {
			HashMap<Character, Integer> wordCharFreq = countCharFrequencies(word); // count each word character freq
			updateMaxFreq(wordCharFreq, maxCharFreq); // update max charac freq from count char freq
		}
		return makeArrFromCharFreq(maxCharFreq);
	}

	public HashMap<Character, Integer> countCharFrequencies(String word) {
		HashMap<Character, Integer> wordCharFreq = new HashMap<>();
		for (char charac : word.toCharArray()) {
			wordCharFreq.put(charac, wordCharFreq.getOrDefault(charac, 0) + 1);
		}
		return wordCharFreq;
	}

	public void updateMaxFreq(HashMap<Character, Integer> wordCharFreq, HashMap<Character, Integer> maxCharFreq) {
		for (Map.Entry<Character, Integer> frequency : wordCharFreq.entrySet()) {
			char charac = frequency.getKey();
			int characFreq = frequency.getValue();

			if (maxCharFreq.containsKey(charac)) {
				maxCharFreq.put(charac, Math.max(characFreq, maxCharFreq.get(charac)));
			} else {
				maxCharFreq.put(charac, characFreq);
			}
		}

	}

	private char[] makeArrFromCharFreq(HashMap<Character, Integer> maxCharFreq) {
		ArrayList<Character> list = new ArrayList<>();
		for (Map.Entry<Character, Integer> freq : maxCharFreq.entrySet()) {
			char charac = freq.getKey();
			int characFreq = freq.getValue();

			for (int i = 0; i < characFreq; i++) {
				list.add(charac);
			}
		}
		char[] finalArray = new char[list.size()];
		for (int i = 0; i < list.size(); i++) {
			finalArray[i] = list.get(i);
		}
		return finalArray;
	}


	public static void main(String[] args) {
		String[] words = new String[] {"this", "that", "did", "deed", "them!", "a"};
		char[] expected = new char[] {'t', 't', 'h', 'i', 's', 'a', 'd', 'd', 'e', 'e', 'm', '!'};
		var actual = new MinimumCharactersForWords().minimumCharactersForWords(words);
		assert (expected == actual);
	}

}
