package com.google;

import java.util.TreeMap;

public class SnapshotArray {

    int id = 0;
    TreeMap<Integer, Integer>[] map;

    public SnapshotArray(int length) {
        map = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            map[i] = new TreeMap<Integer, Integer>();
            map[i].put(0, 0);
        }
    }

    public void set(int index, int val) {
        map[index].put(id, val);
    }

    public int snap() {
        return id++;
    }

    public int get(int index, int snap_id) {
        return map[index].floorEntry(snap_id).getValue();
    }

    public static void main(String[] args) {
        SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
        snapshotArr.set(0,5);  // Set array[0] = 5
        snapshotArr.snap();  // Take a snapshot, return snap_id = 0
        snapshotArr.set(0,6);
        snapshotArr.get(0,0);
    }
}
