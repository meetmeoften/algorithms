package com.algoexpert.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReorganizeString {

	public static String reorganizeString(String s) {
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
		}
		PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
		pq.addAll(map.entrySet());

		StringBuilder sb = new StringBuilder();

		while (!pq.isEmpty()) {
			Map.Entry<Character, Integer> temp1 = pq.poll();
			if (sb.length() == 0 || sb.charAt(sb.length() - 1) != temp1.getKey()) {
				sb.append(temp1.getKey());
				temp1.setValue(temp1.getValue() - 1);
			} else { // the character is same
				Map.Entry<Character, Integer> temp2 = pq.poll();
				if (temp2 == null) {
					return "";
				}
				sb.append(temp2.getKey());
				temp2.setValue(temp2.getValue() - 1);
				if (temp2.getValue() != 0) {
					pq.offer(temp2);
				}
			}
			if (temp1.getValue() != 0) {
				pq.offer(temp1);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(reorganizeString("aaabc"));
	}

}
