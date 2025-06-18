package com.techiedelight.dp.medium.partition;

public class SubsetSum {

    private static boolean isSubsetSum(int[] arr, int ind, int target) {
        if (target == 0) {
            return true;
        }

        if (target < 0) {
            return false;
        }
        if (ind == 0) {
            return arr[0] == target;
        }
        boolean exclude = isSubsetSum(arr, ind - 1, target);
//        boolean include = false;
//        if (arr[ind] <= target) {
//            include = isSubsetSum(arr, ind - 1, target - arr[ind]);
//        }
        boolean include = isSubsetSum(arr, ind - 1, target - arr[ind]);
        return include || exclude;
    }

    public static void main(String[] args) {
        int[] set = {3, 34, 4, 12, 5, 2};
        int sum = 13;
        int n = set.length;

        boolean result = isSubsetSum(set, n - 1, sum);
        System.out.println("Subset with sum " + sum + " exists? " + result);
    }


}
