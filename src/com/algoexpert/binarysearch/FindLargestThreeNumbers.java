package com.algoexpert.binarysearch;

public class FindLargestThreeNumbers {

	class Program {
		public static int[] findThreeLargestNumbers(int[] array) {
			// Write your code here.
			int[] largestArray = new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

			for(int i: array) {
				if(i > largestArray[2]) {
					var temp2 = largestArray[2];
					var temp1 = largestArray[1];
					largestArray[2] = i;
					largestArray[1] = temp2;
					largestArray[0] = temp1;
				} else if (i > largestArray[1]) {
					var temp1 = largestArray[1];
					largestArray[1] = i;
					largestArray[0] = temp1;
				} else if (i > largestArray [0]) {
					largestArray[0] = i;
				}
			}
			return largestArray;
		}

		private void shift(int[] largestArray,  int num, int shiftPosition) {
			for(int i=0; i<= shiftPosition; i++) {
				if(shiftPosition == i) {
					largestArray[i] = num;
				} else {
					largestArray[i] = largestArray[i+1];
				}

			}
		}
	}

	public static void main(String[] args) {
		System.out.println(Program.findThreeLargestNumbers(new int[] {141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7}));
	}
}
