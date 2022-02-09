package com.algoexpert.strings;

import java.util.ArrayList;

public class ReverseWordsInString {

	public String reverseWordsInString(String string) {

		ArrayList<String> words = new ArrayList<>();
		int startOfWord = 0;
		for(int i= 0; i< string.length(); i++) {
			Character character = string.charAt(i);
			if(character == ' ') {
				words.add(string.substring(startOfWord, i));
				startOfWord = i;
			} else if(string.charAt(startOfWord) == ' ') {
				words.add(" ");
				startOfWord = i;
			}
		}
		words.add(string.substring(startOfWord));

		StringBuilder builder  = new StringBuilder();
		for(int i= words.size()-1; i >= 0; i--) {
			builder.append(words.get(i));
		}
		return builder.toString();

		//Collections.reverse(words);
		//return String.join("", words);

	}

	public static void main(String[] args) {
		String input = "AlgoExpert is the best!";
		String expected = "best! the is AlgoExpert";
		String actual = new ReverseWordsInString().reverseWordsInString(input);
	}


}
