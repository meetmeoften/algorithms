package com.algoexpert.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSubStringWithoutDuplicates {

	//Longest Substrings without duplicates
	public static String longestSubstringWithoutDuplication(String str) {
		Map<Character, Integer> map = new HashMap<>();
		int[] longest = {0, 1};
		int start = 0;

		for(int i= 0; i < str.length(); i++) {
			Character c = str.charAt(i);
			if(map.containsKey(c)) {
				start = Math.max(start, map.get(c) +1);
			}
			if(longest[1] - longest[0] < i+1 - start) {
				longest = new int[]{start, i+1};
				System.out.println(str.substring(longest[0], longest[1]));
			}

			map.put(c, i);
		}
		return str.substring(longest[0], longest[1]);
	}

	public static void main(String[] args) {
		longestSubstringWithoutDuplication("clementisacap").equals("mentisac");
	}

	// Underscorify Substring
	public static String underscorifySubstring(String str, String substring) {
		// Write your code here.
		var locations = getLocations(str, substring);
		List<Integer[]> collapsed = collapse(locations);
		return underscorify(str, collapsed);
	}

	public static List<Integer[]> getLocations(String str, String substring) {
		List<Integer[]> locations = new ArrayList<>();
		int startIndex = 0;
		while(startIndex < str.length()) {
			int nextIndex = str.indexOf(substring, startIndex);
			if(nextIndex != -1) {
				locations.add(new Integer[] {nextIndex, nextIndex + substring.length()});
				startIndex = nextIndex +1;
			} else {
				break;
			}
		}
		return locations;
	}

	public static List<Integer[]> collapse(List<Integer[]> locations) {
		if(locations.size() == 0) {
			return locations;
		}
		List<Integer[]> newLocations = new ArrayList<>();
		newLocations.add(locations.get(0));
		Integer[] previous = newLocations.get(0);
		for(int i=1; i< locations.size(); i++) {
			Integer[] current = locations.get(i);
			if(current[0] <= previous[1]) {
				previous[1] = current[1];
			} else {
				newLocations.add(current);
				previous = current;
			}
		}
		return newLocations;
	}

	public static String underscorify(String str, List<Integer[]> locations) {
		int locationIndex = 0;
		int stringIndex = 0;
		boolean inBetweenUnderscores = false;
		List<String> finalChars = new ArrayList<String>();
		int i = 0;
		while(stringIndex < str.length() && locationIndex < locations.size()) {
			if(stringIndex == locations.get(locationIndex)[i]) {
				finalChars.add("_");
				inBetweenUnderscores = !inBetweenUnderscores;
				if(!inBetweenUnderscores) {
					locationIndex++;
				}
				i = i== 1 ? 0 : 1;
			}
			finalChars.add(String.valueOf(str.charAt(stringIndex)));
			stringIndex++;
		}
		if(locationIndex < locations.size()) {
			finalChars.add("_");
		} else if(stringIndex < str.length()) {
			finalChars.add(str.substring(stringIndex));
		}
		return String.join("", finalChars);
	}

}
