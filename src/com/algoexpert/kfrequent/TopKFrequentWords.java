package com.algoexpert.kfrequent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {

	public static List<String> topKFrequent(String[] words, int k) {
		if (words == null || words.length == 0) {
			return Collections.emptyList();
		}

		List<String> res = new ArrayList<>();
		Map<String, Integer> hm = new HashMap<>();

		for (String w : words) {
			hm.put(w, hm.getOrDefault(w, 0) + 1);
		}

		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
				(a, b) -> a.getValue().equals(b.getValue()) ?
						b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue());

		for (Map.Entry<String, Integer> entry : hm.entrySet()) {
			pq.offer(entry);

			if (pq.size() > k) {
				pq.poll();
			}
		}

		while (!pq.isEmpty()) {
			res.add(pq.poll().getKey());
		}

		Collections.reverse(res);
		return res;
	}


	public static void main(String[] args) {
		String[] words = new String[] {"i", "i", "love","leetcode","i","love","coding", "love"};
		Integer k = 2;
		topKFrequent(words, k);
	}

}
