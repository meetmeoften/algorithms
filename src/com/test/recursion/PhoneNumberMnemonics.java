package com.test.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneNumberMnemonics {

	public static Map<Character, String[]> DIGIT_LETTERS = new HashMap<>();

	static {
		DIGIT_LETTERS.put('0', new String[] {"0"});
		DIGIT_LETTERS.put('1', new String[] {"1"});
		DIGIT_LETTERS.put('2', new String[] {"a", "b", "c"});
		DIGIT_LETTERS.put('3', new String[] {"d", "e", "f"});
		DIGIT_LETTERS.put('4', new String[] {"g", "h", "i"});
		DIGIT_LETTERS.put('5', new String[] {"j", "k", "l"});
		DIGIT_LETTERS.put('6', new String[] {"m", "n", "o"});
		DIGIT_LETTERS.put('7', new String[] {"p", "q", "r", "s"});
		DIGIT_LETTERS.put('8', new String[] {"t", "u", "v"});
		DIGIT_LETTERS.put('9', new String[] {"w", "x", "y", "z"});

	}


	public static  List<String> phoneNumberMnemonics(String phoneNumber) {
		// Write your code here.
		String[] currentMneomic = new String[phoneNumber.length()];
		Arrays.fill(currentMneomic, "0");

		List<String> mnemonicsFound = new ArrayList<String>();
		phoneNumberMnemonicsHelper(0, phoneNumber, currentMneomic, mnemonicsFound);
		return mnemonicsFound;
	}

	public static void phoneNumberMnemonicsHelper(
			int index, String phoneNumber, String[] currentMneomic, List<String> mnemonicsFound) {
		if(index == phoneNumber.length()) {
			String mnemonic = String.join("", currentMneomic);
			mnemonicsFound.add(mnemonic);
		} else {
			char digit = phoneNumber.charAt(index);
			String[] letters = DIGIT_LETTERS.get(digit);
			for(String letter: letters) {
				currentMneomic[index] = letter;
				phoneNumberMnemonicsHelper(index+1, phoneNumber, currentMneomic, mnemonicsFound);
			}
		}
	}


	public static void main(String[] args) {
		String phoneNumber = "1905";
		List<String> result = phoneNumberMnemonics(phoneNumber);
		result.stream().forEach(e -> System.out.println(e));
	}

}
