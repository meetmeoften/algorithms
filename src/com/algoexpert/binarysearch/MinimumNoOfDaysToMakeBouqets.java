package com.algoexpert.binarysearch;

import java.util.Arrays;

public class MinimumNoOfDaysToMakeBouqets {


	public static int minDays(int[] bloomDay, int m, int k) {
		int low  = Arrays.stream(bloomDay).min().getAsInt();
		int high =  Arrays.stream(bloomDay).max().getAsInt();

		int  ans = -1;

		while (low<=high){
			int mid = low+(high-low)/2;

			if(isPossible(bloomDay,mid,k,m)){
				ans=mid;
				high=mid-1;
			}
			else {

				low=mid+1;
			}
		}

		return ans;
	}

	private static boolean isPossible(int[] bloomDay, int mid, int k,int booket) {
		int bouquets = 0, flowersCollected = 0;
		for (int value : bloomDay) {
			if (value <= mid) {
				//	                If the current flower can be taken with in days then increase the flower flowersCollected.
				flowersCollected++;
			} else {
				//	                If there is a flower in between that takes more number of days then the given day, then resent the counter.
				flowersCollected = 0;
			}
			//	            If the flowersCollected is same as the required flower per bookie, then increase the bouquets count;
			if (flowersCollected == k) {
				bouquets++;
				flowersCollected = 0;
			}
		}

		return bouquets>=booket;

	}

	public static void main(String[] args) {
		int [] bloomDay = new int[]{1,10,3,10,2};
		int m = 3, k = 1;

		minDays(bloomDay, m,  k);
	}

}
