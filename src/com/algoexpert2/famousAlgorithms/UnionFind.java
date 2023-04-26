package com.algoexpert2.famousAlgorithms;

import java.util.HashMap;

public class UnionFind {


	HashMap<Integer, Integer> parents = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> ranks = new HashMap<Integer, Integer>();

	public void createSet(int value) {
		// Write your code here.
		parents.put(value, value);
		ranks.put(value, 0);
	}

	public Integer find(int value) {
		// Write your code here.
		if(!parents.containsKey(value)) {
			return null;
		}

		if(value != parents.get(value)) {
			parents.put(value, find(parents.get(value)));
		}
		return parents.get(value);
	}

	public void union(int valueOne, int valueTwo) {
		// Write your code here.
		if(!parents.containsKey(valueOne) || !parents.containsKey(valueTwo)) {
			return;
		}

		int valueOneRoot = find(valueOne);
		int valueTwoRoot = find(valueTwo);

		if(ranks.get(valueOneRoot) < ranks.get(valueTwoRoot)) {
			parents.put(valueOneRoot, valueTwoRoot);
		} else if(ranks.get(valueOneRoot) > ranks.get(valueTwoRoot)) {
			parents.put(valueTwoRoot, valueOneRoot);
		} else {
			parents.put(valueTwoRoot, valueOneRoot);
			ranks.put(valueOneRoot, ranks.get(valueOneRoot) + 1);
		}
	}


	public static void main(String[] args) {
		var unionFind = new UnionFind();
		System.out.println(unionFind.find(1) == null);
		unionFind.createSet(1);
		System.out.println(unionFind.find(1) == 1);
		unionFind.createSet(5);
		System.out.println(unionFind.find(1) == 1);
		System.out.println(unionFind.find(5) == 5);
		unionFind.union(5, 1);
		System.out.println(unionFind.find(5) == unionFind.find(1));
	}

}
