package com.algoexpert.intuit;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRooms2 {

	public static int minMeetingRooms(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}

		Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);

		PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> i1[1] - i2[1]);

		for (int[] interval : intervals) {
			if (!pq.isEmpty()) {
				int[] endsNext = pq.poll();

				if (interval[0] < endsNext[1]) {
					pq.offer(endsNext);
				}
			}
			pq.offer(interval);
		}
		return pq.size();
	}

	/**
	 * Input: intervals = [(0,30),(5,10),(15,20)]
		Output: 2
		Explanation:
		We need two meeting rooms
		room1: (0,30)
		room2: (5,10),(15,20)
	 */


	public static void main(String[] args ) {
		int[][] arr = new int[][]{{0,30},{5,10},{15,20}};
		minMeetingRooms(arr);
	}
}
