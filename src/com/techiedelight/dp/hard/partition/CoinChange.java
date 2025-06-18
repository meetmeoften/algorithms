package com.techiedelight.dp.hard.partition;

public class CoinChange {

    public static int coinChange(int[] coins, int target) {
        return dfs(coins, target, coins.length - 1);
    }

    private static int dfs(int[] coins, int target, int i) {

        if( i < 0 || target < 0) {
            return 0;
        }

        if(target == 0) {
            return 1;
        }
//        if (i == 0) {
//            if (target % coins[i] == 0) {
//                return 1;
//            } else {
//                return 0;
//            }
//        }
        int exclude = dfs(coins, target, i - 1);
        int include = 0;
        if (coins[i] <= target) {
            include = dfs(coins, target - coins[i], i);
        }
        return exclude + include;
    }

    public static void main(String[] args) {
       int result =  coinChange(new int[]{1, 3, 5, 7}, 8);
       System.out.println(result);
    }
}
