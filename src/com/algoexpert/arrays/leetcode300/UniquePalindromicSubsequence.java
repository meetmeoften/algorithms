package com.algoexpert.arrays.leetcode300;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniquePalindromicSubsequence {

	public static int countPalindromicSubsequence(String s) {
		char[] letters = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
				'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		Set<Character> left = new HashSet<>();
		Set<String> res = new HashSet<>();

		Map<Character, Integer> right = new HashMap<>();
		for (char c : s.toCharArray()) {
			right.put(c, right.getOrDefault(c, 0) + 1);
		}

		for (int mid = 0; mid < s.length(); mid++) {
			char c = s.charAt(mid);

			right.put(c, right.get(c) - 1);
			if (right.get(c) == 0) {
				right.remove(c);
			}
			for (int i = 0; i < 26; i++) {
				if (left.contains(letters[i]) && right.containsKey(letters[i])) {
					res.add("" + letters[i] + c + letters[i]);
					System.out.println("" + letters[i] + c + letters[i]);
				}
			}
			left.add(c);
		}

		return res.size();
	}

	public static int countPalindromicSubsequence2(String s) {
		int first[] = new int[26], last[] = new int[26], res = 0;
		Arrays.fill(first, Integer.MAX_VALUE);
		for (int i = 0; i < s.length(); ++i) {
			first[s.charAt(i) - 'a'] = Math.min(first[s.charAt(i) - 'a'], i);
			last[s.charAt(i) - 'a'] = i;
		}
		for (int i = 0; i < 26; ++i) {
			if (first[i] < last[i]) {
				res += s.substring(first[i] + 1, last[i]).chars().distinct().count();
			}
		}
		return res;
	}

	public static void main(String[] args) {
		String s = "aabca";
		countPalindromicSubsequence(s);
		countPalindromicSubsequence2(s);
	}

}
