package com.algoexpert.sort;

public class MergeSortUsingAuxilary {


	public static int[] mergeSort(int[] array) {
		// Write your code here.
		if(array.length <=1) {
			return array;
		}
		int[] auxiliaryArray = array.clone();
		mergeSort(array, 0, array.length -1, auxiliaryArray);
		return array;
	}

	public static void mergeSort(int[] mainArray, int start, int end, int[] auxiliaryArray) {
		if(start >= end) {
			System.out.println(start + " | " + "-" + " | " + end);
			return;
		}
		int middle = (start + end)/2;
		System.out.println(start + " | " + middle + " | " + end);
		mergeSort(auxiliaryArray, start, middle, mainArray);
		mergeSort(auxiliaryArray, middle+1, end, mainArray);
		doMerge(mainArray, start, middle, end, auxiliaryArray);
	}

	public static void doMerge(int[] mainArray, int start, int middle, int end, int[] auxiliaryArray){
		int i = start;
		int j = middle+1;
		int k = start;

		while(i <= middle && j<= end) {
			if(auxiliaryArray[i] <= auxiliaryArray[j]) {
				mainArray[k++] = auxiliaryArray[i++];
			} else {
				mainArray[k++] = auxiliaryArray[j++];
			}
		}
		while(i <= middle) {
			mainArray[k++] = auxiliaryArray[i++];
		}

		while(j<= end) {
			mainArray[k++] = auxiliaryArray[j++];
		}
	}


	public static void main(String[] args) {
		int[] expected = {2, 3, 5, 5, 6, 8, 9};
		//		int[] input = {8, 5, 2, 9, 5, 6, 3};
		int[] input = {100,20,15,30,5,75,40};
		mergeSort(input);
	}

}
