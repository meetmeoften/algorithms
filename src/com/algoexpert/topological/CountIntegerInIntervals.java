package com.algoexpert.topological;

import java.util.TreeMap;

public class CountIntegerInIntervals {


    private TreeMap<Integer, Integer> map;
    private int count;

    public CountIntegerInIntervals() {
        map = new TreeMap<>();
        count = 0;
    }

    public void add(int left, int right) {
        Integer start = map.floorKey(right);
        while (start != null && map.get(start) >= left) {
            int end = map.get(start);
            left = Math.min(left, start);
            right = Math.max(right, end);
            count -= (end - start + 1);
            map.remove(start);
            start = map.floorKey(right);
        }
        map.put(left, right);
        count += right - left + 1;
    }

    public void remove(int left, int right) {
        Integer start = map.floorKey(right);
        while (start != null) {
            int end = map.get(start);
            if (end < left)
                break;
            map.remove(start);
            count -= end - start + 1;
            // left portion remains
            if (start < left) {
                map.put(start, left - 1);
                count += left - start;
            }
            // right portion remains
            if (end > right) {
                map.put(right + 1, end);
                count += end - right;
            }
            start = map.floorKey(right);
        }
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        CountIntegerInIntervals obj = new CountIntegerInIntervals();
        obj.add(2, 3);
        System.out.println(obj.count()); // 2

        obj.add(7, 10);
        System.out.println(obj.count()); // 6

        obj.add(5, 8);
        System.out.println(obj.count()); // 8

        obj.add(1, 6);
        System.out.println(obj.count()); // 10
    }

}
