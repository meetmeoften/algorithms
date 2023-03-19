package com.algoexpert.arrays.leetcode300;

import java.util.Arrays;

public class DesignHashMap {

	int size = (int) Math.pow(10, 6) + 1;
	int[] flag;

	public DesignHashMap() {
		flag = new int[size];
		Arrays.fill(flag, -1);
	}

	public void add(int key, int value) {
		flag[key] = value;
	}

	public void remove(int key) {
		flag[key] = -1;
	}

	public int get(int key) {
		return flag[key];
	}

}
