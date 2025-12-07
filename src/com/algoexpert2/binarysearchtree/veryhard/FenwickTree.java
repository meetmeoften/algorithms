package com.algoexpert2.binarysearchtree.veryhard;

public class FenwickTree {
    private final int[] tree;
    private final int n;

    // --- Constructor ---
    public FenwickTree(int n) {
        this.n = n;
        this.tree = new int[n + 1];
    }

    // --- Build in O(n) from array ---
    public FenwickTree(int[] arr) {
        this(arr.length);
        for (int i = 1; i <= n; i++) {
            tree[i] += arr[i - 1];
            int j = i + (i & -i);
            if (j <= n) tree[j] += tree[i];
        }
    }

    // --- Point update: adds value to index i ---
    public void update(int i, int delta) {
        while (i <= n) {
            tree[i] += delta;
            i += (i & -i);
        }
    }

    // --- Prefix sum: sum of arr[1..i] ---
    public int prefixSum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= (i & -i);
        }
        return sum;
    }

    // --- Range sum: arr[l..r] ---
    public int rangeSum(int l, int r) {
        return prefixSum(r) - prefixSum(l - 1);
    }

    // --- Get exact value at index i ---
    // (Only works if updates are point-add operations)
    public int getValue(int i) {
        return rangeSum(i, i);
    }

    // --- Find smallest index such that prefixSum(idx) >= target ---
    // Order Statistics: k-th smallest element
    public int findByPrefix(int target) {
        int idx = 0;
        int bitMask = highestPowerOfTwo(n);

        while (bitMask != 0) {
            int next = idx + bitMask;
            if (next <= n && tree[next] < target) {
                target -= tree[next];
                idx = next;
            }
            bitMask >>= 1;
        }
        return idx + 1;
    }

    private int highestPowerOfTwo(int x) {
        return Integer.highestOneBit(x);
    }
}

