package com.algoexpert.sort;

public class BubbleSort {


	public static int[] bubbleSort(int[] array) {
		// Write your code here.
		for(int i=0; i< array.length;i++) {
			for(int j=0; j<array.length-1-i; j++) {
				if(array[j] > array[j+1]) {
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
		return array;
	}

	public static void main(String[] args) {
		int[] expected = {2, 3, 5, 5, 6, 8, 9};
		int[] input = {8, 5, 2, 9, 5, 6, 3};
		bubbleSort(input);
	}
}
