package com.test.general;

import java.util.ArrayList;
import java.util.List;

public class Dearrangements
{
	public static List<String> get_dearrangements(String str, String original, int depth)
	{
		if(str.length() == 1)
		{
			List<String> list = new ArrayList<String>();
			list.add(str);
			return list;
		}

		List<String> all_dearrangements = new ArrayList<String>();
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) != original.charAt(depth))
			{
				char c = str.charAt(i);
				List<String> dearrangements = get_dearrangements(remove_char_at(str, i), original, depth+1);
				for(String s : dearrangements) {
					all_dearrangements.add(c + s);
				}
			}
		}
		return all_dearrangements;
	}

	public static String remove_char_at(String str, int index)
	{
		StringBuilder sb = new StringBuilder(str);
		sb.deleteCharAt(index);
		return sb.toString();
	}
	public static void main(String args[])
	{
		//		System.out.println(get_dearrangements("ABCDE", "ABCDE", 0));
		System.out.println(get_dearrangements("ABC", "ABC", 0));
	}
}
