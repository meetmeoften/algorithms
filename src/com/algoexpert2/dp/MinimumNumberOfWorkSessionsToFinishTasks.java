package com.algoexpert2.dp;

import java.util.*;

public class MinimumNumberOfWorkSessionsToFinishTasks {

    int sessionTime;
    int[] tasks;
    Map<String, Integer> memo = new HashMap<>();

    public int minSessions(int[] tasks, int sessionTime) {
        Arrays.sort(tasks);
        // reverse sort (largest first)
        for (int i = 0, j = tasks.length - 1; i < j; i++, j--) {
            int tmp = tasks[i];
            tasks[i] = tasks[j];
            tasks[j] = tmp;
        }

        this.tasks = tasks;
        this.sessionTime = sessionTime;

        return dfs(0, new ArrayList<>());
    }

    private int dfs(int idx, List<Integer> sessions) {
        if (idx == tasks.length) {
            return sessions.size();
        }

        // Create memo key
        List<Integer> sorted = new ArrayList<>(sessions);
        Collections.sort(sorted);
        String key = idx + "|" + sorted.toString();

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int ans = Integer.MAX_VALUE;

        // Try placing task in existing sessions
        for (int i = 0; i < sessions.size(); i++) {
            if (sessions.get(i) + tasks[idx] <= sessionTime) {
                sessions.set(i, sessions.get(i) + tasks[idx]);
                ans = Math.min(ans, dfs(idx + 1, sessions));
                sessions.set(i, sessions.get(i) - tasks[idx]);
            }

            // Prune: avoid symmetric placements
            if (sessions.get(i) == 0) break;
        }

        // Start a new session
        sessions.add(tasks[idx]);
        ans = Math.min(ans, dfs(idx + 1, sessions));
        sessions.remove(sessions.size() - 1);

        memo.put(key, ans);
        return ans;
    }

    public static void main(String[] args) {
        MinimumNumberOfWorkSessionsToFinishTasks obj = new MinimumNumberOfWorkSessionsToFinishTasks();
        int[] tasks = {1, 2, 3};
        int sessionTime = 3;
        System.out.println(obj.minSessions(tasks, sessionTime));
    }

}
