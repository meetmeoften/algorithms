package com.algoexpert.backtracking;

import java.util.ArrayList;

public class SplitStringDescending {

	public static boolean splitString(String s) {
		if (s == null || s.length() <= 1) {
			return false;
		}
		return backtrack(0, s, new ArrayList<Long>());
	}

	public static boolean backtrack(int pos, String s, ArrayList<Long> list) {
		// Base case where we reach till end of string and we have atleast 2 parts
		if (pos >= s.length()) {
			return list.size() >= 2;
		}

		long num = 0;
		for (int i = pos; i < s.length(); i++) {
			num = num * 10 + (s.charAt(i) - '0'); // "070" i = 1 -> 0.. i = 2 -> 7.. i =3 -> 70
			if (list.size() == 0 || list.get(list.size() - 1) - num == 1) { // if it is first digit or difference is +1
				// valid

				list.add(num); // add the number and continue to next index
				if (backtrack(i + 1, s, list)) {
					return true;
				}
				list.remove(list.size() - 1); // backtrack, done with that itteration coun't find it

			}
		}
		return false;
	}

	// ------------------

	public static boolean splitString2(String s) {
		// we are only making s.length()/2 hops becase we only want min 2 parts of
		// string
		for (int i = 1; i <= (s.length() / 2) + 1; i++) {
			int index = 0, width = i;
			Long prev = Long.MAX_VALUE;
			while (index < s.length()) {

				// checking if 100 then next time find 2 digit number so width-1
				long ten = (long) Math.pow(10, i - 1);
				if (i > 1 && prev == ten) {
					width--;
				}

				String next = getDigit(index, s, width);
				long val = Long.parseLong(next);

				if (index != 0 && prev - val != 1) {
					break; //
				}

				index += next.length();
				// If it is not the first values && we hopped till end then return true;
				if (prev != Long.MAX_VALUE && index >= s.length()) {
					return true;
				}
				prev = val;
			}
		}
		return false;
	}

	// This function simply returns the next len digit number starting from i index
	// of string
	// getDigit(2,"090087",1) -> 8
	public static String getDigit(int i, String s, int len) {
		int digits = 0;
		int j = i;
		while (j < s.length() && s.charAt(j) == '0') {
			j++;
		}
		return s.substring(i, Math.min(j + len, s.length()));
	}

	public static void main(String[] args) {
		splitString("050043");
	}

}
