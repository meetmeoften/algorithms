package com.test.general;

import java.util.Arrays;

public class RemoveDuplicates {

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

	public static int[] removeDuplicates(int[] A) {
		if (A.length < 2) {
			return A;
		}
		int j = 0;
		int i = 1;
		while (i < A.length) {
			//			System.out.println("Ai " + A[i] + "   Aj "  + A[j]);
			if (A[i] == A[j]) {
				i++;
			} else {
				j++;
				A[j] = A[i];
				System.out.println("Ai " + A[i] + "   Aj "  + A[j]);
				i++;
			}
		}
		int[] B = Arrays.copyOf(A, j + 1);
		return B;
	}


	public static void removeDuplicatesEasy(int[] A) {
		Integer[] arr = new Integer[10];
		for(int value : A) {
			if(arr[value] == null) {
				arr[value] = value;
			}
		}
		System.out.println(arr);
	}

	public static void main(String[] args) {
		int[] arr = { 1, 1, 1, 2, 2, 3, 3 };
		//		arr =removeDuplicates(arr);
		System.out.println(arr);
		removeDuplicatesEasy(arr);
	}

}
