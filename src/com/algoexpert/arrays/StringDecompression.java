package com.algoexpert.arrays;

import java.util.Stack;

public class StringDecompression {

	public static String decompress(String input) {

		char prevChar = input.charAt(0);
		char currentChar = input.charAt(0);
		int num = 0;
		StringBuilder builder = new StringBuilder();

		for (int i = 1; i < input.length(); i++) {
			char ch = input.charAt(i);
			char tempChar = '0';
			if (ch >= 'a' && ch <= 'z') {
				currentChar = ch;
				if (currentChar == prevChar) {
					tempChar = currentChar;
					currentChar = '0';
				}
			} else if (ch >= '0' && ch <= '9') {
				num = num * 10 + ch - '0';
				continue;
			}

			if (prevChar != currentChar) {
				if (num > 0) {
					while (num > 0) {
						builder.append(prevChar);
						num--;
					}
				} else {
					builder.append(prevChar);
				}
			}

			if (tempChar != '0') {
				prevChar = tempChar;
			} else {
				prevChar = currentChar;
			}
		}

		if (num > 0) {
			while (num > 0) {
				builder.append(prevChar);
				num--;
			}
		} else {
			builder.append(prevChar);
		}
		return builder.toString();
	}

	public static String decompress2(String input) {
		StringBuilder builder = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		int num = 0;

		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);

			if (ch >= '0' && ch <= '9') {
				num = num * 10 + ch - '0';
				continue;
			}

			while (!stack.isEmpty()) {
				char prevChar = stack.pop();
				if (num > 0) {
					while (num > 0) {
						builder.append(prevChar);
						num--;
					}
				} else {
					builder.append(prevChar);
				}
			}
			stack.push(ch);
		}

		while (!stack.isEmpty()) {
			char prevChar = stack.pop();
			if (num > 0) {
				while (num > 0) {
					builder.append(prevChar);
					num--;
				}
			} else {
				builder.append(prevChar);
			}
		}

		return builder.toString();

	}

	public static void main(String[] args) {
		System.out.println(decompress2("a10bcde"));
		System.out.println(decompress2("a2c2"));
		System.out.println(decompress2("a1a3b2e1e1"));
	}

}
