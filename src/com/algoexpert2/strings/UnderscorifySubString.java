package com.algoexpert2.strings;

import java.util.ArrayList;
import java.util.List;

public class UnderscorifySubString {

	// Underscorify Substring
	public static String underscorifySubstring(String str, String substring) {
		var locations = getLocations(str, substring);
		List<Integer[]> collapsed = collapse(locations);
		return underscorify(str, collapsed);
	}

	public static List<Integer[]> getLocations(String str, String substring) {
		List<Integer[]> locations = new ArrayList<>();
		int startIndex = 0;
		while (startIndex < str.length()) {
			int nextIndex = str.indexOf(substring, startIndex);
			if (nextIndex != -1) {
				locations.add(new Integer[] { nextIndex, nextIndex + substring.length() });
				startIndex = nextIndex + 1;
			} else {
				break;
			}
		}
		return locations;
	}

	public static List<Integer[]> collapse(List<Integer[]> locations) {
		if (locations.size() == 0) {
			return locations;
		}
		List<Integer[]> newLocations = new ArrayList<>();
		newLocations.add(locations.get(0));
		Integer[] previous = newLocations.get(0);
		for (int i = 1; i < locations.size(); i++) {
			Integer[] current = locations.get(i);
			if (current[0] <= previous[1]) {
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
		while (stringIndex < str.length() && locationIndex < locations.size()) {
			if (stringIndex == locations.get(locationIndex)[i]) {
				finalChars.add("_");
				inBetweenUnderscores = !inBetweenUnderscores;
				if (!inBetweenUnderscores) {
					locationIndex++;
				}
				i = i == 1 ? 0 : 1;
			}
			finalChars.add(String.valueOf(str.charAt(stringIndex)));
			stringIndex++;
		}
		if (locationIndex < locations.size()) {
			finalChars.add("_");
		} else if (stringIndex < str.length()) {
			finalChars.add(str.substring(stringIndex));
		}
		return String.join("", finalChars);
	}

	public static void main(String[] args) {
		String expected = "_test_this is a _testtest_ to see if _testestest_ it works";
		String output = underscorifySubstring("testthis is a testtest to see if testestest it works", "test");
		// Utils.assertTrue(expected.equals(output));
	}

}
