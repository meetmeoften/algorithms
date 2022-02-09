package com.algoexpert.stack;

import java.util.ArrayList;
import java.util.Collections;

public class SunsetViews {

	public static ArrayList<Integer> sunsetViews(int[] buildings, String direction) {
		// Write your code here.
		ArrayList<Integer> buildingWithViews = new ArrayList<Integer>();

		int startIndex = buildings.length - 1;
		int step = -1;

		if (direction.equals("WEST")) {
			startIndex = 0;
			step = 1;
		}
		int index = startIndex;
		int runningMaxHeight = 0;

		while (index >= 0 && index < buildings.length) {
			int buildingHeight = buildings[index];

			if (buildingHeight > runningMaxHeight) {
				buildingWithViews.add(index);
			}

			runningMaxHeight = Math.max(runningMaxHeight, buildingHeight);

			index = index + step;
		}

		if (direction.equals("EAST")) {
			Collections.reverse(buildingWithViews);
		}

		return buildingWithViews;
	}

	public static ArrayList<Integer> sunsetViews2(int[] buildings, String direction) {
		ArrayList<Integer> buildingWithViews = new ArrayList<Integer>();

		int startIndex = buildings.length - 1;
		int step = -1;

		if (direction.equals("EAST")) {
			startIndex = 0;
			step = 1;
		}
		int index = startIndex;
		while (index >= 0 && index < buildings.length) {
			int buildingHeight = buildings[index];

			while (buildingWithViews.size() > 0
					&& buildings[buildingWithViews.get(buildingWithViews.size() - 1)] <= buildingHeight) {
				buildingWithViews.remove(buildingWithViews.size() - 1);
			}
			buildingWithViews.add(index);
			index = index + step;
		}
		if (direction.equals("WEST")) {
			Collections.reverse(buildingWithViews);
		}
		return buildingWithViews;
	}

	public static void main(String[] args) {
		int[] buildings = new int[] {3, 5, 4, 4, 3, 1, 3, 2};
		String direction = "EAST";
		sunsetViews(buildings, direction);
	}

}
