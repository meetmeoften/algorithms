package com.google;

public class SegmentTreeRangeModule {
    private class Node {
        Node left, right;
        boolean tracked;
        boolean lazy;
    }

    private final int START = 0;
    private final int END = (int) 1e9;
    private Node root;

    public SegmentTreeRangeModule() {
        root = new Node();
    }

    public void addRange(int left, int right) {
        update(root, START, END, left, right - 1, true);
    }

    public boolean queryRange(int left, int right) {
        return query(root, START, END, left, right - 1);
    }

    public void removeRange(int left, int right) {
        update(root, START, END, left, right - 1, false);
    }

    private void update(Node node, int start, int end, int l, int r, boolean val) {
        if (r < start || end < l) return;

        if (l <= start && end <= r) {
            node.tracked = val;
            node.lazy = true;
            node.left = null;
            node.right = null;
            return;
        }

        pushDown(node);

        int mid = start + (end - start) / 2;
        update(node.left, start, mid, l, r, val);
        update(node.right, mid + 1, end, l, r, val);
        node.tracked = node.left.tracked && node.right.tracked;
    }

    private boolean query(Node node, int start, int end, int l, int r) {
        if (r < start || end < l) return true;
        if (l <= start && end <= r) return node.tracked;

        pushDown(node);

        int mid = start + (end - start) / 2;
        return query(node.left, start, mid, l, r) &&
                query(node.right, mid + 1, end, l, r);
    }

    private void pushDown(Node node) {
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();

        if (node.lazy) {
            node.left.tracked = node.right.tracked = node.tracked;
            node.left.lazy = node.right.lazy = true;
            node.left.left = node.left.right = null;
            node.right.left = node.right.right = null;
            node.lazy = false;
        }
    }

    public static void main(String[] args) {
        SegmentTreeRangeModule rm = new SegmentTreeRangeModule();

        rm.addRange(10, 20);
        System.out.println(rm.queryRange(10, 14)); // true
        System.out.println(rm.queryRange(13, 15)); // true

        rm.removeRange(14, 16);
        System.out.println(rm.queryRange(13, 15)); // false

        rm.addRange(18, 25);
        System.out.println(rm.queryRange(20, 24)); // true
        System.out.println(rm.queryRange(15, 18)); // false
    }
}
