package com.hackerrank.array;

import java.util.HashMap;
import java.util.Map;

public class MagazineCheck {


	public static void checkMagazine(String[] magazine, String[] note) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(int i= 0; i < magazine.length -1 ; i++) {
			map.clear();
			int magazineCount = Integer.parseInt(magazine[i]);
			String noteValues = note[i];

			String[] noteValuesArray = noteValues.split("");

			for(String noteValue : noteValuesArray) {
				Integer occurrences = map.get(noteValue);
				if(occurrences == null) {
					map.put(noteValue, 1);
				}
				else {
					map.put(noteValue, occurrences + 1);
				}
			}

			if(map.keySet().size() == magazineCount) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}

		}
	}



	public static void main(String[] args) {

	}

}
