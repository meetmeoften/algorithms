package com.algoexpert.graph.advanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.stream.Collectors;

public class ReconstructItineray {

	public static List<String> findItinerary(List<List<String>> tickets) {
		LinkedList<String> itinerary = new LinkedList<>();
		Map<String, PriorityQueue<String>> graph = new HashMap<>();
		Stack<String> stack = new Stack<>();

		for (List<String> ticket : tickets) {
			graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
		}

		stack.push("JFK");
		while (!stack.isEmpty()) {
			String nextDestination = stack.peek();

			if (!graph.getOrDefault(nextDestination, new PriorityQueue<>()).isEmpty()) {
				stack.push(graph.get(nextDestination).poll());
			} else {
				itinerary.addFirst(stack.pop());
			}
		}
		return itinerary;
	}


	public static void main(String[] args) {
		//String[][] tickets = {{"MUC","LHR"},{"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}};

		String[][] tickets = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"}, {"ATL","SFO"}};
		//String[][] tickets = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","JFK"}};

		List<List<String>> resList = new ArrayList<>();
		for(String[] rows : tickets) {
			resList.add(Arrays.stream(rows).collect(Collectors.toList()));
		}
		List<String> result = findItinerary(resList);
		System.out.println(result);

	}

}
