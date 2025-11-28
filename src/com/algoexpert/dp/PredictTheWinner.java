package com.algoexpert.dp;

public class PredictTheWinner {

    public boolean PredictTheWinner(int[] nums) {
        int value =  helper(nums, 0, nums.length - 1) ;
        return value >= 0;
    }

    private int helper(int[] nums, int left, int right) {
        if (left == right) return nums[left];

        int pickLeft = nums[left] - helper(nums, left + 1, right);
        int pickRight = nums[right] - helper(nums, left, right - 1);

        return Math.max(pickLeft, pickRight);
    }

    public static void main(String[] args) {
        PredictTheWinner winner = new PredictTheWinner();
        winner.PredictTheWinner(new int[]{1, 5});
    }
}
