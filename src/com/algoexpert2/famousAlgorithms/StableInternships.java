package com.algoexpert2.famousAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;

public class StableInternships {

	public int[][] stableInternships(int[][] interns, int[][] teams) {
		Stack<Integer> freeInterns = new Stack<>();
		for (int i = 0; i < interns.length; i++) {
			freeInterns.push(i);
		}

		List<HashMap<Integer, Integer>> teamMaps = new ArrayList<>();
		for (int[] team : teams) {
			HashMap<Integer, Integer> rank = new HashMap<>();
			for (int i = 0; i < team.length; i++) {
				rank.put(team[i], i);
			}
			teamMaps.add(rank);
		}

		HashMap<Integer, Integer> chosenInterns = new HashMap<>();
		int[] currentInternChoices = new int[interns.length];

		while (!freeInterns.isEmpty()) {
			int internNum = freeInterns.pop();

			int[] intern = interns[internNum];
			int teamPreference = intern[currentInternChoices[internNum]];
			currentInternChoices[internNum]++;

			if (!chosenInterns.containsKey(teamPreference)) {
				chosenInterns.put(teamPreference, internNum);
				continue;
			}

			int previousIntern = chosenInterns.get(teamPreference);
			int previousInternRank = teamMaps.get(teamPreference).get(previousIntern);
			int currentInternRank = teamMaps.get(teamPreference).get(internNum);

			if (currentInternRank < previousInternRank) {
				freeInterns.push(previousIntern);
				chosenInterns.put(teamPreference, internNum);
			} else {
				freeInterns.push(internNum);
			}
		}

		int[][] matches = new int[interns.length][2];
		int index = 0;
		for (Entry<Integer, Integer> chosenIntern : chosenInterns.entrySet()) {
			matches[index] = new int[] { chosenIntern.getValue(), chosenIntern.getKey() };
			index++;

		}

		return matches;
	}

	public static void main(String[] args) {
		int[][] interns = new int[][] { { 0, 1, 2 }, { 1, 0, 2 }, {1, 2, 0} };
		int[][] teams = new int[][] { { 2, 1, 0 }, { 1, 2, 0 }, {0, 2, 1} };
		int[][] expected = new int[][] { { 0, 0 }, { 1, 1 }, {2, 2}};
		var actual = new StableInternships().stableInternships(interns, teams);
		// Utils.assertTrue(expected.length == actual.length);

		for (int[] match : expected) {
			boolean containsMatch = false;
			for (int[] actualMatch : actual) {
				if (actualMatch[0] == match[0] && actualMatch[1] == match[1]) {
					containsMatch = true;
				}
			}
			// Utils.assertTrue(containsMatch);
		}
	}

}
