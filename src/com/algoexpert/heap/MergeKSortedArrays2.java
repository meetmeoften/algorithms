package com.algoexpert.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedArrays2 {

	static class Node implements Comparable<Node> {
		// `value` stores the element
		private int value;

		// `i` stores the list number of the element
		private int i;

		// `index` stores the column number of the list from which
		// element was taken
		private int index;

		// Constructor
		public Node(int value, int i, int index) {
			this.value = value;
			this.i = i;
			this.index = index;
		}

		public int getValue() {
			return value;
		}

		public int getListNum() {
			return i;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public void setValue(int value) {
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return value - o.value;
		}

	}

	public static List<Integer> mergeSortedArrays(List<List<Integer>> lists) {
		// Write your code here.
		PriorityQueue<Node> pq = new PriorityQueue<Node>();

		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).size() >= 1) {
				pq.add(new Node(lists.get(i).get(0), i, 0));
			}
		}

		while (!pq.isEmpty()) {
			// extract the minimum node from the min-heap
			Node min = pq.poll();

			// print the minimum element
			System.out.print(min.getValue() + " ");

			// take the next element from the "same" list and insert it into the
			// min-heap
			if (min.getIndex() + 1 < lists.get(min.getListNum()).size()) {
				min.setIndex(min.getIndex() + 1);
				min.setValue(lists.get(min.getListNum()).get(min.getIndex()));
				pq.add(min);
			}
		}

		return new ArrayList<Integer>();
	}


	public static void main(String[] args) {
		List<List<Integer>> arrays = new ArrayList<List<Integer>>();
		arrays.add(Arrays.asList(new Integer[] {1, 5, 9, 21}));
		arrays.add(Arrays.asList(new Integer[] {-1, 0}));
		arrays.add(Arrays.asList(new Integer[] {-124, 81, 121}));
		arrays.add(Arrays.asList(new Integer[] {3, 6, 12, 20, 150}));
		var actual = mergeSortedArrays(arrays);
		var expected =
				Arrays.asList(new Integer[] {-124, -1, 0, 1, 3, 5, 6, 9, 12, 20, 21, 81, 121, 150});
	}

}
