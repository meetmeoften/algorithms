package com.algoexpert.intuit;

import java.util.ArrayList;

public class DFS {

	static boolean[] visited;

	public ArrayList<Integer> dfsOfGraph(int v, ArrayList<ArrayList<Integer>> adj) {
		// Code here
		visited =  new boolean[v];
		ArrayList<Integer> res = new ArrayList<>();
		DFS(adj,0,res);
		return res;

		/**
        System.out.println(adj);
        boolean[] visited = new boolean[V];
        for(ArrayList<Integer> k: adj) {
            for(Integer val: k) {
                System.out.println(val);
            }
        }

        return result;
		 */
	}

	static void DFS(ArrayList<ArrayList<Integer>> adj,int s, ArrayList<Integer> res){
		visited[s] = true;
		res.add(s);
		System.out.println("S " + adj.get(s));
		for(int i:adj.get(s)){
			System.out.println("S " + s + "  I " + i);
			if(visited[i]==false){
				DFS(adj,i,res);
			}
		}
	}

}
