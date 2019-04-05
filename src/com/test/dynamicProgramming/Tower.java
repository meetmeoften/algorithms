package com.test.dynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Tower {

	private final Stack<Integer> disks;
	private final int index;

	public Tower(int i) {
		disks = new Stack<Integer>();
		index = i;
	}

	public int index() {
		return index;
	}

	public void add(int d) {
		if (!disks.isEmpty() && disks.peek() <= d) {
			System.out.println("Error placing disk " + d);
		} else {
			disks.push(d);
		}
	}

	public void moveTopTo(Tower t) {
		int top = disks.pop();
		t.add(top);
		System.out.println("Move disk " + top + " from " + index() + " to " + t.index());
	}

	public void print() {
		System.out.println("Contents of Tower " + index());
		for (int i = disks.size() - 1; i >= 0; i--) {
			System.out.println(disks.get(i));
		}
	}

	public void moveDisks(int n, Tower destination, Tower buffer) {
		if (n > 0) {
			moveDisks(n - 1, buffer, destination);
			moveTopTo(destination);
			buffer.moveDisks(n - 1, destination, this);
		}
	}

	public static void main(String[] args) {
		int n = 3;
		Tower[] towers = new Tower[n];
		for (int i = 0; i < 3; i++) {
			towers[i] = new Tower(i);
		}
		for (int i = n - 1; i >= 0; i--) {
			towers[0].add(i);
		}
		towers[0].moveDisks(n, towers[2], towers[1]);
	}


	static int winningLotteryTicket(String[] tickets) {
		List<String> list = new ArrayList<>();
		Set<String> set = new HashSet<String>();

		String[] keywords = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		int result = 1;

		for (int i = 0; i < tickets.length; i++) {
			for (int j = i + 1; j < tickets.length; j++) {
				String concatenate = tickets[i] + tickets[j];
				list.add(concatenate);
			}
		}

		for (String conStr : list) {
			boolean match = true;
			StringBuilder builder = new StringBuilder();
			for (String key : keywords) {
				if (!conStr.contains(key)) {
					match = false;
					break;
				} else {
					Integer value = conStr.indexOf(key);
					builder.append(value);
				}

			}

			if (match) {
				set.add(builder.toString());
			}
		}

		result = set.size();
		return result;

	}
}
