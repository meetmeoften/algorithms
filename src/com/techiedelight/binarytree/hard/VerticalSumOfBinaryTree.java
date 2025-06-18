package com.techiedelight.binarytree.hard;

import com.algoexpert.binarytree.view.Node;

import java.util.Map;
import java.util.TreeMap;

public class VerticalSumOfBinaryTree {

    public void printVerticalSum(Node root) {
        Map<Integer, Integer> map = new TreeMap<>();
        printVerticalSum(root, 0, map);
        return;
    }

    public void printVerticalSum(Node node, int dist, Map<Integer, Integer> map) {
        if (node == null) {
            return;
        }
        map.put(dist, map.getOrDefault(dist, 0) + node.data);
        printVerticalSum(node.left, dist - 1, map);
        printVerticalSum(node.right, dist + 1, map);
    }
}
