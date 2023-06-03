package com.algoexpert.sort;

public class GlobalAndLocalInversions {

	public boolean isIdealPermutation(int[] nums) {
		int n = nums.length;
		int temp[] = new int[n];
		int global = 0, local = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] > nums[i + 1]) {
				local++;
			}
		}
		global = mergesort(nums, temp, 0, n - 1);
		if (local == global) {
			return true;
		}
		return false;
	}

	public int mergesort(int arr[], int temp[], int l, int r) {
		int mid = 0, inv = 0;
		if (r > l) {
			mid = (l + r) / 2;
			inv += mergesort(arr, temp, l, mid);
			inv += mergesort(arr, temp, mid + 1, r);
			inv += merge(arr, temp, l, mid + 1, r);
		}
		return inv;
	}

	public int merge(int arr[], int temp[], int l, int mid, int r) {
		int i = l, j = mid, k = l, inv = 0;
		while (i <= mid - 1 && j <= r) {
			if (arr[i] <= arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];
				inv += mid - i;
			}
		}
		while (i <= mid - 1) {
			temp[k++] = arr[i++];
		}
		while (j <= r) {
			temp[k++] = arr[j++];
		}
		for (i = l; i <= r; i++) {
			arr[i] = temp[i];
		}
		return inv;
	}


	public boolean isIdealPermutation2(int[] A) {
		int max = 0;

		for (int i = 0; i < A.length - 2; i++) {
			max = Math.max(max, A[i]);
			if (max > A[i + 2]) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		GlobalAndLocalInversions inv = new GlobalAndLocalInversions();
		inv.isIdealPermutation(new int[] { 1, 0, 2 });
		inv.isIdealPermutation2(new int[] { 1, 0, 2 });
	}

}
