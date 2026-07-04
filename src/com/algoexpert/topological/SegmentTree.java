package com.algoexpert.topological;

public class SegmentTree {

    int[] tree;
    int n;

    SegmentTree(int[] arr) {
        n= arr.length;
        tree = new int[4 * n];
        build(arr, 0, 0, n-1);
    }

    public void build(int[] arr, int node, int start, int end ) {
        if(start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end)/2;
        build(arr, 2 * node + 1, start, mid);
        build(arr, 2 * node + 2, mid+1, end);
        tree[node] = tree[2 * node + 1] +  tree[2 * node + 2] ;
    }

    public int query(int node, int start,  int end, int left, int right) {
        if(right < start || end < left) return 0;
        if(left <= start && end <= right) return tree[node];
        int mid = (start + end)/2;
        int p1 = query(2 * node + 1, start, mid, left, right);
        int p2 = query(2 * node + 2, mid+ 1, end, left, right);
        return p1+p2;
    }

    public void update(int node, int start, int end, int idx, int val) {
        if(start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end)/2;
        if(idx <= mid) {
            update(2 * node + 1, start, mid, idx, val);
        } else {
            update(2 * node + 1, start, mid, idx, val);
        }
        tree[node] = tree[2 * node + 1] +  tree[2 * node + 2] ;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7};
        SegmentTree st = new SegmentTree(arr);
        System.out.println(st.tree);
    }
}
