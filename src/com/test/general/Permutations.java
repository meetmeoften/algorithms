package com.test.general;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

	public static List<List<Integer>> permute(int[] num) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		//start from an empty list
		result.add(new ArrayList<Integer>());
		for (int element : num) {
			//list of list in current iteration of the array num
			List<List<Integer>> current = new
					ArrayList<List<Integer>>();
			for (List<Integer> l : result) {
				// # of locations to insert is largest index + 1
				for (int j = 0; j < l.size()+1; j++) {
					// + add num[i] to different locations
					l.add(j, element);
					List<Integer> temp = new ArrayList<Integer>(l);
					current.add(temp);
					//System.out.println(temp);
					// - remove num[i] add
					l.remove(j);
				}
			}
			result = new ArrayList<List<Integer>>(current);
		}
		return result;
	}


	private static void permute(String str, int l, int r, List<String> list) {
		//		System.out.println(str + "  " + l + "  " + r);
		if (l == r) {
			//			System.out.println(str);
			list.add(str);
		} else {
			for (int i = l; i <= r; i++) {
				str = swap(str, l, i);
				permute(str, l + 1, r, list);
				str = swap(str, l, i);
			}
		}
	}

	/**
	 * Swap Characters at position
	 *
	 * @param a
	 *            string value
	 * @param i
	 *            position 1
	 * @param j
	 *            position 2
	 * @return swapped string
	 */
	public static String swap(String a, int i, int j) {
		if(i == j ) {
			return a;
		}
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}


	public static void main(String[] args) {
		//		int[] arr = new int[] {1, 2, 3};
		//		List<List<Integer>> result = permute(arr);
		//		System.out.println(result);

		String str = "ABC";
		int n = str.length();
		List<String> list = new ArrayList<>();
		permute(str, 0, n-1, list);
		System.out.println(list);

	}
}