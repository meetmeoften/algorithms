package com.algoexpert2.binarysearchtree.veryhard;

import java.util.*;

public class FenwickRightSmaller {

    public static List<Integer> rightSmallerThan(List<Integer> array) {
        int n = array.size();
        if (n == 0) return new ArrayList<>();

        // 1. Coordinate compression
        List<Integer> sorted = new ArrayList<>(array);
        Collections.sort(sorted);
        Map<Integer, Integer> indexMap = new HashMap<>();

        int idx = 1; // Fenwick tree is 1-indexed
        for (int num : sorted) {
            if (!indexMap.containsKey(num)) {
                indexMap.put(num, idx++);
            }
        }

        // 2. Fenwick Tree structure
        Fenwick fenwick = new Fenwick(idx);
        List<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));

        // 3. Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            int comp = indexMap.get(array.get(i));
            int smallerCount = fenwick.query(comp - 1);
            result.set(i, smallerCount);
            fenwick.update(comp, 1);
        }

        return result;
    }

    // Fenwick Tree with sum operations
    static class Fenwick {
        int[] tree;

        Fenwick(int size) {
            tree = new int[size + 1];
        }

        void update(int i, int delta) {
            while (i < tree.length) {
                tree[i] += delta;
                //int temp = i & -i;
                i += i & -i;
            }
        }

        int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                //int temp = i & -i;
                i -= i & -i;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        var array = Arrays.asList(8, 5, 11, -1);
        var expected = Arrays.asList(5, 4, 4, 0);
        var actual = rightSmallerThan(array);
    }
}

