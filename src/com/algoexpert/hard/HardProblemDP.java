package com.algoexpert.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HardProblemDP {

	// Max Increasing Subsequence
	public static List<List<Integer>> maxSumIncreasingSubsequence(int[] array) {

		int[] sums = array.clone();
		int maxSumIdx = 0;

		int[] sequences = new int[array.length];
		Arrays.fill(sequences, Integer.MIN_VALUE);

		for(int i= 0; i < array.length; i++) {
			int current = array[i];
			for(int j= 0; j < i; j++) {
				int other = array[j];
				int total = current + sums[j];

				if(total >= sums[i] && current > other) {
					sums[i] = total;
					sequences[i] = j;
				}
			}
			if(sums[i] >= sums[maxSumIdx]) {
				maxSumIdx = i;
			}
		}

		return buildSequence(array, sequences, maxSumIdx, sums[maxSumIdx]);

	}

	private static List<List<Integer>> buildSequence(int[] array, int[] sequences, int i, int sum) {
		List<List<Integer>> sequence = new ArrayList<>();
		sequence.add(new ArrayList<Integer>());
		sequence.add(new ArrayList<Integer>());

		sequence.get(0).add(sum);

		while(i != Integer.MIN_VALUE) {
			sequence.get(1).add(0, array[i]);
			i = sequences[i];
		}

		return sequence;
	}

	// Longest Common Subsequence
	public static List<Character> longestCommonSubsequence(String str1, String str2) {
		int[][] lengths = new int[str2.length()+1][str1.length()+1];
		for(int i= 1; i< str2.length()+1 ; i++) {
			for(int j= 1; j < str1.length()+1; j++) {
				if(str2.charAt(i-1) == str2.charAt(j-1)) {
					lengths[i][j] = lengths[i-1][j-1]+1;
				} else {
					lengths[i][j] = Math.max(lengths[i-1][j], lengths[i][j-1]);
				}
			}
		}

		return buildSequence(lengths, str1);
	}

	private static List<Character> buildSequence(int[][] lengths, String str1) {
		// TODO Auto-generated method stub
		List<Character> sequence = new ArrayList<>();
		int i= lengths.length-1;
		int j= lengths[0].length-1;

		while(i != 0 && j != 0) {
			if(lengths[i][j] == lengths[i-1][j]) {
				i--;
			} else if(lengths[i][j] == lengths[i][j-1]) {
				j--;
			} else {
				sequence.add(0, str1.charAt(j-1));  // TODO check why it is added
				i--;
				j--;
			}

		}
		return sequence;
	}

	// Minimum Number of Jumps
	public static int minNumberOfJumps(int[] array) {
		if(array.length == 1) {
			return 0;
		}
		int[] jumps = new int[array.length];
		Arrays.fill(jumps, Integer.MAX_VALUE);
		jumps[0] = 0;

		for(int i=1; i< array.length; i++) {
			for(int j= 0; j < i; j++) {
				if(array[j] + j >= i) {
					jumps[i] = Math.min(jumps[i] , jumps[j]+1);
				}
			}
		}
		return jumps[jumps.length-1];
	}

	public static int minNumberOfJumps2(int[] array) {
		if(array.length == 1) {
			return 0;
		}
		int jumps = 0;
		int maxReach = array[0];
		int steps = array[0];

		for(int i=1; i < array.length-1; i++){
			maxReach = Math.max(maxReach, i+ array[i]);
			steps--;
			if(steps == 0) {
				jumps++;
				steps = maxReach-i;
			}

		}
		return jumps+1;
	}

	// Water Area
	public static int waterArea(int[] heights) {
		// Write your code here.
		if(heights.length == 0) {
			return 0;
		}

		return -1;
	}

	public static int waterArea2(int[] heights) {
		// Write your code here.
		int[] leftMaxes = new int[heights.length];
		int leftMax = 0;
		for(int i=0; i < heights.length; i++) {
			int height = heights[i];
			leftMaxes[i] = leftMax;
			leftMax = Math.max(leftMax, height);
		}

		int[] rightMaxes = new int[heights.length];
		int rightMax = 0;
		for(int i= heights.length-1; i>=0; i--) {
			int height = heights[i];
			rightMaxes[i] = rightMax;
			rightMax = Math.max(rightMax, height);
		}

		int[] maxes = new int[heights.length];
		for(int i= 0; i< heights.length; i++) {
			if(heights[i] < Math.min(leftMaxes[i], rightMaxes[i])) {
				maxes[i] = Math.min(leftMaxes[i], rightMaxes[i]) - heights[i];
			} else {
				maxes[i] = 0;
			}
		}

		int total = 0;
		for(int i= 0; i < heights.length; i++) {
			total = total+ maxes[i];
		}
		return total;
	}

	//Knapsack
	public static List<List<Integer>> knapsackProblem(int[][] items, int capacity) {
		int[][] values = new int[items.length+1][capacity +1];

		for(int i=1; i< items.length+1; i++) {
			int value = items[i-1][0];
			int weight = items[i-1][1];
			for(int c= 0; c < capacity+1 ; c++) {

				if(weight > c) {
					values[i][c] = values[i-1][c];
				} else {
					values[i][c] = Math.max(values[i-1][c], value + values[i-1][c-weight]);
				}
			}
		}

		return getKnapsackItems(values, items, values[items.length][capacity]);
	}

	private static List<List<Integer>> getKnapsackItems(int[][] values, int[][] items, int total) {
		List<List<Integer>> sequence = new ArrayList<>();
		sequence.add(new ArrayList<Integer>());
		sequence.add(new ArrayList<Integer>());
		sequence.get(0).add(total);

		int i = values.length-1;
		int c = values[0].length-1;

		while( i > 0) {
			if(values[i][c] == values[i-1][c]) {
				i--;
			} else {
				sequence.get(1).add(0, i-1);
				c = c - items[i-1][1];  // check solution at 28:37
				i--;
			}
			if(c == 0) {
				break;
			}
		}
		return sequence;
	}

	// Disk Stacking
	public static List<Integer[]> diskStacking(List<Integer[]> disks) {

		disks.sort((d1, d2) -> d1[2].compareTo(d2[2]));

		int[] heights = new int[disks.size()];
		for(int i=0; i < disks.size(); i++) {
			heights[i] = disks.get(i)[2];
		}

		int[] sequences = new int[disks.size()];
		for(int i=0; i < disks.size(); i++) {
			sequences[i] = Integer.MIN_VALUE;
		}

		int maxHeightIndex = 0;
		for(int i= 1; i < disks.size(); i++) {
			Integer[] current = disks.get(i);
			for(int j= 0; j<i; j++) {
				Integer[] other = disks.get(j);
				if(valid(other, current)) {
					int currHeight = current[2] + heights[j];
					if(currHeight >= heights[i]) {
						heights[i] = currHeight;
						sequences[i] = j;
					}
				}
			}

			if(heights[i] >= heights[maxHeightIndex]) {
				maxHeightIndex = i;
			}
		}
		return buildSequence(disks, sequences, maxHeightIndex);
	}

	private static List<Integer[]> buildSequence(List<Integer[]> array, int[] sequences, int currentIndex) {
		List<Integer[]> sequence = new ArrayList<>();
		while(currentIndex != Integer.MIN_VALUE) {
			sequence.add(0, array.get(currentIndex));
			currentIndex = sequences[currentIndex];
		}
		return sequence;
	}

	public static boolean valid(Integer[] other, Integer[] current) {
		return current[0] > other[0] &&
				current[1] > other[1] &&
				current[2] > other[2];
	}

	// MaxSum SubMatrix
	public int maximumSumSubmatrix(int[][] matrix, int size) {
		int[][] sums = createSumMatrix(matrix);
		int maxSubMatrixSum = Integer.MIN_VALUE;

		for(int row= size-1; row< matrix.length;row++) {
			for(int col= size-1; col< matrix[0].length; col++) {
				int total = sums[row][col];

				boolean touchesTopBorder = row - size < 0;
				if(!touchesTopBorder) {
					total = total- sums[row -size][col];
				}

				boolean touchesLeftBorder = col-size <0;
				if(!touchesLeftBorder) {
					total = total- sums[row][col-size];
				}

				boolean touchesTopOrLeftBorder = (!touchesLeftBorder && !touchesTopBorder);
				if(touchesTopOrLeftBorder) {
					total = total + sums[row-size][col-size];
				}

				maxSubMatrixSum = Math.max(maxSubMatrixSum, total);
			}
		}

		return 0;
	}

	private int[][] createSumMatrix(int[][] matrix) {
		int[][] sums = new int[matrix.length][matrix[0].length];
		sums[0][0] = matrix[0][0];

		for(int row= 1; row < matrix.length; row++) {
			sums[row][0]= sums[row-1][0] + matrix[row][0];
		}

		for(int col=1; col< matrix[0].length; col++) {
			sums[0][col] = sums[0][col-1] + matrix[0][col];
		}

		for(int  row = 1; row< matrix.length; row++) {
			for(int col = 1; col< matrix[0].length; col++) {
				sums[row][col] = matrix[row][col] +
						sums[row-1][col] +
						sums[row][col-1]-
						sums[row-1][col-1];

			}
		}
		return sums;
	}


	// Maximize Expression
	public int maximizeExpression(int[] array) {
		if(array.length < 4) {
			return 0;
		}

		List<Integer> maxOfA = new ArrayList<Integer>(Arrays.asList(array[0]));
		List<Integer> maxOfAMinusB = new ArrayList<Integer>
		(Arrays.asList(Integer.MIN_VALUE));

		List<Integer> maxOfAMinusBPlusC = new ArrayList<Integer>
		(Arrays.asList(Integer.MIN_VALUE, Integer.MIN_VALUE));

		List<Integer> maxOfAMinusBPlusCMinusD = new ArrayList<Integer>
		(Arrays.asList(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE));

		for(int i= 1; i< array.length; i++) {
			maxOfA.add(Math.max(array[i], maxOfA.get(i-1)));
		}

		for(int i= 1; i< array.length; i++) {
			int currentMax = Math.max(maxOfAMinusB.get(i-1), maxOfA.get(i-1) - array[i]);
			maxOfAMinusB.add(currentMax);
		}

		for(int i= 2; i< array.length; i++) {
			int currentMax = Math.max(maxOfAMinusBPlusC.get(i-1), maxOfAMinusB.get(i-1) + array[i]);
			maxOfAMinusBPlusC.add(currentMax);
		}

		for(int i=3; i< array.length; i++) {
			int currentMax = Math.max(maxOfAMinusBPlusCMinusD.get(i-1),
					maxOfAMinusBPlusC.get(i-1) - array[i]);
			maxOfAMinusBPlusCMinusD.add(currentMax);
		}
		return maxOfAMinusBPlusCMinusD.get(maxOfAMinusBPlusCMinusD.size()-1);
	}
}
