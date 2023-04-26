package com.neetcode.math;

public class AddBinary {

	public static String addBinary(String a, String b) {

		if (a == null || b == null || a.length() == 0 || b.length() == 0) {
			return "";
		}

		// swap to a is longer than b
		if (a.length() < b.length()) {
			return addBinary(b, a);
		}

		String result = "";
		int carry = 0;
		int add = -1; // each position add up

		int i = a.length() - 1; // pointer of string a
		int j = b.length() - 1; // pointer of string b

		while (j >= 0 || i >= 0) {
			add = (a.charAt(i) - '0') + (j < 0 ? 0 : (b.charAt(j) - '0')) + carry;

			if (add >= 2) {
				carry = add / 2;
				add = add % 2;
			} else {
				carry = 0;
			}

			result = add + result;

			i--;
			j--;
		}

		// final check
		if (carry > 0) {
			result = carry + result;
		}

		return result;
	}

	public static String addBinary2(String a, String b) {
		// Go from right to left

		int index1 = a.length() - 1;
		int index2 = b.length() - 1;

		StringBuilder bld = new StringBuilder();
		boolean carry = false;

		while ((index1 >= 0) || (index2 >= 0) || carry) {
			char c1 = (index1 >= 0) ? a.charAt(index1) : '0';
			char c2 = (index2 >= 0) ? b.charAt(index2) : '0';

			char sum;
			if ((c1 == '0') && (c2 == '0')) { // Both zeros
				sum = carry ? '1' : '0';
				carry = false;
			} else if ((c1 == '1') && (c2 == '1')) { // Both ones
				sum = carry ? '1' : '0';
				carry = true;
			} else { // Any one with a one
				sum = carry ? '0' : '1';
				// carry will be forwarded
			}
			bld.append(sum);

			index1--;
			index2--;
		}

		return bld.reverse().toString();
	}

	public static void main(String[] args) {
		System.out.println(addBinary2("11", "1"));
	}

}
