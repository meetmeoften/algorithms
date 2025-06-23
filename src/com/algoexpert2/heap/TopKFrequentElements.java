package com.algoexpert2.heap;

import java.util.HashMap;
import java.util.Map;

public class TopKFrequentElements {


	public int[] topKFrequent(int[] nums, int k) {


		Map<Integer, Integer> map = new HashMap<>();
		for(int num: nums) {
			map.put(num,  map.getOrDefault(num, 0)+1);
		}

		int i= 0;
		int[] arr = new int[map.size()];
		for(int key :map.keySet()) {
			arr[i++] = key;
		}


		int low = 0;
		int high = map.size()-1;

		return null;


	}

	private void quickSelect(Map<Integer, Integer> map, int[] arr, int low, int high) {
		int idx = low;
		int pivot = high;

		for(int i= low; i < high; i++) {
			if(map.get(i) < map.get(pivot )) {
				swap(arr, idx, i);
				i++;
			}
		}

		swap(arr, idx, pivot);


	}

	private void swap(int[] nums,int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

}
