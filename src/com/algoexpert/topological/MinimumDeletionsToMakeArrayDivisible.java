package com.algoexpert.topological;

import java.util.Arrays;

public class MinimumDeletionsToMakeArrayDivisible {

    public int minOperations(int[] nums, int[] numsDivide) {
        // Step 1: Find gcd of numsDivide
        int gcd = numsDivide[0];
        for (int num : numsDivide) {
            gcd = findGCD(gcd, num);
        }
        // Step 2: Sort nums
        Arrays.sort(nums);
        // Step 3: Find smallest divisor of gcd
        for (int i = 0; i < nums.length; i++) {

            if (gcd % nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private int findGCD(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 2, 4, 3};
        int[] numsDivide = {9, 6, 9, 3, 15};
        MinimumDeletionsToMakeArrayDivisible sol = new MinimumDeletionsToMakeArrayDivisible();
        System.out.println(sol.minOperations(nums, numsDivide));
    }
}
