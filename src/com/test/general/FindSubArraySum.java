package com.test.general;

public class FindSubArraySum {
	public static void main(String[] args) {

		//		Integer arr[] = new Integer[]{ 15, 2, 4, 8, 9, 5, 10, 23 };

		int arr[] = new int[]{ 15, 2, 4, 8, 9, 5, 10, 23 };

		//		int arr[] = new int[] {4, 3, 1, 1};

		//		Arrays.sort(arr, Collections.reverseOrder());//Sort the input descending

		System.out.println(arr);
		findSubArraySumEqualToXOptimized(arr, 15);
		System.out.println("n======================");
		//		findSubArraySumEqualToXOptimized(arr, 33);
		//		System.out.println(subCount(arr, 4, 3));
	}

	static void findSubArraySumEqualToX(Integer arr[], int X) {
		int currentSum;
		for (int i = 0; i < arr.length; i++) {
			currentSum = arr[i];
			// try all subarrays starting with 'i'
			for (int j = i + 1; j <= arr.length; j++) {
				if (currentSum == X) {
					int endIndexForContArray = j - 1;
					System.out.println("Sum found between indexes " + i + " and " + endIndexForContArray);
					for (int k = i; k <= endIndexForContArray; k++) {
						System.out.print(arr[k]+" ");
					}
					return;
				}
				if (currentSum > X || j == arr.length) {
					break;
				}
				currentSum = currentSum + arr[j];
			}
		}

		System.out.println("No subarray found");
		return;
	}

	public static void findSubArraySumEqualToXOptimized(int arr[], int X) {
		int currentSum = arr[0];
		int start = 0;

		for (int i = 1; i <= arr.length; i++) {
			// If currentSum is more than the sum, start removing starting elements unless you get currentSum is less than X
			while (currentSum > X && start < i - 1) {
				currentSum = currentSum - arr[start];
				start++;
			}

			// If currentSum becomes equal to sum, then print the index
			if (currentSum == X) {
				int endIndexForContArray = i - 1;
				System.out.println("Sum found between indexes " + start + " and " + endIndexForContArray);
				System.out.println("Printing Array values : ");
				for (int j = start; j <= endIndexForContArray; j++) {
					System.out.print(arr[j]+" ");
				}
				return;
			}

			// Add this element to currentSum
			if (i < arr.length) {
				currentSum = currentSum + arr[i];
			}
		}

	}



	static void findSubArraySumDivisibleByX(Integer arr[], int X) {
		int currentSum = 0;
		for (int i = 0; i < arr.length; i++) {
			currentSum = arr[i];
			// try all subarrays starting with 'i'
			for (int j = i + 1; j <= arr.length; j++) {
				if (currentSum % X == 0) {
					int endIndexForContArray = j - 1;
					System.out.println("Sum found between indexes " + i + " and " + endIndexForContArray);
					for (int k = i; k <= endIndexForContArray; k++) {
						System.out.print(arr[k]+" ");
					}
					return;
				}
				if (currentSum > X || j == arr.length) {
					break;
				}
				currentSum = currentSum + arr[j];
			}
		}


		for (Integer element : arr) {
			currentSum = currentSum + element;
		}

		System.out.println("No subarray found");
		return;
	}

	static int subCount(int arr[], int n, int k)
	{
		// create auxiliary hash array to count frequency
		// of remainders
		int mod[] = new int[k];
		int index[] = new int[n];
		// Traverse original array and compute cumulative
		// sum take remainder of this current cumulative
		// sum and increase count by 1 for this remainder
		// in mod[] array
		int cumSum = 0;
		for (int i=0; i<n; i++)
		{
			cumSum += arr[i];

			int value = cumSum % k ;
			mod[value]++;



			// as the sum can be negative, taking modulo twice
			//			mod[((cumSum%k)+k)%k]++;
		}

		int result = 0;  // Initialize result

		// Traverse mod[]
		for (int i=0; i<k ; i++) {
			if (mod[i] > 1) {
				result += (mod[i]*(mod[i]-1))/2;
			}
		}

		// add the elements which are divisible by k itself
		// i.e., the elements whose sum = 0
		result += mod[0];

		return result;
	}





}