//package com.practice;
//
//import java.util.Arrays;
//import java.util.PriorityQueue;
//
//public class Maze2 {
//
//    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
//        int m  = maze.length;
//        int n = maze[0].length;
//
//        int[][] dist = new int[m][n];
//        for(int[] row: dist){ Arrays.fill(row, Integer.MAX_VALUE);}
//
//        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
//        pq.offer(new int[]{start[0], start[1], 0});
//        dist[start[0]][start[1]] = 0;
//
//        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
//
//        while (!pq.isEmpty()){
//            int[] cur = pq.poll();
//            int row = cur[0];
//            int col = cur[1];
//            int distance = cur[2];
//
//            if (distance > dist[row][col]) continue;
//            if (row == destination[0] && col == destination[1]) return distance;
//
//            for(int[] dir: dirs){
//                int nr = row;
//                int nc = col;
//                int steps = 0;
//
//                while(nr + dir[0] >=0 && nc + dir[1] >=0 && nr + dir[0] < m && nc + dir[1] < n && maze[nr+dir[0]][nc+dir[1]] == 0){
//                    nr += dir[0];
//                    nc += dir[1];
//                    steps++;
//                }
//                if(distance + steps < dist[nr][nc]){
//                    dist[nr][nc] = distance + steps;
//                    pq.offer(new int[]{nr, nc, dist[nr][nc]});
//                }
//            }
//        }
//
//    }
//}
