package com.test.graph.dijkstra;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 *    Enter the number of vertices
 *    5
 *    Enter the Weighted Matrix for the graph
 *    0 7 0 0 2
 *    0 0 1 0 2
 *    0 0 0 4 0
 *    0 0 5 0 0
 *    0 3 8 5 0
 *    Enter the source
 *    1
 *    The Shorted Path to all nodes are
 *    1 to 1 is 0
 *    1 to 2 is 5
 *    1 to 3 is 6
 *    1 to 4 is 7
 *    1 to 5 is 2
 * @author snaga
 *
 */

public class DijkstraSanFoundry {
	private final int distances[];
	private final Queue<Integer> queue;
	private final Set<Integer> settled;
	private final int number_of_nodes;
	private final int adjacencyMatrix[][];

	public DijkstraSanFoundry(int number_of_nodes)
	{
		this.number_of_nodes = number_of_nodes;
		distances = new int[number_of_nodes + 1];
		settled = new HashSet<Integer>();
		queue = new LinkedList<Integer>();
		adjacencyMatrix = new int[number_of_nodes + 1][number_of_nodes + 1];
	}

	public void dijkstra_algorithm(int adjacency_matrix[][], int source)
	{
		int evaluationNode;
		for (int i = 1; i <= number_of_nodes; i++) {
			for (int j = 1; j <= number_of_nodes; j++) {
				adjacencyMatrix[i][j] = adjacency_matrix[i][j];
			}
		}

		for (int i = 1; i <= number_of_nodes; i++)
		{
			distances[i] = Integer.MAX_VALUE;
		}

		queue.add(source);
		distances[source] = 0;

		while (!queue.isEmpty())
		{
			evaluationNode = getNodeWithMinimumDistanceFromQueue();
			settled.add(evaluationNode);
			evaluateNeighbours(evaluationNode);
		}
	}

	private int getNodeWithMinimumDistanceFromQueue()
	{
		int min ;
		int node = 0;
		Iterator<Integer> iterator = queue.iterator();
		node = iterator.next();
		min = distances[node];

		for (int i = 1; i <= distances.length; i++)
		{
			if (queue.contains(i))
			{
				if (distances[i] <= min)
				{
					min = distances[i];
					node = i;
				}
			}
		}
		queue.remove(node);
		return node;
	}

	private void evaluateNeighbours(int evaluationNode)
	{
		int edgeDistance = -1;
		int newDistance = -1;

		for (int destinationNode = 1; destinationNode <= number_of_nodes; destinationNode++)
		{
			if (!settled.contains(destinationNode))
			{
				if (adjacencyMatrix[evaluationNode][destinationNode] != Integer.MAX_VALUE)
				{
					edgeDistance = adjacencyMatrix[evaluationNode][destinationNode];
					newDistance = distances[evaluationNode] + edgeDistance;
					if (newDistance < distances[destinationNode])
					{
						distances[destinationNode] = newDistance;
					}
					queue.add(destinationNode);
				}
			}
		}
	}

	public static void main(String... arg)
	{
		int adjacency_matrix[][];
		int number_of_vertices;
		int source = 0;
		Scanner scan = new Scanner(System.in);

		try
		{
			System.out.println("Enter the number of vertices");
			number_of_vertices = scan.nextInt();
			adjacency_matrix = new int[number_of_vertices + 1][number_of_vertices + 1];

			System.out.println("Enter the Weighted Matrix for the graph");
			for (int i = 1; i <= number_of_vertices; i++)
			{
				for (int j = 1; j <= number_of_vertices; j++)
				{
					adjacency_matrix[i][j] = scan.nextInt();
					if (i == j)
					{
						adjacency_matrix[i][j] = 0;
						continue;
					}
					if (adjacency_matrix[i][j] == 0)
					{
						adjacency_matrix[i][j] = Integer.MAX_VALUE;
					}
				}
			}

			System.out.println("Enter the source ");
			source = scan.nextInt();
			DijkstraSanFoundry dijkstrasQueue = new DijkstraSanFoundry(number_of_vertices);
			dijkstrasQueue.dijkstra_algorithm(adjacency_matrix, source);

			System.out.println("The Shorted Path to all nodes are ");
			for (int i = 1; i <= dijkstrasQueue.distances.length - 1; i++)
			{
				System.out.println(source + " to " + i + " is " + dijkstrasQueue.distances[i]);
			}
		} catch (InputMismatchException inputMismatch)
		{
			System.out.println("Wrong Input Format");
		}
		scan.close();
	}
}
