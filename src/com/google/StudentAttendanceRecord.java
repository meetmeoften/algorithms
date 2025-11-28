package com.google;

public class StudentAttendanceRecord {

    public int checkRecord(int n) {
        int absent = 2;
        int leave = 3;
        return dfs(n, 0, 0);
    }

    public int dfs(int n, int absent, int leave) {
        if(absent >= 2 || leave >=3) {
            return 0;
        }

        if( n == 0) {
            return 1;
        }

        int count =0;
        count += dfs(n-1, absent, 0); // since consecutive days
        count += dfs(n-1, absent+1, 0);  // since consecutive days
        count += dfs(n-1, absent, leave+1);  // since consecutive days
        return count;
    }
}
