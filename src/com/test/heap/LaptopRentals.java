package com.test.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LaptopRentals {

	public static int laptopRentals(ArrayList<ArrayList<Integer>> times) {
		// Write your code here.
		if(times.size() == 0) {
			return 0;
		}

		int usedLaptops = 0;
		List<Integer> startTimes = new ArrayList<Integer>();
		List<Integer> endTimes = new ArrayList<Integer>();
		for(ArrayList<Integer> interval: times) {
			startTimes.add(interval.get(0));
			endTimes.add(interval.get(1));
		}
		Collections.sort(startTimes);
		Collections.sort(endTimes);

		int i = 0;
		int j = 0;

		while(i < times.size()) {
			if(startTimes.get(i) >= endTimes.get(j)) {
				usedLaptops--;
				j++;
			}
			usedLaptops++;
			i++;
		}
		return usedLaptops;
	}

	public static void main(String[] args) {
		int[][] times = new int[][] {{0, 2}, {0, 4}};
		ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
		for (int[] time : times) {
			input.add(new ArrayList(Arrays.asList(time[0], time[1])));
		}
		laptopRentals(input);
	}

}
