package com.algoexpert.intuit;

public class IntuitSign {

	public static int method(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int number = 0;
		int max = 0, min = 0;
		for (char c : s.toCharArray()) {
			if (c == '>') {
				number++;
				if (number > max) {
					max = number;
				}
			} else if (c == '<') {
				number--;
				if (number < min) {
					min = number;
				}
			}
		}
		return max - min + 1; // 1 is for first element which was ignored before.
	}

	private int getValidRange(String s) {
		if(s==null || s.isEmpty()) {
			return 0;
		}
		char prev = '0';
		int count = 0;
		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '=') {
				continue;
			}
			if (s.charAt(i) == prev) {
				count++;
			} else {
				prev = s.charAt(i);
				count = 1;
			}
			max = Math.max(max, count);
		}
		return max + 1;
	}

	public static void main(String[] args) {
		System.out.println("<<<" +" : "+ method("<<<"));
	}

}
