package com.algoexpert.arrays;

public class RemoveOneElementStrictlyIncreasing {

    public boolean canBeIncreasing(int[] nums) {
        int removed = 0;
        for(int i=1; i < nums.length; i++) {
            if(nums[i] <= nums[i-1]) {
                removed++;
            }
            if(removed > 1) return false;

            if(i > 1 && nums[i] <= nums[i-2]) {
                nums[i] = nums[i-1];
            }
        }

        return true;
    }

    public static void main(String[] args) {
        RemoveOneElementStrictlyIncreasing increasingTriplet = new RemoveOneElementStrictlyIncreasing();
        int[] nums = new int[]{2,3,1,2};
        System.out.println(increasingTriplet.canBeIncreasing(nums));
    }
}
