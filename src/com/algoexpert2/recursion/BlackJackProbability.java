package com.algoexpert2.recursion;

import java.util.*;

class BlackJackProbability {

    public double blackjackProbability(int target, int startingHand, int[] cards, double[] probabilities) {
        Map<Integer, Double> memo = new HashMap<>();
        double result = dfs(startingHand, target, cards, probabilities, memo);
        return  result;
    }

    private double dfs(int total, int target, int[] cards, double[] probabilities,
                       Map<Integer, Double> memo) {

        if (total > target)
            return 0.0;

        if (memo.containsKey(total))
            return memo.get(total);

        double probabilitySum = 0.0;

        for (int i = 0; i < cards.length; i++) {
            int newTotal = total + cards[i];
            double p = probabilities[i];
            probabilitySum += p * dfs(newTotal, target, cards, probabilities, memo);
        }

        memo.put(total, probabilitySum);
        return probabilitySum;
    }

    public static void main(String[] args) {
        BlackJackProbability blackJackProbability = new BlackJackProbability();
        int target = 21;
        int startingHand = 18;
        int cards[] = {1, 5, 10};
        double probabilities[] = {0.5, 0.3, 0.2};
        blackJackProbability.blackjackProbability(target, startingHand, cards, probabilities);
    }
}

