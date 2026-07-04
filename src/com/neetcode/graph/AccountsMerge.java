package com.neetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountsMerge {



	public List<List<String>> accountsMergeDFS(List<List<String>> accounts) {
		Map<String, Set<String>> graph = new HashMap<>();
		Map<String, String> emailToName = new HashMap<>();

//		// Build graph
//		for (List<String> account : accounts) {
//			String name = account.get(0);
//			for (int i = 1; i < account.size(); i++) {
//				String email = account.get(i);
//				graph.putIfAbsent(email, new HashSet<>());
//				emailToName.put(email, name);
//
//				if (i > 1) {
//					String prevEmail = account.get(i - 1);
//					graph.get(email).add(prevEmail);
//					graph.get(prevEmail).add(email);
//				}
//			}
//		}

		// Build graph
		for (List<String> account : accounts) {
			String name = account.get(0);
			String firstEmail = account.get(1);

			graph.putIfAbsent(firstEmail, new HashSet<>());

			for (int i = 1; i < account.size(); i++) {
				String email = account.get(i);

				graph.putIfAbsent(email, new HashSet<>());

				// connect emails
				graph.get(firstEmail).add(email);
				graph.get(email).add(firstEmail);

				emailToName.put(email, name);
			}
		}

		Set<String> visited = new HashSet<>();
		List<List<String>> result = new ArrayList<>();

		// DFS each component
		for (String email : graph.keySet()) {
			if (!visited.contains(email)) {
				List<String> component = new ArrayList<>();
				dfs(email, graph, visited, component);
				Collections.sort(component);
				component.add(0, emailToName.get(email));
				result.add(component);
			}
		}

		return result;
	}

	private void dfs(String email, Map<String, Set<String>> graph,
					 Set<String> visited, List<String> component) {
		visited.add(email);
		component.add(email);

		for (String neighbor : graph.get(email)) {
			if (!visited.contains(neighbor)) {
				dfs(neighbor, graph, visited, component);
			}
		}
	}

	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		UnionFind uf = new UnionFind(accounts.size());

		Map<String, Integer> emailToAcc = new HashMap<>();

		for (int i = 0; i < accounts.size(); i++) {
			for (int j = 1; j < accounts.get(i).size(); j++) {
				String email = accounts.get(i).get(j);

				if (emailToAcc.containsKey(email)) {
					int prevParent = emailToAcc.get(email);
					uf.union(prevParent, i);
				} else {
					emailToAcc.put(email, i);
				}
			}
		}

		Map<Integer, Set<String>> emailsForAcc = new HashMap<>();

		for (int i = 0; i < accounts.size(); i++) {
			int parent = uf.find(i);
			List<String> emails = accounts.get(i);

			emailsForAcc.putIfAbsent(parent, new HashSet<>());
			emailsForAcc.get(parent).addAll(emails.subList(1, emails.size()));
		}

		List<List<String>> result = new ArrayList<>();

		for (int key : emailsForAcc.keySet()) {
			List<String> temp = new ArrayList<>();
			temp.addAll(emailsForAcc.get(key));
			Collections.sort(temp);
			temp.add(0, accounts.get(key).get(0));
			result.add(temp);
		}

		return result;
	}

	private class UnionFind {
		private int[] parents;

		public UnionFind(int n) {
			parents = new int[n];

			for (int i = 0; i < parents.length; i++) {
				parents[i] = i;
			}
		}

		public int find(int node) {
			if (parents[node] == node) {
				return node;
			}
			parents[node] = find(parents[parents[node]]);
			return parents[node];
		}

		public void union(int i, int j) {
			int p1 = find(i), p2 = find(j);
			if (p1 == p2) {
				return;
			}
			parents[p2] = p1;
		}
	}

	public static void main(String[] args) {
		String[][] accounts = {
				{ "John", "johnsmith@mail.com", "john_newyork@mail.com" },
				{ "John", "johna@mail.com", "johnsmith@mail.com", "john00@mail.com" },
				{ "Mary", "mary@mail.com" },
				{ "John", "johnnybravo@mail.com" } };
		List<List<String>> resList = new ArrayList<>();
		for (String[] rows : accounts) {
			resList.add(Arrays.stream(rows).collect(Collectors.toList()));
		}
		AccountsMerge merge = new AccountsMerge();
		//merge.accountsMerge(resList);
		merge.accountsMergeDFS(resList);

	}

}
