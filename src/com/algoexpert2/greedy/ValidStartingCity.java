package com.algoexpert2.greedy;

public class ValidStartingCity {

	public int validStartingCity(int[] distances, int[] fuel, int mpg) {
		int numberOfCities = distances.length;
		int milesRemaining = 0;

		int indexOfStartingCityCandidate = 0;
		int milesRemainingAtStartingCityCandidate = 0;

		for (int cityIndex = 1; cityIndex < numberOfCities; cityIndex++) {
			int fuelFromPreviousCity = fuel[cityIndex - 1];
			int distanceFromPreviousCity = distances[cityIndex - 1];

			milesRemaining += (fuelFromPreviousCity * mpg) - distanceFromPreviousCity;

			if (milesRemaining < milesRemainingAtStartingCityCandidate) {
				milesRemainingAtStartingCityCandidate = milesRemaining;
				indexOfStartingCityCandidate = cityIndex;
			}
		}
		return indexOfStartingCityCandidate;
	}

	// ---------

	public int validStartingCity2(int[] distances, int[] fuel, int mpg) {
		int totalTank = 0, currTank = 0, startingStation = 0;

		for (int i = 0; i < fuel.length; i++) {
			totalTank += (fuel[i] * mpg) - distances[i];
			currTank += (fuel[i] * mpg) - distances[i];

			if (currTank < 0) {
				startingStation = i + 1;
				currTank = 0;
			}
		}

		return totalTank >= 0 ? startingStation : -1;
	}

	public static void main(String[] args) {
		int[] distances = new int[] { 5, 25, 15, 10, 15 };
		int[] fuel = new int[] { 1, 2, 1, 0, 3 };
		int mpg = 10;
		int expected = 4;
		var actual = new ValidStartingCity().validStartingCity(distances, fuel, mpg);
	}

}
