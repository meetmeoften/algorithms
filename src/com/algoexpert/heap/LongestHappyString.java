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
			if (n > 1 && answer.charAt(n - 1) == answer.charAt(n - 2) && answer.charAt(n - 1) == pair.value) {
				if (maxHeap.isEmpty()) {
					break;
				} else {
					Pair nextPair = maxHeap.poll();
					answer.append(nextPair.value);
					nextPair.count--;
					if (nextPair.count != 0) {
						maxHeap.offer(nextPair);
					}
				}
			} else {
				answer.append(pair.value);
				pair.count--;
			}
			if (pair.count != 0) {
				maxHeap.offer(pair);
			}
		}
		return answer.toString();
	}

	class Pair {
		int count;
		char value;

		Pair(int x, char y) {
			this.count = x;
			this.value = y;
		}
	}

	class PairComparator implements Comparator<Pair> {
		@Override
		public int compare(Pair p1, Pair p2) {
			return p2.count - p1.count;
		}
	}

	public static void main(String[] args) {
		int a = 1, b = 1, c = 7;
		LongestHappyString longest = new LongestHappyString();
		longest.longestDiverseString(a, b, c);
	}

}
