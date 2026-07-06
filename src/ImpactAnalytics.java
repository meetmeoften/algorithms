/**
 * Given an integer array nums, find the continuous subarray (containing at least one element) which has the largest sum, and return that sum.
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Input: [-1, -2, -3, -4]
 * Output: -1
 * Input: [1, 2, 3, 4]
 * Output: 10
 */

public class ImpactAnalytics {


    public static void main(String[] args) {
        ImpactAnalytics a = new ImpactAnalytics();
        System.out.println(a.findLargestSum(new int[]{-2,1,-3,4,-1,2,1,-5,4}));

    }

    public int findLargestSum(int[] arr) {
        int currMax = arr[0];
        int maxSum = arr[0];
        for(int i = 1; i < arr.length; i++){
            currMax = Math.max(currMax, arr[i] + currMax);
            maxSum = Math.max(maxSum, currMax);
        }
        return maxSum;
    }


}
