package com.neetcode.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainsDuplicate2 {

	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return false;
		}

		Map<Integer, Integer> hm = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			if (hm.containsKey(nums[i])) {
				if (Math.abs(hm.get(nums[i]) - i) <= k) {
					return true;
				}
			}

			hm.put(nums[i], i);
		}

		return false;
	}

	public static boolean containsNearbyDuplicate2(int[] nums, int k) {
		if (k == 0) {
			return false;
		}

		Set<Integer> slidingWindow = new HashSet<>();
		for (int index = 0; index < nums.length; index++) {
			if (slidingWindow.contains(nums[index])) {
				return true;
			}
			if (index >= k) {
				slidingWindow.remove(nums[index - k]);
			}
			slidingWindow.add(nums[index]);
		}
		return false;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 1 };
		containsNearbyDuplicate(nums, 3);
		containsNearbyDuplicate2(nums, 3);
	}

}
