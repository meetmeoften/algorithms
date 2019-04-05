package com.hackerrank.array;

import java.io.IOException;

public class TwoStrings {

	// Complete the twoStrings function below.
	public static boolean twoStrings(String a, String b) {
		boolean w1[] = new boolean[26];
		boolean w2[] = new boolean[26];

		for (char c : a.toCharArray()) {
			w1[c - 'a'] = true;
		}
		for (char c : b.toCharArray()) {
			w2[c - 'a'] = true;
		}

		for (int i = 0;i < 26;i++) {
			if (w1[i] && w2[i]) {
				return true;
			}
		}
		return false;

		//		char[] bCharArray = b.toCharArray();
		//		char[] aCharArray = a.toCharArray();
		//
		//		Arrays.sort(bCharArray);
		//		for(char c : aCharArray){
		//			if( Arrays.binarySearch(bCharArray , c) >= 0){
		//
		//				System.out.println("YES");
		//				continue z;
		//			}
		//		}
		//		System.out.println("NO");

	}


	public static void main(String[] args) throws IOException {

	}

}
