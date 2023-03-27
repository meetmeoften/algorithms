package com.algoexpert2.strings;

public class OneEdit {

	public boolean oneEdit(String stringOne, String stringTwo) {
		// Write your code here.

		int lengthOne = stringOne.length();
		int lengthTwo = stringTwo.length();

		if (Math.abs(lengthOne - lengthTwo) > 1) {
			return false;
		}

		for (int i = 0; i < Math.min(lengthOne, lengthTwo); i++) {
			if (stringOne.charAt(i) != stringTwo.charAt(i)) {
				if (lengthOne > lengthTwo) {
					return stringOne.substring(i + 1).equals(stringTwo.substring(i));
				} else if (lengthTwo > lengthOne) {
					return stringOne.substring(i).equals(stringTwo.substring(i + 1));
				} else {
					return stringOne.substring(i + 1).equals(stringTwo.substring(i + 1));
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		var stringOne = "hello";
		var stringTwo = "helo";
		var expected = true;
		var actual = new OneEdit().oneEdit(stringOne, stringTwo);
		// Utils.assertTrue(expected == actual);
	}

}
