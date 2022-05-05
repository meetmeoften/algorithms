package com.hackerrank.array;

import java.util.Arrays;
import java.util.Scanner;

public class DiagonalDiff {

	static int diagonalDifference(int[][] arr) {
		int leftSum = 0, rightSum = 0;
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			leftSum += arr[i][i];
			rightSum += arr[i][n - 1 - i];
		}
		return (Math.abs(leftSum - rightSum));
	}

	public static void leftRotation(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int a[] = new int[n];
		int b[] = new int[n];
		for(int a_i=0; a_i < n; a_i++){
			a[a_i] = in.nextInt();
		}
		for(int i=0;i<n;i++){
			if((i-k)<0) {
				b[n+i-k]=a[i];
			} else {
				b[i-k]=a[i];
			}
		}
		for(int i=0;i<n;i++){
			System.out.print(b[i]+" ");
		}
		System.out.println();
	}

	static int minimumSwaps(int[] array) {
		int n = array.length - 1;
		int minSwaps = 0;
		for (int i = 0; i < n; i++) {
			if (i < array[i] - 1) {
				swap(array, i, Math.min(n, array[i] - 1));
				minSwaps++;
				i--;
			}
		}
		return minSwaps;
	}

	static String twoStrings(String s1, String s2) {
		for(char c : "abcdefghijklmnopqrstuvwxyz".toCharArray())
		{
			if(s1.indexOf(c)>-1 && s2.indexOf(c)>-1) {
				return "YES";
			}
		}
		return "NO";
	}

	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}


	static int maxMin(int k, int[] arr) {
		Arrays.sort(arr);
		int min = 1000000000;
		int i = 0;
		for(i=0; (i+k-1)<arr.length; i++){
			if(arr[i+k-1] - arr[i] < min) {
				min = arr[i+k-1] - arr[i];
			}
		}
		System.out.println(i);
		return min;
	}


}
