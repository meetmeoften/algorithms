package com.techiedelight.dp.medium.partition;

public class CoinChange {

    public static int findMinCoins(int[] arr, int i, int target) {
        if (target == 0) {
            return 0;
        }
        if (target < 0) {
            return (int) Math.pow(10, 9);
        }
        if (i < 0) {
            return (int) Math.pow(10, 9);
        }
        int exclude = findMinCoins(arr, i - 1, target);
        int include = 1 + findMinCoins(arr, i, target - arr[i]);

        return Math.min(exclude, include);


    }

    public static int minimumElements(int[] arr, int T) {
        int n = arr.length;
        int ans = findMinCoins(arr, n - 1, T);
        if (ans >= (int) Math.pow(10, 9)) {
            return -1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] input = {2, 5, 7};
        System.out.println(minimumElements(input, 9));
    }
}
