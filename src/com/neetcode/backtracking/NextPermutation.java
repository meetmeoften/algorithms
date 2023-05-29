package com.neetcode.backtracking;

public class NextPermutation {

	public void nextPermutation(int[] nums) {
		int i = nums.length - 2;
		while (i >= 0 && nums[i] >= nums[i+1]) {
			i--;
		}
		if (i >= 0){
			int j = nums.length - 1;
			while(j >= 0 && nums[j] <= nums[i]) {
				j--;
			}
			swap(nums, i, j);
		}
		reverse(nums, i + 1);
	}

	public void swap(int[] nums, int i, int j){
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public void reverse(int[] nums, int start){
		int end = nums.length -1;
		while(start < end){
			swap(nums, start++, end--);
		}
	}

	public void nextPermutation2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}

		int n = nums.length - 1, idx = -1;

		for (int i = n - 1; i >= 0; i--) {
			if (nums[i] < nums[i + 1]) {
				idx = i;
				break;
			}
		}

		if (idx == -1) {
			reverseArray2(nums, 0, n);
			return;
		}

		for (int i = n; i >= 0; i--) {
			if (nums[i] > nums[idx]) {
				swap2(nums, i, idx);
				break;
			}
		}

		reverseArray2(nums, idx + 1, n);
	}

	private void reverseArray2(int[] nums, int start, int end) {
		while (start < end) {
			swap(nums, start, end);
			++start;
			--end;
		}
	}

	private void swap2(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String[] args) {
		new NextPermutation().nextPermutation(new int[] {1, 3, 2});
		new NextPermutation().nextPermutation2(new int[] {1,3, 2 });
	}

}
