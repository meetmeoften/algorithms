package com.algoexpert.graph.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeAndLadder {

	public static int snakesAndLadders(int[][] board) {
		int n = board.length;
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		boolean[][] visited = new boolean[n][n];
		visited[n - 1][0] = true;
		int steps = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				int curr = q.poll();
				if (curr == n * n) {
					return steps;
				}
				for (int i = 1; i <= 6; i++) {
					int newCell = curr + i;
					if (newCell > n * n) {
						break;
					}
					int[] newPos = findCoordinates(newCell, n);
					int r = newPos[0], c = newPos[1];
					if (visited[r][c]) {
						continue;
					}
					visited[r][c] = true;
					if (board[r][c] == -1) {
						q.offer(newCell);
					} else {
						q.offer(board[r][c]);
					}
				}
			}
			steps++;
		}

		return -1;
	}

	private static int[] findCoordinates(int pos, int n) {
		int r = n - ((pos - 1) / n) - 1;
		int c = (pos - 1) % n;
		if (r % 2 == n % 2) {
			return new int[] { r, n - c - 1 };
		} else {
			return new int[] { r, c };
		}
	}

	public static void main(String[] args) {
		int[][] board = { { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 },
				{ -1, 35, -1, -1, 13, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, 15, -1, -1, -1, -1 } };
		snakesAndLadders(board);
	}

}
