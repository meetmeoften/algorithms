package com.techiedelight.dp.medium.partition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivitySelection {


    public static void selectActivities(Activity[] activities) {
        // Sort activities based on finish time
        Arrays.sort(activities, (a, b) -> a.finish - b.finish);

        // The first activity is always selected
        System.out.println("Selected activities: ");
        System.out.println("Start: " + activities[0].start + ", Finish: " + activities[0].finish);
        int lastFinishTime = activities[0].finish;

        // Iterate over the rest of the activities
        for (int i = 1; i < activities.length; i++) {
            // If the start time of the current activity is greater than or equal to the finish time of the last selected activity
            if (activities[i].start >= lastFinishTime) {
                // Select the current activity
                System.out.println("Start: " + activities[i].start + ", Finish: " + activities[i].finish);
                lastFinishTime = activities[i].finish;  // Update the last finish time
            }
        }
    }

    public static int selectActivitiesDP(Activity[] activities) {
        int n = activities.length;
        if (n == 0) return 0;

        // Sort activities based on their finish times
        Arrays.sort(activities, (a, b) -> a.finish - b.finish);

        // dp[i] will hold the maximum number of activities that can be selected up to i-th activity
        int[] dp = new int[n];

        // Array to keep track of previous activity in optimal solution
        int[] prev = new int[n];
        Arrays.fill(prev, -1);

        // Initialize the dp array with the first activity being selected
        dp[0] = 1;

        // Fill the dp table
        for (int i = 1; i < n; i++) {
            // Option 1: Don't include current activity
            dp[i] = dp[i-1];
            prev[i] = i-1;

            // Option 2: Include current activity - find latest non-conflicting activity
            for (int j = i - 1; j >= 0; j--) {
                if (activities[j].finish <= activities[i].start) {
                    // If including activity i gives better result
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                    }
                    break; // Found the latest non-conflicting activity
                }
            }

            // If no previous activity was compatible
            if (prev[i] == -1 && dp[i] < 1) {
                dp[i] = 1;
                prev[i] = -1;
            }
        }

        // The result will be the maximum value in dp array (which should be at dp[n-1])
        int result = dp[n-1];

        // Now, reconstruct the selected activities
        List<Activity> selectedActivities = new ArrayList<>();
        int i = n - 1;
        while (i >= 0) {
            if (i == 0 || prev[i] != i-1) { // This activity is part of the solution
                selectedActivities.add(0, activities[i]); // Add to front to maintain order
                i = prev[i];
            } else {
                i = i-1;
            }
        }

        // Printing selected activities
        System.out.println("Selected Activities:");
        for (Activity activity : selectedActivities) {
            System.out.println("Start: " + activity.start + ", Finish: " + activity.finish);
        }

        return result;
    }

    public static void main(String[] args) {
        // Define activities with start and finish times
        Activity[] activities = {
                new Activity(1, 3),
                new Activity(2, 5),
                new Activity(4, 7),
                new Activity(6, 8),
                new Activity(5, 9),
                new Activity(8, 10)
        };

        // Number of activities
        int n = activities.length;

        // Get the maximum number of non-overlapping activities
        int maxActivities = selectActivitiesDP(activities);
        System.out.println("The maximum number of activities that can be selected is: " + maxActivities);
        selectActivities(activities);

        // Output the result
    }
}

class Activity {
    int start;
    int finish;

    // Constructor to initialize the start and finish times
    Activity(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }
}
