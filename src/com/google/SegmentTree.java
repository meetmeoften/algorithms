package com.google;

public class SegmentTree {
    int[] tree;
    int n;

    public SegmentTree(int[] nums) {
        n = nums.length;
        tree = new int[4 * n];  // Safe size for segment tree array
        build(nums, 0, 0, n - 1);
    }

    // Build the tree from array nums
    private void build(int[] nums, int index, int left, int right) {
        if (left == right) {
            tree[index] = nums[left];
            return;
        }
        int mid = left + (right - left) / 2;
        build(nums, 2 * index + 1, left, mid);
        build(nums, 2 * index + 2, mid + 1, right);
        tree[index] = tree[2 * index + 1] + tree[2 * index + 2];
    }

    // Query sum in range [l, r]
    public int query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }

    private int query(int index, int left, int right, int l, int r) {
        if (r < left || l > right) return 0; // No overlap
        if (l <= left && right <= r) return tree[index]; // Total overlap

        int mid = left + (right - left) / 2;
        int leftSum = query(2 * index + 1, left, mid, l, r);
        int rightSum = query(2 * index + 2, mid + 1, right, l, r);
        return leftSum + rightSum;
    }

    // Update value at position pos to val
    public void update(int pos, int val) {
        update(0, 0, n - 1, pos, val);
    }

    private void update(int index, int left, int right, int pos, int val) {
        if (left == right) {
            tree[index] = val;
            return;
        }
        int mid = left + (right - left) / 2;
        if (pos <= mid) {
            update(2 * index + 1, left, mid, pos, val);
        } else {
            update(2 * index + 2, mid + 1, right, pos, val);
        }
        tree[index] = tree[2 * index + 1] + tree[2 * index + 2];
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7};
        SegmentTree segTree = new SegmentTree(nums);

        System.out.println(segTree.query(1, 3)); // Sum of nums[1..3] = 3+5+7 = 15

        segTree.update(1, 10); // nums[1] = 10

        System.out.println(segTree.query(1, 3)); // Sum now = 10+5+7 = 22
    }
}

