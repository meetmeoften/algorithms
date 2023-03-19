package com.algoexpert.arrays.index;

public class LongestCommonPrefix {

	public static String longestCommonPrefix(String[] strs) {
		String prefix = strs[0];
		for (int index = 1; index < strs.length; index++) {
			// int index1 = strs[0].indexOf(prefix);
			while (strs[index].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
			}
		}
		return prefix;
	}

	public String longestCommonPrefix(String arr[], int n){
		int min = 1000;
		String s = "";
		int count = 0;
		int temp = 1000;
		for (int i = 0; i < n; i++) {
			int len = arr[i].length();
			if (len < min) {
				min = len;
				s = arr[i];
			}
		}
		for (int i = 0; i < n; i++) {
			count = 0;
			for (int j = 0; j < min; j++) {
				if (arr[i].charAt(j) == s.charAt(j)) {
					count++;

				} else {
					break;

				}
			}
			if (count < temp) {
				temp = count;
			}

		}
		if (temp == 0) {
			return "-1";
		} else {
			return s.substring(0, temp);
		}
	}

	public static void main(String[] args) {
		String[] arr = new String[]{ "geeks", "geeksforgeeks", "galgo" , "geek", "geezer"};
		System.out.println(longestCommonPrefix(arr));
	}
}
