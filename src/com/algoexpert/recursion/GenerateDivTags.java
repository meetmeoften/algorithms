package com.algoexpert.recursion;

import java.util.ArrayList;

public class GenerateDivTags {

	public static ArrayList<String> generateDivTags(int numberOfTags) {
		ArrayList<String> result = new ArrayList<String>();
		generateDivTagsFromPrefix(numberOfTags, numberOfTags, "", result);
		return result;
	}

	public static void generateDivTagsFromPrefix(
			int opening, int closing, String prefix, ArrayList<String> result) {
		if(opening > 0) {
			String newPrefix = prefix + "<div>";
			generateDivTagsFromPrefix(opening-1, closing, newPrefix, result);
		}

		if(closing > opening ) {
			String newPrefix = prefix + "</div>";
			generateDivTagsFromPrefix(opening, closing-1, newPrefix, result);
		}

		if(closing == 0) {
			result.add(prefix);
		}
	}

	public static void main(String[] args) {
		int input = 3;
		generateDivTags(input);

	}

}
