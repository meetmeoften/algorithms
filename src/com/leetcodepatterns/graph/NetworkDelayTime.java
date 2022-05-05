package com.leetcodepatterns.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class NetworkDelayTime {

	public int networkDelayTime(int[][] times, int N, int K) {
		Map<Integer, Map<Integer,Integer>> map = new HashMap<>();
		for(int[] time : times){
			map.putIfAbsent(time[0], new HashMap<>());
			map.get(time[0]).put(time[1], time[2]);
		}

		//distance, node into pq
		Queue<int[]> pq = new PriorityQueue<>((a,b) -> (a[0] - b[0]));

		pq.add(new int[]{0, K}); // distance and node

		boolean[] visited = new boolean[N+1];
		int res = 0;

		while(!pq.isEmpty()){
			int[] cur = pq.remove();
			int curNode = cur[1];
			int curDist = cur[0];
			if(visited[curNode]) {
				continue;
			}
			visited[curNode] = true;
			res = curDist;
			N--;
			if(map.containsKey(curNode)){
				for(int next : map.get(curNode).keySet()){
					pq.add(new int[]{curDist + map.get(curNode).get(next), next});
				}
			}
		}
		return N == 0 ? res : -1;

	}

	public int networkDelayTime_BF(int[][] times, int N, int K) {
		double[] disTo = new double[N];
		Arrays.fill(disTo, Double.POSITIVE_INFINITY);
		disTo[K - 1] = 0;
		for (int i = 1; i < N; i++) {
			for (int[] edge : times) {
				int u = edge[0] - 1, v = edge[1] - 1, w = edge[2];
				disTo[v] = Math.min(disTo[v], disTo[u] + w);
			}
		}
		double res = Double.MIN_VALUE;
		for (double i: disTo) {
			res = Math.max(i, res);
		}
		return res == Double.POSITIVE_INFINITY ? -1 : (int) res;
	}

	public static void main(String[] args) {
		int[][] flights = new int[][] {{2,1,1},{2,3,1},{3,4,1}};
		int n = 4,  k = 2;
		new NetworkDelayTime().networkDelayTime(flights, n, k );
	}

}
