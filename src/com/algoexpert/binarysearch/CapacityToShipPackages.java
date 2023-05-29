package com.algoexpert.binarysearch;

public class CapacityToShipPackages {

	public static int shipWithinDays(int[] weights, int days) {
		int left = 0;
		int right = 0;

		for (int weight : weights) {
			right += weight;
			left = Math.max(left, weight);
		}

		while (left < right) {
			int mid = (left + right) / 2;

			int curr = canShip(weights, mid);

			if (curr <= days) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}

		return left;

	}

	private static int canShip(int[] weights, int mid) {
		int sum = 0;
		int curr = 0;
		int i = 0;

		while (i < weights.length) {
			sum += weights[i];
			if (sum == mid) {
				curr++;
				sum = 0;
			} else if (sum > mid) {
				curr++;
				sum = weights[i];
			}
			i++;
		}

		if (sum != 0) {
			curr++;
		}
		return curr;
	}

	public static void main(String[] args) {
		int[] weights = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int days = 5;

		shipWithinDays(weights, days);
	}

}
