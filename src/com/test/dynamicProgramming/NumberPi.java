package com.test.dynamicProgramming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumberPi {

	public static int numbersInPi(String pi, String[] numbers) {
		// Write your code here.
		Set<String> numbersTable = new HashSet<>();
		for(String number : numbers){
			numbersTable.add(number);
		}

		Map<Integer, Integer> cache = new HashMap<>();
		int minSpaces = getMinSpaces(pi, numbersTable, cache, 0);

		return minSpaces == Integer.MAX_VALUE ? -1 : minSpaces;
	}

	public static int getMinSpaces(
			String pi, Set<String> numbersTable, Map<Integer, Integer> cache, int index) {
		System.out.println(pi + " Index:" + index);
		if(index == pi.length()) {
			return -1;
		}
		if(cache.containsKey(index)) {
			return cache.get(index);
		}

		int minSpaces = Integer.MAX_VALUE;
		for(int i= index; i<pi.length(); i++) {
			String prefix = pi.substring(index, i+1);
			if(numbersTable.contains(prefix)) {
				System.out.println(prefix + " Index:" + index);
				int minSpacesInSuffix = getMinSpaces(pi, numbersTable, cache, i+1);

				if(minSpacesInSuffix == Integer.MAX_VALUE) {
					minSpaces = Math.min(minSpaces, minSpacesInSuffix);
				} else {
					minSpaces = Math.min(minSpaces, minSpacesInSuffix +1);
				}
			}
		}
		cache.put(index, minSpaces);
		System.out.println("INDEX :" + index + " " + " MINSPACE: " + minSpaces);
		return cache.get(index);
	}


	public static void main(String[] args) {
		String pi = "3141592653589793238462643383279";
		String[] numbers = new String[] {
				"314159265358979323846", "26433", "8", "3279", "314159265", "35897932384626433832", "79"
		};
		//		String pi = "3141592653589793238462643383279";
		//		String[] numbers = new String[] {
		//				"3141", "1512", "159", "793", "12412451", "8462643383279"
		//		};

		System.out.println(numbersInPi(pi, numbers));
	}

}
