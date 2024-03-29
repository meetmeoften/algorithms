package com.test.dynamicProgramming;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CoinChangingMinimumCoin implements Serializable {

	/**
	 * Top down dynamic programing. Using map to store intermediate results.
	 * Returns Integer.MAX_VALUE if total cannot be formed with given coins
	 */
	public int minimumCoinTopDown(int total, int coins[], Map<Integer, Integer> map) {

		//if total is 0 then there is nothing to do. return 0.
		if ( total == 0 ) {
			return 0;
		}

		//if map contains the result means we calculated it before. Lets return that value.
		if ( map.containsKey(total) ) {
			return map.get(total);
		}

		//iterate through all coins and see which one gives best result.
		int min = Integer.MAX_VALUE;
		for (int coin : coins) {

			//if value of coin is greater than total we are looking for just continue.
			if( coin > total ) {
				continue;
			}
			//recurse with total - coins[i] as new total
			int val = minimumCoinTopDown(total - coin, coins, map);

			//if val we get from picking coins[i] as first coin for current total is less
			// than value found so far make it minimum.
			if( val < min ) {
				min = val;
			}
		}

		//if min is MAX_VAL dont change it. Just result it as is. Otherwise add 1 to it.
		min =  (min == Integer.MAX_VALUE ? min : min + 1);

		//memoize the minimum for current total.
		map.put(total, min);
		return min;
	}



	public int coinChange(int[] coins, int amount) {
		if(amount==0) {
			return 0;
		}
		int[] dp = new int [amount+1];
		dp[0]=0; // do not need any coin to get 0 amount
		for(int i=1;i<=amount; i++) {
			dp[i]= Integer.MAX_VALUE;
		}
		for(int i=0; i<=amount; i++){
			for(int coin: coins){
				if(i+coin <=amount){
					if(dp[i]==Integer.MAX_VALUE){
						dp[i+coin] = dp[i+coin];
					}else{
						dp[i+coin] = Math.min(dp[i+coin], dp[i]+1);
					}
				}
			}
		}
		if(dp[amount] >= Integer.MAX_VALUE) {
			return -1;
		}
		return dp[amount];
	}

	/**
	 * Bottom up way of solving this problem.
	 * Keep input sorted. Otherwise temp[j-arr[i]) + 1 can become Integer.Max_value + 1 which
	 * can be very low negative number
	 * Returns Integer.MAX_VALUE - 1 if solution is not possible.
	 */
	public int minimumCoinBottomUp(int total, int coins[]){
		int T[] = new int[total + 1];
		int R[] = new int[total + 1];
		T[0] = 0;
		for(int i=1; i <= total; i++){
			T[i] = Integer.MAX_VALUE-1;
			R[i] = -1;
		}
		for(int j=0; j < coins.length; j++){
			for(int i=1; i <= total; i++){
				if(i >= coins[j]){
					if (T[i - coins[j]] + 1 < T[i]) {
						T[i] = 1 + T[i - coins[j]];
						R[i] = j;
					}
				}
			}
		}
		printCoinCombination(R, coins);
		return T[total];
	}

	private void printCoinCombination(int R[], int coins[]) {
		if (R[R.length - 1] == -1) {
			System.out.print("No solution is possible");
			return;
		}
		int start = R.length - 1;
		System.out.print("Coins used to form total ");
		while ( start != 0 ) {
			int j = R[start];
			System.out.print(coins[j] + " ");
			start = start - coins[j];
		}
		System.out.print("\n");
	}


	public static int minNumberOfCoinsForChange(int n, int[] denoms) {
		// Write your code here.

		int[] numOfCoins = new int[n+1];
		Arrays.fill(numOfCoins, Integer.MAX_VALUE);
		numOfCoins[0] = 0;
		int toCompare = 0;
		for(int denom: denoms) {
			for(int amount = 1; amount < numOfCoins.length; amount++) {
				if(amount >= denom) {
					int value = numOfCoins[amount - denom];
					if(value == Integer.MAX_VALUE) {
						toCompare = value;
					} else {
						toCompare = value +1;
					}
					numOfCoins[amount] = Math.min(numOfCoins[amount], toCompare);
				}
			}
		}
		return -1;
	}

	public static void main ( String args[] ) {
		// int total = 16;
		// int coins[] = {7, 10, 3, 3, 6};
		int total = 7;
		int coins[] = {2, 5, 10};
		CoinChangingMinimumCoin cc = new CoinChangingMinimumCoin();
		System.out.println(cc.coinChange(coins, total));
		Map<Integer, Integer> map = new HashMap<>();
		int topDownValue = cc.minimumCoinTopDown(total, coins, map);
		int bottomUpValue = cc.minimumCoinBottomUp(total, coins);

		minNumberOfCoinsForChange(total, coins);

		System.out.print(String.format("Bottom up and top down result %s %s", bottomUpValue, topDownValue));

	}
}
