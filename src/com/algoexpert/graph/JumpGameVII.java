package com.algoexpert.graph;

import java.util.LinkedList;
import java.util.Queue;

public class JumpGameVII {


	public static boolean jump1(int[] nums) {

		int maxReach = 0;
		for(int i= 0; i< nums.length; i++) {
			if(maxReach < i) {
				return false;
			}

			maxReach = Math.max(maxReach, nums[i] + i);
		}
		return true;

	}

	public static int jump2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int currFarthest = 0;
		int jumps = 0;
		int currEnd = 0;

		for(int i= 0; i < nums.length; i++){
			if(currEnd < i) {
				currEnd = currFarthest;
				jumps++;
			}
			currFarthest = Math.max(currFarthest, i+nums[i]);
		}
		return jumps;

	}


	// This is the base solution
	public static boolean canReach2(String s, int minJump, int maxJump) {
		if(s.charAt(s.length() - 1) != '0') {
			return false;
		}

		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[s.length()];
		queue.add(0);

		while(!queue.isEmpty()){
			int idx = queue.remove();
			if(idx == s.length() - 1) {
				return true;
			}
			for(int i = idx + minJump; i <= idx + maxJump && i < s.length(); i++){
				if(!visited[i] && s.charAt(i) == '0'){
					visited[i] = true;
					queue.add(i);
				}
			}
		}

		return false;
	}


	public static boolean canReach(String s, int minJump, int maxJump) {
		if(s.charAt(s.length() - 1) != '0') {
			return false;
		}

		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);

		// This variable tells us till which index we have processed
		int maxReach = 0;

		while(!queue.isEmpty()){
			int idx = queue.remove();
			// If we reached the last index
			if(idx == s.length() - 1) {
				return true;
			}

			// start the loop from max of [current maximum (idx + minJump), maximum processed index (maxReach)]
			for(int j = Math.max(idx + minJump, maxReach); j <= Math.min(idx + maxJump, s.length() - 1); j++){
				if(s.charAt(j) == '0') {
					queue.add(j);
				}
			}

			// since we have processed till idx + maxJump so update maxReach to next index
			maxReach = Math.min(idx + maxJump + 1, s.length() - 1);
		}

		return false;
	}

	public static void main(String[] args) {
		String s = "011010";
		canReach2(s, 2, 3);

		int[] nums = new int[] {2,3,1,1,4};
		System.out.println(jump2(nums));
	}

}
