package com.test.slidingwindow;

import java.util.ArrayList;
import java.util.List;

public class SlidingWindow {

	public static int staircaseTraversal(int height, int maxSteps) {
		// Write your code here.
		int currentNumberOfWays = 0;
		List<Integer> waysToTop = new ArrayList<Integer>();
		waysToTop.add(1);


		for(int currentHeight=1; currentHeight < height+1; currentHeight++) {
			int startOfWindow = currentHeight - maxSteps -1;
			int endOfWindow = currentHeight -1;

			if(startOfWindow >= 0 ) {
				currentNumberOfWays -= waysToTop.get(startOfWindow);
			}

			currentNumberOfWays += waysToTop.get(endOfWindow);
			waysToTop.add(currentNumberOfWays);
		}

		return waysToTop.get(height);
	}

	public static void main(String[] args) {
		staircaseTraversal(4, 2);
	}

}
