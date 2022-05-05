package com.algoexpert.arrays;

import java.util.ArrayList;

public class ElementLeftSideSmaller {

	public int findElement(int a[], int n) {
		// Java program to find the element
		// which is greater than all left
		// elements and smaller than all
		// right elements.
		// Base case
		if (n == 1 || n == 2) {
			return -1;
		}

		// 1.element is the possible candidate for
		// the solution of the problem.
		// 2.idx is the index of the possible
		// candidate.
		// 3.maxx is the value which is maximum on the
		// left side of the array.
		// 4.bit tell whether the loop is
		// terminated from the if condition or from
		// the else condition when loop do not
		// satisfied the condition.
		// 5.check is the variable which tell whether the
		// element is updated or not
		int element = a[0], maxx = a[0], bit = -1, check = 0;
		int idx = -1;

		// The extreme two of the array can
		// not be the solution. Therefore
		// iterate the loop from i = 1 to < n-1
		for (int i = 1; i < (n - 1);) {

			// Here we find the possible candidate
			// where Element with left side smaller
			// and right side greater. When the if
			// condition fail we check and update in
			// else condition.
			if (a[i] < maxx && i < (n - 1)) {
				i++;
				bit = 0;
			}

			// Here we update the possible element
			// if the element is greater than the
			// maxx (maximum element so far). In
			// while loop we sur-pass the value which
			// is greater than the element
			else {
				if (a[i] >= maxx) {
					element = a[i];
					idx = i;
					check = 1;
					maxx = a[i];
				}
				if (check == 1) {
					i++;
				}
				bit = 1;

				while (a[i] >= element && i < (n - 1)) {
					if (a[i] > maxx) {
						maxx = a[i];
					}
					i++;
				}
				check = 0;
			}
		}

		// Checking for the last value and whether
		// the loop is terminated from else or
		// if block.
		if (element <= a[n - 1] && bit == 1) {
			return idx;
		} else {
			return -1;
		}

	}

	int method(int arr[], int n) {
		int[] left = new int[n];
		int[] right = new int[n];

		left[0] = Integer.MIN_VALUE;
		right[n - 1] = Integer.MAX_VALUE;

		for (int i = 0; i < n - 1; i++) {
			left[i + 1] = Math.max(left[i], arr[i]);
		}

		for (int i = n - 1; i > 0; i--) {
			right[i - 1] = Math.min(right[i], arr[i]);
		}

		for (int i = 1; i < n - 1; i++) {
			if (left[i] <= arr[i] && right[i] >= arr[i]) {
				return arr[i];
			}
		}
		return -1;
	}


	static boolean []visited ;

	static void DFS(ArrayList<ArrayList<Integer>> adj,int s, ArrayList<Integer> res){
		// Mark the current vertex as true
		visited[s] = true;

		// add the current vertex into the result
		res.add(s);

		// call the DFS for the adjacent of the current node
		for(int i:adj.get(s)){

			// call DFS only if the adjacent node is not visited
			if(visited[i]==false){
				DFS(adj,i,res);
			}
		}
	}



	public ArrayList<Integer> dfsOfGraph(int v, ArrayList<ArrayList<Integer>> adj) {
		// create a boolean array
		visited =  new boolean[v];

		// create a resultant arraylist
		ArrayList<Integer> res = new ArrayList<>();

		// call the DFS for the source node
		DFS(adj,0,res);

		// return the result
		return res;
	}

}
