package com.algoexpert.arrays.leetcode300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomizedSet {

	private Map<Integer, Integer> map;
	private List<Integer> numberList;

	public RandomizedSet() {
		this.map = new HashMap<>();
		this.numberList = new ArrayList<>();
	}

	// Append to the end, maintain indexing
	// Delete by swapping with the end, maintain indexing
	// Get random by getting a random index wrt list's size

	public boolean insert(int val) {
		if (this.map.containsKey(val)) {
			return false;
		}
		int indexInsert = this.numberList.size();
		this.numberList.add(val);
		this.map.put(val, indexInsert);
		return true;
	}

	public boolean remove(int val) {
		if (!this.map.containsKey(val)) {
			return false;
		}

		int lastIndex = this.numberList.size() - 1;
		int lastElement = this.numberList.get(lastIndex);
		int index = this.map.get(val);

		// Swap with last element
		this.numberList.set(index, lastElement);

		// Update indices [Add & Delete]
		this.map.put(lastElement, index);
		this.map.remove(val);

		// Remove from list
		this.numberList.remove(lastIndex);
		return true;
	}

	public int getRandom() {
		int randomIndex = (int) (Math.random() * this.numberList.size());
		return this.numberList.get(randomIndex);
	}


	public static void main(String[] args) {
		RandomizedSet randomizedSet = new RandomizedSet();
		// ["RandomizedSet","insert","remove","insert","getRandom","remove","insert","getRandom"]
		// [[],[1],[2],[2],[],[1],[2],[]]

		randomizedSet.insert(1);
		randomizedSet.remove(2);
		randomizedSet.insert(2);
		randomizedSet.insert(3);
		randomizedSet.getRandom();
		randomizedSet.remove(1);
		randomizedSet.insert(2);
		randomizedSet.getRandom();
	}

}
