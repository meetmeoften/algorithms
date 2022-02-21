package com.algoexpert.recursion;

public class NQueens {

	public int nonAttackingQueens(int n) {
		// Write your code here.
		int[] columnPlacements = new int[n];
		return getNumberOfNonAttackingQueenPlacments(0, columnPlacements, n);
	}

	public int getNumberOfNonAttackingQueenPlacments(int row, int[] columnPlacements,
			int boardSize) {
		if(row == boardSize) {
			return 1;
		}

		int validPlacements = 0;
		for(int col = 0; col < boardSize; col++) {
			if(isNonAttackingPlacement(row, col, columnPlacements)) {
				columnPlacements[row] = col;
				validPlacements += getNumberOfNonAttackingQueenPlacments(row+1, columnPlacements, boardSize);
			}
		}
		return validPlacements;
	}


	public boolean isNonAttackingPlacement(int row, int col, int[] columnPlacements) {

		for(int i= 0; i < row; i++) {
			int colToCheck= columnPlacements[i];
			boolean sameColumn = (colToCheck == col);
			boolean onDiagonal = Math.abs(colToCheck - col) == (row - i);
			if(sameColumn || onDiagonal) {
				return false;
			}
		}
		return true;
	}

}
