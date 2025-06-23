package com.algoexpert.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestToOrigin {

	public static int[][] kClosest(int[][] points, int K) {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
				(p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);

		for (int[] p : points) {
			pq.offer(p);
			if (pq.size() > K) {
				pq.poll();
			}
		}
		int[][] res = new int[K][2];
		while (K > 0) {
			res[--K] = pq.poll();
		}
		return res;
	}


	public int[][] kClosest2(int[][] points, int K) {
		int len =  points.length, l = 0, r = len - 1;
		while (l <= r) {
			int mid = helper(points, l, r);
			if (mid == K) {
				break;
			}
			if (mid < K) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return Arrays.copyOfRange(points, 0, K);
	}

	private int helper(int[][] A, int l, int r) {
		int[] pivot = A[l];
		while (l < r) {
			while (l < r && compare(A[r], pivot) >= 0) {
				r--;
			}
			A[l] = A[r];
			while (l < r && compare(A[l], pivot) <= 0) {
				l++;
			}
			A[r] = A[l];
		}
		A[l] = pivot;
		return l;
	}

	private int compare(int[] p1, int[] p2) {
		return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
	}


	public int[][] kClosest3(int[][] points, int K) {
		int headIdx = 0;
		int tailIdx = points.length - 1;
		while (headIdx < tailIdx) {
			int sortedPosIdx = partition(points, headIdx, tailIdx);
			if (sortedPosIdx == K-1) {
				break;
			} else if (sortedPosIdx > K-1) {
				tailIdx = sortedPosIdx - 1;
			} else {
				headIdx = sortedPosIdx + 1;
			}
		}
		int[][] result = new int[K][2];
		for (int i = 0; i < result.length; i++) {
			result[i] = points[i];
		}
		return result;
	}

	private int partition(int[][] points, int headIdx, int tailIdx) {
		int pivotIdx = headIdx;
		int pivotPointDistance = getDistance(points[pivotIdx]);
		swap(points, pivotIdx, tailIdx);
		int j = headIdx;
		for (int i = headIdx; i < tailIdx; i++) {
			int currPointDistance = getDistance(points[i]);
			if (currPointDistance < pivotPointDistance) {
				swap(points, i, j);
				j++;
			}
		}
		swap(points, j, tailIdx);
		return j;
	}

	private int getDistance(int[] points) {
		int x = Math.abs(points[0]);
		int y = Math.abs(points[1]);
		return x*x + y*y;
	}

	private void swap(int[][] points, int idx1, int idx2) {
		int[] temp = points[idx1];
		points[idx1] = points[idx2];
		points[idx2] = temp;
	}

	public static void main(String[] args) {
		//int[][] points = { { 1, 3 }, { -2, 2 } };
		int[][] points = { { 3, 1 }, { 2, 2 } };

		//int[][] points = { { 1, 1 }, { 0, 1 } };
		int k = 1;

		kClosest(points, k);
	}

	// a[1, 1]
			// b[2, 2]  -- b[0]^2 + b[1]^2 - a[0

}
