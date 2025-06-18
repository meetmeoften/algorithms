package com.techiedelight.dp.medium.stairs;

public class MinSquaresSum {

    public static int minSquares(int n) {
        if(n ==0) return 0;

        int minCount = Integer.MAX_VALUE;

        for(int i=1; i * i <=n; i++) {
            int square = i*i;
            int count  = minSquares(n - square);
            minCount = Math.min(count+1, minCount);
        }
        return minCount;
    }

    public static void main(String[] args) {
        int n = 2;
        System.out.println("Minimum number of squares for " + n + ": " + minSquares(n));
    }
}
