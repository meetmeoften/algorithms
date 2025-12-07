package com.leetcodepatterns.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreak2 {

	public List<String> wordBreak(String s, List<String> wordDict) {
		return dfs(s, new HashSet<String>(wordDict), new HashMap<String, List<String>>());
	}

	private List<String> dfs(String s, Set<String> dict, Map<String, List<String>> memo) {
		if (memo.containsKey(s)) {
			return memo.get(s);
		}

		List<String> result = new ArrayList<>();

		for (String word : dict) {
			if (s.startsWith(word)) {
				if (word.length() == s.length()) {
					result.add(word);
					continue;
				}

				List<String> postfix = dfs(s.substring(word.length()), dict, memo);

				for (String str : postfix) {
					result.add(word + " " + str);
				}
			}
		}

		memo.put(s, result);
		return memo.get(s);
	}

	public List<String> wordBreak2(String s, List<String> wordDict) {
		Set<String> dict = new HashSet<>(wordDict);
		Map<Integer, List<String>> memo = new HashMap<>();
		return dfs(0, s, dict, memo);
	}

	private List<String> dfs(int start, String s, Set<String> dict, Map<Integer, List<String>> memo) {
		if (memo.containsKey(start)) return memo.get(start);

		List<String> results = new ArrayList<>();

		// If we reached the end, return an empty string as a valid sentence end
		if (start == s.length()) {
			results.add("");
			return results;
		}

		// Try all substrings starting at 'start'
		for (int end = start + 1; end <= s.length(); end++) {
			String prefix = s.substring(start, end);

			if (dict.contains(prefix)) {
				List<String> suffixSentences = dfs(end, s, dict, memo);

				for (String sentence : suffixSentences) {
					if (sentence.isEmpty()) {
						results.add(prefix);
					} else {
						results.add(prefix + " " + sentence);
					}
				}
			}
		}

		memo.put(start, results);
		return results;
	}

	public static void main(String[] args) {
		List<String> result = new WordBreak2().wordBreak2("catsanddog",
				Arrays.asList(new String[] {"cat","cats","and","sand","dog"}));
		System.out.println(result);
	}
}
