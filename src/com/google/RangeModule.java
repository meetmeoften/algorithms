package com.google;

import java.util.TreeMap;

public class RangeModule {

    private TreeMap<Integer, Integer> ranges;

    public RangeModule() {
        ranges = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        Integer l = ranges.floorKey(left);
        if (l != null && ranges.get(l) >= left) {
            left = l;
        }
        Integer r = ranges.floorKey(right);
        if (r != null && ranges.get(r) > right) {
            right = ranges.get(r);
        }
        ranges.subMap(left, right).clear();
        ranges.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        Integer l = ranges.floorKey(left);
        return l != null && ranges.get(l) >= right;
    }

    public void removeRange(int left, int right) {
        Integer l = ranges.floorKey(left);
        Integer r = ranges.floorKey(right);

        if (r != null && ranges.get(r) > right) {
            ranges.put(right, ranges.get(r));
        }
        if (l != null && ranges.get(l) > left) {
            ranges.put(l, left);
        }
        ranges.subMap(left, right).clear();
    }
}
