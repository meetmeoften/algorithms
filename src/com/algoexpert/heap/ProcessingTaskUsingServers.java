package com.algoexpert.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class ProcessingTaskUsingServers {

	public static int[] assignTasks(int[] servers, int[] tasks) {
		Queue<int[]> free = new PriorityQueue<>((a, b) -> a[1] == b[1] ? (a[0] - b[0]) : (a[1] - b[1]));
		Queue<int[]> busy = new PriorityQueue<>((a, b) -> a[1] - b[1]);

		int ans[] = new int[tasks.length];

		for (int i = 0; i < servers.length; i++) {
			free.add(new int[] { i, servers[i] });
		}

		int curTime = 0;
		int i = 0;
		while (i < tasks.length) {
			while (!busy.isEmpty() && busy.peek()[1] <= curTime) {
				int id = busy.poll()[0];
				free.add(new int[] { id, servers[id] });
			}
			while (i < tasks.length && i <= curTime && !free.isEmpty()) {
				int id = free.poll()[0];
				busy.add(new int[] { id, curTime + tasks[i] });
				ans[i++] = id;
			}

			if (free.isEmpty()) {
				curTime = busy.peek()[1];
			} else {
				curTime++;
			}

		}

		return ans;
	}

	public static void main(String[] args) {
		int[] servers = { 3, 3, 2 }, tasks = { 1, 2, 3, 2, 1, 2 };
		assignTasks(servers, tasks);
	}

}
