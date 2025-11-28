package com.google;

public class CheckWordPlacedInCrossWord {

    int m, n;
    String word;

    public boolean placeWordInCrossword(char[][] board, String word) {
        this.m = board.length;
        this.n = board[0].length;
        this.word = word;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Try all 4 directions from (i, j) if starting here is valid
                if ((j == 0 || board[i][j - 1] == '#') && check(board, i, j, 0, 1)) return true;   // → right
                if ((j == n - 1 || board[i][j + 1] == '#') && check(board, i, j, 0, -1)) return true; // ← left
                if ((i == 0 || board[i - 1][j] == '#') && check(board, i, j, 1, 0)) return true;   // ↓ down
                if ((i == m - 1 || board[i + 1][j] == '#') && check(board, i, j, -1, 0)) return true; // ↑ up
            }
        }
        return false;
    }

    private boolean check(char[][] board, int i, int j, int di, int dj) {
        int k = 0;
        int len = word.length();
        // Traverse the word's letters
        while (k < len) {
            int x = i + di * k;
            int y = j + dj * k;
            if (x < 0 || x >= m || y < 0 || y >= n) return false;
            if (board[x][y] == '#') return false;
            if (board[x][y] != ' ' && board[x][y] != word.charAt(k)) return false;
            k++;
        }
        // Ensure the cell after the word is blocked or out of bounds
        int x2 = i + di * len;
        int y2 = j + dj * len;
        return (x2 < 0 || x2 >= m || y2 < 0 || y2 >= n || board[x2][y2] == '#');
    }

    public static void main(String[] args) {
        char[][] board = {
                {'#', ' ', '#'},
                {' ', ' ', '#'},
                {'#', 'c', ' '}
        };
        String word = "abc";
        boolean result = new CheckWordPlacedInCrossWord().placeWordInCrossword(board, word);
        System.out.println("Can the word \"" + word + "\" be placed in the crossword? " + result);
    }
}
