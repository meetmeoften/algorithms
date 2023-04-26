package com.test.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if (beginWord.equals(endWord)) {
			return 1;
		}

		Set<String> dict = new HashSet<>(wordList);
		Queue<String> q = new LinkedList<>();
		q.offer(beginWord);

		int steps = 1;

		while (!q.isEmpty()) {
			int level = q.size();

			for (int i = 0; i < level; i++) {
				String s = q.poll();

				if (s.equals(endWord)) {
					return steps;
				}

				char[] letters = s.toCharArray();

				for (int j = 0; j < s.length(); j++) {
					char prevCh = letters[j];

					for (char ch = 'a'; ch <= 'z'; ch++) {
						letters[j] = ch;
						String nextWord = new String(letters);

						if (dict.contains(nextWord)) {
							q.offer(nextWord);
							dict.remove(nextWord);
						}
					}

					letters[j] = prevCh;
				}
			}

			++steps;
		}

		return 0;
	}

	public static void main(String[] args) {
		String start = "hit";
		String end = "cog";
		List<String> list = new ArrayList<>();
		String[] dict = new String[] { "hot", "dot", "dog", "lot", "log" };
		list.addAll(Arrays.asList(dict));

		WordLadder ladder = new WordLadder();
		System.out.println(ladder.ladderLength(start, end, list));

	}
}
