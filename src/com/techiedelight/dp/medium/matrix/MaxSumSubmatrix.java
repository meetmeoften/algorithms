package com.techiedelight.dp.medium.matrix;

public class MaxSumSubmatrix {
    // Function to find the maximum sum submatrix
    public static int maxSumSubmatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;  // Initialize max sum to a very low value

        // Variables to store the coordinates of the maximum sum submatrix
        int startRow = 0, startCol = 0, endRow = 0, endCol = 0;

        // Iterate over all pairs of rows
        for (int top = 0; top < rows; top++) {
            int[] temp = new int[cols];  // Temporary array to store column sums

            for (int bottom = top; bottom < rows; bottom++) {
                // Calculate column sums between row `top` and row `bottom`
                for (int col = 0; col < cols; col++) {
                    temp[col] += matrix[bottom][col];
                }
                // Now find the maximum sum subarray in the `temp` array
                int[] result = kadane1(temp);
                int currentSum = result[0];
                int left = result[1];
                int right = result[2];
                //maxSum = Math.max(maxSum, currentSum);
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    startRow = top;
                    endRow = bottom;
                    startCol = left;
                    endCol = right;
                }
            }
        }
        // Print the maximum sum submatrix
        printSubmatrix(matrix, startRow, startCol, endRow, endCol);

        return maxSum;
    }

    // Kadane's algorithm to find the maximum sum subarray in 1D
    public static int kadane(int[] arr) {
        int max = arr[0];
        int currMax = arr[0];

        for (int i = 1; i < arr.length; i++) {
            currMax = Math.max(arr[i], currMax + arr[i]);
            max = Math.max(max, currMax);
        }

        return max;
    }

    // Kadane's algorithm to find the maximum sum subarray in a 1D array
    private static int[] kadane1(int[] arr) {
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        int start = 0;
        int end = 0;
        int tempStart = 0;
        int left = 0;
        int right = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxEndingHere + arr[i]) {
                maxEndingHere = arr[i];
                tempStart = i;
            } else {
                maxEndingHere += arr[i];
            }

            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
            }
        }

        left = start;
        right = end;
        return new int[]{maxSoFar, left, right};
    }

    // Method to print the submatrix
    private static void printSubmatrix(int[][] matrix, int startRow, int startCol, int endRow, int endCol) {
        System.out.println("Maximum sum submatrix is:");
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Example matrix
//        int[][] matrix = {
//                {1, 2, -1, -4, -20},
//                {-8, -3, 4, 2, 1},
//                {3, 8, 10, 1, 3},
//                {-4, -1, 1, 7, -6}
//        };

        int[][] matrix = {
                {-5, -6},
                {9, 7}
        };

        System.out.println("Maximum Sum of Submatrix: " + maxSumSubmatrix(matrix));
    }
}

