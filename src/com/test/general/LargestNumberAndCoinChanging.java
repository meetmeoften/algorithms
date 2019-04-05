package com.test.general;

public class LargestNumberAndCoinChanging {

	private static int largestNumber(int data) {
		int num = data;
		int[] times = new int[10];
		while (num != 0) {
			if (num == 0) {
				break;
			}
			int val = num % 10;
			times[val]++;
			num /= 10;
		}
		String largestNumber = "";
		for (int i = 9; i >= 0; i--) {
			for (int j = 0; j < times[i]; j++) {
				largestNumber += i;
			}
		}
		return Integer.parseInt(largestNumber);
	}


	/**
	 * that, given a non-empty zero-indexed array A consisting of N integers
	 * representing the coins, returns the maximum possible adjacency that can
	 * be obtained by reversing exactly one coin (that is, one of the coins must
	 * be reversed). Consecutive elements of array A represent consecutive coins
	 * in the row. Array A contains only 0s and/or 1s:
	 *
	 * 0 represents a coin with heads facing up; 1 represents a coin with tails
	 * facing up. For example, given array A consisting of eight numbers, such
	 * that:
	 *
	 * A[0] = 1 A[1] = 1 A[2] = 0 A[3] = 1 A[4] = 0 A[5] = 0 A[6] = 1 A[7] = 1
	 *
	 * 11010011 -> Output 5
	 *
	 * the function should return 5. The initial adjacency is 3, as there are
	 * three pairs of adjacent coins with the same side facing up, namely (0,
	 * 1), (4, 5) and (6, 7). After reversing the coin represented by A[2], the
	 * adjacency equals 5, as there are five pairs of adjacent coins with the
	 * same side facing up, namely: (0, 1), (1, 2), (2, 3), (4, 5) and (6, 7),
	 * and it is not possible to obtain a higher adjacency.
	 *
	 * The same adjacency can be obtained by reversing the coin represented by
	 * A[3].
	 *
	 * Assume that:
	 *
	 * N is an integer within the range [1..100,000]; each element of array A is
	 * an integer within the range [0..1]. Complexity:
	 *
	 * expected worst-case time complexity is O(N); expected worst-case space
	 * complexity is O(1), beyond input storage (not counting the storage
	 * required for input arguments).
	 *
	 * @param A
	 * @return
	 */

	private static int solution(int[] A) {

		int n = A.length;
		int result = 0;
		for (int i = 0; i < n - 1; i++) {
			if (A[i] == A[i + 1]) {
				result = result + 1;
			}
		}
		int r = 0;
		for (int i = 0; i < n; i++) {
			int count = 0;
			if (i > 0) {
				if (A[i - 1] != A[i]) {
					count = count + 1;
				} else {
					count = count - 1;
				}
			}
			if (i < n - 1) {
				if (A[i + 1] != A[i]) {
					count = count + 1;
				} else {
					count = count - 1;
				}
			}
			r = Math.max(r, count);
		}
		return result + r;

	}

	public static void main(String[] args) {
		System.out.println(largestNumber(129940));
		//		int[] input = new int[] {1, 1, 1};
		//		int output = solution(input);
		//		System.out.println(output);
	}

}
