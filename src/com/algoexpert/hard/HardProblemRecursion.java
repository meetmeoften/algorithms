package com.algoexpert.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HardProblemRecursion {

	// Generate Div Tags
	public ArrayList<String> generateDivTags(int numberOfTags) {
		ArrayList<String> result = new ArrayList<String>();
		generateDivTagsFromPrefix(numberOfTags, numberOfTags, "", result);
		return result;
	}

	public void generateDivTagsFromPrefix(int opening, int closing, String prefix, ArrayList<String> result) {
		if (opening > 0) {
			String newPrefix = prefix + "<div>";
			generateDivTagsFromPrefix(opening - 1, closing, newPrefix, result);
		}

		if (closing > opening) {
			String newPrefix = prefix + "</div>";
			generateDivTagsFromPrefix(opening, closing - 1, newPrefix, result);
		}

		if (closing == 0) {
			result.add(prefix);
		}
	}

	// Ambigous Measurements
	public boolean ambiguousMeasurements(int[][] measuringCups, int low, int high) {
		HashMap<String, Boolean> memoization = new HashMap<>();
		return canMeasureInrange(measuringCups, low, high, memoization);
	}

	private boolean canMeasureInrange(int[][] measuringCups, int low, int high, HashMap<String, Boolean> map) {
		// TODO Auto-generated method stub
		String key = createHashableKey(low, high);
		if (map.containsKey(key)) {
			return map.get(key);
		}

		if (low <= 0 && high <= 0) {
			return false;
		}

		boolean canMeasure = false;
		for (int[] cup : measuringCups) {
			int cupLow = cup[0];
			int cupHigh = cup[1];

			if (low <= cupLow && high >= cupHigh) {
				canMeasure = true;
				break;
			}
			int newLow = Math.max(0, low - cupLow);
			int newHigh = Math.max(0, high - cupHigh);
			canMeasure = canMeasureInrange(measuringCups, newLow, newHigh, map);
			if (canMeasure) {
				break;
			}
		}
		map.put(key, canMeasure);
		return canMeasure;
	}

	public String createHashableKey(int low, int high) {
		return String.valueOf(low) + ":" + String.valueOf(high);
	}

	// Lowest Common Manager
	public static OrgChart getLowestCommonManager(OrgChart topManager, OrgChart reportOne, OrgChart reportTwo) {
		return getOrgInfo(topManager, reportOne, reportTwo).lowestCommonManager;
	}

	public static OrgInfo getOrgInfo(OrgChart manager, OrgChart reportOne, OrgChart reportTwo) {
		int noOfReports = 0;
		for (OrgChart report : manager.directReports) {
			OrgInfo orgInfo = getOrgInfo(report, reportOne, reportTwo);
			if (orgInfo.lowestCommonManager != null) {
				return orgInfo;
			}
			noOfReports += orgInfo.numImportantReports;
		}

		if (manager == reportOne || manager == reportTwo) {
			noOfReports++;
		}

		OrgChart lcm = noOfReports == 2 ? manager : null;
		return new OrgInfo(lcm, noOfReports);
	}

	static class OrgInfo {
		public OrgChart lowestCommonManager;
		int numImportantReports;

		OrgInfo(OrgChart lowestCommonManager, int numImportantReports) {
			this.lowestCommonManager = lowestCommonManager;
			this.numImportantReports = numImportantReports;
		}
	}

	static class OrgChart {
		public char name;
		public List<OrgChart> directReports;

		OrgChart(char name) {
			this.name = name;
			this.directReports = new ArrayList<OrgChart>();
		}

		// This method is for testing only.
		public void addDirectReports(OrgChart[] directReports) {
			for (OrgChart directReport : directReports) {
				this.directReports.add(directReport);
			}
		}
	}

	// Interweaving Strings
	public static boolean interweavingStrings(String one, String two, String three) {
		if(three.length() != one.length() + two.length()) {
			return false;
		}

		Boolean[][] cache = new Boolean[one.length() + 1][two.length() + 1];
		return areInterwoven(one, two, three, 0, 0, cache);
	}

	private static boolean areInterwoven(String one, String two, String three, int i, int j, Boolean[][] cache) {
		if(cache[i][j] != null) {
			return cache[i][j];
		}

		int k = i + j;
		if(k == three.length()) {
			return true;
		}

		if(i < one.length() && one.charAt(i) == three.charAt(k)) {
			cache[i][j] = areInterwoven(one, two, three, i+1, j, cache);
			if(cache[i][j]) {
				return true;
			}
		}


		if(j< two.length() && two.charAt(j) == three.charAt(k)) {
			cache[i][j] =  areInterwoven(one, two , three, i, j+1, cache);
			if(cache[i][j]) {
				return true;
			}
		}

		cache[i][j] = false;
		return false;

	}

}
