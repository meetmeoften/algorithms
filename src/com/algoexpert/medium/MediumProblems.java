package com.algoexpert.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class MediumProblems {

	// Two number Sum
	public static int[] twoNumberSum(int[] array, int targetSum) {

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for(int i=0; i< array.length; i++) {
			int diff = targetSum - array[i];
			Integer secondNum = map.get(diff);
			if(secondNum != null) {
				return new int[] {array[secondNum], array[i]};
			} else {
				map.put(array[i], i);
			}
		}

		return new int[] {};
	}

	// Two number Sum
	public static int[] twoNumberSum2(int[] array, int targetSum) {

		Arrays.sort(array);
		int left = 0;
		int right = array.length -1;

		while(left < right) {
			int sum = array[left] + array[right];
			if(sum == targetSum) {
				return new int[] {array[left], array[right]};
			} else if(sum > targetSum) {
				right--;
			} else {
				left++;
			}
		}
		return new int[0];
	}

	// Three number sum
	public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
		Arrays.sort(array);
		List<Integer[]> triplets = new ArrayList<>();

		for(int i= 0; i< array.length; i++) {
			int left = i+1;
			int right = array.length-1;

			while(left < right) {
				int cs = array[i]+ array[left]+ array[right];
				if(cs == targetSum) {
					triplets.add(new Integer[] { array[i], array[left],  array[right]});
					left++;
					right--;
				} else if(cs > targetSum) {
					right--;
				} else if(cs < targetSum) {
					left++;
				}
			}
		}
		return triplets;
	}

	// smallest Difference
	public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
		Arrays.sort(arrayOne);
		Arrays.sort(arrayTwo);

		int i= 0;
		int j = 0;

		int smallest = Integer.MAX_VALUE;
		int current = Integer.MAX_VALUE;

		int[] pair = new int[2];
		while(i< arrayOne.length && j < arrayTwo.length) {

			int first = arrayOne[i];
			int second = arrayTwo[j];

			if(first > second) {
				current = first - second;
				j++;
			} else if (second > first) {
				current = second - first;
				i++;
			} else {
				return new int[] {first, second};
			}

			if(current < smallest) {
				smallest = current;
				pair = new int[] {first, second};
			}
		}
		return pair;
	}


	// Monotonic array
	public static boolean isMonotonic(int[] array) {
		if(array.length <= 2) {
			return true;
		}
		var direction = array[1] - array[0];
		for(int i= 2; i< array.length; i++) {
			if(direction == 0) {
				direction = array[i] - array[i-1];
				continue;
			}
			var currentDifference = array[i] - array[i-1];
			if(direction > 0 && currentDifference < 0) {
				return false;
			} else if(direction < 0 && currentDifference > 0) {
				return false;
			}
		}
		return true;
	}

	// Monotonic array2
	public static boolean isMonotonic2(int[] array) {
		var isNonIncr = true;
		var isNonDecr = true;
		for(int i= 1; i < array.length; i++) {
			if(array[i] < array[i-1]) {
				isNonDecr = false;
			}
			if(array[i] > array[i-1]) {
				isNonIncr = false;
			}
		}
		return isNonDecr || isNonIncr;
	}

	// Move Element To End
	public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
		int i= 0;
		int j = array.size()-1;

		while(i < j) {
			while(i < j && array.get(j) == toMove) {
				j--;
			}
			if(array.get(i) == toMove) {
				// swap element
				swap(array, i, j);
			}
			i++;
		}
		return array;

	}

	private static void swap(List<Integer> array, Integer i, Integer j) {
		Integer temp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);

	}

	// Longest Peak
	public static int longestPeak(int[] array) {
		int longestPeakLength = 0;
		int i = 1;

		while(i < array.length-1) {
			boolean isPeak = array[i] > array[i-1] && array[i] > array[i+1];
			if(!isPeak) {
				i++;
				continue;
			}

			int leftIndex = i-2;
			while(leftIndex >= 0 && array[leftIndex+1] > array[leftIndex]) {
				leftIndex--;
			}

			int rightIndex = i+2;
			while(rightIndex< array.length  && array[rightIndex-1] > array[rightIndex]) {
				rightIndex++;
			}

			int currentPeakLength = rightIndex - leftIndex -1;
			longestPeakLength = Math.max(currentPeakLength, longestPeakLength);
			i++;

		}
		return longestPeakLength;
	}

	//First Duplicate Value
	public int firstDuplicateValue(int[] array) {
		Set<Integer> hashSet = new HashSet<>();
		for(Integer value: array) {
			if(hashSet.contains(value)) {
				return value;
			}
			hashSet.add(value);
		}
		return -1;
	}


	// ArrayOfProducts
	public int[] arrayOfProducts(int[] array) {
		int[] products = new int[array.length];
		int[] leftProducts = new int[array.length];
		int[] rightProducts = new int[array.length];

		int leftRunningProduct = 1;
		for(int i= 0; i< array.length; i++) {
			leftProducts[i] = leftRunningProduct;
			leftRunningProduct = leftRunningProduct * array[i];
		}

		int rightRunningProduct = 1;
		for(int i= array.length-1; i >= 0; i--) {
			rightProducts[i] = rightRunningProduct;
			rightRunningProduct = rightRunningProduct * array[i];
		}

		for(int i= 0; i< array.length; i++) {
			products[i] = leftProducts[i] * rightProducts[i];
		}

		return products;

	}

	// MergeOverlappingIntervals
	public int[][] mergeOverlappingIntervals(int[][] intervals) {

		int[][] sortedIntervals = intervals.clone();
		Arrays.sort(sortedIntervals, (a, b) -> Integer.compare(a[0], b[0]));

		List<int[]> mergedIntervals = new ArrayList<>();
		int[] currentInterval = sortedIntervals[0];
		mergedIntervals.add(currentInterval);

		for(int[] nextInterval : sortedIntervals) {

			int currentIntervalEnd = currentInterval[1];
			int nextIntervalStart = nextInterval[0];
			int nextIntervalEnd = nextInterval[1];

			if(currentIntervalEnd >= nextIntervalStart) {
				currentInterval[1] = Math.max(currentIntervalEnd, nextIntervalEnd);
			} else {
				currentInterval = nextInterval;
				mergedIntervals.add(currentInterval);
			}
		}
		return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
	}

	// Kadane's Algorithm
	public static int kadanesAlgorithm(int[] array) {

		int maxEndHere = array[0];
		int maxSoFar = array[0];

		for(int i=1; i< array.length; i++) {
			maxEndHere = array[i] + maxEndHere;
			maxEndHere = Math.max(maxEndHere, array[i]);
			maxSoFar = Math.max(maxSoFar, maxEndHere);
		}
		return maxSoFar;

	}


	// Spiral Traverse
	public static List<Integer> spiralTraverse(int[][] array) {
		List<Integer> results = new ArrayList<>();
		if(array.length == 0) {
			return results;
		}
		int startRow = 0;
		int endRow = array.length -1;

		int startCol = 0;
		int endCol = array[0].length -1;

		while(startRow <= endRow && startCol <= endCol) {
			for(int col= startCol; col<= endCol; col++) {
				results.add(array[startRow][col]);
			}

			for(int row= startRow+1; row<= endRow; row++) {
				results.add(array[row][endCol]);
			}

			for(int col= endCol-1; col>= startCol; col--) {
				if(startRow == endRow) {
					break;
				}
				results.add(array[endRow][col]);
			}

			for(int row = endRow-1; row > startRow; row--) {
				if(startCol == endCol) {
					break;
				}
				results.add(array[row][startCol]);
			}

			startRow++;
			startCol++;
			endRow--;
			endCol--;
		}
		return results;
	}




	// DFS and BFS
	static class Node {
		String name;
		List<Node> children = new ArrayList<Node>();

		public Node(String name) {
			this.name = name;
		}

		public List<String> breadthFirstSearch(List<String> array) {
			// Write your code here.
			Queue<Node> queue = new LinkedList<Node>();
			queue.add(this);
			while(queue.size()> 0) {
				Node node = queue.poll();
				array.add(node.name);
				var childNodes = node.children;
				for(Node child: childNodes) {
					queue.add(child);
				}

			}
			return array;
		}

		public List<String> depthFirstSearch(List<String> array) {
			array.add(name);
			for(int i= 0; i < children.size(); i++) {
				children.get(i).depthFirstSearch(array);
			}
			return array;
		}

		public List<String> depthFirstSearch2(List<String> array) {
			// Write your code here.
			Stack<Node> stack = new Stack<Node>();
			stack.add(this);
			while(!stack.isEmpty()) {
				Node node = stack.pop();
				array.add(node.name);
				for (int i = node.children.size()-1; i >= 0; i--) {
					Node n=node.children.get(i);
					stack.add(n);
				}
			}
			return array;
		}

		public Node addChild(String name) {
			Node child = new Node(name);
			children.add(child);
			return this;
		}
	}


}
