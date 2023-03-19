package com.leetcodepatterns.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

	public boolean wordBreak(String s, List<String> wordDict) {
		if (s == null || s.length() == 0) {
			return false;
		}

		Set<String> dict = new HashSet<>(wordDict);
		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;

		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				System.out.println(s.substring(j, i));
				if (dp[j] && dict.contains(s.substring(j, i))) {
					dp[i] = true;
					System.out.println(s.substring(j, i) + " " + dp[i]);
				}
			}
		}
		return dp[s.length()];
	}

	public static void wordBreak(List<String> dict, String word, String out) {
		// if the end of the string is reached,
		// print the output string

		if (word.length() == 0) {
			System.out.println(out);
			return;
		}

		for (int i = 1; i <= word.length(); i++) {
			// consider all prefixes of the current string
			String prefix = word.substring(0, i);

			// if the prefix is present in the dictionary, add it to the
			// output string and recur for the remaining string

			if (dict.contains(prefix)) {
				wordBreak(dict, word.substring(i), out + " " + prefix);
			}
		}
	}

	public static void main(String[] args) {
		// String word = "leetcode";
		// new WordBreak().wordBreak(word, Arrays.asList(new String[] {"leet",
		// "code"}));
		boolean result = new WordBreak().wordBreak("catsandog",
				Arrays.asList(new String[] { "cats", "dog", "sand", "and", "cat" }));
		System.out.println(result);
	}

}
