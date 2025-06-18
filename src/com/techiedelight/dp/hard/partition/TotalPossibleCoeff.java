package com.techiedelight.dp.hard.partition;

public class TotalPossibleCoeff {

    public static int count(int[] arr, int k, int rhs) {
        if (rhs == 0) {
            return 1;
        }

        if (rhs < 0 || k < 0) {
            return 0;
        }

        int exclude = count(arr, k - 1, rhs);
        int include = count(arr, k, rhs - arr[k]);

        return exclude + include;
    }

    public static void main(String[] args) {
        // `k` coefficients of the given equation
        int[] coeff = {1, 2, 3};
        int k = coeff.length;
        int rhs = 4;
        System.out.println("The total number of solutions is " +
                count(coeff, k - 1, rhs));
    }

}
