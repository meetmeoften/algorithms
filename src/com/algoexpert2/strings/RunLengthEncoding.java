package com.algoexpert2.strings;

public class RunLengthEncoding {

	public String runLengthEncoding(String string) {
		// Write your code here.

		StringBuilder builder = new StringBuilder();
		int currentRunLength = 1;

		for (int i = 1; i < string.length(); i++) {
			char currentChar = string.charAt(i);
			char previousChar = string.charAt(i - 1);

			if ((currentChar != previousChar) || currentRunLength == 9) {
				builder.append(Integer.toString(currentRunLength));
				builder.append(previousChar);
				currentRunLength = 0;
			}
			currentRunLength++;
		}

		// Handle last run
		builder.append(Integer.toString(currentRunLength));
		builder.append(string.charAt(string.length() - 1));

		return builder.toString();

	}

	public static void main(String[] args) {
		var input = "AAAAAAAAAAAAABBCCCCDD";
		var expected = "9A4A2B4C2D";
		var actual = new RunLengthEncoding().runLengthEncoding(input);
		// Utils.assertTrue(expected.equals(actual));
	}

}
