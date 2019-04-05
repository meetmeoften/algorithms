package com.hackerrank.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringAnagram {



	public static void method(String s) {
		int n = s.length();
		Map<String, Integer> counts = new HashMap<>();
		for (int j = 0; j < n; j++) {
			for (int k = j + 1; k <= n; k++) {
				char[] chars = s.substring(j, k).toCharArray();
				Arrays.sort(chars);
				String sorted = new String(chars);
				if (counts.containsKey(sorted)) {
					counts.put(sorted, counts.get(sorted) + 1);
				}
				else {
					counts.put(sorted, 1);
				}
			}
		}
		int sum = 0;
		for (int value : counts.values()) {
			sum += (value * (value - 1)) / 2;
		}
		System.out.println(sum);
	}

	static int pairs(int[] a,int k) {
		/* Complete this function */
		HashMap<Integer,Integer> diff=new HashMap<Integer,Integer>();
		int count=0;
		for(int i=0;i<a.length;i++)
		{
			diff.put(a[i]-k,1);
		}
		for(int  i=0;i<a.length;i++) {
			if(diff.containsKey(a[i])) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		method("abba");
		method("kkkk");

		pairs( new int[]{1, 2, 3, 4}, 1);
	}

}
