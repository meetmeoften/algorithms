package com.algoexpert.topological;

class RobotImpl implements Robot {

    private int[][] room;
    private int row;
    private int col;
    private int dir;

    // 0 = up, 1 = right, 2 = down, 3 = left
    private int[][] dirs = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    public RobotImpl(int[][] room, int startRow, int startCol) {
        this.room = room;
        this.row = startRow;
        this.col = startCol;
        this.dir = 0; // initially facing up
    }

    @Override
    public boolean move() {
        int newRow = row + dirs[dir][0];
        int newCol = col + dirs[dir][1];

        if (newRow < 0 || newRow >= room.length ||
                newCol < 0 || newCol >= room[0].length ||
                room[newRow][newCol] == 0) {
            return false;
        }

        row = newRow;
        col = newCol;
        return true;
    }

    @Override
    public void turnLeft() {
        dir = (dir + 3) % 4;
    }

    @Override
    public void turnRight() {
        dir = (dir + 1) % 4;
    }

    @Override
    public void clean() {
        room[row][col] = 2;
        System.out.println("Cleaned cell: (" + row + "," + col + ")");
    }

    public void printRoom() {
        System.out.println("\nFinal room:");
        for (int[] r : room) {
            for (int cell : r) {
                if (cell == 0) System.out.print("# ");
                else if (cell == 2) System.out.print("C ");
                else System.out.print(". ");
            }
            System.out.println();
        }
    }
}

