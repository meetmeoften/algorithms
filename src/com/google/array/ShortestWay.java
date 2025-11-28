package com.google.array;


import java.util.ArrayList;// 1055 leetcode
import java.util.Collections;
import java.util.List;

public class ShortestWay {

    public int shortestWay(String source, String target) {
        int m = source.length(), n = target.length();
        int count = 0, tIdx = 0;

        // Quick check: if any target char isn't in source, it's impossible
        boolean[] has = new boolean[26];
        for (char c : source.toCharArray()) has[c - 'a'] = true;
        for (char c : target.toCharArray()) {
            if (!has[c - 'a']) return -1;
        }

        // While we haven't matched all target characters
        while (tIdx < n) {
            boolean progressed = false;
            for (int sIdx = 0; sIdx < m && tIdx < n; sIdx++) {
                if (source.charAt(sIdx) == target.charAt(tIdx)) {
                    tIdx++;
                    progressed = true;
                }
            }
            if (!progressed) return -1;  // stuck — can't match more
            count++;
        }
        return count;
    }

    public int shortestWay2(String source, String target) {
        // Build character → sorted positions map
        List<List<Integer>> pos = new ArrayList<>();
        for (int i = 0; i < 26; i++) pos.add(new ArrayList<>());
        for (int i = 0; i < source.length(); i++) {
            pos.get(source.charAt(i) - 'a').add(i);
        }

        int count = 1, currIdx = -1;
        for (char c : target.toCharArray()) {
            List<Integer> list = pos.get(c - 'a');
            if (list.isEmpty()) return -1;

            // Find first occurrence > currIdx
            int i = Collections.binarySearch(list, currIdx + 1);
            if (i < 0) i = -i - 1;

            if (i == list.size()) {
                // wrap: start a new subsequence
                count++;
                currIdx = list.get(0);
            } else {
                currIdx = list.get(i);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String source = "xyz", target = "xzyxz";
        new ShortestWay().shortestWay2(source, target);
    }
}
