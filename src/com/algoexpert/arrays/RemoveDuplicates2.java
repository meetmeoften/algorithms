package com.algoexpert.arrays;

public class RemoveDuplicates2 {

	public static String removeDuplicates(String s, int k) {
		int i = 0, n = s.length(), count[] = new int[n];
		char[] stack = s.toCharArray();
		for (int j = 0; j < n; ++j, ++i) {
			stack[i] = stack[j];
			count[i] = 1;
			if(i> 0 && stack[i-1] == stack[j]) {
				count[i] = count[i-1]+1;
			}
			//			count[i] = i > 0 && stack[i - 1] == stack[j] ? count[i - 1] + 1 : 1;
			if (count[i] == k) {
				i =i- k;
			}
		}
		return new String(stack, 0, i);
	}

	public static void main(String[] args) {
		String s = "deeedbbcccbdaa";
		int k = 3;
		removeDuplicates(s, k);
	}

}
