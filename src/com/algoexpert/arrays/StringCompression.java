package com.algoexpert.arrays;

public class StringCompression {

	public static int compress(char[] chars) {
		char previousChar = chars[0];
		int count = 0;
		StringBuilder sb = new StringBuilder();
		for (char ch : chars) {
			if (previousChar == ch) {
				count++;
			} else {
				sb.append(previousChar);
				if (count > 1) {
					sb.append(Integer.toString(count));
				}
				count = 1;
				previousChar = ch;
			}
		}
		sb.append(previousChar);
		if (count > 1) {
			sb.append(Integer.toString(count));
		}

		System.out.println(sb.length());
		return sb.length();

	}

	public static void main(String[] args) {
		char[] chars = { 'a', 'a', 'b', 'b', 'c', 'c', 'c' };
		compress(chars);
	}

}
