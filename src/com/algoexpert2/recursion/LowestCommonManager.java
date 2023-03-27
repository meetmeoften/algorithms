package com.algoexpert2.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LowestCommonManager {

	public static OrgChart getLowestCommonManager(
			OrgChart topManager, OrgChart reportOne, OrgChart reportTwo) {
		// Write your code here.

		return getOrgInfo(topManager, reportOne, reportTwo).lowestCommonManager;
	}

	public static OrgInfo getOrgInfo(OrgChart manager, OrgChart reportOne, OrgChart reportTwo) {
		if(manager != null ) {
			System.out.println(manager.name);
		}
		int numImportantReports = 0;
		for(OrgChart directReport: manager.directReports) {
			OrgInfo orgInfo = getOrgInfo(directReport, reportOne, reportTwo);
			if(orgInfo.lowestCommonManager != null) {
				return orgInfo;
			}
			numImportantReports += orgInfo.numImportantReports;
		}

		if(manager == reportOne || manager == reportTwo) {
			numImportantReports++;
		}
		OrgChart lowestCommonManager = numImportantReports == 2 ? manager : null;
		OrgInfo newOrgInfo = new OrgInfo(lowestCommonManager, numImportantReports);
		return newOrgInfo;

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

	public static HashMap<Character, OrgChart> getOrgCharts() {
		var orgCharts = new HashMap<Character, OrgChart>();
		var alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (char a : alphabet.toCharArray()) {
			orgCharts.put(a, new OrgChart(a));
		}
		orgCharts.get('X').addDirectReports(new OrgChart[] {orgCharts.get('Z')});
		return orgCharts;
	}

	public static void main(String[] args) {
		var orgCharts = getOrgCharts();
		orgCharts
		.get('A')
		.addDirectReports(new OrgChart[] {orgCharts.get('B'), orgCharts.get('C')});
		orgCharts
		.get('B')
		.addDirectReports(new OrgChart[] {orgCharts.get('D'), orgCharts.get('E')});
		orgCharts
		.get('C')
		.addDirectReports(new OrgChart[] {orgCharts.get('F'), orgCharts.get('G')});
		orgCharts
		.get('D')
		.addDirectReports(new OrgChart[] {orgCharts.get('H'), orgCharts.get('I')});

		OrgChart lcm =
				getLowestCommonManager(orgCharts.get('A'), orgCharts.get('E'), orgCharts.get('I'));
	}

}
