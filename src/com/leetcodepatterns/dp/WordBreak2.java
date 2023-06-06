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

	public static void main(String[] args) {
		List<String> result = new WordBreak2().wordBreak("catsanddog",
				Arrays.asList(new String[] {"cat","cats","and","sand","dog"}));
		System.out.println(result);
	}
}
