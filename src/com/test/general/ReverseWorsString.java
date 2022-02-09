package com.test.general;

import java.util.ArrayList;
import java.util.Collections;

public class ReverseWorsString {

	public static String reverseWordsInString(String string) {
		// Write your code here.
		ArrayList<String> words = new ArrayList<>();
		int startOfWord = 0;

		for(int i=0; i< string.length(); i++) {
			char character = string.charAt(i);

			if(character == ' ') {
				words.add(string.substring(startOfWord, i));
				startOfWord = i;
			} else if(string.charAt(startOfWord) == ' ') {
				words.add(" ");
				startOfWord = i;
			}
		}

		words.add(string.substring(startOfWord));
		Collections.reverse(words);


		return String.join("", words);
	}

	public static String reverseWordsInString2(String string) {
		// Write your code here.
		char[] characters = string.toCharArray();

		reverseListRange(characters, 0, characters.length-1);

		int startOfWord = 0;
		while(startOfWord < characters.length) {
			int endOfWord = startOfWord;
			while(endOfWord < characters.length && characters[endOfWord]!= ' ') {
				endOfWord++;
			}

			reverseListRange(characters, startOfWord, endOfWord-1);
			startOfWord = endOfWord+1;
		}

		return new String(characters);
	}

	public static char[] reverseListRange(char[] list, int start, int end) {
		while(start < end) {
			char temp = list[start];
			list[start] = list[end];
			list[end] = temp;
			start++;
			end--;
		}
		return list;
	}


	public static void main(String[] args) {
		String input = "AlgoExpert  is the best!";
		reverseWordsInString2(input);
	}

}
