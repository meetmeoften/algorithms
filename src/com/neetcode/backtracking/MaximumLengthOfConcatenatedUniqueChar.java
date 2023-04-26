package com.neetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MaximumLengthOfConcatenatedUniqueChar {

	public int maxLength(List<String> arr) {

		int[] max = new int[1];// since there is no pass by ref in java i used array of size 1 to store max
		// we can keep static variable and use it but i did what i did

		helper(0, "", arr, max);

		return max[0];

	}

	public void helper(int i, String temp, List<String> arr, int[] max) {
		// choices are done for every strings in arr
		if (i == arr.size()) {
			max[0] = Math.max(temp.length(), max[0]);
			return;
		}

		// choice for not pick
		helper(i + 1, temp, arr, max);

		String s = arr.get(i);

		// pick string s but first overcome the below two conditions
		if (check(s)) {
			return;
			// if flow comes here it mean s is unque
		}

		if (check(s, temp)) {
			return;
		}

		// if flow comes here s and temp have no character in common so concatination
		// will be unique

		// if above two checks doesnt run that means we got unique temp+s
		String str = temp + s;

		helper(i + 1, str, arr, max);

	}

	public boolean check(String s1, String s2) {
		HashSet<Character> set = new HashSet<>();
		for (char c : s1.toCharArray()) {
			set.add(c);
		}
		for (char c : s2.toCharArray()) {
			if (set.contains(c)) {
				return true;
			}
			set.add(c);
		}
		return false;
	}

	// if duplicate characters in string s return true else return false
	public boolean check(String s) {
		HashSet<Character> set = new HashSet<>();
		for (char c : s.toCharArray()) {
			if (set.contains(c)) {
				return true;
			}
			set.add(c);
		}
		return false;
	}

	public static void main(String[] args) {
		String[] arr = { "un", "iq", "ue" };
		List<String> list = new ArrayList<>(Arrays.asList(arr));
		MaximumLengthOfConcatenatedUniqueChar max = new MaximumLengthOfConcatenatedUniqueChar();
		max.maxLength(list);
	}
}
