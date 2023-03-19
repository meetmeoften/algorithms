package com.algoexpert.greedy;

import java.util.Arrays;
import java.util.HashMap;

public class HandOfStraights {

	public static boolean isNStraightHand(int[] hand, int groupSize) {
		if (hand.length % groupSize != 0) {
			return false;
		}

		HashMap<Integer, Integer> hm = new HashMap<>();
		for (int card : hand) {
			hm.put(card, hm.getOrDefault(card, 0) + 1);
		}

		Arrays.sort(hand);
		for (int card : hand) {
			if (hm.get(card) <= 0) {
				continue;
			}
			for (int i = 1; i < groupSize; i++) {
				int count = hm.getOrDefault(card + i, 0);
				if (count > 0) {
					hm.put(card + i, count - 1);
				} else {
					return false;
				}
			}
			hm.put(card, hm.get(card) - 1);
		}

		return true;
	}

	public static void main(String[] args) {
		int[] hand = { 1, 2, 3, 6, 2, 3, 4, 7, 8 };
		int size = 3;
		isNStraightHand(hand, size);
	}

}
