package com.techiedelight.binarytree.hard;

import com.algoexpert.binarytree.view.Node;

import java.util.HashMap;
import java.util.Map;

public class CountPathsWithGivenSum {


    public int countPaths(Node root, int targetSum) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1); // Important: base case (path equals target from root)

        return dfs(root, 0, targetSum, prefixSumMap);
    }

    private int dfs(Node node, int currSum, int targetSum, Map<Integer, Integer> prefixSumMap) {
        if (node == null) return 0;

        currSum += node.data;

        int paths = prefixSumMap.getOrDefault(currSum - targetSum, 0);
        // backtrack
        prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);
        paths += dfs(node.left, currSum, targetSum, prefixSumMap);
        paths += dfs(node.right, currSum, targetSum, prefixSumMap);
        // backtrack end
        prefixSumMap.put(currSum, prefixSumMap.get(currSum) - 1);
        return paths;
    }

    // bruteforce

    public int countPaths2(Node root, int sum) {
        if (root == null) return 0;
        int fromRoot = count(root, sum);
        int left = countPaths2(root.left, sum);
        int right = countPaths2(root.right, sum);
        return fromRoot + left + right;
    }

    public int count(Node node, int target) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        if (node.data == target) {
            count++;
        }
        count += count(node.left, target - node.data);
        count += count(node.right, target - node.data);
        return count;
    }

    public static void main(String[] args) {
        // Build the tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        // Run the algorithm
        CountPathsWithGivenSum visualizer = new CountPathsWithGivenSum();
        int result = visualizer.countPaths(root, 3);
        System.out.println("Total paths with sum 3: " + result);
    }
}
