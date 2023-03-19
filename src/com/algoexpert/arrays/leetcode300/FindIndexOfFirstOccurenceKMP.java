package com.algoexpert.arrays.leetcode300;

public class FindIndexOfFirstOccurenceKMP {

	public static int strStr2(String haystack, String needle) {
		int n = needle.length();
		int m = haystack.length();
		if (n > m) {
			return -1;
		}
		if (n == 0) {
			return 0;
		}
		boolean match;
		for (int firstIndex = 0; firstIndex <= m - n; firstIndex++) {
			match = true;
			for (int currIndex = 0; currIndex < n; currIndex++) {
				if (haystack.charAt(firstIndex + currIndex) != needle.charAt(currIndex)) {
					match = false;
					break;
				}
			}
			if (match == true) {
				return firstIndex;
			}
		}
		return -1;
	}

	public static int strStr(String haystack, String needle) {
		int hLen = haystack.length();
		int nLen = needle.length();
		int nIndex = 0;
		for(int i=0; i<hLen; i++){
			// as long as the characters are equal, increment needleIndex
			if(haystack.charAt(i)==needle.charAt(nIndex)){
				nIndex++;
			}
			else{
				// start from the next index of previous start index
				i=i-nIndex;
				// needle should start from index 0
				nIndex=0;
			}
			// check if needleIndex reached needle length
			if(nIndex==nLen){
				// return the first index
				return i-nLen+1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		//String haystack = "sadbutsad", needle = "sad";
		String haystack = "leetcodeleetcleeto", needle = "leeto";
		strStr(haystack, needle);
	}
}
