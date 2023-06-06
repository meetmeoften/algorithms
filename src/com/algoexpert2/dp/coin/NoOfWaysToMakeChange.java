package com.algoexpert2.dp.coin;

import java.util.Arrays;

public class NoOfWaysToMakeChange {

	public static int numberOfWaysToMakeChange(int n, int[] denoms) {
		int[] ways = new int[n + 1];
		ways[0] = 1;
		for (int denom : denoms) {
			for (int amount = 1; amount < n + 1; amount++) {
				if (amount >= denom) {
					ways[amount] += ways[amount - denom];
				}
			}
		}
		return ways[n];
	}

	//	public static int change(int amount, int[] coins) {
	//
	//		Integer[][] memo = new Integer[coins.length][amount+1];
	//
	//		return findNoOfCombs(amount, coins, memo, coins.length-1);
	//	}
	//
	//	public static int findNoOfCombs(int amount, int[] coins, Integer[][] memo, int index){
	//		if(amount==0) {
	//			return 1;
	//		}
	//
	//		//if i reached all my coins
	//		if(index < 0 || amount < 0) {
	//			return 0;
	//		}
	//
	//		//if this was already calculated
	//		if(memo[index][amount]!=null) {
	//			return memo[index][amount];
	//		}
	//
	//		int way1=0;
	//		if(coins[index]<=amount){
	//			way1 = findNoOfCombs(amount-coins[index], coins, memo, index);
	//		}
	//		int way2 = findNoOfCombs(amount, coins, memo, index-1);
	//
	//		//save all possible ways on this memo
	//		memo[index][amount]=way1+way2;
	//
	//		return memo[index][amount];
	//
	//	}


	public static long countWaysToMakeChangeUtil(int[] arr,int ind, int T, long[][] dp){
		if(ind == 0){
			if(T%arr[0]==0) {
				return 1;
			} else {
				return 0;
			}
		}

		if(dp[ind][T]!=-1) {
			return dp[ind][T];
		}

		long notTaken = countWaysToMakeChangeUtil(arr,ind-1,T,dp);

		long taken = 0;
		if(arr[ind] <= T) {
			taken = countWaysToMakeChangeUtil(arr,ind,T-arr[ind],dp);
		}
		return dp[ind][T] = notTaken + taken;
	}


	public static long countWaysToMakeChange(int[] arr, int n, int T){

		long dp[][]=new long[n][T+1];
		for(long row[]: dp) {
			Arrays.fill(row,-1);
		}
		return countWaysToMakeChangeUtil(arr,n-1, T, dp);
	}

	public static void main(String[] args) {
		int amount = 5;
		int[] coins = { 1, 2, 5 };
		System.out.println(numberOfWaysToMakeChange(amount, coins));
		System.out.println(countWaysToMakeChange(coins, coins.length, amount));
	}
}
