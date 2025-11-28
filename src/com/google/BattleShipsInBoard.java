package com.google;

public class BattleShipsInBoard {

    public int countBattleships(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int result = 0;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X' && (!visited[i][j])) {
                    dfs(board, i, j, visited);
                    result++;
                }
            }
        }
        return result;
    }

    private void dfs(char[][] board, int i, int j, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j] || board[i][j] != 'X') {
            return;
        }

        visited[i][j] = true;
        dfs(board, i + 1, j, visited);
        dfs(board, i - 1, j, visited);
        dfs(board, i, j + 1, visited);
        dfs(board, i, j - 1, visited);

    }
}
