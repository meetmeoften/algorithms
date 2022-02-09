package com.test.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApartmentFind {

	public static int apartmentHunting(List<Map<String, Boolean>> blocks, String[] reqs) {
		// Write your code here.

		int[][] minDistancesFromBlocks = new int[reqs.length][];
		for(int i=0; i< reqs.length; i++) {
			minDistancesFromBlocks[i] = getMinDistances(blocks, reqs[i]);
		}

		int[] maxDistancesAtBlocks = getMaxDistancesAtBlocks(blocks, minDistancesFromBlocks);
		return getIndexAtMinValue(maxDistancesAtBlocks);
	}

	public static int[] getMinDistances(List<Map<String, Boolean>> blocks, String req) {
		int[] minDistances = new int[blocks.size()];
		int closestReqIdx = Integer.MAX_VALUE;

		for(int i= 0; i< blocks.size(); i++) {
			if(blocks.get(i).get(req)) {
				closestReqIdx = i;
			}
			minDistances[i] = distanceBetween(i, closestReqIdx);
		}

		for(int i= blocks.size()-1; i>=0; i--) {
			if(blocks.get(i).get(req)) {
				closestReqIdx = i;
			}
			minDistances[i] = Math.min(minDistances[i], distanceBetween(i, closestReqIdx));
		}
		return minDistances;
	}

	public static int[] getMaxDistancesAtBlocks(
			List<Map<String, Boolean>> blocks, int[][] minDistanceFromBlocks) {

		int[] maxDistancesAtBlocks = new int[blocks.size()];
		for(int i=0; i< blocks.size(); i++) {
			int[] minDistancesAtBlock = new int[minDistanceFromBlocks.length];
			for(int j=0; j< minDistanceFromBlocks.length; j++) {
				minDistancesAtBlock[j] = minDistanceFromBlocks[j][i];
			}
			maxDistancesAtBlocks[i]= arrayMax(minDistancesAtBlock);
		}
		return maxDistancesAtBlocks;

	}


	public static int distanceBetween(int a, int b) {
		return Math.abs(a-b);
	}

	public static int arrayMax(int[] array) {
		int max = array[0];
		for(int a: array) {
			if(a > max) {
				max = a;
			}
		}
		return max;
	}

	public static int getIndexAtMinValue(int[] array) {
		int minValueIdx =0;
		int minValue = Integer.MAX_VALUE;
		for(int i=0; i<array.length; i++) {
			int currentValue = array[i];
			if(currentValue < minValue) {
				minValue = currentValue;
				minValueIdx = i;
			}
		}
		return minValueIdx;
	}


	public static void main(String[] args) {
		List<Map<String, Boolean>> blocks = new ArrayList<Map<String, Boolean>>();

		blocks.add(0, new HashMap<String, Boolean>());
		blocks.get(0).put("gym", false);
		blocks.get(0).put("school", true);
		blocks.get(0).put("store", false);

		blocks.add(1, new HashMap<String, Boolean>());
		blocks.get(1).put("gym", true);
		blocks.get(1).put("school", false);
		blocks.get(1).put("store", false);

		blocks.add(2, new HashMap<String, Boolean>());
		blocks.get(2).put("gym", true);
		blocks.get(2).put("school", true);
		blocks.get(2).put("store", false);

		blocks.add(3, new HashMap<String, Boolean>());
		blocks.get(3).put("gym", false);
		blocks.get(3).put("school", true);
		blocks.get(3).put("store", false);

		blocks.add(4, new HashMap<String, Boolean>());
		blocks.get(4).put("gym", false);
		blocks.get(4).put("school", true);
		blocks.get(4).put("store", true);

		String[] reqs = new String[] {"gym", "school", "store"};

		System.out.println(apartmentHunting(blocks, reqs));

	}
}
