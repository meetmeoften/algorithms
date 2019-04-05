package com.test.leetcode;

import java.util.HashMap;

public class TwoSum {

	public static int[] twoSum(int[] numbers, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] result = new int[2];
		for (int i = 0; i < numbers.length; i++) {
			if (map.containsKey(numbers[i])) {
				int index = map.get(numbers[i]);
				result[0] = index ;
				result[1] = i;
				break;
			} else {
				map.put(target - numbers[i], i);
			}
		}
		return result;
	}

	public static int removeDuplicatesNaive(int[] A) {
		if (A.length < 2) {
			return A.length;
		}
		int j = 0;
		int i = 1;
		while (i < A.length) {
			if (A[i] == A[j]) {
				i++;
			} else {
				j++;
				A[j] = A[i];
				i++;
			}
		}
		return j + 1;
	}

	public static void moveZeroes(int[] nums) {
		int m=-1;
		for(int i=0; i<nums.length; i++){
			if(nums[i]==0){
				if(m==-1 || nums[m]!=0){
					m=i;
				}
			}else{
				if(m!=-1){
					int temp = nums[i];
					nums[i]=nums[m];
					nums[m]=temp;
					m++;
				}
			}
		}
	}


	public static void main(String[] args) {

		int[] numbers=new int[]{2, 7, 11, 15};
		int target=9;

		twoSum(numbers, target);


		removeDuplicatesNaive(new int[]{1, 2, 2, 3});

		moveZeroes(new int[] {0, 1, 9, 0, 3, 0, 12});



	}

}
