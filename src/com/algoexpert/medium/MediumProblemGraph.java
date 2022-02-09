package com.algoexpert.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MediumProblemGraph {

	// Single Cycle
	public static boolean hasSingleCycle(int[] array) {
		int numberOfElementsVisited = 0;
		int currentIndex = 0;

		while(numberOfElementsVisited < array.length) {
			if(numberOfElementsVisited > 0 && currentIndex == 0) {
				return false;
			}

			numberOfElementsVisited++;
			currentIndex = getNextIndex(currentIndex, array);
		}
		return currentIndex == 0;

	}

	private static int getNextIndex(int currentIndex, int[] array) {
		// TODO Auto-generated method stub
		int jump = array[currentIndex];
		int nextIndex = (currentIndex+jump) % array.length;
		int nextValue;
		if(nextIndex >= 0) {
			nextValue = nextIndex;
		} else {
			nextValue = nextIndex + array.length;
		}
		return nextValue;

	}

	// Cycle in Graph
	public boolean cycleInGraph(int[][] edges) {
		int numberOfNodes = edges.length;

		boolean[] visited = new boolean[numberOfNodes];
		boolean[] currentlyInStack = new boolean[numberOfNodes];

		Arrays.fill(visited, false);
		Arrays.fill(currentlyInStack, false);

		for(int i=0; i < numberOfNodes; i++) {
			if(visited[i]) {
				continue;
			}
			boolean containsCycle = isNodeCycle(i, edges, visited, currentlyInStack);
			if(containsCycle) {
				return true;
			}
		}

		return false;

	}

	private boolean isNodeCycle(int i, int[][] edges, boolean[] visited, boolean[] currentlyInStack) {
		visited[i] = true;
		currentlyInStack[i] = true;
		boolean containsCycle = false;

		int[] neighbours = edges[i];
		for(int neighbour : neighbours) {
			if(!visited[neighbour]) {
				containsCycle = isNodeCycle(neighbour, edges, visited, currentlyInStack);
			}
			if(containsCycle) {
				return true;
			} else if(currentlyInStack[neighbour]) {
				return true;
			}
		}
		currentlyInStack[i] = false;
		return false;
	}

	//RiverSizes
	public static List<Integer> riverSizes(int[][] matrix) {
		List<Integer> sizes = new ArrayList<>();
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		for(int i= 0; i< matrix.length; i++) {
			for(int j=0; j < matrix[0].length; j++) {
				if(visited[i][j]) {
					continue;
				}
				traverseNode(i, j, matrix, visited, sizes);
			}
		}
		return sizes;

	}

	private static void traverseNode(int i, int j, int[][] matrix, boolean[][] visited, List<Integer> sizes) {
		// TODO Auto-generated method stub
		int currentRiverSize = 0;
		Stack<Integer[]> stack = new Stack<>();
		stack.push(new Integer[] {i, j});

		while(!stack.isEmpty()) {
			Integer[] currentNode = stack.pop();
			i = currentNode[0];
			j = currentNode[1];

			if(visited[i][j]) {
				continue;
			}
			visited[i][j] = true;
			if(matrix[i][j] == 0) {
				continue;
			}
			currentRiverSize++;
			List<Integer[]> unvisitedNeighbours = getUnvisitedNeighbours(i, j, matrix, visited);
			for(Integer[] neighbour : unvisitedNeighbours) {
				stack.add(neighbour);
			}
		}
		if(currentRiverSize > 0) {
			sizes.add(currentRiverSize);
		}
	}

	private static List<Integer[]> getUnvisitedNeighbours(int i, int j, int[][] matrix, boolean[][] visited) {
		// TODO Auto-generated method stub
		List<Integer[]> unvisitedNeighbours = new ArrayList<>();

		if(i>0 && !visited[i-1][j]) {  // check top
			unvisitedNeighbours.add(new Integer[] {i-1, j});
		}

		if(i<matrix.length-1 && !visited[i+1][j]) {  // check below
			unvisitedNeighbours.add(new Integer[] {i+1, j});
		}

		if(j>0 && !visited[i][j-1]) { // check left
			unvisitedNeighbours.add(new Integer[] {i, j-1});
		}

		if(j<matrix[0].length-1 && !visited[i][j+1]) {  // check right
			unvisitedNeighbours.add(new Integer[] {i, j+1});
		}
		return unvisitedNeighbours;
	}

	//Remove islands
	public int[][] removeIslands(int[][] matrix) {
		boolean[][] onesConnectedToBorder = new boolean[matrix.length][matrix[0].length];
		for(int i=0; i< matrix.length; i++) {
			onesConnectedToBorder[i][matrix[0].length-1] = false;
		}

		for(int row = 0; row< matrix.length; row++) {
			for(int col = 0; col < matrix[row].length; col++) {

				boolean rowBorder = row == 0 || row == matrix.length-1;
				boolean colBorder = col == 0 || col == matrix[0].length-1;
				boolean isBorder = rowBorder || colBorder;

				if(!isBorder) {
					continue;
				}

				if(matrix[row][col] != 1) {
					continue;
				}
				findOnesConnectedToBorder(matrix, row, col, onesConnectedToBorder);
			}
		}
		for(int row = 1; row< matrix.length-1; row++) {
			for(int col=1; col < matrix[row].length-1; col++) {
				if(onesConnectedToBorder[row][col]) {
					continue;
				}
				matrix[row][col] = 0;
			}
		}
		return matrix;
	}

	private void findOnesConnectedToBorder(int[][] matrix, int row, int col, boolean[][] onesConnectedToBorder) {
		// TODO Auto-generated method stub
		Stack<Integer[]> stack = new Stack<>();
		stack.push(new Integer[] {row, col});

		while(stack.size() > 0) {
			Integer[] currentPosition = stack.pop();
			int i = currentPosition[0];
			int j= currentPosition[1];

			if(onesConnectedToBorder[i][j]) {
				continue;
			}
			onesConnectedToBorder[i][j] = true;
			if(matrix[i][j] != 1) {
				continue;
			}

			//			var neighbours = getNeighbours(matrix, i, j);
			//			for(Integer[] neighbour: neighbours) {
			//				stack.add(neighbour);
			//			}

			List<Integer[]> unvisitedNeighbours = getUnvisitedNeighbours(i, j, matrix, onesConnectedToBorder);
			for(Integer[] neighbour : unvisitedNeighbours) {
				stack.add(neighbour);
			}
		}
	}

	private Integer[][] getNeighbours(int[][] matrix, int row, int col) {
		Integer numRows = matrix.length;
		Integer numCols = matrix[row].length;
		ArrayList<Integer[]> temp = new ArrayList<>();


		if(row -1 >=0) {
			temp.add(new Integer[] {row-1, col});
		}

		if(row +1 < numRows) {
			temp.add(new Integer[] {row+1, col});
		}

		if(col-1 >= 0) {
			temp.add(new Integer[] {row, col-1});
		}

		if(col +1 < numCols) {
			temp.add(new Integer[] {row, col+1});
		}

		Integer[][] neighbours = new Integer[temp.size()][2];
		for(Integer i=0; i< temp.size(); i++) {
			neighbours[i] = temp.get(i);
		}
		return neighbours;

	}


	// Youngest Common Ancestor
	public static AncestralTree getYoungestCommonAncestor(
			AncestralTree topAncestor, AncestralTree descendantOne, AncestralTree descendantTwo) {

		int depthOne = getDescendantDepth(descendantOne, topAncestor);
		int depthTwo = getDescendantDepth(descendantTwo, topAncestor);

		if(depthOne > depthTwo) {
			return backtrackAncestralTree(descendantOne, descendantTwo, depthOne-depthTwo);
		} else {
			return backtrackAncestralTree(descendantTwo, descendantOne, depthTwo-depthOne);
		}
	}



	private static AncestralTree backtrackAncestralTree(AncestralTree lower, AncestralTree higher,
			int diff) {
		// TODO Auto-generated method stub
		while(diff > 0) {
			lower = lower.ancestor;
			diff--;
		}

		while(lower != higher) {
			lower = lower.ancestor;
			higher = higher.ancestor;
		}
		return higher;
	}

	private static int getDescendantDepth(AncestralTree descendant, AncestralTree topAncestor) {
		// TODO Auto-generated method stub
		int depth = 0;
		while(descendant != topAncestor) {
			depth++;
			descendant = descendant.ancestor;
		}
		return depth;
	}



	static class AncestralTree {
		public char name;
		public AncestralTree ancestor;

		AncestralTree(char name) {
			this.name = name;
			this.ancestor = null;
		}

		// This method is for testing only.
		void addAsAncestor(AncestralTree[] descendants) {
			for (AncestralTree descendant : descendants) {
				descendant.ancestor = this;
			}
		}
	}

}
