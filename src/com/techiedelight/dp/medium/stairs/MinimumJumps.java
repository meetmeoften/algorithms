package com.techiedelight.dp.medium.stairs;

public class MinimumJumps {

    public static int jump(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int jumps = 0;
        int max = 0;
        int curr = 0;
        for (int i = 0; i < nums.length; i++) {
            if (curr < i) {
                curr = max;
                jumps++;
            }
            max = Math.max(max, i + nums[i]);
        }
        return jumps;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(jump(nums));
    }
}
