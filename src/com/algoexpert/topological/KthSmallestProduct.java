package com.algoexpert.topological;

public class KthSmallestProduct {

    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long low = -10000000000L;
        long high = 10000000000L;

        while (low < high) {
            long mid = low + (high - low) / 2;
            long count = countProducts(nums1, nums2, mid);
            if (count >= k)
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }


    private long countProducts(int[] nums1, int[] nums2, long x) {
        long count = 0;
        for (long a : nums1) {
            if (a > 0) {
                count += upperBound(nums2, x / a);
            } else if (a < 0) {
                // For negative numbers, inequality flips
                count += nums2.length -
                        lowerBound(nums2, (long) Math.ceil((double) x / a));
            } else {
                // a = 0
                if (x >= 0)
                    count += nums2.length;
            }
        }
        return count;
    }


    private int upperBound(int[] arr, long target) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (arr[m] <= target)
                l = m + 1;
            else
                r = m;
        }
        return l;
    }


    private int lowerBound(int[] arr, long target) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (arr[m] < target)
                l = m + 1;
            else
                r = m;
        }
        return l;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 3};
        int[] nums2 = {5, 4};
        long k = 2;
        KthSmallestProduct sol = new KthSmallestProduct();
        long result = sol.kthSmallestProduct(nums1, nums2, k);
        System.out.println(result);
    }
}
