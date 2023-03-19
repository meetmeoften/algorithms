package com.algoexpert.arrays.leetcode300;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RepeatedDNASequences {

	public static List<String> findRepeatedDnaSequences(String s) {
		HashSet<String> set = new HashSet<>();
		int start = 0;
		HashSet<String> ans = new HashSet<>();
		for (int end = 10; end <= s.length(); end++) {
			String sequence = s.substring(start, end);
			if (set.contains(sequence)) {
				ans.add(sequence);
			}
			set.add(sequence);
			start++;
		}
		List<String> list = new ArrayList<>(ans);
		return list;
	}

	public static void main(String[] args) {
		String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
		findRepeatedDnaSequences(s);

	}

}
