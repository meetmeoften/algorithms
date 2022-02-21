package com.algoexpert.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlidingWindowSmallestSubstringContaining {

	public static String smallestSubstringContaining(String bigString, String smallString) {
		Map<Character, Integer> map = getCharCounts(smallString);
		List<Integer> subStringBounds = getSubStringBounds(bigString, map);
		return getStringFromBounds(bigString, subStringBounds);
	}

	public static Map<Character, Integer> getCharCounts(String string) {
		Map<Character, Integer> map = new HashMap<>();
		for(char c: string.toCharArray()) {
			increaseCharCount(map, c);
		}
		return map;
	}

	private static String getStringFromBounds(String bigString, List<Integer> subStringBounds) {
		int start = subStringBounds.get(0);
		int end = subStringBounds.get(1);
		if(end == Integer.MAX_VALUE) {
			return "";
		}
		return bigString.substring(start, end+1);
	}

	private static List<Integer> getSubStringBounds(String string, Map<Character, Integer> map) {
		Map<Character, Integer> subStringCount = new HashMap<>();
		List<Integer> subStringBounds = new ArrayList<>(Arrays.asList(0, Integer.MAX_VALUE));
		int numUniqueChars = map.size();
		int numUniqueCharsDone = 0;

		int leftIdx = 0;
		int rightIdx = 0;


		while(rightIdx < string.length()) {
			char rightChar = string.charAt(rightIdx);
			if(!map.containsKey(rightChar)) {
				rightIdx++;
				continue;
			}

			increaseCharCount(subStringCount, rightChar);
			if(subStringCount.get(rightChar).equals(map.get(rightChar))) {
				numUniqueCharsDone++;
			}

			while(numUniqueCharsDone == numUniqueChars && leftIdx <= rightIdx) {
				subStringBounds = getCloserBounds(leftIdx, rightIdx, subStringBounds.get(0), subStringBounds.get(1));
				char leftChar = string.charAt(leftIdx);
				if(!map.containsKey(leftChar)) {
					leftIdx++;
					continue;
				}

				if(subStringCount.get(leftChar).equals(map.get(leftChar))) {
					numUniqueCharsDone--;
				}
				decreaseCharCount(subStringCount, leftChar);
				leftIdx++;
			}
			rightIdx++;
		}
		return subStringBounds;
	}

	private static List<Integer> getCloserBounds(int idx1, int idx2, Integer idx3, Integer idx4) {
		return idx2- idx1 < idx4 - idx3
				? new ArrayList<>(Arrays.asList(idx1, idx2))
						: new ArrayList<>(Arrays.asList(idx3, idx4));
	}

	private static void decreaseCharCount(Map<Character, Integer> map, char c) {
		map.put(c,  map.get(c) -1);
	}

	private static void increaseCharCount(Map<Character, Integer> map, char c) {
		if(!map.containsKey(c)) {
			map.put(c, 1);
		} else {
			map.put(c,  map.get(c) + 1);
		}
	}


	public static void main(String[] args) {
		String bigString = "abcd$ef$axb$c$";
		String smallString = "$$abf";
		String expected = "f$axb$";
		smallestSubstringContaining(bigString, smallString);
	}

}
