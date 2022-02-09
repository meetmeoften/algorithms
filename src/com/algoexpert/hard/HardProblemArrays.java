package com.algoexpert.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public class HardProblemArrays {

	public static int[] subarraySort(int[] array) {

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < array.length; i++) {
			int num = array[i];

			if (isOutOfOrder(i, num, array)) {
				min = Math.min(min, num);
				max = Math.max(max, num);
			}
		}

		if (min == Integer.MAX_VALUE) {
			return new int[] { -1, -1 };
		}

		int left = 0;
		while (min >= array[left]) {
			left++;
		}

		int right = array.length - 1;
		while (max <= array[right]) {
			right--;
		}
		return new int[] { left, right };
	}

	private static boolean isOutOfOrder(int i, int num, int[] array) {
		if (i == 0) {
			return num > array[i + 1];
		}
		if (i == array.length - 1) {
			return num < array[i - 1];
		}

		return num > array[i + 1] || num < array[i - 1];

	}

	// Largest Range
	public static int[] largestRange(int[] array) {
		int[] bestRange = new int[2];
		int longestLength = 0;

		Map<Integer, Boolean> map = new HashMap<>();

		for (int value : array) {
			map.put(value, true);
		}

		for (int value : array) {
			if (!map.get(value)) {
				continue;
			}
			map.put(value, false);
			int currentLength = 1;

			int left = value - 1;
			while (map.containsKey(left)) {
				map.put(value, false);
				currentLength++;
				left--;
			}

			int right = value + 1;
			while (map.containsKey(right)) {
				map.put(value, false);
				currentLength++;
				right++;
			}

			if (currentLength > longestLength) {
				longestLength = currentLength;
				bestRange = new int[] { left + 1, right - 1 };
			}
		}

		return bestRange;
	}

	// Zig Zag Traverse
	public static List<Integer> zigzagTraverse(List<List<Integer>> array) {
		int startRow = 0;
		int endRow = array.size() - 1;
		int startCol = 0;
		int endCol = array.get(0).size() - 1;
		List<Integer> result = new ArrayList<>();

		boolean goingDown = true;

		while (!isOutOfBounds(startRow, startCol, endRow, endCol)) {
			result.add(array.get(startRow).get(startCol));
			if (goingDown) {
				if (startCol == 0 || startRow == endRow) {
					goingDown = false;
					if (startRow == endRow) {
						startCol++;
					} else {
						startRow++;
					}
				} else {
					startRow++;
					startCol--;
				}
			} else {
				if (startRow == 0 || startCol == endCol) {
					goingDown = true;
					if (startCol == endCol) {
						startRow++;
					} else {
						startCol++;
					}
				} else {
					startRow--;
					startCol++;
				}
			}

		}
		return result;
	}

	private static boolean isOutOfBounds(int startRow, int startCol, int endRow, int endCol) {
		return startRow < 0 || startCol < 0 || startRow > endRow || startCol > endCol;
	}

	// MinRewards
	public static int minRewards(int[] scores) {
		// Write your code here.
		int[] rewards = new int[scores.length];
		Arrays.fill(rewards, 1);

		for (int i = 1; i < scores.length; i++) {
			int j = i - 1;
			if (scores[i] > scores[j]) {
				rewards[i] = rewards[j] + 1;
			} else {
				while (j >= 0 && scores[j] > scores[j + 1]) {
					rewards[j] = Math.max(rewards[j], rewards[j + 1] + 1);
					j--;
				}
			}
		}
		return IntStream.of(rewards).sum();
	}

	// Shifted Binary Search
	public static int shiftedBinarySearch(int[] array, int target) {
		return shiftedBinarySearch(array, target, 0, array.length - 1);
	}

	private static int shiftedBinarySearch(int[] array, int target, int left, int right) {
		while (left <= right) {
			int mid = (left + right) / 2;
			int midV = array[mid];
			int leftV = array[left];
			int rightV = array[right];

			if (midV == target) {
				return mid;
			} else if (leftV <= midV) {
				if (midV > target && leftV <= target) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {
				if (midV < target && rightV >= target) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}

			if (leftV <= midV) {
				if (target > midV || target < leftV) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			} else {
				if (target < midV || target > rightV) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
		}
		return -1;
	}

	// Kth smallest Integer
	public static int quickselect(int[] array, int k) {
		int position = k - 1;
		return quickselect(array, 0, array.length - 1, position);
	}

	private static int quickselect(int[] array, int start, int end, int position) {
		while (true) {
			if (start > end) {
				throw new RuntimeException();
			}

			int pivot = start;
			int left = start + 1;
			int right = end;

			while (left <= right) {
				if (array[pivot] < array[left] && array[pivot] > array[right]) {
					swap(left, right, array);
				}
				if (array[left] <= array[pivot]) {
					left++;
				}
				if (array[right] >= array[pivot]) {
					right--;
				}
			}
			swap(pivot, right, array);
			if (right == position) {
				return array[position];
			} else if (right < position) {
				start = right + 1;
			} else {
				end = right - 1;
			}
		}
	}

	public static void swap(int i, int j, int[] array) {
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}

	// KSum
	public static List<List<Integer>> fourNumberSum(int[] array, int targetSum) {
		Arrays.sort(array);
		return KSum_Recursion(array, 0, 4, targetSum);
	}

	private static List<List<Integer>> KSum_Recursion(int[] nums, int index, int k, int target) {
		if (k == 2) {
			return twoSum(nums, index, nums.length - 1, target);
		}

		List<List<Integer>> kList = new ArrayList<>();
		for (int i = index; i < nums.length - k + 1; i++) {
			List<List<Integer>> temp = KSum_Recursion(nums, i + 1, k - 1, target - nums[i]);
			if (temp != null && temp.size() > 0) {
				for (List<Integer> sumList : temp) {
					sumList.add(0, nums[i]);
				}
				kList.addAll(temp);
			}
			while (i < nums.length - k + 1 && nums[i] == nums[i + 1]) {
				i++;
			}
		}
		return kList;
	}

	static List<List<Integer>> twoSum(int[] nums, int left, int right, int target) {
		var twosumList = new ArrayList<List<Integer>>();

		while (left < right) {
			int sum = nums[left] + nums[right];
			if (sum == target) {
				var temp = new ArrayList<Integer>();
				temp.add(nums[left]);
				temp.add(nums[right]);
				twosumList.add(temp);
			}
			if (sum > target) {
				right--;
			} else {
				left++;
			}
		}
		return twosumList;
	}

	// Dijkstras

	public int[] dijkstrasAlgorithm(int start, int[][][] edges) {

		int numberOfVertices = edges.length;
		int[] minDistances = new int[edges.length];
		Arrays.fill(minDistances, Integer.MAX_VALUE);
		minDistances[start] = 0;

		Set<Integer> visited = new HashSet<Integer>();

		while (numberOfVertices != visited.size()) {
			int[] getVertexData = getMinDistanceVertexData(minDistances, visited);
		}

		return minDistances;
	}

	private int[] getMinDistanceVertexData(int[] minDistances, Set<Integer> visited) {
		// TODO Auto-generated method stub
		return null;
	}

}
