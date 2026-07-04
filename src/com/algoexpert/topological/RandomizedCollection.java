package com.algoexpert.topological;

import java.util.*;

class RandomizedCollection {

    private List<Integer> nums;
    private Map<Integer, Set<Integer>> map;
    private Random random;

    public RandomizedCollection() {
        nums = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {

        boolean firstOccurrence = !map.containsKey(val);

        map.putIfAbsent(val, new HashSet<>());

        nums.add(val);

        map.get(val).add(nums.size() - 1);

        return firstOccurrence;
    }

    public boolean remove(int val) {

        if (!map.containsKey(val))
            return false;

        // Get one index of val
        int removeIndex = map.get(val).iterator().next();

        // Last element
        int lastVal = nums.get(nums.size() - 1);

        // Move last element to removeIndex
        nums.set(removeIndex, lastVal);

        // Update lastVal indices
        map.get(lastVal).add(removeIndex);
        map.get(lastVal).remove(nums.size() - 1);

        // Remove removeIndex from val set
        map.get(val).remove(removeIndex);

        // Remove last element
        nums.remove(nums.size() - 1);

        // Cleanup
        if (map.get(val).isEmpty())
            map.remove(val);

        return true;
    }

    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }

    public static void main(String[] args) {

        RandomizedCollection rc = new RandomizedCollection();

        System.out.println(rc.insert(1));   // true
        System.out.println(rc.insert(1));   // false
        System.out.println(rc.insert(2));   // true

        System.out.println(rc.getRandom());

        System.out.println(rc.remove(1));   // true

        System.out.println(rc.getRandom());
    }
}
