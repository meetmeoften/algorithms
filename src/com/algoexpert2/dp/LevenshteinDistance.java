package com.algoexpert2.dp;

public class LevenshteinDistance {


	public static int levenshteinDistance(String str1, String str2) {
		// Write your code here.
		int[][] edits = new int[str1.length()+1][str2.length()+1];

		for(int i=0; i < str1.length()+1; i++) {
			for(int j=0; j < str2.length() +1; j++) {
				edits[i][j] = j;
			}
			edits[i][0] = i;
		}

		for(int i=1; i < str1.length()+1; i++) {
			for(int j=1; j < str2.length() +1; j++) {

				if(str1.charAt(i-1) == str2.charAt(j-1)) {
					edits[i][j] = edits[i-1][j-1];
				} else {
					edits[i][j] = 1+ Math.min(edits[i-1][j], Math.min(edits[i-1][j-1], edits[i][j-1]));
				}
			}

		}

		return edits[str1.length()][str2.length()];
	}

	public static void main(String[] args) {
		levenshteinDistance("abc", "yabd");

		//	'	y	a	b	d
		//'	0	1	2	3	4
		//a	1	1	1	2	3
		//b	2
		//c	3
	}
}
