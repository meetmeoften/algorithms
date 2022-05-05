package com.algoexpert.matrix;

public class GoldManDP {

	public static Integer optimalPath(int[][] grid){
		// Todo: Implement optimalPath
		int[][] ways = new int[grid.length][grid[0].length];
		int rowStart = grid.length-1;

		for(int i= rowStart; i >= 0; i--) {
			for(int j= 0; j < grid[0].length; j++) {

				if(j == 0 && i== rowStart) {
					ways[i][j] = grid[i][j];
				} else if( j== 0) {
					ways[i][j] = grid[i][j] + ways[i+1][j] ;
				} else if (i == rowStart) {
					ways[i][j] = grid[i][j] +
							ways[i][j-1];
				} else {
					ways[i][j] = grid[i][j] + Math.max (ways[i+1][j], ways[i][j-1]);
				}
			}
		}

		return ways[0][grid[0].length-1];
	}

	public static void main(String[] args) {
		int[][] edges = new int[][] { {1, 1, 1 }, {1, 0, 1 }, { 2, 0, 0}};
		optimalPath(edges);
	}

}
