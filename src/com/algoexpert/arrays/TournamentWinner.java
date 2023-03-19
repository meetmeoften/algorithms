package com.algoexpert.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TournamentWinner {

	public String tournamentWinner(ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {
		// Write your code here.
		String currentBestTeam = "";
		HashMap<String, Integer> map = new HashMap<>();
		map.put(currentBestTeam, 0);

		for (int i = 0; i < competitions.size(); i++) {
			ArrayList<String> competition = competitions.get(i);
			int result = results.get(i);

			String homeTeam = competition.get(0);
			String awayTeam = competition.get(1);

			String winningTeam = (result == 1) ? homeTeam : awayTeam;

			if (!map.containsKey(winningTeam)) {
				map.put(winningTeam, 0);
			}
			map.put(winningTeam, map.get(winningTeam) + 3);

			if (map.get(winningTeam) > map.get(currentBestTeam)) {
				currentBestTeam = winningTeam;
			}
		}

		return currentBestTeam;
	}

	public static void main(String[] args) {
		ArrayList<ArrayList<String>> competitions = new ArrayList<ArrayList<String>>();
		ArrayList<String> competition1 = new ArrayList<String>(Arrays.asList("HTML", "C#"));
		ArrayList<String> competition2 = new ArrayList<String>(Arrays.asList("C#", "Python"));
		ArrayList<String> competition3 = new ArrayList<String>(Arrays.asList("Python", "HTML"));
		competitions.add(competition1);
		competitions.add(competition2);
		competitions.add(competition3);
		ArrayList<Integer> results = new ArrayList<Integer>(Arrays.asList(0, 0, 1));
		String expected = "Python";
		var actual = new TournamentWinner().tournamentWinner(competitions, results);
	}

}
