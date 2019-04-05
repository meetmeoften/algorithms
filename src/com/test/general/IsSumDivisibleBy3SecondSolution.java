package com.test.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class IsSumDivisibleBy3SecondSolution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer a[] = { 4,3,1,1,0 };
		LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(a));
		ArrayList<Long> max = new ArrayList<Long>();
		Collections.sort(list);
		if (list.get(0) != 0) {
			System.out.println("Can not find such number!!!");
			return;
		}
		int k = 1;
		int nocosiderDigit = -1;
		boolean remove = false;
		while (true) {

			long number = list.get(0);
			int pow = 1;
			for (int i = 1; i < list.size(); i++) {
				if (nocosiderDigit == i) {
					continue;
				}
				number += (long) (list.get(i) * Math.pow(10, pow++));
			}
			if (number % 3 == 0) {
				max.add(number);

			}
			while (true) {
				if (list.get(k) % 3 != 0) {
					if (!remove) {
						nocosiderDigit = list.indexOf(list.get(k));
					} else {
						list.remove(k);
					}
					k++;
					break;
				}
				k++;

				if (k >= list.size()) {
					break;
				}

			}
			if (k >= list.size()) {
				if (max.size() == 0 && !remove) {
					k = 1;
					remove = true;
				} else {
					break;
				}

			}

		}
		if (max.size() > 0) {
			Collections.sort(max);
			System.out.println(max.get(max.size() - 1));
			System.out.println(max.toString());
		} else {
			System.out.println("Can not find such number!!!");
		}

	}
}
