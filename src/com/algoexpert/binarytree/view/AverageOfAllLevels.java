package com.algoexpert.binarytree.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.neetcode.binaryTree.TreeNode;

public class AverageOfAllLevels {

	public List<Double> averageOfLevels(TreeNode root) {
		List<Double> result = new ArrayList<>();

		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);

		while (!q.isEmpty()) {
			double average = 0;
			int levelSize = q.size();

			for (int i = 0; i < levelSize; i++) {
				TreeNode t = q.poll();

				average += t.val;

				if (t.left != null) {
					q.offer(t.left);
				}
				if (t.right != null) {
					q.offer(t.right);
				}
			}

			average /= levelSize;
			result.add(average);
		}

		return result;
	}

}
