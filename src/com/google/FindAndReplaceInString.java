package com.google;

import java.util.HashMap;
import java.util.Map;

public class FindAndReplaceInString {

    public static String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        Map<Integer, Integer> map = new HashMap<>();
        // Check if each source matches with the substring at the corresponding index
        for (int i = 0; i < indices.length; i++) {
            String source = sources[i];
            int startIdx = indices[i];
            // Check if we can perform replacement (source matches substring of s)
            if (startIdx + source.length() <= s.length() &&
                    s.substring(startIdx, startIdx + source.length()).equals(source)) {
                map.put(startIdx, i);
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(i)) {
                int replaceIndex = map.get(i);
                result.append(targets[replaceIndex]);
                i += sources[replaceIndex].length() - 1;
            } else {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "abcd";
        int[] indices = {0, 2};
        String[] sources = {"a", "cd"};
        String[] targets = {"eee", "ffff"};
        findReplaceString(s, indices, sources, targets);
    }
}
