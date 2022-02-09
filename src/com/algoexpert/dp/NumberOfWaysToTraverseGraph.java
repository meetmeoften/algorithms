package com.algoexpert.dp;

public class NumberOfWaysToTraverseGraph {

	public int numberOfWaysToTraverseGraph(int width, int height) {
		int[][] numberOfWays = new int[height+1][width+1];

		for(int i= 1; i < width+1; i++ ) {
			for(int j=1; j < height+1; j++) {
				if(i== 1 || j == 1) {
					numberOfWays[j][i] = 1;
				} else {
					numberOfWays[j][i] = numberOfWays[j][i-1] +
							numberOfWays[j-1][i];
				}
			}
		}

		return numberOfWays[height][width];
	}

	public int numberOfWaysToTraverseGraph2(int width, int height) {
		int[][] numberOfWays = new int[height][width];

		for(int i= 0; i < width; i++ ) {
			for(int j=0; j < height; j++) {
				if(i== 0 || j == 0) {
					numberOfWays[j][i] = 1;
				} else {
					numberOfWays[j][i] = numberOfWays[j][i-1] +
							numberOfWays[j-1][i];
				}
			}
		}

		return numberOfWays[height-1][width-1];
	}

	public static void main(String[] args) {
		int width = 4;
		int height = 3;
		int expected = 10;
		var actual = new NumberOfWaysToTraverseGraph().numberOfWaysToTraverseGraph(width, height);
	}

}
