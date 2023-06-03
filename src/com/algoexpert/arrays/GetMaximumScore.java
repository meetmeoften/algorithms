package com.algoexpert.arrays;

public class GetMaximumScore {

	// Leetcode 1537

	public static int maxSum(int[] nums1, int[] nums2) {
		long s = 0;
		int i = 0, j = 0;
		long s1 = 0, s2 = 0;
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] < nums2[j]) {
				s1 = s1 + nums1[i];
				i++;
			} else if (nums1[i] > nums2[j]) {
				s2 = s2 + nums2[j];
				j++;
			} else {
				s = s + Math.max(s1, s2);
				// at this point whether we add nums1 or
				// nums2 to the sum it will not matter since value of nums1 and
				// nums2 are same at this point
				s = s + nums1[i];

				i++;
				j++;
				s1 = 0;
				s2 = 0;
			}
		}
		while (i < nums1.length) {
			s1 = s1 + nums1[i];
			i++;
		}
		while (j < nums2.length) {
			s2 = s2 + nums2[j];
			j++;
		}
		s = s + Math.max(s1, s2);
		return (int) (s % 1000000007);
	}

	public static void main(String[] args) {
		int[] nums1 = { 2, 4, 5, 8, 10 };
		int[] nums2 = { 4, 6, 8, 9 };

		maxSum(nums1, nums2);

	}

}
