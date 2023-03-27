package com.algoexpert2.strings;

public class PalindromeCheck {

	public static boolean isPalindrome(String str) {
		// Write your code here.
		int leftIdx = 0;
		int rightIdx = str.length() - 1;

		while (leftIdx < rightIdx) {
			if (str.charAt(leftIdx) != str.charAt(rightIdx)) {
				return false;
			}
			leftIdx++;
			rightIdx--;
		}

		return true;
	}

	public static void main(String[] args) {
		isPalindrome("ababa");
	}

}
