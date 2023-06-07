package com.algoexpert.arrays;

public class StringDecompression {

	public static String decompress(String input) {

		char prevChar = input.charAt(0);
		char currentChar = input.charAt(0);
		int num = 0;
		StringBuilder builder = new StringBuilder();

		for(int i= 1; i < input.length(); i++) {
			char ch = input.charAt(i);
			char tempChar = '0';
			if(ch >= 'a' && ch <= 'z') {
				currentChar = ch;
				if(currentChar == prevChar) {
					currentChar  = '0';
					tempChar = ch;
				}
			} else if(ch >= '0' && ch <= '9') {
				num = num * 10 + ch - '0';
				continue;
			}

			if(prevChar != currentChar) {
				if(num > 0) {
					while(num > 0) {
						builder.append(prevChar);
						num--;
					}
				} else {
					builder.append(prevChar);
				}
			}

			if(tempChar != '0') {
				prevChar = tempChar;
			} else {
				prevChar = currentChar;
			}
		}

		if(num > 0) {
			while(num > 0) {
				builder.append(prevChar);
				num--;
			}
		} else {
			builder.append(prevChar);
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		System.out.println(decompress("a10bcde"));
		System.out.println(decompress("a2c2"));
		System.out.println(decompress("a1a3"));
	}

}
