package com.neetcode.math;

public class IntegerToRoman {

	public String intToRoman(int num) {
		String ones[] = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
		String tens[] = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
		String hrns[] = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
		String ths[] = { "", "M", "MM", "MMM" };

		return ths[num / 1000] + hrns[(num % 1000) / 100] + tens[(num % 100) / 10] + ones[num % 10];
	}



	public static String intToRoman2(int num) {
		int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] strs = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < values.length; i++) {
			while (num >= values[i]) {
				num -= values[i];
				sb.append(strs[i]);
			}
		}
		return sb.toString();
	}


	public static boolean isUgly(int n) {
		if(n <= 0) {
			return false;
		}

		int[] arr = {2, 3, 5};

		for(int v: arr) {
			while(n % v == 0) {
				n = n / v;
			}
		}

		return n==1;

	}


	public static void main(String[] args) {
		intToRoman2(954);
		isUgly(6);
	}

}
