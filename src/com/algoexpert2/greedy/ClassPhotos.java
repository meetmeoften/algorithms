package com.algoexpert2.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ClassPhotos {

	public boolean classPhotos(ArrayList<Integer> redShirtHeights, ArrayList<Integer> blueShirtHeights) {
		// Write your code here.
		Collections.sort(redShirtHeights, Collections.reverseOrder());
		Collections.sort(blueShirtHeights, Collections.reverseOrder());

		String shirtInFirstColor = (redShirtHeights.get(0) < blueShirtHeights.get(0)) ? "RED" : "BLUE";

		for (int i = 0; i < redShirtHeights.size(); i++) {
			int redShirtHeight = redShirtHeights.get(i);
			int blueShirtHeight = blueShirtHeights.get(i);

			if (shirtInFirstColor == "RED") {
				if (redShirtHeight >= blueShirtHeight) {
					return false;
				}
			} else {
				if (blueShirtHeight >= redShirtHeight) {
					return false;
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		ArrayList<Integer> redShirtHeights = new ArrayList<Integer>(Arrays.asList(5, 8, 1, 3, 4));
		ArrayList<Integer> blueShirtHeights = new ArrayList<Integer>(Arrays.asList(6, 9, 2, 4, 5));
		boolean expected = true;
		boolean actual = new ClassPhotos().classPhotos(redShirtHeights, blueShirtHeights);
		// Utils.assertTrue(expected == actual);
	}

}
