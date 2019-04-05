package com.test.general;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class DoorsClosed {

	private static void minimumDoorsClosed(int n) {
		HashSet<Integer> doorClosedSet = new LinkedHashSet<>();
		for (int i = 3; i <= n; i++) {
			int count = 0;
			int lastDoor = i * 100;
			for (int j = 3; j <= lastDoor; j++) {
				if (doorClosedSet.contains(j)) {
					continue;
				}
				if (j % i == 0) {
					doorClosedSet.add(j);
					count++;
				} else if ((j - 1) % i == 0) {
					doorClosedSet.add(j);
					count++;
				} else if ((j - 2) % i == 0) {
					doorClosedSet.add(j);
					count++;
				}
			}
			if (count == 0) {
				System.out.println(count);
				break;
			}
			System.out.println(
					"Minimum Doors closed " + count + "  for the number " + i);
		}
	}



	public static void minimumRounds() {
		int n = 10;
		HashSet<Integer> doorClosedSet = new LinkedHashSet<>();
		for (int i = 3; i <= n; i++) {
			int count = 0;
			for (int j = 3; j <= n; j++) {
				if (doorClosedSet.contains(j)) {
					continue;
				}
				if (j % i == 0) {
					doorClosedSet.add(j);
					count++;
				}
				//				else if ((j - 1) % i == 0) {
				//					doorClosedSet.add(j);
				//					count++;
				//				} else if ((j - 2) % i == 0) {
				//					doorClosedSet.add(j);
				//					count++;
				//				}
			}
			if(count == 0) {
				System.out.println(i);
			}
		}
	}


	public static void shuffleArray(int[] cards) {
		int temp, index;
		for (int i = 0; i < cards.length; i++){
			int value = (int) (Math.random() * (cards.length - i)) ;
			System.out.println(value);
			index = value + i;
			temp = cards[i];
			cards[i] = cards[index];
			cards[index] = temp;
		}
		System.out.println(cards);
	}

	public static void main(String[] args) {
		int[] arr = new int[] {1, 2, 3, 4, 5};
		//		shuffleArray(arr);
		minimumRounds();
		System.out.println(2^3);
		//		minimumDoorsClosed(99);
		//		rounds();

	}
}
