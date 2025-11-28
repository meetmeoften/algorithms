package com.google;

public class RLEIterator {


    private int[] encoding;
    private int index;


    public RLEIterator(int[] encoding) {
        this.encoding = encoding;
        index = 0;
    }

    // 3, 8, 0, 9, 2, 5
    // 8, 8, 8, 5, 5

    public int next(int n) {
        while(index < encoding.length && n > 0) {
            if(encoding[index] >= n) {
                encoding[index] -= n;
                n =0;
                return encoding[index+1];
            } else {
                n -= encoding[index];
                encoding[index] = 0;
                index = index + 2;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        RLEIterator rleIterator = new RLEIterator(new int[]{3, 8, 0, 9, 2, 5});
        System.out.println(rleIterator.next(2));
        System.out.println(rleIterator.next(1));
        System.out.println(rleIterator.next(1));
        System.out.println(rleIterator.next(2));
    }
}
