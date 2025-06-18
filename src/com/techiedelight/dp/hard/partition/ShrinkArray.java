package com.techiedelight.dp.hard.partition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShrinkArray {

    public static List<Integer> shrinkArray(List<Integer> arr, int k) {
        // Step 1: Sort the array
        Collections.sort(arr);

        // Step 2: Use a List to store the result (to modify in place)
        List<Integer> result = new ArrayList<>(arr);

        // Step 3: Iterate through the array to find and remove triplets
        int i = 0;
        while (i <= result.size() - 3) {
            // Check if a triplet (x, y, z) satisfies the condition
            int x = result.get(i);
            int y = result.get(i + 1);
            int z = result.get(i + 2);

            // If the triplet satisfies the condition: y = x + k and z = y + k
            if (y == x + k && z == y + k) {
                // Remove the triplet (x, y, z)
                result.remove(i);    // Remove x
                result.remove(i);    // Remove y (now at index i)
                result.remove(i);    // Remove z (now at index i)
                // Don't increment i, since we need to recheck the new triplet starting from index i
            } else {
                // If no triplet is found, move to the next element
                i++;
            }
        }

        return result;
    }

    // Example usage
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int k = 1;

        List<Integer> result = shrinkArray(arr, k);
        System.out.println("Array after removing triplets: " + result);
    }
}

