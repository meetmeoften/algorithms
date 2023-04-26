package com.algoexpert.binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedKeyValue {

	static class Pair {
		public String value;
		public int time;

		public Pair(String value, int time) {
			this.value = value;
			this.time = time;
		}
	}


	Map<String, List<Pair>> timeMap;

	public TimeBasedKeyValue() {
		timeMap = new HashMap<>();
	}

	public void set(String key, String value, int timestamp) {
		if (!timeMap.containsKey(key)) {
			timeMap.put(key, new ArrayList<>());
		}
		timeMap.get(key).add(new Pair(value, timestamp));
	}

	public String get(String key, int timestamp) {
		List<Pair> values = timeMap.getOrDefault(key, new ArrayList<>());
		String result = "";

		int l = 0, r = values.size() - 1;
		while (l <= r) {
			int m = (l + r) / 2;
			if (values.get(m).time == timestamp) {
				result = values.get(m).value;
				return result;
			} else if (values.get(m).time < timestamp) {
				result = values.get(m).value;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		TimeBasedKeyValue keyValue = new TimeBasedKeyValue();
		keyValue.set("foo", "bar", 1);
		keyValue.get("foo", 1);
		keyValue.get("foo", 3);
	}




}


