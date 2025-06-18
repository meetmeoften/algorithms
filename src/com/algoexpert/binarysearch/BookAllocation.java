package com.algoexpert.binarysearch;

import java.util.Arrays;

public class BookAllocation {

    public static void main(String[] args) {
        int[] books = {12, 34, 67, 90};
        //int[] books = {2, 4, 6, 8};
        int students = 2;

        System.out.println("Minimum possible maximum pages: " + allocateBooks(books, students));
    }

    private static int allocateBooks(int[] books, int students) {

        int low = Arrays.stream(books).max().getAsInt();
        int high = Arrays.stream(books).sum();
        int result = -1;

        while(low <= high) {
            int mid = low + (high - low) / 2;

            if (isPossible(books, students, mid)) {
                high = mid  - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static boolean isPossible(int[] books, int students, int maxPages) {
        int sum = 0;
        int count = 1;
        for(int book: books) {
            if(book > maxPages) {
                return false;
            }

            if(sum + book > maxPages) {
                count++;
                sum =0;
            }
            sum+= book;
        }

        if(count > students) {
            return false;
        }

        return true;
    }
}
