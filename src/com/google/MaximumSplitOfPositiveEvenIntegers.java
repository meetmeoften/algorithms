package com.google;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MaximumSplitOfPositiveEvenIntegers {

    List<Long> best = new ArrayList<>();

    public List<Long> maximumEvenSplit2(long finalSum) {
        if (finalSum % 2 != 0) return new ArrayList<>();
        backtrack(finalSum, 2, new ArrayList<>());
        return best;
    }

    private void backtrack(long remain, long start, List<Long> current) {
        if (remain == 0) {
            if (current.size() > best.size()) {
                best = new ArrayList<>(current);
            }
            return;
        }

        for (long i = start; i <= remain; i += 2) {
            current.add(i);
            backtrack(remain - i, i + 2, current); // ensure uniqueness
            current.remove(current.size() - 1);    // backtrack
        }
    }


    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> result = new ArrayList<>();
        if (finalSum % 2 != 0) return result;

        long curr = 2;
        while (finalSum >= curr) {
            result.add(curr);
            finalSum -= curr;
            curr += 2;
        }

        if (finalSum > 0) {
            // Add the remaining to the last element to maintain the total sum
            result.set(result.size() - 1, result.get(result.size() - 1) + finalSum);
        }
        return result;
    }

    public static void main(String[] args) {
        MaximumSplitOfPositiveEvenIntegers max = new MaximumSplitOfPositiveEvenIntegers();
        max.maximumEvenSplit(10);
    }
}
