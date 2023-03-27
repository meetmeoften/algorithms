package com.algoexpert2.strings;

public class LongestPalindromicSubstring {

	public static String longestPalindromicSubstring(String str) {
		int[] currentLongest = new int[]{0, 1};

		for(int i=1; i<str.length(); i++) {
			int[] odd = getLongestPalindromeFrom(str, i-1, i+1);
			int[] even = getLongestPalindromeFrom(str,i-1, i);

			int[] longest = odd[1] -odd[0]> even[1] - even[0] ? odd: even;
			currentLongest= currentLongest[1] - currentLongest[0] >
			longest[1] - longest[0] ? currentLongest: longest;
		}
		return str.substring(currentLongest[0], currentLongest[1]);
	}

	public static int[] getLongestPalindromeFrom(String str, int leftIndex, int rightIndex) {
		while (leftIndex >= 0 && rightIndex < str.length()) {
			if (str.charAt(leftIndex) != str.charAt(rightIndex)) {
				break;
			}
			leftIndex--;
			rightIndex++;
		}
		return new int[] { leftIndex + 1, rightIndex };
	}

	//----------------------

	public static String longestPalindromicSubstring2(String str) {
		// Write your code here.
		String longest = "";
		for(int i=0; i< str.length(); i++) {
			for(int j=i; j< str.length(); j++) {
				String substring = str.substring(i, j+1);
				System.out.println(substring);
				if(substring.length() > longest.length() && isPalindrome(substring)) {
					longest = substring;
				}
			}
		}
		return longest;
	}

	public static boolean isPalindrome(String str) {
		int leftIndex = 0;
		int rightIndex = str.length() -1;
		while(leftIndex < rightIndex) {
			if(str.charAt(leftIndex) != str.charAt(rightIndex)) {
				return false;
			}
			leftIndex++;
			rightIndex--;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("abaxyzzyxf".substring(0, 1));
		// longestPalindromicSubstring("abaxyzzyxf");
		longestPalindromicSubstring2("abaxyzzyxf");
	}
}
