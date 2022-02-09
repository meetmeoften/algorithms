package com.algoexpert.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class HardProblemTrees {

	// Same BST
	public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
		if(arrayOne.size() == 0 && arrayTwo.size() == 0 ) {
			return true;
		}

		if(arrayOne.size() != arrayTwo.size()) {
			return false;
		}

		if(arrayOne.get(0).intValue() != arrayTwo.get(0).intValue()) {
			return false;
		}

		List<Integer> leftOne = getSmaller(arrayOne);
		List<Integer> leftTwo = getSmaller(arrayTwo);

		List<Integer> rightOne = getBigger(arrayOne);
		List<Integer> rightTwo = getBigger(arrayTwo);

		return sameBsts(leftOne, leftTwo) && sameBsts(rightOne, rightTwo);
	}

	private static List<Integer> getSmaller(List<Integer> array) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<>();
		for(int i= 1; i< array.size(); i++) {
			if(array.get(0).intValue() > array.get(i).intValue()) {
				list.add(array.get(i));
			}
		}
		return list;
	}

	private static List<Integer> getBigger(List<Integer> array) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<>();
		for(int i= 1; i< array.size(); i++) {
			if(array.get(0).intValue() <= array.get(i).intValue()) {
				list.add(array.get(i));
			}
		}
		return list;
	}

	// Validate Three Nodes
	public boolean validateThreeNodes(BST nodeOne, BST nodeTwo, BST nodeThree) {
		// Write your code here
		if(isDescendant(nodeTwo, nodeOne)) {
			return isDescendant(nodeThree, nodeTwo);
		}

		if(isDescendant(nodeTwo, nodeThree)) {
			return isDescendant(nodeOne, nodeTwo);
		}
		return false;
	}

	public boolean isDescendant(BST node, BST target) {
		while(node != null && node != target) {
			if(node.value < target.value) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		return node == target;
	}

	static class BST {
		public int value;
		public BST left = null;
		public BST right = null;

		public BST(int value) {
			this.value = value;
		}
	}


	// Max Path Sum in a Binary Tree
	public static int maxPathSum(BinaryTree tree) {
		List<Integer> maxSumArray = findMaxSum(tree);
		return maxSumArray.get(1);
	}

	// Find MaxSum
	public static List<Integer> findMaxSum(BinaryTree tree) {
		if(tree == null) {
			return new ArrayList<Integer>(Arrays.asList(0, Integer.MIN_VALUE));
		}
		List<Integer> lsArray = findMaxSum(tree.left);
		Integer lsb = lsArray.get(0);
		Integer ls = lsArray.get(1);

		List<Integer> rsArray = findMaxSum(tree.right);
		Integer rsb = rsArray.get(0);
		Integer rs = rsArray.get(1);

		Integer mcsb= Math.max(lsb, rsb);
		Integer msb = Math.max(mcsb + tree.value, tree.value);

		Integer mst =  Math.max(msb, lsb + tree.value+ rsb);
		Integer rmps = Math.max(mst, Math.max(ls, rs));
		return new ArrayList<Integer>(Arrays.asList(msb, rmps));
	}

	static class BinaryTree {
		public int value;
		public BinaryTree left;
		public BinaryTree right;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	// K-Sorted Array
	public int[] sortKSortedArray(int[] array, int k) {
		// Write your code here.
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		for(int i= 0; i < Math.min(k+1, array.length); i++) {
			pq.add(array[i]);
		}

		int n= 0;
		for(int i=k+1; i< array.length; i++) {
			Integer min = pq.remove();
			array[n] = min;
			n++;

			Integer current = array[i];
			pq.add(current);

		}

		while(!pq.isEmpty()) {
			Integer min = pq.remove();
			array[n] = min;
			n++;

		}
		return array;
	}

	// Continous median
	static class ContinuousMedianHandler {
		Queue<Integer> minHeap = new PriorityQueue<>();
		Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		double median = 0;

		public void insert(int number) {
			minHeap.offer(number);
			maxHeap.offer(minHeap.poll());

			if (maxHeap.size() > minHeap.size()) {
				minHeap.offer(maxHeap.poll());
			}
		}

		public double getMedian() {
			if(minHeap.size() == maxHeap.size()) {
				median = ((double) minHeap.peek() + maxHeap.peek()) / 2.0;
			} else {
				median = minHeap.peek();
			}
			return median;
		}
	}

	// Laptop rentals
	public int laptopRentals(ArrayList<ArrayList<Integer>> times) {
		if(times.size() == 0) {
			return 0;
		}
		int usedLaptops = 0;
		List<Integer> startTimes = new ArrayList<>();
		List<Integer> endTimes = new ArrayList<>();

		for(ArrayList<Integer> interval: times) {
			startTimes.add(interval.get(0));
			endTimes.add(interval.get(1));
		}

		Collections.sort(startTimes);
		Collections.sort(endTimes);

		int i = 0;
		int j = 0;

		while(i < times.size()) {
			if(startTimes.get(i) >= endTimes.get(j)) {
				usedLaptops--;
				j++;
			}
			usedLaptops++;
			i++;
		}
		return usedLaptops;
	}


}
