package com.algoexpert.arrays;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Colors {

	public static int minCost(String s, int[] cost) {
		int n = s.length();
		int result = 0;
		for (int i = 1; i < n; i++) {
			if (s.charAt(i) == s.charAt(i - 1)) {
				result = result + Math.min(cost[i], cost[i - 1]);
				cost[i] = Math.max(cost[i], cost[i - 1]);
			}
		}
		return result;

	}

	public static void main(String[] args) {
		// Input: colors = "aabaa", neededTime = [1,2,3,4,1]
		// Output: 2

		int[] arr = new int[] { 1, 2, 3, 4, 10 };
		System.out.println(minCost("aabaa", arr));

		int n1 = 0, n2 = 1, n3, i, count = 10;
		System.out.print(n1 + " " + n2);// printing 0 and 1

		for (i = 2; i < count; ++i){ // loop starts from 2 because 0 and 1 are already printed
			n3 = n1 + n2;
			System.out.print(" " + n3);
			n1 = n2;
			n2 = n3;
		}

		UnaryOperator<int[]> res = t -> new int[]{t[1], t[0] + t[1]};
		int[] value = res.apply(new int[] {1, 3});
		System.out.println(value);

		System.out.println("--------");
		System.out.println("--------");

		Integer n = 4;

		Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
		.limit(5)
		.map(t -> t[0])
		.filter(x -> x == n)
		.forEach(x -> System.out.println(x));


	}

}
