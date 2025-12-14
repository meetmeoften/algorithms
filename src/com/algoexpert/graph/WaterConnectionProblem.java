package com.algoexpert.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WaterConnectionProblem {

    private static void waterConnectionProblem(int n, int[] in, int[] to, int[] diam) {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (in[i] == 0 && to[i] != 0) {
                int curr = i;
                int minDiam = Integer.MAX_VALUE;

                while (to[curr] != 0) {
                    minDiam = Math.min(minDiam, diam[curr]);
                    curr = to[curr];
                }

                sb.append(i).append(" ")
                        .append(curr).append(" ")
                        .append(minDiam).append("\n");
                count++;
            }
        }

        System.out.println(count);
        System.out.print(sb.toString());
    }


    public static void main(String[] args) {

        /* -------- FIXED INPUT ARRAY --------
           Format:
           n, p,
           a1, b1, d1,
           a2, b2, d2, ...
        */
        int[] input = {
                9, 3,
                7, 4, 98,
                5, 9, 72,
                4, 6, 10
        };

        int idx = 0;
        int n = input[idx++];   // number of houses
        int p = input[idx++];   // number of pipes

        int[] to = new int[n + 1];
        int[] diam = new int[n + 1];
        int[] in = new int[n + 1];

        for (int i = 0; i < p; i++) {
            int a = input[idx++];
            int b = input[idx++];
            int d = input[idx++];

            to[a] = b;
            diam[a] = d;
            in[b]++;
        }
        waterConnectionProblem(n, in, to, diam);
    }
}


