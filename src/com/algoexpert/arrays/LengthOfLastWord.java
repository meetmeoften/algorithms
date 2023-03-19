package com.algoexpert.arrays;

public class LengthOfLastWord {

	public static int lengthOfLastWord(String s) {
		int i = s.length() - 1, length = 0;
		System.out.println(i);
		while (s.charAt(i) == ' ') {
			i -= 1;
		}
		System.out.println(i);
		while (i >= 0 && s.charAt(i) != ' ') {
			length += 1;
			i -= 1;
		}
		return length;
	}


	public static void main(String[] args) {
		lengthOfLastWord("Hello World ");
	}

}
