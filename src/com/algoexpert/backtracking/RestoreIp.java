package com.algoexpert.backtracking;

import java.util.ArrayList;
import java.util.List;

public class RestoreIp {

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
		//System.out.println("TEMP --> " + temp);
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
			//System.out.println("INDEX --> " + index + "  VALUE I --> " + i);

			if (Integer.parseInt(s.substring(index, i + 1)) < 256 && (i == index || s.charAt(index) != '0')) {
				back(s, (temp + s.substring(index, i + 1) + "."), i + 1, dots + 1, ans);
			}
		}
	}

	public static void main(String[] args) {
		restoreIpAddresses("25525511135");
		//restoreIpAddresses("101023");
	}

}
