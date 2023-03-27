package com.algoexpert2.strings;

import java.util.ArrayList;
import java.util.List;

public class ValidIpAddress {

	public static ArrayList<String> validIPAddresses(String string) {
		ArrayList<String> ipAddressFound = new ArrayList<>();
		for (int i = 1; i < Math.min(string.length(), 4); i++) {

			String[] currentIpAddressParts = new String[] { "", "", "", "" };

			currentIpAddressParts[0] = string.substring(0, i);

			if (!isValidPart(currentIpAddressParts[0])) {
				continue;
			}

			int jmaxValue = i + Math.min(string.length() - i, 4);
			for (int j = i + 1; j < jmaxValue; j++) {
				currentIpAddressParts[1] = string.substring(i, j);
				if (!isValidPart(currentIpAddressParts[1])) {
					continue;
				}
				int kmaxValue = j + Math.min(string.length() - j, 4);
				for (int k = j + 1; k < kmaxValue; k++) {
					currentIpAddressParts[2] = string.substring(j, k);
					currentIpAddressParts[3] = string.substring(k);

					if (isValidPart(currentIpAddressParts[2]) && isValidPart(currentIpAddressParts[3])) {
						ipAddressFound.add(join(currentIpAddressParts));
					}
				}
			}
		}
		return ipAddressFound;
	}

	public static boolean isValidPart(String string) {
		int stringAsInt = Integer.parseInt(string);
		if (stringAsInt > 255) {
			return false;
		}
		return string.length() == Integer.toString(stringAsInt).length();
	}

	public static String join(String[] strings) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < strings.length; i++) {
			b.append(strings[i]);
			if (i < strings.length - 1) {
				b.append(".");
			}
		}
		return b.toString();
	}

	// --------------------------

	public static List<String> restoreIpAddresses(String s) {
		List<String> ans = new ArrayList<>();
		if (s.length() > 12) {
			return ans;
		}
		String temp = "";
		back(s, temp, 0, 0, ans);
		return ans;
	}

	private static void back(String s, String temp, int index, int dots, List<String> ans) {
		// System.out.println("TEMP --> " + temp);
		if (dots == 4 && index == s.length()) {
			System.out.println("ADDED --> " + temp);
			ans.add(temp.substring(0, temp.length() - 1));
			return;
		}
		if (dots > 4) {
			System.out.println("TEMP --> " + temp);
			return;
		}
		for (int i = index; i < (Math.min((3 + index), s.length())); i++) {
			// System.out.println("INDEX --> " + index + " VALUE I --> " + i);

			if (Integer.parseInt(s.substring(index, i + 1)) < 256 && (i == index || s.charAt(index) != '0')) {
				back(s, (temp + s.substring(index, i + 1) + "."), i + 1, dots + 1, ans);
			}
		}
	}

	public static void main(String[] args) {
		String input = "1921680";
		validIPAddresses(input);
	}
}
