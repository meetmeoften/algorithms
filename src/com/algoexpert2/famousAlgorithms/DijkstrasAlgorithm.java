package com.algoexpert2.famousAlgorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DijkstrasAlgorithm {

	public int[] dijkstrasAlgorithm(int start, int[][][] edges) {
		// Write your code here.
		int numberOfVertices = edges.length;

		int[] minDistances = new int[edges.length];
		Arrays.fill(minDistances, Integer.MAX_VALUE);
		minDistances[start] = 0;

		Set<Integer> visited = new HashSet<Integer>();

		while (numberOfVertices != visited.size()) {
			int[] getVertexData = getMinDistanceVertexData(minDistances, visited);
			int vertex = getVertexData[0];
			int currentMinDistance = getVertexData[1];

			if (currentMinDistance == Integer.MAX_VALUE) {
				break;
			}
			visited.add(vertex);

			for (int[] edge : edges[vertex]) {
				int destination = edge[0];
				int distanceToDestination = edge[1];

				if (visited.contains(destination)) {
					continue;
				}

				int newPathDistance = currentMinDistance + distanceToDestination;
				int currentDestinationDistance = minDistances[destination];
				if (newPathDistance < currentDestinationDistance) {
					minDistances[destination] = newPathDistance;
				}
			}
		}

		int[] finalDistances = new int[minDistances.length];
		for (int i = 0; i < minDistances.length; i++) {
			int distance = minDistances[i];
			if (distance == Integer.MAX_VALUE) {
				finalDistances[i] = -1;
			} else {
				finalDistances[i] = distance;
			}
		}
		return finalDistances;
	}

	public int[] getMinDistanceVertexData(int[] distances, Set<Integer> visited) {
		int currentMinDistance = Integer.MAX_VALUE;
		int vertex = -1;

		for (int vertexIndex = 0; vertexIndex < distances.length; vertexIndex++) {
			int distance = distances[vertexIndex];

			if (visited.contains(vertexIndex)) {
				continue;
			}

			if (distance <= currentMinDistance) {
				vertex = vertexIndex;
				currentMinDistance = distance;
			}
		}

		return new int[] { vertex, currentMinDistance };
	}

	public static void main(String[] args) {
		int start = 0;
		int[][][] edges = {
				{{1, 7}},
				{{2, 6}, {3, 20}, {4, 3}},
				{{3, 14}},
				{{4, 2}},
				{},
				{}
		};
		int[] expected = {0, 7, 13, 27, 10, -1};
		int[] actual = new DijkstrasAlgorithm().dijkstrasAlgorithm(start, edges);
	}

}
