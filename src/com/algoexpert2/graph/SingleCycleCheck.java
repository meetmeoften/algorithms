package com.algoexpert2.graph;

public class SingleCycleCheck {

	public static boolean hasSingleCycle(int[] array) {
		// Write your code here.
		int numberOfElementsVisited = 0;
		int currentIndex = 0;

		while (numberOfElementsVisited < array.length) {
			if (numberOfElementsVisited > 0 && currentIndex == 0) {
				return false;
			}
			numberOfElementsVisited++;
			currentIndex = getNextIndex(currentIndex, array);
		}
		return currentIndex == 0;
	}

	public static int getNextIndex(int currentIndex, int[] array) {
		int jump = array[currentIndex];
		int nextIdx = (currentIndex + jump) % array.length;
		int nextValue = nextIdx >= 0 ? nextIdx : nextIdx + array.length;
		return nextValue;

	}

	public static void main(String[] args) {
		int arr[] = { 2, 3, 1, -4, -4, 2 };
		hasSingleCycle(arr);
	}

}
