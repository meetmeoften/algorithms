package com.neetcode.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

public class LongestSubArray {

    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        int left = 0;
        int res = 0;

        for (int right = 0; right < nums.length; right++) {
            // maintain maxDeque (decreasing)
            while (!maxDeque.isEmpty() && nums[right] > maxDeque.peekLast()) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(nums[right]);

            // maintain minDeque (increasing)
            while (!minDeque.isEmpty() && nums[right] < minDeque.peekLast()) {
                minDeque.pollLast();
            }
            minDeque.offerLast(nums[right]);

            // shrink window if invalid
            while (maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
                if (nums[left] == maxDeque.peekFirst()) {
                    maxDeque.pollFirst();
                }
                if (nums[left] == minDeque.peekFirst()) {
                    minDeque.pollFirst();
                }
                left++;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }


    public int longestSubarray2(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        int left = 0;
        int res = 0;

        for (int right = 0; right < nums.length; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }

    static void main(String[] args) {
        LongestSubArray solution = new LongestSubArray();
        solution.longestSubarray2(new int[] {8,2,4,7}, 4);
    }

}
