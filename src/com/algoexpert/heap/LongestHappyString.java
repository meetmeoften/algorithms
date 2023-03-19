package com.algoexpert.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LongestHappyString {

	public String longestDiverseString(int a, int b, int c) {
		PriorityQueue<Pair> maxHeap = new PriorityQueue<>(new PairComparator());
		StringBuilder answer = new StringBuilder();
		if (a != 0) {
			maxHeap.offer(new Pair(a, 'a'));
		}
		if (b != 0) {
			maxHeap.offer(new Pair(b, 'b'));
		}
		if (c != 0) {
			maxHeap.offer(new Pair(c, 'c'));
		}
		while (!maxHeap.isEmpty()) {
			Pair pair = maxHeap.poll();
			int n = answer.length();
			if (n > 1 && answer.charAt(n - 1) == answer.charAt(n - 2) && answer.charAt(n - 1) == pair.y) {
				if (maxHeap.isEmpty()) {
					break;
				} else {
					Pair nextPair = maxHeap.poll();
					answer.append(nextPair.y);
					nextPair.x--;
					if (nextPair.x != 0) {
						maxHeap.offer(nextPair);
					}
				}
			} else {
				answer.append(pair.y);
				pair.x--;
			}
			if (pair.x != 0) {
				maxHeap.offer(pair);
			}
		}
		return answer.toString();
	}

	class Pair {
		int x;
		char y;

		Pair(int x, char y) {
			this.x = x;
			this.y = y;
		}
	}

	class PairComparator implements Comparator<Pair> {
		@Override
		public int compare(Pair p1, Pair p2) {
			return p2.x - p1.x;
		}
	}

}
