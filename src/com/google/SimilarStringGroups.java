package com.google;

public class SimilarStringGroups {

    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        boolean[] visited = new boolean[n];
        int groups = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(strs, visited, i);
                groups++;
            }
        }
        return groups;
    }

    public void dfs(String[] strs, boolean[] visited, int i) {
        visited[i] = true;
        for (int j = 0; j < strs.length; j++) {
            if (!visited[j] && isSimilar(strs[i], strs[j])) {
                dfs(strs, visited, j);
            }
        }
    }

    private boolean isSimilar(String a, String b) {
        if (a.length() != b.length()) return false;
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
            if (diff > 2) return false;
        }
        return diff == 0 || diff == 2;
    }

    public static void main(String[] args) {
        SimilarStringGroups similarStringGroups = new SimilarStringGroups();
        similarStringGroups.numSimilarGroups(new String[]{"tars", "rats", "arts", "star"});
    }
}
