package com.google;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MinimumTimeDifference {

    public int findMinDifference(List<String> timePoints) {

        List<Integer> list = new ArrayList<>();
        for(String points: timePoints) {
            String[] pointInt = points.split(":");
            int hour = Integer.parseInt(pointInt[0]);
            int min = Integer.parseInt(pointInt[1]);

            list.add(hour * 60 + min);
        }
        Collections.sort(list);

        int minDiff = Integer.MAX_VALUE;
        for(int i=1; i < list.size()-1; i++) {
            minDiff = Math.min(minDiff, list.get(i) - list.get(i-1));
        }

        // Compare the first and last across midnight
        int first = list.get(0);
        int last = list.get(list.size() - 1);
        minDiff = Math.min(minDiff, (first + 1440) - last);
        return minDiff;
    }
}
