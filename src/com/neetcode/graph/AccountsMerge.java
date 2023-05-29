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
				{ "John", "johnsmith@mail.com", "john00@mail.com" },
				{ "Mary", "mary@mail.com" },
				{ "John", "johnnybravo@mail.com" } };
		List<List<String>> resList = new ArrayList<>();
		for (String[] rows : accounts) {
			resList.add(Arrays.stream(rows).collect(Collectors.toList()));
		}
		AccountsMerge merge = new AccountsMerge();
		merge.accountsMerge(resList);

	}

}
