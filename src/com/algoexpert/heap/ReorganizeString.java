package com.algoexpert.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class ReorganizeString {

	public static String reorganizeString(String s) {
		Map<Character, Integer> freqMap = new HashMap<>();

		// Count frequency of each character
		for (char c : s.toCharArray()) {
			freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
		}

		// Max heap based on frequency
		PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
				new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

		maxHeap.addAll(freqMap.entrySet());

		StringBuilder sb = new StringBuilder();
		Map.Entry<Character, Integer> prev = null;

		while (!maxHeap.isEmpty()) {
			Map.Entry<Character, Integer> curr = maxHeap.poll();

			// Append current character
			sb.append(curr.getKey());
			curr.setValue(curr.getValue() - 1);
			// Re-add the previous character if it still has remaining frequency
			if (prev != null && prev.getValue() > 0) {
				maxHeap.offer(prev);
			}
			prev = curr;
		}
		// Check if reorganization is valid
		return sb.length() == s.length() ? sb.toString() : "";
	}

	public int[] topKFrequent(int[] nums, int k) {

		HashMap<Integer, Integer> map = new HashMap<>();
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
		List<Integer> list = new ArrayList<>();

		for(int num: nums) {
			map.put(num, map.getOrDefault(map.get(num), 0) + 1);

		}

		for(Entry<Integer, Integer> entry: map.entrySet()) {
			pq.add(new int[] {entry.getKey(), entry.getValue()});

			if(pq.size() > k) {
				int[] n = pq.poll();
				list.add(n[0]);
			}
		}

		return list.stream().mapToInt(Integer::intValue).toArray();
	}

	public static void main(String[] args) {
		System.out.println(reorganizeString("aaabc"));
	}

}
