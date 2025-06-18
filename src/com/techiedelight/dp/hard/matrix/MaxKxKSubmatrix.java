package com.techiedelight.dp.hard.matrix;

public class MaxKxKSubmatrix {

    public static int findMaxKxKSum(int[][] mat, int K) {
        int M = mat.length;
        int N = mat[0].length;

        if (K > M || K > N) {
            System.out.println("K is larger than matrix dimensions.");
            return -1;
        }

        int[][] prefix = new int[M + 1][N + 1];

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                prefix[i][j] = mat[i - 1][j - 1]
                        + prefix[i - 1][j]
                        + prefix[i][j - 1]
                        - prefix[i - 1][j - 1];
            }
        }

        // Step 2: Evaluate KxK sums using prefix sums
        int maxSum = Integer.MIN_VALUE;
        int maxRow = -1, maxCol = -1;

        for (int i = K; i <= M; i++) {
            for (int j = K; j <= N; j++) {
                int total = prefix[i][j]
                        - prefix[i - K][j]
                        - prefix[i][j - K]
                        + prefix[i - K][j - K];

                if (total > maxSum) {
                    maxSum = total;
                    maxRow = i - K;
                    maxCol = j - K;
                }
            }
        }

        System.out.println("Top-left corner of max sum KxK submatrix: (" + maxRow + ", " + maxCol + ")");
        return maxSum;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int K = 2;

        int max = findMaxKxKSum(matrix, K);
        System.out.println("Maximum sum of " + K + "x" + K + " submatrix: " + max);
    }
}
