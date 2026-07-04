package com.algoexpert.topological;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {

    Map<Integer, Integer> values;
    Map<Integer, Integer> counts;
    Map<Integer, LinkedHashSet<Integer>> freqMap;
    int capacity;
    int minFreq;

    LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        values = new HashMap<>();
        counts = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        if (values.containsKey(key)) {
            values.put(key, value);
            get(key);
            return;
        }
        if (values.size() >= capacity) {
            LinkedHashSet<Integer> minFreqKeys = freqMap.get(minFreq);
            int evictKey = minFreqKeys.iterator().next();
            minFreqKeys.remove(evictKey);
            values.remove(evictKey);
            counts.remove(evictKey);
        }
        values.put(key, value);
        counts.put(key, 1);
        freqMap.putIfAbsent(1, new LinkedHashSet<>());
        freqMap.get(1).add(key);
        minFreq = 1;
    }

    public int get(int key) {
        if (!values.containsKey(key)) {
            return -1;
        }
        Integer freq = counts.get(key);
        freqMap.get(freq).remove(key);
        // update minFreq
        if (freq == minFreq && freqMap.get(freq).isEmpty()) {
            minFreq++;
        }
        // increase freq
        counts.put(key, freq + 1);
        freqMap.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqMap.get(freq + 1).add(key);
        return values.get(key);
    }

    public static void main(String[] args) {

        LFUCache cache = new LFUCache(2);

        cache.put(1, 10);
        cache.put(2, 20);

        System.out.println(cache.get(1)); // 10

        cache.put(3, 30); // evicts key 2

        System.out.println(cache.get(2)); // -1
        System.out.println(cache.get(3)); // 30
    }
}
