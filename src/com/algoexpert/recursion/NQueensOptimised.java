package com.algoexpert.recursion;

public class NQueensOptimised {

    static int N;

    public static void solveNQueens(int n) {
        N = n;
        char[][] board = new char[N][N];

        // initialize board
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = '.';
            }
        }

        backtrack(board, 0);
    }

    private static boolean backtrack(char[][] board, int col) {
        // base case: all queens placed
        if (col == N) {
            printBoard(board);
            return true; // return false if you want ALL solutions
        }

        for (int row = 0; row < N; row++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                if (backtrack(board, col + 1)) {
                    return true; // remove for all solutions
                }
                board[row][col] = '.'; // backtrack
            }
        }
        return false;
    }

    private static boolean isSafe(char[][] board, int row, int col) {
        // check row on left
        for (int j = 0; j < col; j++) {
            if (board[row][j] == 'Q') return false;
        }

        // upper-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // lower-left diagonal
        for (int i = row, j = col; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 'Q') return false;
        }

        return true;
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        solveNQueens(1);
    }
}
