package com.algoexpert.graph;

public class CheckMoveLegal {

	private static final int[][] DIRS = new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 },
		{ 1, 0 }, { 1, 1 } };

		public static boolean checkMove(char[][] board, int rMove, int cMove, char color) {

			int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };

			for (int i = 0; i < 8; i++) {
				if (isLegal(board, rMove, cMove, color, i, directions, 1)) {
					return true;
				}
			}

			return false;

		}

		private static boolean isLegal(char[][] board, int rMove, int cMove, char color, int direction, int[][] directions,
				int length) {

			int r = rMove + directions[direction][0];
			int c = cMove + directions[direction][1];

			length = length + 1;

			if (r >= 8 || c >= 8) {
				return false;
			}

			if (r < 0 || c < 0) {
				return false;
			}

			if (board[r][c] == color) {
				if (length >= 3) {
					return true;
				} else {
					return false;
				}
			} else {
				if (board[r][c] == '.') {
					return false;
				} else {
					return isLegal(board, r, c, color, direction, directions, length);
				}
			}
		}

		// ----------------------------

		public static boolean checkMove2(char[][] board, int rMove, int cMove, char color) {
			final char opposite = (color == 'W' ? 'B' : (color == 'B' ? 'W' : ' '));
			if (opposite == ' ' || !find(board, rMove, cMove, '.')) {
				return false;
			}
			for (int[] dir : DIRS) {
				int rNext = rMove + dir[0];
				int cNext = cMove + dir[1];
				// For a good line, the "next" cell must be opposite color (at least one).
				if (find(board, rNext, cNext, opposite)) {
					rNext += dir[0];
					cNext += dir[1];
					// Skip past additional cells of opposite color.
					while (find(board, rNext, cNext, opposite)) {
						rNext += dir[0];
						cNext += dir[1];
					}
					// Finally, we must have an endpoint of our color to have made a good line.
					if (find(board, rNext, cNext, color)) {
						// We found it - we need only one, so return true.
						return true;
					}
				}
			}
			// No good lines in any direction, return false.
			return false;
		}

		private static final boolean find(char[][] board, final int r, final int c, final char target) {
			return (r >= 0 && r < board.length && c >= 0 && c < board[r].length && board[r][c] == target);
		}

		public static void main(String[] args) {
			char[][] board = { { '.', '.', '.', 'B', '.', '.', '.', '.' }, { '.', '.', '.', 'W', '.', '.', '.', '.' },
					{ '.', '.', '.', 'W', '.', '.', '.', '.' }, { '.', '.', '.', 'W', '.', '.', '.', '.' },
					{ 'W', 'B', 'B', '.', 'W', 'W', 'W', 'B' }, { '.', '.', '.', 'B', '.', '.', '.', '.' },
					{ '.', '.', '.', 'B', '.', '.', '.', '.' }, { '.', '.', '.', 'W', '.', '.', '.', '.' } };

			checkMove(board, 4, 3, 'B');
			checkMove2(board, 4, 3, 'B');

		}

}
