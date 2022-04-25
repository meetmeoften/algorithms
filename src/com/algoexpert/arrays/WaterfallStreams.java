package com.algoexpert.arrays;

public class WaterfallStreams {

	public double[] waterfallStreams(double[][] array, int source) {
		// Write your code here.
		double[] rowAbove = array[0];

		rowAbove[source] = -1;
		for(int row=1; row< array.length; row++) {
			double[] currentRow = array[row];

			for(int i=0; i< rowAbove.length; i++) { // this i refers to col
				double valueAbove = rowAbove[i];

				boolean hasWaterAbove = valueAbove < 0;
				boolean hasBlock = currentRow[i] == 1.0;

				if(!hasWaterAbove) {
					continue;
				}

				if(!hasBlock) {
					currentRow[i] = currentRow[i] + valueAbove;
					continue;
				}

				double splitWater = valueAbove/2;

				int rightIndex = i;
				while(rightIndex +1 < rowAbove.length) {
					rightIndex++;
					if(rowAbove[rightIndex] == 1.0) {
						break;
					}
					if(currentRow[rightIndex] != 1) {
						currentRow[rightIndex] += splitWater;
						break;
					}
				}

				int leftIndex = i;
				while(leftIndex-1 >=0) {
					leftIndex--;
					if(rowAbove[leftIndex] == 1.0) {
						break;
					}
					if(currentRow[leftIndex] != 1) {
						currentRow[leftIndex] += splitWater;
						break;
					}
				}
			}
			rowAbove = currentRow;
		}

		double[] finalPercentages = new double[rowAbove.length];
		for(int i= 0; i< rowAbove.length; i++) {
			double num = rowAbove[i];

			if(num == 0) {
				finalPercentages[i] = num;
			} else {
				finalPercentages[i] = (num * -100);
			}
		}

		return finalPercentages;
	}


	public static void main(String[] args) {
		double[][] array =
				new double[][] {
			{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
			{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
			{0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0},
			{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
			{1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0},
			{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0},
			{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
		};
		var source = 3;
		double[] expected = {0.0, 0.0, 0.0, 25.0, 25.0, 0.0, 0.0};
		double[] actual = new WaterfallStreams().waterfallStreams(array, source);
	}
}
