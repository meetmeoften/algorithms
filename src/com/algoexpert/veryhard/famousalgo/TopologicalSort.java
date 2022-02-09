package com.algoexpert.veryhard.famousalgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopologicalSort {

	public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
		// Write your code here
		JobGraph jobGraph = createJobGraph(jobs, deps);
		return getOrderedJobs(jobGraph);
	}

	public static JobGraph createJobGraph(List<Integer> jobs, List<Integer[]> deps) {
		JobGraph graph = new JobGraph(jobs);
		for (Integer[] dep : deps) {
			graph.addPrereq(dep[1], dep[0]);
		}
		return graph;
	}

	public static List<Integer> getOrderedJobs(JobGraph graph) {
		List<Integer> orderedJobs = new ArrayList<Integer>();
		List<JobNode> nodes = new ArrayList<>(graph.nodes);
		while (nodes.size() > 0) {
			JobNode node = nodes.get(nodes.size() - 1);
			nodes.remove(nodes.size() - 1);
			boolean containsCycle = depthFirstTraverse(node, orderedJobs);
			if (containsCycle) {
				return new ArrayList<>();
			}
		}

		return orderedJobs;

	}

	private static boolean depthFirstTraverse(JobNode node, List<Integer> orderedJobs) {
		// TODO Auto-generated method stub
		if (node.visited) {
			return false;
		}
		if (node.visiting) {
			return true;
		}
		node.visiting = true;
		for (JobNode prereqNode : node.prereqs) {
			boolean containsCycle = depthFirstTraverse(prereqNode, orderedJobs);
			if (containsCycle) {
				return true;
			}
		}
		node.visited = true;
		node.visiting = false;
		orderedJobs.add(node.job);
		return false;
	}

	static class JobGraph {
		public List<JobNode> nodes;
		public Map<Integer, JobNode> graph;

		public JobGraph(List<Integer> jobs) {
			nodes = new ArrayList<>();
			graph = new HashMap<>();
			for (Integer job : jobs) {
				addNode(job);
			}
		}

		public void addPrereq(Integer job, Integer prereq) {
			JobNode jobNode = getNode(job);
			JobNode prereqNode = getNode(prereq);
			jobNode.prereqs.add(prereqNode);
		}

		public void addNode(Integer job) {
			graph.put(job, new JobNode(job));
			nodes.add(graph.get(job));
		}

		public JobNode getNode(Integer job) {
			if (!graph.containsKey(job)) {
				addNode(job);
			}
			return graph.get(job);
		}
	}

	static class JobNode {
		public Integer job;
		public List<JobNode> prereqs;
		public boolean visited;
		public boolean visiting;

		public JobNode(Integer job) {
			this.job = job;
			prereqs = new ArrayList<>();
			visited = false;
			visiting = false;
		}
	}

	public static void main(String[] args) {
		List<Integer> jobs = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
		Integer[][] depsArray = new Integer[][] { { 1, 2 }, { 1, 3 }, { 3, 2 }, { 4, 2 }, { 4, 3 } };
		List<Integer[]> deps = new ArrayList<Integer[]>();
		fillDeps(depsArray, deps);
		List<Integer> order = TopologicalSort.topologicalSort(jobs, deps);
		isValidTopologicalOrder(order, jobs, deps);
	}

	static void fillDeps(Integer[][] depsArray, List<Integer[]> deps) {
		for (Integer[] depArray : depsArray) {
			deps.add(depArray);
		}
	}

	static boolean isValidTopologicalOrder(List<Integer> order, List<Integer> jobs, List<Integer[]> deps) {
		Map<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		for (Integer candidate : order) {
			for (Integer[] dep : deps) {
				if (candidate == dep[0] && visited.containsKey(dep[1])) {
					return false;
				}
			}
			visited.put(candidate, true);
		}
		for (Integer job : jobs) {
			if (!visited.containsKey(job)) {
				return false;
			}
		}
		return order.size() == jobs.size();
	}

}
