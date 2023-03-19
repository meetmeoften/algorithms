package com.algoexpert.intervals;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingRoomsI {

	public class Interval {
		int start, end;

		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public boolean canAttendMeetings(List<Interval> intervals) {
		if (intervals == null || intervals.size() == 0) {
			return true;
		}

		Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);

		PriorityQueue<Interval> pq = new PriorityQueue<>((i1, i2) -> i1.end - i2.end);

		for (Interval interval : intervals) {
			if (!pq.isEmpty()) {
				Interval endsNext = pq.poll();

				if (interval.start < endsNext.end) {
					pq.offer(endsNext);
				}
			}
			pq.offer(interval);
		}
		return pq.size() == 1;

	}


	public static void main(String[] args) {
		MeetingRoomsI room = new MeetingRoomsI();
	}

}
