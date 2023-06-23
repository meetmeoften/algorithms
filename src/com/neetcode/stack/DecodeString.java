package com.neetcode.stack;

import java.util.Stack;

public class DecodeString {

	public static String decodeString(String s) {
		Stack<Integer> numStack = new Stack<>();
		Stack<StringBuilder> strBuildStack = new Stack<>();
		StringBuilder builder = new StringBuilder();
		int num = 0;
		for (char c : s.toCharArray()) {
			if (c >= '0' && c <= '9') {
				num = num * 10 + c - '0';
			} else if (c == '[') {
				strBuildStack.push(builder);
				builder = new StringBuilder();
				numStack.push(num);
				num = 0;
			} else if (c == ']') {
				StringBuilder temp = builder;
				builder = strBuildStack.pop();
				int count = numStack.pop();
				while (count-- > 0) {
					builder.append(temp);
				}
			} else {
				builder.append(c);
			}
		}
		return builder.toString();
	}

	static int i = 0;

	public static String decodeString2(String s) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		String tmp_string = "";

		while (i < s.length()) {
			char c = s.charAt(i);
			i++;

			if (c == '[') {
				tmp_string = decodeString2(s); // do subproblem
				for (int j = 0; j < count; j++) {
					sb.append(tmp_string);
				}
				count = 0; // reset counter
			} else if (c == ']') { // subproblem complete
				break;
			} else if (Character.isAlphabetic(c)) {
				sb.append(c);
			} else {
				count = count * 10 + c - '0';
			}
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		String s = "1[a]2[bc]";
		System.out.println(decodeString(s));
		System.out.println(decodeString2(s));

	}

}
