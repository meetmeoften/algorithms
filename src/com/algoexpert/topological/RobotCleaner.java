package com.algoexpert.topological;

import java.util.HashSet;
import java.util.Set;


class RobotCleaner {
    private Set<String> visited = new HashSet<>();
    private int[][] dirs = {
            {-1, 0}, // up
            {0, 1},  // right
            {1, 0},  // down
            {0, -1}  // left
    };

    public void cleanRoom(Robot robot) {
        dfs(robot, 0, 0, 0);
    }

    private void dfs(Robot robot, int row, int col, int dir) {
        String key = row + "," + col;
        if (visited.contains(key)) {
            return;
        }
        visited.add(key);
        robot.clean();
        for (int i = 0; i < 4; i++) {
            int newDir = (dir + i) % 4;
            int newRow = row + dirs[newDir][0];
            int newCol = col + dirs[newDir][1];
            String newKey = newRow + "," + newCol;
            if (!visited.contains(newKey) && robot.move()) {
                dfs(robot, newRow, newCol, newDir);
                // come back to previous cell
                goBack(robot);
            }
            // rotate to next direction
            robot.turnRight();
        }
    }

    private void goBack(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    public static void main(String[] args) {
        /*
            1 = open cell
            0 = blocked cell

            . . . .
            . . # .
            . . R .
            . . . .
        */

        int[][] room = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };

        int[][] room2 = {
                {1, 1},
                {0, 1}
        };

        int startRow = 1;
        int startCol = 1;
        RobotImpl robot = new RobotImpl(room2, startRow, startCol);
        RobotCleaner solution = new RobotCleaner();
        solution.cleanRoom(robot);
        robot.printRoom();
    }
}
