package com.algoexpert.topological;

public class FenwickTree{

    int[] bit;
    int n;

    FenwickTree(int size) {
        n = size;
        bit = new int[n + 1];
    }

    void update(int index, int value) {
        index++;
        while(index <= n) {
            bit[index] += value;
            index += index & (-index);
        }
    }

    public int query(int index) {
       index++;
       int sum = 0;
       while(index > 0) {
           sum += bit[index];
           index -= index & (-index);
       }
       return sum;
    }

    // Range sum
    int rangeQuery(int left, int right) {
        return query(right) - query(left - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7};
        FenwickTree ft = new FenwickTree(arr.length);

        for(int i= 0; i < arr.length; i++) {
            ft.update(i, arr[i]);
        }

        ft.rangeQuery(0, 2);
    }
}
