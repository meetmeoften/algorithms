package com.neetcode.math;

public class ZigZagConversion {

	/**
	 * P A H N A P L S I I G Y I R
	 *
	 * @param s
	 * @param row
	 * @return
	 */

	public static String convert(String s, int row) {
		if (row == 1) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < row; i++) {
			int j = i;
			if (i == 0 || i == row - 1) {
				while (j < s.length()) {
					sb.append(s.charAt(j));
					j += 2 * (row - 1);
				}
			} else {
				boolean diagonal = false;
				while (j < s.length()) {
					if (!diagonal) {
						sb.append(s.charAt(j));
						j += 2 * (row - i - 1);
						diagonal = true;
					} else {
						sb.append(s.charAt(j));
						j += 2 * i;
						diagonal = false;
					}
				}
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		convert("PAYPALISHIRING", 3);
	}

}
