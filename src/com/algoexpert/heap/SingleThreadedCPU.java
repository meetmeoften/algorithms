package com.algoexpert.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SingleThreadedCPU {

	public static int[] getOrder(int[][] tasks) {

		// Arrays.sort(tasks);
		int time = 1;

		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		int[] task = tasks[0];

		int taskTime = task[0];
		int processingTime = task[1];
		q.offer(new int[] { taskTime, processingTime, 0 });

		int index = 1;
		List<Integer> res = new ArrayList<>();

		while (!q.isEmpty()) {
			int[] qTask = q.poll();
			int qTaskTime = qTask[1];
			res.add(qTask[2]);
			time += qTaskTime;

			taskTime = Integer.MIN_VALUE;
			while (time >= taskTime) {
				task = tasks[index];
				taskTime = task[0];
				processingTime = task[1];

				if (time >= taskTime) {
					q.offer(new int[] { taskTime, processingTime, index });
					index++;
				}
			}
		}
		return null;
	}

	public static int[] getOrder2(int[][] tasks) {

		int[][] newTasks = new int[tasks.length][3];
		for (int i = 0; i < tasks.length; i++) {
			newTasks[i][0] = tasks[i][0];
			newTasks[i][1] = tasks[i][1];
			newTasks[i][2] = i;
		}

		Arrays.sort(newTasks, Comparator.comparingInt(a -> a[0]));

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] != b[0] ? (a[0] - b[0]) : (a[1] - b[1])));
		int[] ans = new int[tasks.length];
		int ansIndex = 0, currTime = 0;

		for (int i = 0; i < newTasks.length; i++) {
			while (currTime < newTasks[i][0] && !pq.isEmpty()) {
				int[] topTask = pq.remove();
				ans[ansIndex++] = topTask[1];
				currTime += topTask[0];
			}
			currTime = Math.max(currTime, newTasks[i][0]);
			pq.add(new int[]{newTasks[i][1], newTasks[i][2]});
		}
		while (!pq.isEmpty()) {
			int[] topTask = pq.remove();
			ans[ansIndex++] = topTask[1];
		}

		return ans;
	}

	public static void main(String[] args) {
		int[][] tasks = { { 1, 2 }, { 2, 4 }, { 3, 2 }, { 4, 1 } };
		getOrder2(tasks);
	}

}
