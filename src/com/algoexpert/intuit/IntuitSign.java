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

	public static void main(String[] args) {
		System.out.println("<<<" +" : "+ method("<<<"));
	}

}
