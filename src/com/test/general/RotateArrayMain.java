package com.test.general;

public class RotateArrayMain {

	public static void main(String[] args) {

		int nums[] = { 1, 2, 3, 4, 5, 6, 7, 8 };

		System.out.println(
				"Rotate array by shifting one elements by 1 and do it k times");
		int[] result1 = rotateBruteForce(nums, 2);
		System.out.println("Final rotated array :");
		printArray(result1);
		System.out.println();
		System.out.println("================================");
		System.out.println("Rotate array using extra space");

		int nums2[] = { 10, 20, 30, 40, 50, 60 };
		int[] result2 = rotateExtraSpace(nums2, 5);
		printArray(result2);
		System.out.println();
		System.out.println("================================");
		System.out.println("Rotate array most optimized approach");
		int nums3[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] result3 = rotateOptimized(nums3, 4);
		printArray(result3);
	}

	public static int[] rotateBruteForce(int[] nums, int k) {
		int n = nums.length;
		if (k > n) {
			k = k % n;
		}
		for (int i = 0; i < k; i++) {
			for (int j = n - 1; j > 0; j--) {
				// move each number by 1 place
				int temp = nums[j];
				nums[j] = nums[j - 1];
				nums[j - 1] = temp;
			}
			System.out.println("Array rotation after " + (i + 1) + " step");
			printArray(nums);
			System.out.println();
		}
		return nums;
	}

	public static int[] rotateExtraSpace(int[] nums, int k) {
		int n = nums.length;
		if (k > n) {
			k = k % n;
		}

		int[] result = new int[n];

		for (int i = 0; i < k; i++) {
			result[i] = nums[n - k + i];
		}

		int index = 0;
		for (int i = k; i < n; i++) {
			result[i] = nums[index++];
		}
		return result;
	}

	public static int[] rotateOptimized(int[] nums, int k) {
		int n = nums.length;
		if (k > n) {
			k = k % n;
		}
		nums = reverse(nums, 0, n - 1);
		nums = reverse(nums, 0, k - 1);
		nums = reverse(nums, k, n - 1);
		return nums;
	}

	public static int[] reverse(int[] nums, int start, int end) {

		while (start <= end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
		return nums;
	}

	public static void printArray(int[] arr) {
		for (int element : arr) {
			System.out.print(element + " ");
		}
	}

}